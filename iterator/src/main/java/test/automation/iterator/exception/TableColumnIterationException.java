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
package test.automation.iterator.exception;

import test.automation.iterator.enumeration.PageIteration;
import test.automation.iterator.enumeration.SortOrder;

/**
 * Exception to capture an error when iterating between pages.
 * Unfortunately, you cannot specify the type of an exception like IterationException<Type>, that is not allowed.
 * @author Walter White
 */
public class TableColumnIterationException extends IllegalStateException {

	private static final long serialVersionUID = -3042686055658047285L;
	protected final SortOrder expectedTableColumnIteration;
	protected final SortOrder actualTableColumnIteration;

	public TableColumnIterationException(final SortOrder expectedTableColumnIteration, final SortOrder actualTableColumnIteration, final PageIteration pageIteration) {
		super("Expected table column iteration:" + expectedTableColumnIteration + ", but actually got:" + actualTableColumnIteration);
		this.expectedTableColumnIteration = expectedTableColumnIteration;
		this.actualTableColumnIteration = actualTableColumnIteration;
	}

	public TableColumnIterationException(final SortOrder expectedTableColumnIteration, final SortOrder actualTableColumnIteration, final PageIteration pageIteration, final Throwable cause) {
		super("Expected table column iteration:" + expectedTableColumnIteration + ", but actually got:" + actualTableColumnIteration, cause);
		this.expectedTableColumnIteration = expectedTableColumnIteration;
		this.actualTableColumnIteration = actualTableColumnIteration;
	}

	public SortOrder getActualTableColumnIteration() {
		return actualTableColumnIteration;
	}

	public SortOrder getExpectedTableColumnIteration() {
		return expectedTableColumnIteration;
	}
}
