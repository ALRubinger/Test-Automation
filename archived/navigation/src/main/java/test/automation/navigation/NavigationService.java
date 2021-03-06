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
package test.automation.navigation;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author Walter White
 */
public interface NavigationService {
	/**
	 * Clicks the back button.
	 * @param htmlPage the current html page the test is on.
	 */
	void back(final HtmlPage htmlPage);
}
