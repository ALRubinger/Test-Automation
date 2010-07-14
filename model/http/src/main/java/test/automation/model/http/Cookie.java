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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class Cookie extends AbstractEntity {

	@Column(updatable = false)
	protected String domain;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	protected Date expires;
	@Column(nullable = false, updatable = false)
	protected String name;
	@Column(updatable = false)
	protected String path;
	@Column(nullable = false, updatable = false)
	protected String value;

	public Cookie() {
		super();
	}

	public Cookie(final String domain, final Date expires, final String name, final String path, final String value) {
		this();

		setDomain(domain);
		setExpires(expires);
		setName(name);
		setPath(path);
		setValue(value);
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
