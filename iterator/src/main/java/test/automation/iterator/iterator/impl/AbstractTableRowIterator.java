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

import test.automation.iterator.iterator.TableRowIterator;

/**
 * @author Walter White
 */
public class AbstractTableRowIterator extends AbstractIterator implements TableRowIterator {

	@Override
	protected void goNext() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void check(int expectedIndex) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void refreshCount() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void goPrevious() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void goFirst() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void goLast() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void goGet(int index) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected int getActualIndex() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
