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
package test.automation.comparator.table.impl;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import test.automation.comparator.table.TableComparator;

/**
 * @author Walter White
 */
public class DefaultTableComparator implements TableComparator{

	private static final Logger LOGGER = LoggerFactory.getLogger(TableUtil.class);

	private TableUtil() {
	}

	public static final String getCellText(final HtmlTableRow htmlTableRow, final int cellIndex) {
		return (getCellText(htmlTableRow.getCell(cellIndex)));
	}

	public static final String getCellText(final HtmlTableCell cell) {
		final String cellText = cell.getTextContent().trim();

		LOGGER.trace("cellText:" + cellText + ":");
		return (cellText);
	}

	/**
	 * Compares 2 tables asserting that the content is identical.
	 * This is used when navigating to an edit page and then returning back to the result page.
	 * Ensures the elements are in exactly the same position they were when we left the page to edit an item.
	 * @param tableA the source table.
	 * @param tableB the compare table.
	 * @return true if the tables are identical, false otherwise.
	 */
	public static final boolean equals(final HtmlTable tableA, final HtmlTable tableB) {
		// assert that they are the same size
		if (tableA.getRowCount() != tableB.getRowCount()) {
			LOGGER.debug("tables have different row counts:" + tableA.getRowCount() + ":" + tableB.getRowCount());
			return (false);
		}

		LOGGER.debug("tables have the same number of rows:" + tableA.getRowCount());
		for (int index = 0; index < tableA.getRowCount(); index++) {
			if (!equals(tableA.getRow(index), tableB.getRow(index))) {
				LOGGER.debug("the rows were different.");
				return (false);
			}
		}

		LOGGER.debug("tables have the same contents.");
		return (true);
	}

	/**
	 * Compares 2 rows for equality.
	 * @param rowA the source row.
	 * @param rowB the compare row.
	 * @return true if the rows are identical, false otherwise.
	 */
	public static final boolean equals(final HtmlTableRow rowA, final HtmlTableRow rowB) {
		if (rowA.getCells().size() != rowB.getCells().size()) {
			LOGGER.debug("the rows were different sizes:" + rowA.getCells().size() + ":" + rowB.getCells().size());
			return (false);
		}

		for (int column = 0; column < rowA.getCells().size(); column++) {
			if (!equals(rowA.getCell(column), rowB.getCell(column))) {
				LOGGER.debug("the cells are different:" + rowA.getCell(column).getTextContent() + "->" + rowB.getCell(column).getTextContent());
				return (false);
			}
		}

		LOGGER.debug("rows have the same contents.");
		return (true);
	}

	/**
	 * Compares 2 cells.
	 * @param cellA the source cell.
	 * @param cellB the compare cell.
	 * @return true if the 2 cells are identical, false otherwise.
	 */
	public static final boolean equals(final HtmlTableCell cellA, final HtmlTableCell cellB) {
		return (cellA.getTextContent().equals(cellB.getTextContent()));
	}

	/**
	 * Returns a list of column data, useful for ensuring the column is sorted ascending or descending.
	 * @param table the table to fetch column data from.
	 * @param column the column index, starting with 0 being the far left column, and n - 1 the rightmost column.
	 * @return the list of string values.
	 */
	public static final List<String> getColumnData(final HtmlTable table, final int column) {
		final List<String> columnData = new ArrayList<String>();
		for (int rowIndex = 1; rowIndex < table.getRowCount(); rowIndex++) {
			columnData.add(getCellText(table.getRow(rowIndex).getCell(column)));
		}

		return (columnData);
	}

	@Override
	public int compare(HtmlTable a, HtmlTable b) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Selects a random row.
	 * @return
	 */
	/*
	public static final HtmlTableRow selectRow(final HtmlPage currentPage) {
		final HtmlTable table = SearchResultUtil.getTable(currentPage);
		return (selectRow(currentPage, TestUtil.selectRandom(table.getRowCount())));
	}
	 */

	/**
	 * Selects the row specified by the index.
	 * @param currentPage
	 * @param rowIndex
	 * @return
	 */
	/*
	public static final HtmlTableRow selectRow(final HtmlPage currentPage, final int rowIndex) {
		final HtmlTable table = SearchResultUtil.getTable(currentPage);
		return (table.getRow(rowIndex));
	}
	 */
}
