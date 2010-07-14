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
package test.automation.model.test.model.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
@Entity
public class TestCaseAssertion extends AbstractEntity {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected TestCase testCase;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected Assertion assertion;
	@Column(nullable = false)
	protected boolean outcome;

	public TestCaseAssertion() {
		super();
	}

	public TestCaseAssertion(TestCase testCase, Assertion assertion, final boolean outcome) {
		this();

		setTestCase(testCase);
		setAssertion(assertion);
		setOutcome(outcome);
	}

	public Assertion getAssertion() {
		return assertion;
	}

	public void setAssertion(Assertion assertion) {
		this.assertion = assertion;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public boolean isOutcome() {
		return outcome;
	}

	public void setOutcome(boolean outcome) {
		this.outcome = outcome;
	}
}
