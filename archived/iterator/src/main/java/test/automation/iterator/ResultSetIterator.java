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
package test.automation.iterator;

import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import test.automation.model.context.model.context.SessionContext;

/**
 * Iterates through all of the rows in the table.
 * @author Walter White
 */
public class ResultSetIterator extends AbstractResultIterator<HtmlTableRow> {

	protected final int rowCount;
	protected int currentRow;

	public ResultSetIterator(final SessionContext sessionContext) {
		super(sessionContext);
		rowCount = SearchResultUtil.getRowCount(sessionContext.getCurrentPageContext().getCurrentPage());
	}

	public boolean hasNext() {
		return (currentRow < rowCount);
	}

	public HtmlTableRow next() {
		currentRow++;
		return (SearchResultUtil.getRow(sessionContext.getCurrentPageContext().getCurrentPage(), currentRow));
	}
}
