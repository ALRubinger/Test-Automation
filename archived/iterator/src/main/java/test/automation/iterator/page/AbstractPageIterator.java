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

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Walter White
 */
public abstract class AbstractPageIterator extends AbstractResultIterator<HtmlPage> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPageIterator.class);
	protected final int pageCount;
	protected int currentPage;
	private boolean wentNext;
	private boolean wentPrevious;
	private boolean wentLast;
	private boolean wentFirst;
	protected boolean wentOther;

	protected AbstractPageIterator(final AbstractSearchResults searchResults) {
		super(sessionContext);
		pageCount = SearchResultUtil.getPageCount(sessionContext.getCurrentPageContext().getCurrentPage());

		LOGGER.debug("pageCount:" + pageCount);
		currentPage = 0;
	}

	protected abstract void goNext() throws IOException;

	protected abstract void goPrevious() throws IOException;

	protected abstract void goFirst() throws IOException;

	protected abstract void goLast() throws IOException;

	protected abstract void goOther() throws IOException;

	public boolean hasNext() {
		if (pageCount > 1) {
			if (!wentNext) {
				return (true);
			}
			if (!wentPrevious) {
				return (true);
			}

			if (!wentLast) {
				return (true);
			}
			if (!wentFirst) {
				return (true);
			}

			if (!wentOther) {
				return (true);
			}
		}

		return (false);
	}

	public HtmlPage next() {
		try {
			if (!wentNext) {
				goNext();
				currentPage++;
				wentNext = (currentPage == pageCount);
			}
			if (!wentPrevious) {
				goPrevious();
				currentPage--;
				wentPrevious = (currentPage == 1);
			}

			if (!wentLast) {
				goLast();
				currentPage = pageCount;
				wentLast = true;
			}
			if (!wentFirst) {
				goFirst();
				currentPage = 1;
				wentFirst = true;
			}

			if (!wentOther) {
				goOther();
			}

			validatePagination(sessionContext.getCurrentPageContext().getCurrentPage(), currentPage, pageCount);
		} catch (IOException e) {
		}

		return (sessionContext.getCurrentPageContext().getCurrentPage());
	}

	protected final void onPage(final HtmlPage currentPage, final int expectedPage) {
		final int actualCurrentPage = getCurrentPageNumber(currentPage);
		Assert.assertEquals("Expected the current page to be:" + currentPage, currentPage, actualCurrentPage);
	}

	protected final void canGoNext(final HtmlPage currentPage) {
		Assert.assertNotNull(getNextLink(currentPage));
	}

	protected final void cannotGoNext(final HtmlPage currentPage) {
		Assert.assertNull(getNextLink(currentPage));
	}

	protected final void canGoLast(final HtmlPage currentPage) {
		Assert.assertNotNull(getLastLink(currentPage));
	}

	protected final void cannotGoLast(final HtmlPage currentPage) {
		Assert.assertNull(getLastLink(currentPage));
	}

	protected final void canGoPrevious(final HtmlPage currentPage) {
		Assert.assertNotNull(getPreviousLink(currentPage));
	}

	protected final void cannotGoPrevious(final HtmlPage currentPage) {
		Assert.assertNull(getPreviousLink(currentPage));
	}

	protected final void canGoFirst(final HtmlPage currentPage) {
		Assert.assertNotNull(getFirstLink(currentPage));
	}

	protected final void cannotGoFirst(final HtmlPage currentPage) {
		Assert.assertNull(getFirstLink(currentPage));
	}
}
