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
package test.automation.iterator.column;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableHeaderCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import test.automation.model.context.SessionContext;
import test.automation.iterator.AbstractResultIterator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Walter White
 */
public abstract class AbstractColumnSortingtIterator extends AbstractResultIterator<HtmlTableCell> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractColumnSortingtIterator.class);
	protected final int defaultSortColumn;
	protected final boolean isAscendingByDefault;
	protected final int columnCount;
	protected int currentColumn;
	protected boolean ascending;

	protected AbstractColumnSortingtIterator(final SessionContext sessionContext) {
		super(sessionContext);
		columnCount = SearchResultUtil.getColumnCount(sessionContext.getCurrentPageContext().getCurrentPage());
		LOGGER.debug("column count:" + columnCount);
		currentColumn = -1;

		defaultSortColumn = 0;
		isAscendingByDefault = true;

		testDefaults();
	}

	/**
	 * Tests the defaults.
	 */
	protected void testDefaults() {
		final HtmlPage currentPage = sessionContext.getCurrentPageContext().getCurrentPage();
		final List<HtmlTableHeaderCell> headerCells = SearchResultUtil.getTableHeaderCells(currentPage);
		if(headerCells.size() > 0)
		{
			LOGGER.debug("testing the default sorting behavior");

			final HtmlTableHeaderCell headerCell = headerCells.get(defaultSortColumn);
			isHeaderAscending(headerCell, isAscendingByDefault);
			isDataAscending(currentPage, isAscendingByDefault, defaultSortColumn);
		}
		else
			LOGGER.debug("unable to test the default sorting behavior as no tabular data was found.");
	}

	public boolean hasNext() {
		if (currentColumn >= (columnCount - 1)) {
			return (false);
		}

		return (SearchResultUtil.nextSortableColumn(sessionContext.getCurrentPageContext().getCurrentPage(), columnCount, currentColumn) >= 0);
	}

	public HtmlTableCell next() {
		updateSorting();
		return (sortOnColumn());
	}

	protected abstract void updateSorting();

	private final HtmlTableCell sortOnColumn() {
		final HtmlTableHeaderCell selectedTableHeaderCell = SearchResultUtil.getTableHeaderCells(sessionContext.getCurrentPageContext().getCurrentPage()).get(currentColumn);

		if (SearchResultUtil.isSortable(selectedTableHeaderCell)) {
			final HtmlAnchor selectedTableHeaderCellLink = (HtmlAnchor) selectedTableHeaderCell.getChildElements().iterator().next();
			try {
				GetUtil.click(sessionContext, selectedTableHeaderCellLink);
			} catch (Exception e) {
			}

			checkSorting();
		}

		return (selectedTableHeaderCell);
	}

	private final void checkSorting() {
		int columnIndex = 0;
		for (final HtmlTableHeaderCell headerCell : SearchResultUtil.getTableHeaderCells(sessionContext.getCurrentPageContext().getCurrentPage())) {
			if (SearchResultUtil.isSorted(headerCell)) {
				Assert.assertEquals("The actual column we're on doesn't match what we thought we would be on.", currentColumn, columnIndex);
				isHeaderAscending(headerCell, ascending);
				isDataAscending(sessionContext.getCurrentPageContext().getCurrentPage(), ascending, currentColumn);
			}

			columnIndex++;
		}
	}

	private static final void isHeaderAscending(final HtmlTableHeaderCell headerCell, final boolean ascending) {
		Assert.assertEquals("The actual sort order doesn't match what we expected.", ascending, SearchResultUtil.isAscending(headerCell));
		Assert.assertEquals("The actual sort order doesn't match what we expected.", !ascending, SearchResultUtil.isDescending(headerCell));
	}

	private static final void isDataAscending(final HtmlPage currentPage, final boolean ascending, final int column) {
		final List<String> columnData = new ArrayList<String>();

		final HtmlTable table = SearchResultUtil.getTable(currentPage);
		for (int rowIndex = 1; (rowIndex + 1) < table.getRowCount(); rowIndex++) {
			final HtmlTableRow row = table.getRow(rowIndex);
			columnData.add(row.getCell(column).getTextContent());
		}

		for (int index = 0; (index + 1) < columnData.size(); index++) {
			final String a = columnData.get(index);
			final String b = columnData.get(index + 1);
			if (ascending) {
				Assert.assertTrue("expected data to be sorted (ascending):" + a + "->" + b, isAscending(a, b));
			} else {
				Assert.assertTrue("expected data to be sorted (descending)" + a + "->" + b, isDescending(a, b));
			}
		}
	}

	private static final boolean isAscending(final String value1, final String value2) {
		return (value1.compareTo(value2) <= 0);
	}

	private static final boolean isDescending(final String value1, final String value2) {
		return (value1.compareTo(value2) >= 0);
	}

	public static final boolean isSortable(final HtmlTableHeaderCell htmlTableHeaderCell) {
		return (isCssClassPresent(htmlTableHeaderCell, "sortable"));
	}

	public static final boolean isSorted(final HtmlTableHeaderCell htmlTableHeaderCell) {
		return (isCssClassPresent(htmlTableHeaderCell, "sorted"));
	}

	public static final boolean isAscending(final HtmlTableHeaderCell htmlTableHeaderCell) {
		return (isCssClassPresent(htmlTableHeaderCell, "ascending"));
	}

	public static final boolean isDescending(final HtmlTableHeaderCell htmlTableHeaderCell) {
		return (isCssClassPresent(htmlTableHeaderCell, "descending"));
	}
}
