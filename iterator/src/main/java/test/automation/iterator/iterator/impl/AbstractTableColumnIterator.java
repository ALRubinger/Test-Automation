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
package test.automation.iterator.iterator.impl;

import test.automation.iterator.enumeration.SortOrder;
import test.automation.iterator.exception.IterationException;
import test.automation.iterator.iterator.TableColumnIterator;

/**
 * @author Walter White
 */
public abstract class AbstractTableColumnIterator extends AbstractIterator implements TableColumnIterator {

	protected boolean ascending;
	protected SortOrder defaultTableColumnIteration;

	@Override
	protected void check(final int expectedIndex) throws IterationException {
		final int actualIndex = getActualIndex();
		if(actualIndex != expectedIndex)
		{
			//throw(new TableColumnIterationException());
		}
	}

	/*
	@Override
	protected void goNext() {
		if (!isLast()) {
		}
	}

	@Override
	protected int check(int expectedIndex) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void refreshCount() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void goPrevious() {
		if (!isFirst()) {
		}
	}

	@Override
	protected void goFirst() {
		if (!isFirst()) {
		}
	}

	@Override
	protected void goLast() {
		if (!isLast()) {
		}
	}

	@Override
	protected void goGet(int index) {
		if (index < count) {
		}

		throw (new IndexOutOfBoundsException(index + " is greater than " + count));
	}

	@Override
	public Object sort(TableColumnIteration tableColumnIteration) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean sortable() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean sorted() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	 */


}
