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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class HttpSession extends AbstractEntity {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected User user;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "httpSession")
	protected List<HttpRequest> httpRequests;

	public HttpSession() {
		super();
		setHttpRequests(new ArrayList<HttpRequest>());
	}

	public HttpSession(User user) {
		this();
		setUser(user);
	}

	public List<HttpRequest> getHttpRequests() {
		return httpRequests;
	}

	public void setHttpRequests(List<HttpRequest> httpRequests) {
		this.httpRequests = httpRequests;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
