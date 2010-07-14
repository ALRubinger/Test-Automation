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
package test.automation.model.test.model.configuration;

import test.automation.model.test.model.test.TestDefinition;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import test.automation.authentication.Authenticator;
import test.automation.model.AbstractEntity;
import test.automation.model.http.User;

/**
 * This client pattern allows each client to implement their own unique functionality in Java code.
 * Any custom logic may be applied here.
 * @author Walter White
 */
@Immutable
@Entity
public class Client extends AbstractEntity {

	@Lob
	@Column(nullable = false, updatable = false)
	protected Class<? extends Authenticator> authenticator;
	/**
	 * Test definitions this client will run.
	 */
	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany
	@JoinTable
	protected List<TestDefinition> testDefinitions;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected User user;
	/**
	 * How long the client will wait between executing commands such as click or get.
	 */
	@Column(nullable = false, updatable = false)
	protected int delay;

	public Client() {
		super();
		setTestDefinitions(new ArrayList<TestDefinition>());
	}

	public Client(final Set<TestDefinition> testDefinitions, final int delay) {
		this();

		this.testDefinitions.addAll(testDefinitions);
		Collections.shuffle(this.testDefinitions);

		setDelay(delay);
	}

	public Class<? extends Authenticator> getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Class<? extends Authenticator> authenticator) {
		this.authenticator = authenticator;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public List<TestDefinition> getTestDefinitions() {
		return testDefinitions;
	}

	public void setTestDefinitions(final List<TestDefinition> testDefinitions) {
		this.testDefinitions = testDefinitions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
