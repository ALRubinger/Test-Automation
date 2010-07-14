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
package test.automation.iterator.iterator;

import test.automation.iterator.enumeration.SortOrder;

/**
 * @author Walter White
 */
public interface TableColumnIterator<Type> extends Iterator<Type> {

	/**
	 * Sorts on the current column in the specified order.
	 * @param tableColumnIteration
	 * @return
	 */
	Type sort(final SortOrder tableColumnIteration);

	/**
	 * Returns true if the current column is sortable.
	 * @return
	 */
	boolean sortable();

	/**
	 * Returns true if the column is sorted, false otherwise.
	 * @return
	 */
	boolean sorted();
}
