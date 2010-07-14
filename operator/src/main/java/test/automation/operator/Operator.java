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
package test.automation.operator;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import java.io.IOException;

/**
 * @author Walter White
 */
public interface Operator {

	/**
	 * Clicks the specified html element just as a user would click a mouse button on a link or button, etc.
	 * @param htmlElement the element to click.
	 * @throws Exception thrown if there is an exception during clicking the element.
	 */
	void click(final HtmlElement htmlElement) throws Exception;

	/**
	 * Gets the default url, home page.
	 * @throws Exception thrown if we cannot get the default url.
	 */
	void get() throws Exception;

	/**
	 * Gets the specified url.
	 * @param url the url to fetch.
	 * @throws Exception thrown if we cannot fetch the specified url.
	 */
	void get(final String url) throws Exception;

	/**
	 * Types the given value into the given html input.
	 * @param htmlInput
	 * @param value
	 */
	void type(final HtmlInput htmlInput, final String value) throws IOException;

	/**
	 * Selects the given option in the html select.
	 * @param htmlSelect
	 * @param value
	 */
	void select(final HtmlSelect htmlSelect, final String value);
}
