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
package test.automation.iterator.page;

import test.automation.model.context.SessionContext;
import java.io.IOException;
import org.apache.commons.lang.NotImplementedException;

/**
 * Very simple iterator.
 * Just goes forward and then back to the first page.
 * Runs through the pages 2 times.
 * @author Walter White
 */
public class ForwardAndBackwardPageIterator extends AbstractPageIterator {

	public ForwardAndBackwardPageIterator(final SessionContext sessionContext) {
		super(sessionContext);
		wentOther = true;
	}

	protected final void goNext() throws IOException {
		GetUtil.click(sessionContext, SearchResultUtil.getNextLink(sessionContext.getCurrentPageContext().getCurrentPage()));
	}

	protected final void goPrevious() throws IOException {
		GetUtil.click(sessionContext, SearchResultUtil.getPreviousLink(sessionContext.getCurrentPageContext().getCurrentPage()));
	}

	protected final void goLast() throws IOException {
		GetUtil.click(sessionContext, SearchResultUtil.getLastLink(sessionContext.getCurrentPageContext().getCurrentPage()));
	}

	protected final void goFirst() throws IOException {
		GetUtil.click(sessionContext, SearchResultUtil.getFirstLink(sessionContext.getCurrentPageContext().getCurrentPage()));
	}

	/**
	 * Presently, no implementation.
	 * @throws IOException
	 */
	protected final void goOther() throws IOException {
		throw(new NotImplementedException());
	}
}
