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

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;
import test.automation.model.test.enumeration.TestType;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class TestCase extends AbstractEntity {

	@ManyToOne
	protected TestMethod testMethod;
	@Enumerated
	@Column(nullable = false, updatable = false)
	protected TestType testType;
	@OneToMany(mappedBy = "testCase")
	protected List<TestCaseAssertion> testCaseAssertions;

	public TestMethod getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(TestMethod testMethod) {
		this.testMethod = testMethod;
	}

	public List<TestCaseAssertion> getTestCaseAssertions() {
		return testCaseAssertions;
	}

	public void setTestCaseAssertions(List<TestCaseAssertion> testCaseAssertions) {
		this.testCaseAssertions = testCaseAssertions;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}
}
