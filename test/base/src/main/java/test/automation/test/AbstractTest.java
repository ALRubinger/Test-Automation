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
package test.automation.test;

import java.io.File;
import java.util.Set;
import javax.inject.Inject;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.model.history.model.Execution;
import test.automation.model.test.model.configuration.Client;
import test.automation.model.test.model.test.TestCase;
import test.automation.model.test.model.test.TestDefinition;
import test.automation.state.handler.StateHandler;

/**
 * @author Walter White
 */
public abstract class AbstractTest<StateEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
	@Inject
	protected Client client;
	@Inject
	protected TestDefinition testDefinition;
	@Inject
	protected Execution execution;
	@Inject
	protected Session session;
	@Inject
	protected Set<TestCase> testCases;
	@Inject
	protected StateHandler stateHandler;
	protected Object state;

	//@TestCaseScoped
	protected File resourcesDirectoryFile;

	protected AbstractTest() {
		super();
		
		resourcesDirectoryFile = new File(getResourceDirectory() + getTestResourceDirectoryName());
	}

	/**
	 * Gets the location where all the resources are stored.
	 * @return the resource directory name as a string.
	 */
	protected final String getResourceDirectory()
	{
		return(System.getProperty("user.dir") + File.separator + "target" + File.separator + "test-classes" + File.separator);
	}

	/**
	 * Gets the test resource directory name computed from the test method name.
	 * @return the test resource directory name, relative to the path to the resource directory.
	 */
	protected final String getTestResourceDirectoryName()
	{
		return((getClass().getName() + testDefinition.getTestMethod().getTestMethod().getName()).replaceAll("\\.", File.separator));
	}

	/**
	 * Restores the test state (reading the object in from YAML, XML, JSON, or the database).
	 * @return the state entity to be used for the test.
	 */
	protected void restore() throws Exception
	{
		state = stateHandler.read();
	}

	/**
	 * Writes the state entity to disk (YAML, XML, JSON) or the database.
	 */
	protected void store() throws Exception
	{
		stateHandler.write(state);
	}

	public void executeTestCases() {
		for (TestCase testCase : testCases) {
			executeTestCase(testCase);
		}
	}

	//@Transactional
	private void executeTestCase(TestCase testCase) {
		//originalState = null;

		//Object testCaseInstance = Component.getInstance(testCase.getTestMethod().getTestMethod().getDeclaringClass());
		//final boolean result = (boolean) testCase.getTestMethod().getTestMethod().invoke(testCaseInstance);

		session.save(testCase);
	}
}
