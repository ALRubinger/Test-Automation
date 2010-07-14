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
package test.automation.listener.cdi;

import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindowEvent;

import com.gargoylesoftware.htmlunit.util.NameValuePair;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;

import test.automation.model.http.AbstractHttpCommunication;
import test.automation.model.http.HttpHeader;
import test.automation.model.http.HttpRequest;
import test.automation.model.http.HttpResponse;
import test.automation.model.http.HttpSession;
import test.automation.test.annotation.scope.ClientScoped;
import test.automation.test.annotation.scope.RequestScoped;

/**
 * Listener that automatically saves the new page to the database as it is changed.
 * @author Walter White
 */
@ClientScoped
public class HttpLoggingService {
	@Inject
	protected HttpSession httpSession;
	@RequestScoped
	protected HttpRequest httpRequest;
	@RequestScoped
	protected HttpResponse httpResponse;

	public void log(final WebWindowEvent webWindowEvent) {
		final WebResponse webResponse = webWindowEvent.getNewPage().getWebResponse();
		final WebRequestSettings webRequestSettings = webResponse.getRequestSettings();

		logRequest(webResponse, webRequestSettings, httpSession);
		logResponse(webResponse);
	}

	private void logRequest(final WebResponse webResponse, final WebRequestSettings webRequestSettings, HttpSession httpSession) {
		httpRequest = new HttpRequest();
		httpRequest.setHttpSession(httpSession);
		httpRequest.getHttpHeaders().addAll(createHttpHeaders(httpRequest, webRequestSettings.getRequestParameters(), webRequestSettings.getAdditionalHeaders()));
		httpRequest.setHttpMethod(webResponse.getRequestSettings().getHttpMethod());
	}

	private Set<HttpHeader> createHttpHeaders(final AbstractHttpCommunication httpCommunication, final List<NameValuePair> nameValuePairs, final Map<String, String> additionalHeaders) {
		final Set<HttpHeader> httpHeaders = new HashSet<HttpHeader>();
		if (nameValuePairs != null) {
			for (final NameValuePair nameValuePair : nameValuePairs) {
				httpHeaders.add(createHttpHeader(httpCommunication, nameValuePair));
			}
		}

		if (additionalHeaders != null) {
			for (final Entry<String, String> mapEntry : additionalHeaders.entrySet()) {
				httpHeaders.add(createHttpHeader(httpCommunication, mapEntry.getKey(), mapEntry.getValue()));
			}
		}

		return (httpHeaders);
	}

	private HttpHeader createHttpHeader(final AbstractHttpCommunication httpCommunication, final NameValuePair nameValuePair) {
		return (createHttpHeader(httpCommunication, nameValuePair.getName(), nameValuePair.getValue()));
	}

	private HttpHeader createHttpHeader(final AbstractHttpCommunication httpCommunication, final String name, final String value) {
		HttpHeader httpHeader = new HttpHeader();
		httpHeader.setName(name);
		httpHeader.setValue(value);
		httpCommunication.getHttpHeaders().add(httpHeader);

		return (httpHeader);
	}

	private void logResponse(final WebResponse webResponse) {
		httpResponse = new HttpResponse();

		final Set<HttpHeader> httpHeaders = createHttpHeaders(httpResponse, webResponse.getResponseHeaders(), null);
		//final Set<${REMOVED}Cookie> cookies = createCookies(webResponse.getRequestSettings().);
		//HttpRequest httpRequest = testExecution.getLastHtmlCommand().getResultingHttpRequest();

		httpResponse.setDelay(webResponse.getLoadTime());
		httpResponse.setHttpRequest(httpRequest);
		httpResponse.setStatusCode(webResponse.getStatusCode());
		httpResponse.setResponse(webResponse.getContentAsBytes());
	}
	/*
	private static final void createCookies(final AbstractHttpCommunication httpCommunication, final PageContext pageContext) {
	final Iterator<Cookie> cookieIterator = pageContext.getCurrentPage().getWebClient().getCookieManager().getCookies().iterator();
	while (cookieIterator.hasNext()) {
	final Cookie cookie = cookieIterator.next();
	${REMOVED}Cookie cookieEntity = new ${REMOVED}.model.Cookie(cookie.getDomain(), cookie.getExpires(), cookie.getPath(), cookie.getName(), cookie.getValue());
	httpCommunication.getCookies().add(cookieEntity);
	}
	}
	 */
}
