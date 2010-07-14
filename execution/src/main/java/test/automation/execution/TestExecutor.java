/*
 * WalterJWhite.com
 * Copyright 2010, Walter White, and individual contributors
 * by the @authors tag.
 *
 * Licensed under the GNU Lesser General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/lgpl.html
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.automation.execution;

import org.hibernate.Session;

import test.automation.model.test.model.configuration.Client;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.lang.StringUtils;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.model.test.enumeration.TestType;
import test.automation.model.test.model.configuration.Property;

import test.automation.model.test.model.configuration.TestConfiguration;
import test.automation.model.test.model.test.TestDefinition;
import test.automation.test.annotation.MethodSignature;
import test.automation.test.annotation.Test;
import test.automation.test.annotation.TestURL;

/**
 * When Seam has started up, begin testing, once testing is complete, we may shutdown.
 * Ideas for the next version:
 * 1. enable client-server model so that multiple machines may be used to load test the server.
 *     use JMS queues to communicate
 * 2. enable bandwidth throttling to see how that affects the server.
 * @author Walter White
 */
@ApplicationScoped
public class TestExecutor {

	private Logger LOGGER = LoggerFactory.getLogger(TestExecutor.class);
	protected String entityManagerName;
	@Inject
	private Session session;
	@ApplicationScoped
	private TestConfiguration testConfiguration;
	@Inject
	private ClientExecutionService clientExecutionService;
	@Inject
	private Set<Client> clients;
	@ApplicationScoped
	private EntityManagerFactory entityManagerFactory;

	public void onStartup(@Observes final ContainerInitialized containerInitialized) {
		initializeHibernate();
		buildTestConfiguration();
		buildTests();

		session.save(testConfiguration);
	}

	private void initializeHibernate() {
		entityManagerFactory = Persistence.createEntityManagerFactory(entityManagerName);

	}

	@Produces
	@TransactionScoped
	public EntityManager getEntityManger()
	{
		return(entityManagerFactory.createEntityManager());
	}

	/*
	public void onShutdown(@Observes final Container)
	{

	}
	 */
	private void buildTestConfiguration() {
		testConfiguration = new TestConfiguration(buildProperties(System.getProperties()));
	}

	private Set<Property> buildProperties(final Properties properties) {
		final Set<Property> configurationProperties = new HashSet<Property>();
		for (final String key : properties.stringPropertyNames()) {
			configurationProperties.add(new Property(key, properties.getProperty(key)));
		}

		return (configurationProperties);
	}

	/**
	 * Builds the test definitions.
	 */
	private void buildTests() {
		skipTestTypes(testConfiguration);

		try {
			testConfiguration.getTestDefinitions().addAll(buildTestDefinitions(testConfiguration));
		} catch (Exception e) {
			throw (new RuntimeException("Unable to build test definitions", e));
		}
	}

	/**
	 * Configures which tests are to be skipped.
	 */
	private void skipTestTypes(TestConfiguration testConfiguration) {
		final String testTypes = System.getProperty("integration.test.types.skip");

		LOGGER.debug("testTypes to skip:" + testTypes);
		if (StringUtils.isNotBlank(testTypes)) {
			for (final String testType : testTypes.split(",")) {
				testConfiguration.getTestTypesToSkip().add(TestType.valueOf(testType));
			}
		}
	}

	/**
	 * Called by the TestExecutor to build the test definitions.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	private Set<TestDefinition> buildTestDefinitions(TestConfiguration testConfiguration) throws ClassNotFoundException, NoSuchMethodException {
		final Set<Class> testableClasses = getTestableClasses();

		final Set<TestDefinition> testDefinitionsLevelOne = getTestDefinitions(testConfiguration, testableClasses, Test.class);
		final Set<TestDefinition> testDefinitionsLevelTwo = getTestDefinitions(testConfiguration, testableClasses, MethodSignature.class);

		organizeTests(testConfiguration, testDefinitionsLevelOne, testDefinitionsLevelTwo);

		return (testDefinitionsLevelOne);
	}

	private Set<Class> getTestableClasses() throws ClassNotFoundException {
		final Set<File> files = getFiles(new ClassFilter(), getTestClassPath());
		LOGGER.trace("testable classes:" + files);
		final Set<Class> testableClasses = new HashSet<Class>();

		for (final File file : files) {
			final String className = getClassName(file);
			LOGGER.trace("className:" + className);
			if (className.startsWith("${REMOVED}")) {
				final Class testClass = Class.forName(className);
				if (testClass.isAnnotationPresent(TestURL.class)) {
					testableClasses.add(testClass);
				}
			}
		}

		LOGGER.trace("testable classes:" + testableClasses);
		return (testableClasses);
	}

	private String getClassName(final File file) {
		final String fileNameLessSlashes = file.getAbsolutePath().replaceAll("\\" + File.separator, ".");
		final int start = fileNameLessSlashes.indexOf("com");
		final int end = fileNameLessSlashes.lastIndexOf(".class");

		return (fileNameLessSlashes.substring(start, end));
	}

	private Set<File> getFiles(final FilenameFilter classFilter, final File root) {
		final Set<File> files = new HashSet<File>();
		for (final File file : root.listFiles()) {
			// recursively get all files ...
			if (file.isDirectory()) {
				files.addAll(getFiles(classFilter, file));
			} else {
				if (file.getName().endsWith(".class")) {
					files.add(file);
				}
			}
		}

		return (files);
	}

	private File getTestClassPath() {
		final String classpath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "test-classes" + File.separator + "${REMOVED}";
		return (new File(classpath));
	}

	/**
	 * Scan all tests and determine which classes and methods we can execute directly without executing a test-case prior.
	 * @return
	 */
	private Set<TestDefinition> getTestDefinitions(TestConfiguration testConfiguration, final Set<Class> testableClasses, final Class classType) {
		final Set<TestDefinition> testDefinitions = new HashSet<TestDefinition>();
		for (final Class testableClass : testableClasses) {
			testDefinitions.addAll(createTestDefinitions(testConfiguration, testableClass, classType));
		}

		return (testDefinitions);
	}

