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

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.authentication.Authenticator;
import test.automation.execution.event.TestExecutionEvent;
import test.automation.listener.htmlunit.CDIWebWindowListener;
import test.automation.test.annotation.scope.ClientScoped;
import test.automation.model.history.model.Execution;
import test.automation.model.history.model.NavigationEvent;
import test.automation.model.http.HttpSession;
import test.automation.model.test.model.TestExecutionException;
import test.automation.model.test.model.configuration.Client;
import test.automation.model.test.model.test.TestCase;
import test.automation.model.test.model.test.TestDefinition;
import test.automation.operator.Operator;
import test.automation.test.AbstractTest;
import test.automation.test.annotation.TestURL;
import test.automation.test.annotation.event.After;
import test.automation.test.annotation.event.Before;
import test.automation.test.annotation.scope.RequestScoped;
import test.automation.test.annotation.scope.TestDefinitionScoped;

/**
 * @author Walter White
 */
@ApplicationScoped
public class ClientExecutionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientExecutionService.class);
	
	@ClientScoped
	private WebClient webClient;
	@ClientScoped
	private HttpSession httpSession;
	@RequestScoped
	private HtmlPage htmlPage;
	@Inject
	private Session session;
	@Inject
	private Authenticator authenticator;
	@Inject
	private Operator operator;
	@Inject
	private Event<TestExecutionEvent> testExecutionEvent;
	@ClientScoped
	private Client client;
	@TestDefinitionScoped
	private TestDefinition currentTestDefinition;
	@ClientScoped
	private Execution execution;
	@Inject
	private NavigationEvent currentHtmlCommand;
	@Inject
	private CDIWebWindowListener cdiWebWindowListener;

	/**
	 * Executes the specified test plan in a separate thread.
	 * Passes the web client, http session, and other contextual variables in the event context attached to this thread.
	 * @TODO, this can be extended very easily to support scheduled test execution.
	 * Right now, there isn't much advantage in doing it that way.
	 */
	//@Asynchronous
	public void run(Client client) throws Exception {
		this.client = client;

		setup();
		executeTests();
	}

	private void setup() throws Exception {
		buildWebClient();
		createHttpSession();

		registerListener();
	}

	private void buildWebClient() {
		webClient = new WebClient();
	}

	//@Transactional
	private void createHttpSession() {
		httpSession = new HttpSession(client.getUser());
		session.save(httpSession);
	}

	/**
	 * Registers all the necessary web window listeners which are responsible for recording history (commands executed, buttons clicked, links followed, etc.).
	 * Listeners are unique to a session.
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void registerListener() throws InstantiationException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LOGGER.debug("registering listeners");

		webClient.addWebWindowListener(cdiWebWindowListener);

		LOGGER.debug("registered listeners");
	}

	/**
	 * Executes all test definitions.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void executeTests() throws Exception {
		authenticate();
		runTests();
	}

	/**
	 * Authenticates with the web application.
	 */
	private void authenticate() throws Exception {
		authenticator.login();
	}

	/**
	 * Runs through all tests.
	 * @throws Exception thrown if there is a problem executing the tests.
	 */
	private void runTests() throws Exception {
		for (final TestDefinition testDefinition : client.getTestDefinitions()) {
			LOGGER.debug("running test:" + testDefinition.getTestMethod());
			this.currentTestDefinition = testDefinition;
			runTest();
		}
	}

	/**
	 * Runs the test.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void runTest() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		try {
			fetch();
			run();
		} catch (Throwable e) {
			LOGGER.error("error occured during test execution", e);

			TestExecutionException testExecutionException = new TestExecutionException(e, currentTestDefinition, currentHtmlCommand);
			session.save(testExecutionException);
		}
	}

	private void fetch() throws Exception {
		final TestURL testURL = currentTestDefinition.getTestMethod().getTestMethod().getAnnotation(TestURL.class);
		operator.get(testURL.value());
	}

	/**
	 * Runs the given test including handling the assertions after the test is run and saving the test results.
	 * This method is recursive, if there are more subsequent tests, it executes those.
	 * @param runnableTest
	 * @param sessionContext
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IOException
	 */
	private void run() throws Exception {
		invoke();
		//AssertionUtil.runAssertions(testContext);
		runNext();

	}

	/**
	 * Runs the next test.
	 * @throws Exception
	 */
	private void runNext() throws Exception {
		// if this test is linked to sub-tests, run those ...
		if (currentTestDefinition.getNext() != null) {
			LOGGER.trace("running next test");
			next();
			run();
		}
	}

	/**
	 * Moves to the next test definition.
	 */
	private void next() {
		currentTestDefinition = currentTestDefinition.getNext();
	}

	/**
	 * Invokes the given test wrapping it in a test context.
	 * @param testDefinition
	 * @param sessionContext
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * @throws NoSuchMethodException
	 */
	protected void invoke() throws Exception {
		final TestExecutionEvent currentTestExecutionEvent = new TestExecutionEvent(currentTestDefinition);
		testExecutionEvent.select(new AnnotationLiteral<Before>() {
		}).fire(currentTestExecutionEvent);

		final Method testMethod = currentTestDefinition.getTestMethod().getTestMethod();
		final AbstractTest test = (AbstractTest) currentTestDefinition.getTestMethod().getTestClass().getConstructor(HttpSession.class, Client.class, WebClient.class).newInstance(httpSession, client, webClient);
		testMethod.invoke(test);

		testExecutionEvent.select(new AnnotationLiteral<After>() {
		}).fire(currentTestExecutionEvent);

		invokeTestCases();
	}

	protected void invokeTestCases() throws Exception {
		for (TestCase testCase : currentTestDefinition.getTestCases()) {
			invokeTestCase(testCase);
		}
	}

	protected void invokeTestCase(TestCase testCase) {
		//events.raiseEvent(PRE_TEST_CASE_EXECUTION, currentTestDefinition);

		//testCase.getTestMethod().getTestMethod().invoke(testCase);

		//events.raiseEvent(POST_TEST_CASE_EXECUTION, currentTestDefinition);
	}
}
