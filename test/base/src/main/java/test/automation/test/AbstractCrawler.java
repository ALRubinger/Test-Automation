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
package test.automation.test;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import test.automation.model.html.model.Button;
import test.automation.model.html.model.Form;
import test.automation.model.html.model.Link;
import test.automation.model.html.model.Page;

/**
 * @author Walter White
 */
public class AbstractCrawler extends AbstractHtmlTest {

	/**
	 * Paths to crawl:
	 * www.github.com/a
	 * www.github.com/b
	 * crawler.paths=www.github.com/a,www.github.com/b
	 */
	protected String[] pathsToCrawl;
	protected int maxExternalDepth;

	protected void crawlPage() {
		if (!isInternal()) {
			if (withinExternalDepthLimit()) {
				crawl();
			} else {
				if (returnToPreviousPage()) {
				} else {
					throw (new IllegalStateException("unable to continue"));
				}
			}
		} else {
			crawl();
		}
	}

	protected void crawl() {
	}

	protected boolean isInternal() {
		return (false);
	}

	protected boolean withinExternalDepthLimit() {
		return (false);
	}

	protected boolean returnToPreviousPage() {
		return (true);
	}

	/*
	protected boolean wasCrawled() {
		AuditedReader auditedReader = null;
		return (false);
	}

	protected void crawlLinksOnPage(Page page) throws IOException {
		for (Link link : page.getLinks()) {
			final HtmlAnchor htmlAnchor = sessionContext.getCurrentPageContext().getCurrentPage().getAnchorByHref(link.getUrl());
			final PageContext newPageContext = GetUtil.click(sessionContext, htmlAnchor);

			buildPage(link.getUrl());
		}
	}

	protected void clickButtonsOnPage(Page page) throws IOException {
		for (Form form : page.getForms()) {
			final HtmlForm htmlForm = sessionContext.getCurrentPageContext().getCurrentPage().getFormByName(form.getName());

			for (Button button : form.getButtons()) {
				final HtmlButton htmlButton = htmlForm.getButtonByName(button.getName());
				final PageContext newPageContext = GetUtil.click(sessionContext, htmlButton);

				//buildPage(link.getUrl());
			}
		}
	}
*/

	/**
	 * Crawls the specified page and returns a set of urls on this page that we have not yet visited.
	 * @param url the url to crawl.
	 * @return a set of unique urls we can crawl from this url.
	 * @throws IOException thrown if we cannot crawl the url.
	 */
	/*
	protected Page buildPage(final String url) {
		Page page = new Page(url);

		buildLinks(sessionContext.getCurrentPageContext().getCurrentPage(), page);
		buildForm(sessionContext.getCurrentPageContext().getCurrentPage(), page);

		TestExecutor.persist(page);
		return (page);
	}

	protected void buildLinks(final HtmlPage currentPage, Page page) {
		for (final HtmlAnchor htmlAnchor : currentPage.getAnchors()) {
			final String url = htmlAnchor.getHrefAttribute();

			if (url.startsWith("javascript:")) {
				continue;
			}
			if (url.startsWith("#")) {
				continue;
			}

			new Link(page, url);
		}
	}

	protected void buildForm(final HtmlPage currentPage, Page page) {
		for (final HtmlForm htmlForm : getCurrentPage().getForms()) {
			Form form = new Form(page, htmlForm.getNameAttribute());

			for (final HtmlElement element : htmlForm.getHtmlElementsByTagName("input")) {
				if (element instanceof HtmlInput) {
					final HtmlInput htmlInput = (HtmlInput) element;
					if ("button".equals(htmlInput.getTypeAttribute())) {
						//Button button = new Button(form);
					} else {
						//Input input = new Input(form);
					}
				}
			}
		}
	}
	 */
}
