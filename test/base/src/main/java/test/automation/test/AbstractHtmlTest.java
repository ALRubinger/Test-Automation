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

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.net.URL;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.authentication.Authenticator;
import test.automation.model.history.model.NavigationEvent;
import test.automation.model.html.model.Page;
import test.automation.model.http.HttpSession;
import test.automation.operator.Operator;

import test.automation.test.annotation.TestURL;

/**
 * @author Walter White
 */
public abstract class AbstractHtmlTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHtmlTest.class);
	public static final String CSS_CLASS_ATTRIBUTE = "class";
	public static final String HTML_UNIT_CSS_CLASS_MODIFIED = " htmlUnitModified";
	@Inject
	protected HtmlPage currentPage;
	/**
	 * This entity is created by the listeners we have registered.
	 */
	@Inject
	protected Page page;
	@Inject
	protected WebClient webClient;
	@Inject
	protected HttpSession httpSession;
	@Inject
	protected Authenticator authenticator;
	//@Out
	protected NavigationEvent htmlCommand;
	@Inject
	protected Operator operator;

	/**
	 * Fetches the test, right now, that is a get; however, this should look at the 'home' page, find the link, and click the link.
	 * That way, we can validate the link exists and is working.
	 * @throws IOException thrown if we cannot fetch the specified page.
	 */
	public final void fetch() throws Exception {
		operator.get(getTestUrl());
	}

	public final String getTestUrl() {
		final TestURL testURL = (TestURL) testDefinition.getTestMethod().getTestMethod().getDeclaringClass().getAnnotation(TestURL.class);
		final URL pageURL = currentPage.getWebResponse().getRequestSettings().getUrl();
		return (getServerContextPath(pageURL) + "/" + testURL.value());
	}

	/**
	 * Gets the context path of the server.
	 * @param url
	 * @return
	 */
	public static final String getServerContextPath(final URL url) {
		final String urlString = url.toString();

		final int indexOfSlash = urlString.lastIndexOf("/");
		if (indexOfSlash > 0) {
			return (urlString.substring(0, indexOfSlash));
		}

		return (urlString);
	}

	/*
	public static final void elementPresent(final String elementId, final TestContext testContext) {
	Assert.assertNull("This element should be present on the current page",
	testContext.getSessionContext().getCurrentPageContext().getCurrentPage().getElementById(elementId));
	}

	public static final void elementNotPresent(final String elementId, final TestContext testContext) {
	Assert.assertNull("This element should not be present on the current page", testContext.getSessionContext().getCurrentPageContext().getCurrentPage().getElementById(elementId));
	}
	 */
	/**
	 * Asserts that the form itself be empty, ie, nothing is selected or typed in.
	 */
	/*
	protected void assertEmpty() {
	for (final HtmlElement htmlElement : getInputs()) {
	if (HtmlSelect.class.isInstance(htmlElement)) {
	assertEmpty((HtmlSelect) htmlElement);
	} else {
	assertEmpty((HtmlInput) htmlElement);
	}
	}
	}

	/ **
	 * Asserts that a select element is null.
	 * @param htmlSelect
	 * /
	protected void assertEmpty(final HtmlSelect htmlSelect) {
	final List<HtmlOption> selectedOptions = htmlSelect.getSelectedOptions();
	if (selectedOptions != null) {
	if (selectedOptions.size() > 0) {
	Assert.assertTrue("Only a null value may be selected by default, found:" + selectedOptions.size() + " options selected though.", 1 == selectedOptions.size());
	assertEmpty(selectedOptions.get(0).getValueAttribute());
	}
	}
	}

	/ **
	 * Asserts that an input is empty.
	 * @param htmlInput
	 * /
	protected void assertEmpty(final HtmlInput htmlInput) {
	assertEmpty(htmlInput.getValueAttribute());
	}

	/ **
	 * Asserts that the passed in value is empty / null.
	 * @param value
	 * /
	protected void assertEmpty(final String value) {
	Assert.assertTrue("Default value must be empty:" + value, StringUtils.isBlank(value));
	}
	/ **
	 * Checks that a given option is selected even after an AJAX or non-AJAX call.
	 * @param htmlSelect
	 * @param htmlOption
	 * /
	protected void assertIsSelected(final HtmlSelect htmlSelect, final HtmlOption htmlOption) {
	final HtmlSelect currentSelect = form.getSelectByName(htmlSelect.getNameAttribute());
	final List<HtmlOption> selectedOptions = currentSelect.getSelectedOptions();
	Assert.assertNotNull("Expected selection to be not null.", selectedOptions);
	Assert.assertEquals("Expected exactly 1 item to be selected.", 1, selectedOptions.size());
	Assert.assertEquals("Expected the same value to be selected.", selectedOptions.get(0).getValueAttribute(), htmlOption.getValueAttribute());
	}
	 */
	private test.automation.model.html.model.Element getHtmlElement(final HtmlElement htmlElement) {
		return (null);
	}
}
