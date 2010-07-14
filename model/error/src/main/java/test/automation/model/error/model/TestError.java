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
package test.automation.model.error.model;

import test.automation.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Immutable;
import test.automation.model.test.model.test.TestDefinition;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class TestError extends AbstractEntity {

	@Lob
	@Column(nullable = false, updatable = false)
	protected Throwable exception;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected TestDefinition testDefinition;

	public TestError() {
		super();
	}

	public TestError(TestDefinition testDefinition, final Throwable e) {
		this();

		setException(e);
		setTestDefinition(testDefinition);
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public TestDefinition getTestDefinition() {
		return testDefinition;
	}

	public void setTestDefinition(TestDefinition testDefinition) {
		this.testDefinition = testDefinition;
	}
}
