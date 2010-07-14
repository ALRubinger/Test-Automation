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

import com.gargoylesoftware.htmlunit.HttpMethod;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Walter White
 */
@Entity
public class HttpRequest extends AbstractHttpCommunication {

	@Column(nullable = false, updatable = false)
	protected String url;
	@ManyToOne
	@JoinColumn
	protected HttpSession httpSession;
	@OneToOne
	@JoinColumn
	protected HttpResponse httpResponse;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	protected Date date;
	@Enumerated
	@Column(nullable = false, updatable = false)
	protected HttpMethod httpMethod;

	public HttpRequest(final Set<HttpHeader> httpHeaders, final String url, HttpSession httpSession, final HttpMethod httpMethod) {
		super(httpHeaders);

		setUrl(url);
		setHttpSession(httpSession);
		setHttpMethod(httpMethod);

		setDate(new Date());
	}

	public HttpRequest() {
		super();
		setDate(new Date());
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
