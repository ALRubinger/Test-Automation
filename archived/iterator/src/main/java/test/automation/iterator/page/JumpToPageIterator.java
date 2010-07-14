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

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import test.automation.model.context.SessionContext;
import java.io.IOException;
import java.util.Set;

/**
 * Iterator that checks that we can goto every page in a linear fashion (forward, then backward).
 * And then randomly goes to each page.
 * Runs through the pages 3 times + 1 (to return to the first page).
 * @author Walter White
 */
public class JumpToPageIterator extends AbstractPageIterator {

	protected Set<Integer> visitedPages;

	public JumpToPageIterator(final SessionContext sessionContext) {
		super(sessionContext);
	}

	protected void goOther() throws IOException {
		final int gotoPage = 0;
		currentPage = gotoPage;
		wentOther = (visitedPages.size() == 0);
	}

	private static final void gotoPage(final SessionContext sessionContext, final int gotoPage) throws IOException {
		final HtmlPage currentPage = sessionContext.getCurrentPageContext().getCurrentPage();
		final HtmlInput gotoInput = SearchResultUtil.getGotoInput(currentPage);
		gotoInput.setValueAttribute(Integer.toString(gotoPage));
		GetUtil.click(sessionContext, SearchResultUtil.getGotoLink(currentPage));
	}

	@Override
	protected void goNext() throws IOException {
		gotoPage(sessionContext, currentPage + 1);
	}

	@Override
	protected void goPrevious() throws IOException {
		gotoPage(sessionContext, currentPage - 1);
	}

	@Override
	protected void goFirst() throws IOException {
		gotoPage(sessionContext, 1);
	}

	@Override
	protected void goLast() throws IOException {
		gotoPage(sessionContext, pageCount);
	}
}
