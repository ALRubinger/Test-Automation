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

/**
 * @author Walter White
 */
public interface Iterator<Type> {

	/**
	 * Returns the next entry.
	 * @return
	 * @throws Exception
	 */
	Type next() throws Exception;

	/**
	 * Returns the previous entry.
	 * @return
	 * @throws Exception
	 */
	Type previous() throws Exception;

	/**
	 * Returns the first entry.
	 * @return
	 * @throws Exception
	 */
	Type first() throws Exception;

	/**
	 * Returns the last entry.
	 * @return
	 * @throws Exception
	 */
	Type last() throws Exception;

	/**
	 * Returns the specified index.
	 * @param pageNumber the page number to go to.
	 * @return
	 * @throws Exception
	 */
	Type get(int index) throws Exception;

	/**
	 * Returns the size of this entity.
	 * @return
	 */
	int getCount();

	/**
	 * Returns the current index.
	 * @return
	 */
	int getIndex();

	/**
	 * Returns true if we can call next.
	 * @return
	 */
	boolean hasNext();

	/**
	 * Returns true if we can call previous.
	 * @return
	 */
	boolean hasPrevious();

	/**
	 * Returns true if we are currently at the first entity.
	 * @return
	 */
	boolean isFirst();

	/**
	 * Returns true if we are currently at the last entity.
	 * @return
	 */
	boolean isLast();
}
