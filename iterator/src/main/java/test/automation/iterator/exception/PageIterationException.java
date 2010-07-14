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

/**
 * Exception to capture an error when iterating between pages.
 * @author Walter White
 */
public class PageIterationException extends IterationException {

	private static final long serialVersionUID = -3042686055658047285L;
	protected final PageIteration pageIteration;

	public PageIterationException(final int expectedPage, final int actualPage, final PageIteration pageIteration) {
		super( expectedPage, actualPage);
		
		this.pageIteration = pageIteration;
	}

	public PageIterationException(final int expectedPage, final int actualPage, final PageIteration pageIteration, final Throwable cause) {
		super( expectedPage, actualPage, cause);
		this.pageIteration = pageIteration;
	}

	public PageIteration getPageIteration() {
		return pageIteration;
	}
}
