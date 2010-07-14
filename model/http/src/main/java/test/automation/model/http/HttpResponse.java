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

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Immutable;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class HttpResponse extends AbstractHttpCommunication {

	@OneToOne(mappedBy = "httpResponse", cascade = CascadeType.MERGE)
	protected HttpRequest httpRequest;
	@Column(nullable = false, updatable = false)
	protected long delay;
	@Column(nullable = false, updatable = false)
	protected int statusCode;
	@Lob
	@Column(nullable = false, updatable = false)
	protected byte[] response;

	public HttpResponse(final Set<HttpHeader> httpHeaders, HttpRequest httpRequest, final long delay, final int statusCode, final byte[] response) {
		super(httpHeaders);

		setHttpRequest(httpRequest);
		httpRequest.setHttpResponse(this);
		setDelay(delay);
		setStatusCode(statusCode);
		setResponse(response);
	}

	public HttpResponse(HttpRequest httpRequest, final long delay, final int statusCode, final byte[] response) {
		this();

		setHttpRequest(httpRequest);
		setDelay(delay);
		setStatusCode(statusCode);
		setResponse(response);
	}

	public HttpResponse() {
		super();
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public HttpRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public byte[] getResponse() {
		return response;
	}

	public void setResponse(byte[] response) {
		this.response = response;
	}
}
