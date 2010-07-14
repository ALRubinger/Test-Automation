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

import java.util.Date;
import java.util.HashSet;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import test.automation.model.AbstractEntity;
import test.automation.model.test.enumeration.TestType;
import test.automation.model.test.model.test.TestDefinition;

/**
 * @author Walter White
 */
@Entity
public class TestConfiguration extends AbstractEntity {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	protected Date creationDate;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "testConfiguration", cascade = CascadeType.ALL)
	protected Set<TestDefinition> testDefinitions;
	@Enumerated
	@ElementCollection(targetClass = TestType.class)
	@JoinTable
	@Column
	protected Set<TestType> testTypesToSkip;
	@ManyToOne
	@JoinColumn
	protected TestProfile testProfile;
	@ManyToMany
	@JoinTable
	protected Set<Property> properties;

	public TestConfiguration() {
		super();
		setTestDefinitions(new HashSet<TestDefinition>());
		setTestTypesToSkip(new HashSet<TestType>());
		setProperties(new HashSet<Property>());

		setCreationDate(new Date());
	}

	public TestConfiguration(final Set<Property> properties) {
		this();
		this.properties.addAll(properties);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<TestDefinition> getTestDefinitions() {
		return testDefinitions;
	}

	public void setTestDefinitions(final Set<TestDefinition> testDefintions) {
		this.testDefinitions = testDefintions;
	}

	public Set<TestType> getTestTypesToSkip() {
		return testTypesToSkip;
	}

	public void setTestTypesToSkip(final Set<TestType> testTypesToSkip) {
		this.testTypesToSkip = testTypesToSkip;
	}

	public TestProfile getTestProfile() {
		return testProfile;
	}

	public void setTestProfile(TestProfile testProfile) {
		this.testProfile = testProfile;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(final Set<Property> properties) {
		this.properties = properties;
	}
}
