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
package test.automation.test.search;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.test.AbstractForm;

/**
 * @author Walter White
 */
public abstract class SearchForm extends AbstractForm {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchForm.class);

	protected SearchForm(final Object command) {
		//super( command);
	}

	/**
	 * Performs the given search.
	 */
	protected void search() throws IOException {
		//clickButton("Search");
	}
}
