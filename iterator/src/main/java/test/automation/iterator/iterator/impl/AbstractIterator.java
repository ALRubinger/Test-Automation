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

import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import test.automation.iterator.PaginationEvent;
import test.automation.iterator.annotation.First;
import test.automation.iterator.annotation.Get;
import test.automation.iterator.annotation.Last;
import test.automation.iterator.annotation.Next;
import test.automation.iterator.annotation.Previous;
import test.automation.iterator.iterator.Iterator;
import test.automation.operator.Operator;

/**
 * @author Walter White
 */
public abstract class AbstractIterator<Type> implements Iterator<Type> {

	@Inject
	protected Operator operator;
	@Inject
	protected Event<PaginationEvent> paginationEvent;

	protected int index;
	protected int count;
	protected Type currentEntity;

	protected AbstractIterator() {
		index = 0;
		refreshCount();
	}

	@Override
	public Type next() throws Exception {
		final int expectedIndex = index + 1;
		goNext();
		paginationEvent.select(new AnnotationLiteral<Next>(){}).fire(new PaginationEvent());
		check(expectedIndex);

		return (currentEntity);
	}

	protected abstract void goNext() throws Exception;

	protected abstract void check(final int expectedIndex) throws Exception;
	
	protected abstract int getActualIndex();

	/**
	 * Dynamically counts the number of records.
	 */
	protected abstract void refreshCount();

	@Override
	public Type previous() throws Exception {
		final int expectedIndex = index - 1;
		goPrevious();
		paginationEvent.select(new AnnotationLiteral<Previous>(){}).fire(new PaginationEvent());
		check(expectedIndex);
		return (currentEntity);
	}

	protected abstract void goPrevious() throws Exception;

	@Override
	public Type first() throws Exception {
		final int expectedIndex = 0;
		goFirst();
		paginationEvent.select(new AnnotationLiteral<First>(){}).fire(new PaginationEvent());
		check(expectedIndex);

		return (currentEntity);
	}

	protected abstract void goFirst() throws Exception;

	@Override
	public Type last() throws Exception {
		final int expectedIndex = count - 1;

		goLast();
		paginationEvent.select(new AnnotationLiteral<Last>(){}).fire(new PaginationEvent());
		check(expectedIndex);

		return (currentEntity);
	}

	protected abstract void goLast() throws Exception;

	@Override
	public Type get(int index) throws Exception {
		final int expectedIndex = index;

		goGet(index);
		paginationEvent.select(new AnnotationLiteral<Get>(){}).fire(new PaginationEvent());
		check(expectedIndex);

		return (currentEntity);
	}

	protected abstract void goGet(final int index) throws Exception;

	@Override
	public int getCount() {
		return (count);
	}

	@Override
	public int getIndex() {
		return (index);
	}

	@Override
	public boolean hasNext() {
		return (index + 1 < count);
	}

	@Override
	public boolean hasPrevious() {
		return (index > 0);
	}

	@Override
	public boolean isFirst() {
		return (index == 0);
	}

	@Override
	public boolean isLast() {
		return (index + 1 == count);
	}
}
