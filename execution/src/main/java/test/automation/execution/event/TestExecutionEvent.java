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
package test.automation.execution.event;

import test.automation.model.test.model.test.TestDefinition;

/**
 * @author Walter White
 */
public class TestExecutionEvent {

	protected final TestDefinition testDefinition;

	public TestExecutionEvent(TestDefinition testDefinition) {
		this.testDefinition = testDefinition;
	}

	public TestDefinition getTestDefinition() {
		return testDefinition;
	}
}
