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
package test.automation.iterator;

import java.util.Iterator;
import test.automation.model.context.model.context.SessionContext;

/**
 * @author Walter White
 */
public abstract class AbstractResultIterator<Type> implements Iterator<Type> {

	protected final SessionContext sessionContext;

	protected AbstractResultIterator(final SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/*
	protected boolean hasNext()
	{
	if(hasMoreRows())
	{

	}
	if(hasMorePages())
	{

	}

	return(false);
	}
	 */
	protected boolean hasMoreRows() {
		return (false);
	}

	/**
	 * Checks if the next page link is present.
	 * @return true if we can navigate to the next page.
	 */
	protected boolean hasMorePages() {
		return (false);
	}

	public void remove() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public SessionContext getSessionContext() {
		return (sessionContext);
	}
}
