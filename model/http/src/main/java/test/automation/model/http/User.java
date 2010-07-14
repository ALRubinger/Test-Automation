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
package test.automation.model.http;

import java.security.Principal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
// these named queries will be replaced with a query service implementation ...
@NamedQueries(
@NamedQuery(name = "User.findActive", query = "FROM User user WHERE user.active = TRUE"))
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User extends AbstractEntity implements Principal {

	@Column(nullable = false, updatable = false)
	protected String name;
	@Column(nullable = false)
	protected boolean active;

	public User() {
		super();
	}

	public User(final String name) {
		this();
		setName(name);
	}

	public String getName() {
		return (name);
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
