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
package test.automation.test.assertion;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

/**
 * @author Walter White
 */
@Name("elementPresentAssertion")
public class ElementPresentAssertion implements Assertion {

	@In
	protected HtmlPage currentPage;
	@In(required = false)
	protected String elementId;
	@In(required = false)
	protected String elementInnerHtml;

	public boolean test() {
		if (elementId != null) {
			final HtmlElement htmlElement = currentPage.getElementById(elementId);
			return (htmlElement != null);
		}

		return (currentPage.getWebResponse().getContentAsString().indexOf(elementInnerHtml) >= 0);
	}
}
