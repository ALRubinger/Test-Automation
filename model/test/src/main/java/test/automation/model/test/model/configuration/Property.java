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

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;

/**
 * Stores properties (key/value pair).
 * @author Walter White
 */
@Immutable
@Entity
public class Property extends AbstractEntity {

	@Column(nullable = false, updatable = false)
	protected String name;
	@Column(nullable = false, updatable = false)
	protected String value;

	public Property() {
		super();
	}

	public Property(final String name, final String value) {
		this();

		setName(name);
		setValue(value);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
}
