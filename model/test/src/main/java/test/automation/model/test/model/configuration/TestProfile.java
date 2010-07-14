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

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class TestProfile extends AbstractEntity {

	@Column(nullable = false, updatable = false)
	protected String profileId;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "testConfiguration")
	protected Set<Property> testProperties;

	public TestProfile() {
		super();
		setTestProperties(new HashSet<Property>());
	}

	public TestProfile(final String profileId, final Set<Property> testProperties) {
		setProfileId(profileId);
		setTestProperties(testProperties);
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(final String profileId) {
		this.profileId = profileId;
	}

	public Set<Property> getTestProperties() {
		return testProperties;
	}

	public void setTestProperties(final Set<Property> testProperties) {
		this.testProperties = testProperties;
	}
}
