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
package test.automation.resourceServlet;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.automation.model.http.HttpHeader;
import test.automation.model.http.HttpResponse;

/**
 * Serves a HttpResponse to a browser so that we may view what was captured when the tests were run.
 * @author Walter White
 */
public class ResourceServlet extends HttpServlet {

	/**
	 * Attempt to send the client the requested file.
	 * /resource/1
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
		HttpResponse httpResponse = findHttpResponse();
		final OutputStream outputStream = httpServletResponse.getOutputStream();

		writeHeaders(httpResponse,httpServletResponse, outputStream);
		writeContent(httpResponse, outputStream);
		//httpServletResponse.getOutputStream()
	}

	/**
	 * Finds the Http Response as requested by the user.
	 * @return the matching http response or throws an exception if not found.
	 */
	protected HttpResponse findHttpResponse() {
		if (true) {
			throw (new RuntimeException("not found."));
		}

		return (null);
	}

	/**
	 * Writes the Http Headers as were originally captured.
	 * @param httpResponse
	 * @param httpServletResponse
	 * @param outputStream
	 */
	protected void writeHeaders(HttpResponse httpResponse, final HttpServletResponse httpServletResponse, final OutputStream outputStream) {
		for (HttpHeader httpHeader : httpResponse.getHttpHeaders()) {
			httpServletResponse.addHeader(httpHeader.getName(), httpHeader.getValue());
		}

		httpServletResponse.setStatus(httpResponse.getStatusCode());
	}

	/**
	 * Writes the actual response as was captured.
	 * @param httpResponse
	 * @param outputStream
	 * @throws IOException
	 */
	protected void writeContent(HttpResponse httpResponse, final OutputStream outputStream) throws IOException {
		outputStream.write(httpResponse.getResponse());
	}
}
