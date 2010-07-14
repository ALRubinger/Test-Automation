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
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.hibernate.Session;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import test.automation.model.html.model.Button;
import test.automation.model.html.model.Form;
import test.automation.model.html.model.Attribute;
import test.automation.model.html.model.Element;
import test.automation.model.html.model.Input;
import test.automation.model.html.model.Link;

import test.automation.model.html.model.Page;
import test.automation.model.http.HttpResponse;
import test.automation.test.annotation.scope.ClientScoped;
import test.automation.test.annotation.scope.RequestScoped;

/**
 * @author Walter White
 */
@ClientScoped
public class HtmlLoggingService {

	@Inject
	protected Session session;
	@RequestScoped
	protected HttpResponse httpResponse;
	
	/**
	 * Logs the HTML and creates corresponding entities to represent it.
	 * @param webWindowEvent
	 */
	public void log(final WebWindowEvent webWindowEvent) {
		final HtmlPage htmlPage = (HtmlPage) webWindowEvent.getNewPage();

		Page page = new Page();

		httpResponse = null;
		page.setHttpResponse(httpResponse);

		page.getHtmlElements().addAll(buildHtmlElements(htmlPage.getDocumentElement(), null));
		session.save(page);
	}

	protected Set<Element> buildHtmlElements(final DomNode domNodeParent, Element parent) {
		final Set<Element> htmlElements;

		if (parent == null) {
			htmlElements = new HashSet<Element>();
		} else {
			htmlElements = null;
		}

		for (final DomNode child : domNodeParent.getChildren()) {

			Page page = null;
			Element htmlElement;
			if ("a".equalsIgnoreCase(child.getNodeName())) {
				htmlElement = new Link(page, parent);
			} else if ("form".equalsIgnoreCase(child.getNodeName())) {
				htmlElement = new Form(page, parent);
			} else if ("input".equalsIgnoreCase(child.getNodeName())) {
				Form form = null;

				if ("submit".equalsIgnoreCase(child.getAttributes().getNamedItem("type").getNodeValue())) {
					htmlElement = new Button(page, parent, form);
				} else {
					htmlElement = new Input(page, parent, form);
				}
			} else {
				htmlElement = new Element(page, parent);
			}

			if (parent == null) {
				htmlElements.add(htmlElement);
			}

			buildAttributes(child, htmlElement);
			if (child.getChildren().iterator().hasNext()) {
				buildHtmlElements(child, htmlElement);
			}
		}

		return (htmlElements);
	}

	private void buildAttributes(final DomNode child, Element htmlElement) {
		final NamedNodeMap attributeMap = child.getAttributes();
		for (int index = 0; index < attributeMap.getLength(); index++) {
			final Node node = attributeMap.item(index);
			new Attribute(htmlElement, node.getNodeName(), node.getNodeValue());
		}
	}
}
