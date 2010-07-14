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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class TestMethod extends AbstractEntity {

	@Lob
	@Column(nullable = false, updatable = false)
	protected Class testClass;
	@Column(nullable = false, updatable = false)
	protected String testMethodName;
	@ManyToMany
	@JoinTable
	protected Class[] testMethodParameterTypes;
	@Transient
	protected transient Method testMethod;

	public TestMethod() {
		super();
	}

	public TestMethod(final Method method) {
		this();

		setTestClass(method.getDeclaringClass());
		setTestMethodName(method.getName());
		setTestMethodParameterTypes(method.getParameterTypes());
		setTestMethod(method);
	}

	public Class getTestClass() {
		return testClass;
	}

	public void setTestClass(final Class testClass) {
		this.testClass = testClass;
	}

	public Method getTestMethod() {
		if (testMethod == null) {
			try {
				testMethod = testClass.getDeclaredMethod(testMethodName, testMethodParameterTypes);
			} catch (Exception e) {
			}
		}

		return testMethod;
	}

	public void setTestMethod(final Method testMethod) {
		this.testMethod = testMethod;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(final String testMethodName) {
		this.testMethodName = testMethodName;
	}

	public Class[] getTestMethodParameterTypes() {
		return testMethodParameterTypes;
	}

	public void setTestMethodParameterTypes(final Class[] testMethodParameterTypes) {
		this.testMethodParameterTypes = testMethodParameterTypes;
	}
}