	/**
	 * Creates the test definitions.
	 * @param testClass
	 * @param annotationClass
	 * @return
	 */
	private Set<TestDefinition> createTestDefinitions(TestConfiguration testConfiguration, final Class testClass, final Class annotationClass) {
		final Set<TestDefinition> tests = new HashSet<TestDefinition>();

		final Set<Method> testableMethods = getTestableMethods(testClass, annotationClass);
		for (final Method method : testableMethods) {
			tests.add(new TestDefinition(testConfiguration, method));
			LOGGER.trace("test definition:" + testClass + ":" + method);
		}

		return (tests);
	}

	/**
	 * Get all the methods marked with the given annotation in the given class.
	 * @param testClass the test class.
	 * @param annotationClass the annotation class we're interested.
	 * @return all the matching methods.
	 */
	private Set<Method> getTestableMethods(final Class testClass, final Class annotationClass) {
		final Set<Method> methods = new HashSet<Method>();

		for (final Method testMethod : testClass.getMethods()) {
			if (testMethod.isAnnotationPresent(annotationClass)) {
				methods.add(testMethod);
			}
		}

		return (methods);
	}

	/**
	 * After we read in all of the tests and have located the primary and secondary tests, string them together.
	 */
	private void organizeTests(TestConfiguration testConfiguration, final Set<TestDefinition> testDefinitionsLevelOne, final Set<TestDefinition> testDefinitionsLevelTwo) throws NoSuchMethodException {
		LOGGER.trace("organizing tests:" + testDefinitionsLevelTwo);
		for (final TestDefinition testDefinition : testDefinitionsLevelTwo) {
			final MethodSignature methodSignature = (MethodSignature) testDefinition.getTestMethod().getTestMethod().getAnnotation(MethodSignature.class);
			final Method testMethod = methodSignature.methodClass().getMethod(methodSignature.methodName());
			final TestDefinition parentTest = getParent(testConfiguration, testDefinitionsLevelOne, testDefinitionsLevelTwo, methodSignature.methodClass(), testMethod);

			// link these tests together
			parentTest.setNext(testDefinition);
			testDefinition.setPrevious(parentTest);
			LOGGER.trace("set parent:" + parentTest + "->" + testDefinition);
		}
	}

	/**
	 * Check if the test definition exists in the first tier, if not, proceed to the second level.
	 * Finally, if it doesn't, create a new test definition.
	 * @param testDefinitionsLevelOne
	 * @param testDefinitionsLevelTwo
	 * @param testClass
	 * @param testMethod
	 * @return
	 */
	private TestDefinition getParent(TestConfiguration testConfiguration, final Set<TestDefinition> testDefinitionsLevelOne, final Set<TestDefinition> testDefinitionsLevelTwo, final Class testClass, final Method testMethod) {
		TestDefinition parent = getParent(testDefinitionsLevelOne, testClass, testMethod);
		if (parent == null) {
			parent = getParent(testDefinitionsLevelTwo, testClass, testMethod);
		}
		if (parent == null) {
			parent = new TestDefinition(testConfiguration, testMethod);
			testDefinitionsLevelOne.add(parent);
		}

		return (parent);
	}

	/**
	 * Finds the parent test or creates a new one if the next test is not null.
	 * @param testClass the parent test class.
	 * @param methodSignature the parent test method.
	 * @return the parent test.
	 */
	private TestDefinition getParent(final Set<TestDefinition> testDefinitions, final Class testClass, final Method testMethod) {
		for (final TestDefinition testDefinition : testDefinitions) {
			if (testDefinition.getTestMethod().getTestMethod().getDeclaringClass().equals(testClass) && testDefinition.getTestMethod().equals(testMethod) && testDefinition.getNext() == null) {
				return (testDefinition);
			}
		}

		return (null);
	}

	private void runTests() throws Exception {
		for (final TestType testType : TestType.values()) {
			if (testConfiguration.getTestTypesToSkip().contains(testType)) {
				LOGGER.debug("skipping test type:" + testType);
				continue;
			}

			LOGGER.debug("testing application:" + testConfiguration.getTestDefinitions());
			for (Client client : clients) {
				clientExecutionService.run(client);
			}
		}
	}
}
