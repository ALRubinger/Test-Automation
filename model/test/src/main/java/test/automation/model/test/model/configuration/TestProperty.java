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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Immutable;

/**
 * Stores properties specific to a test profile.
 * @author Walter White
 */
@Immutable
@Entity
public class TestProperty extends Property {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected TestProfile testProfile;

	public TestProperty() {
		super();
	}

	public TestProperty(TestProfile testProfile, final String name, final String value) {
		super(name, value);
		setTestProfile(testProfile);
	}

	public TestProfile getTestProfile() {
		return testProfile;
	}

	public void setTestProfile(TestProfile testProfile) {
		this.testProfile = testProfile;
	}
}
