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

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import test.automation.model.test.model.configuration.TestConfiguration;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;
import test.automation.model.history.model.Execution;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class TestDefinition extends AbstractEntity {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected TestConfiguration testConfiguration;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected TestMethod testMethod;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(updatable = false)
	protected TestDefinition previous;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(updatable = false)
	protected TestDefinition next;
	@ManyToOne
	@JoinColumn
	protected Execution execution;
	@OneToMany(mappedBy = "testDefinition")
	protected Set<TestCase> testCases;

	public TestDefinition() {
		super();
		setTestCases(new HashSet<TestCase>());
	}

	public TestDefinition(TestConfiguration testConfiguration, final Method method) {
		this();
		setTestConfiguration(testConfiguration);
		setTestMethod(new TestMethod(method));
	}

	public Execution getExecution() {
		return execution;
	}

	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	public TestDefinition getNext() {
		return next;
	}

	public void setNext(TestDefinition next) {
		this.next = next;
	}

	public TestDefinition getPrevious() {
		return previous;
	}

	public void setPrevious(TestDefinition previous) {
		this.previous = previous;
	}

	public TestConfiguration getTestConfiguration() {
		return testConfiguration;
	}

	public void setTestConfiguration(TestConfiguration testConfiguration) {
		this.testConfiguration = testConfiguration;
	}

	public TestMethod getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(TestMethod testMethod) {
		this.testMethod = testMethod;
	}

	public Set<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(Set<TestCase> testCases) {
		this.testCases = testCases;
	}
}
