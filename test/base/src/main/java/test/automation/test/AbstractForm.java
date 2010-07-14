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

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.operator.FormHandler;

/**
 * @author Walter White
 */
public abstract class AbstractForm extends AbstractHtmlTest {

	public static final String BACK_EVENT = "back";
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractForm.class);
	public static final String FORM_CHANGED_EVENT = "test.automation.events.formChanged";
	public static final String PRE_SET_FORM_EVENT = "test.automation.events.setForm.pre";
	public static final String POST_SET_FORM_EVENT = "test.automation.events.setForm.post";
	//@Out
	protected HtmlForm currentForm;
	//@Out
	protected Object formData;
	//@Out
	protected Object originalState;
	//@Inject
	//protected Events events;
	@Inject
	protected FormHandler formHandler;

	public Object getFormData() {
		return formData;
	}

	public HtmlForm getCurrentForm() {
		return currentForm;
	}

	/**
	 * Selects a random option and returns the selected option.
	 * @param htmlSelect
	 * @return
	 */
	protected static final HtmlOption selectRandomOption(final HtmlSelect htmlSelect) {
		final int index = TestHelper.selectRandom(htmlSelect.getOptionSize());
		final HtmlOption htmlOption = htmlSelect.getOption(index);

		LOGGER.trace("selected:" + htmlOption);
		return (htmlOption);
	}

	protected static final boolean inList(final HtmlSelect htmlSelect, final HtmlOption selectedOption) {
		for (final HtmlOption htmlOption : htmlSelect.getOptions()) {
			if (htmlOption.getValueAttribute().equals(selectedOption.getValueAttribute())) {
				return (true);
			}
		}

		return (false);
	}

	/**
	 * Does the form have unsaved changes?
	 * @return
	 */
	protected boolean isPageDirty() {
		return (false);
	}

	/**
	 * After confirming that the page is indeed dirty, check that we cannot navigate away without a JavaScript alert.
	 * Check all links, buttons, and inputs.
	 */
	protected void navigateAway() {
	}

	protected void clickButton(final String buttonValue) throws Exception {
		operator.click(currentForm.getInputByValue(buttonValue));
	}

	protected void back() throws Exception {
		back(currentPage);
	}

	protected void back(final HtmlPage page) throws Exception {
		clickButton("Back");
		//events.raiseEvent(BACK_EVENT, page, currentPage);
	}

	protected void set() throws Exception {
		//events.raiseEvent(PRE_SET_FORM_EVENT, formData);
		formHandler.set(formData);
		//events.raiseEvent(POST_SET_FORM_EVENT, formData);
	}

	/*
	public final void assertPageDirty() {
	Assert.assertTrue(getDirtyPageFlagValue());
	}

	private final boolean getDirtyPageFlagValue() {
	// check that the variable, dirtyPageFlag is true
	final ScriptResult result = currentPage.executeJavaScript("dirtyPageFlag.value");
	final String dirtyPageFlagValue = (String) result.getJavaScriptResult();

	if (dirtyPageFlagValue != null) {
	return (Boolean.valueOf(dirtyPageFlagValue));
	}

	return (false);
	}

	public final void assertWarned() {
	for (final String alertMessage : ((CollectingAlertHandler) webClient.getAlertHandler()).getCollectedAlerts()) {
	if ("You have not saved your work yet.  If you continue, your work will not be saved.".equals(alertMessage)) {
	return;
	}
	}

	Assert.fail("page does not appear to be dirty.");
	}

	protected final void savedSuccessfully() {
	savedSuccessfully(currentPage);
	}

	protected final void savedSuccessfully(final HtmlPage page) {
	Assert.assertTrue(page.getBody().asText().indexOf("Record Saved successfully.") >= 0);
	}
	 */
}
