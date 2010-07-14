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
package test.automation.util;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Walter White
 */
public class FormUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FormUtil.class);

	private FormUtil() {
	}

	/**
	 * Get's all of the input elements on the page.
	 * @param htmlElement
	 * @return
	 */
	public static final List<HtmlElement> getInputs(final HtmlForm htmlForm, final HtmlElement htmlElement) {
		final List<HtmlElement> inputs = new ArrayList<HtmlElement>();
		for (final HtmlElement child : htmlForm.getChildElements()) {
			if (isInputElement(child)) {
				inputs.add(child);
			} else {
				inputs.addAll(getInputs(htmlForm, child));
			}
		}

		return (inputs);
	}

	/**
	 * Simple check to ensure that a given element is an html input.
	 * @param htmlElement
	 * @return
	 */
	public static final boolean isInputElement(final HtmlElement htmlElement) {
		if (HtmlSelect.class.isInstance(htmlElement)) {
			return (true);
		}
		if (HtmlInput.class.isInstance(htmlElement)) {
			return (true);
		}

		return (false);
	}

	public static final HtmlInput getButton(final HtmlPage currentPage, final String buttonValue) {
		return (getButton(getForm(currentPage), buttonValue));
	}

	public static final HtmlInput getButton(final HtmlForm form, final String buttonValue) {
		return (form.getInputByValue(buttonValue));
	}

	public static final HtmlForm getForm(final HtmlPage currentPage) {
		return (currentPage.getForms().get(0));
	}

	// this is a multi-select add button, this should be made static, and available to the general application ...
	public static final HtmlInput getAddButton(final HtmlForm form) {
		return (getButton(form, "\\>\\>"));
	}

	public static final HtmlInput getRemoveButton(final HtmlForm form) {
		return (getButton(form, "\\<\\<"));
	}

	public static final HtmlInput getSaveButton(final HtmlForm form) {
		return (getButton(form, "Save"));
	}

	public static final boolean isXPath(final String value) {
		return (value.startsWith("//"));
	}

	public static final String getValueByXPath(final HtmlPage currentPage, final String xpath) {
		final DomElement element = (DomElement) currentPage.getByXPath(xpath).get(0);

		return (element.getTextContent());
	}

	public static final HtmlSelect getSelect(final HtmlPage currentPage, final String elementId) {
		return ((HtmlSelect) currentPage.getElementById(elementId));
	}
}
