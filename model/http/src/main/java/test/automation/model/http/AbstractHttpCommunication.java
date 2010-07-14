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


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;

import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
@Embeddable
@MappedSuperclass
public abstract class AbstractHttpCommunication extends AbstractEntity {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable
	protected Set<HttpHeader> httpHeaders;

	protected AbstractHttpCommunication() {
		super();

		setHttpHeaders(new HashSet<HttpHeader>());
	}

	public AbstractHttpCommunication(final Set<HttpHeader> httpHeaders) {
		this();
		
		this.httpHeaders.addAll(httpHeaders);
	}

	public Set<HttpHeader> getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(Set<HttpHeader> httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
}
