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

/**
 * Exception to capture an error when iterating between pages.
 * @author Walter White
 */
public abstract class IterationException extends IllegalStateException {

	private static final long serialVersionUID = -3042686055658047285L;
	protected final int expected;
	protected final int actual;

	protected IterationException(final int expected, final int actual) {
		super("Expected :" + expected + ", but actually is:" + actual);
		this.expected = expected;
		this.actual = actual;
	}

	protected IterationException(final int expected, final int actual, final Throwable cause) {
		super("Expected :" + expected + ", but actually is:" + actual, cause);
		this.expected = expected;
		this.actual = actual;
	}

	public int getActual() {
		return actual;
	}

	public int getExpected() {
		return expected;
	}
}
