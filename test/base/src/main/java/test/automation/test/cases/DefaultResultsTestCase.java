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
package test.automation.test.cases;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import test.automation.iterator.iterator.PageIterator;
import test.automation.iterator.iterator.TableColumnIterator;
import test.automation.iterator.iterator.TableRowIterator;
import test.automation.model.test.enumeration.TestType;
import test.automation.model.test.model.test.TestDefinition;
import test.automation.test.annotation.TestCase;
import test.automation.test.annotation.assertion.search.HasNoResults;
import test.automation.test.annotation.detail.FullTest;
import test.automation.test.annotation.scope.TestCaseScoped;
import test.automation.test.event.ResultSetEvent;

/**
 * Tests the result set.
 * @author Walter White
 */
@TestCaseScoped
public class DefaultResultsTestCase {

	@Inject
	protected PageIterator pageIterator;
	@Inject
	protected TableColumnIterator tableColumnIterator;
	@Inject
	protected TableRowIterator tableRowIterator;
	@Inject
	protected TestDefinition testDefinition;
	//@Inject
	//protected Results results;

	@TestCase(type = TestType.Positive)
	public void testResultSet(@Observes ResultSetEvent resultSetEvent) throws Exception {
		testExpectations(resultSetEvent);
		testHasResults();
	}

	private final void testExpectations(ResultSetEvent resultSetEvent) {
		final boolean notExpectingResults = testDefinition.getTestMethod().getTestMethod().isAnnotationPresent(HasNoResults.class);
		final boolean hasResults = false;//results.hasResults();

		if (notExpectingResults == hasResults) {
		}
	}

	public void testEmptyResultSet(@Observes ResultSetEvent resultSetEvent) throws Exception {
		testExpectations(resultSetEvent);
		testHasNoResults();
	}

	/**
	 * Test the result set, go through each page, test sorting ...
	 */
	private final void testHasResults() {
	}

	/**
	 * This test is only executed if we're running a full-test, not a smoke test.
	 * @param resultSet the result set to test / iterate through.
	 */
	@TestCase(type = TestType.Positive)
	private final void testLong(@Observes @FullTest ResultSetEvent resultSetEvent) {
		//tableColumnIterator.
	}


	/**
	 * Simply test the error message.
	 */
	private final void testHasNoResults() {
	}
}
