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
package test.automation.test.cases;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import test.automation.model.test.enumeration.TestType;
import test.automation.model.test.model.test.TestDefinition;
import test.automation.test.AbstractForm;
import test.automation.test.annotation.FieldOrder;
import test.automation.test.annotation.TestCase;
import test.automation.test.annotation.scope.TestCaseScoped;
import test.automation.test.event.FormChangedEvent;

/**
 * @author Walter White
 */
@TestCaseScoped
public class FieldOrderTestCase {

	@Inject
	protected AbstractForm form;
	@Inject
	protected TestDefinition testDefinition;
	@Inject
	protected HtmlPage currentPage;
	@Inject
	protected HtmlForm currentForm;

	/**
	 * SImply checks that the fields are in the correct order.
	 */
	@TestCase(type = TestType.Positive)
	public void testFieldOrder(@Observes FormChangedEvent formChangedEvent) {
		final FieldOrder fieldOrder = testDefinition.getTestMethod().getTestMethod().getAnnotation(FieldOrder.class);
		if (fieldOrder != null) {
			for (final String fieldName : fieldOrder.value()) {
				currentPage.tabToNextElement();

				int tabIndex = 0;
				for (final HtmlElement htmlElement : currentPage.getTabbableElements()) {
					if (htmlElement instanceof HtmlSelect
							|| htmlElement instanceof HtmlInput
							|| htmlElement instanceof HtmlTextArea) {
						tabIndex++;
					}
				}
			}
		}
	}
}
