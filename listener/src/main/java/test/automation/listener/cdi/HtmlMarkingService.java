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

import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import javax.inject.Inject;


import test.automation.model.history.model.ClickEvent;
import test.automation.model.history.model.NavigationEvent;
import test.automation.model.html.model.Attribute;
import test.automation.model.html.model.Element;
import test.automation.test.annotation.scope.ClientScoped;

/**
 * Alters the HTML to indicate which element was clicked or typed into.
 * @author Walter White
 */
@ClientScoped
public class HtmlMarkingService {

	private static final String HTML_CSS_CLASS_MODIFIED = "htmlUnit";
	
	@Inject
	protected NavigationEvent htmlCommand;

	/**
	 * Logs the HTML and creates corresponding entities to represent it.
	 * @param webWindowEvent
	 */
	public void mark(final WebWindowEvent webWindowEvent) {
		final HtmlPage htmlPage = (HtmlPage) webWindowEvent.getNewPage();

		if (htmlCommand instanceof ClickEvent) {
			ClickEvent clickedElement = (ClickEvent) htmlCommand;
			Element htmlElement = clickedElement.getClickedHtmlElement();

			com.gargoylesoftware.htmlunit.html.HtmlElement actualHtmlElement = null;
			for (Attribute htmlAttribute : htmlElement.getHtmlAttributes()) {
				if ("id".equalsIgnoreCase(htmlAttribute.getName())) {
					actualHtmlElement = htmlPage.getElementById(htmlAttribute.getValue());
					break;
				}
			}

			appendCssClass(actualHtmlElement);
		}
	}

	/**
	 * Modifies the CSS Class in the Html Element so that when it is stored, we know this element was clicked.
	 * When viewing the Html later, we will see this element will stand out to indicate it was clicked.
	 * @param actualHtmlElement
	 */
	private void appendCssClass(final com.gargoylesoftware.htmlunit.html.HtmlElement actualHtmlElement) {
		if (actualHtmlElement != null) {
			final String cssClass = actualHtmlElement.getAttribute("class");

			final StringBuilder buffer = new StringBuilder();
			if (cssClass != null && cssClass.length() > 0) {
				buffer.append(cssClass);
				buffer.append(" ");
			}

			buffer.append(HTML_CSS_CLASS_MODIFIED);
			actualHtmlElement.setAttribute("class", buffer.toString());
		}
	}
}
