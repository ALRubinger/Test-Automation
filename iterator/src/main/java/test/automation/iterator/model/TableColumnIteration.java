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
package test.automation.iterator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import org.hibernate.annotations.Immutable;
import test.automation.iterator.enumeration.SortOrder;

/**
 * @author Walter White
 */
@Immutable
@Entity
public class TableColumnIteration extends AbstractIteration {

	@Enumerated
	@Column(nullable = false, updatable = false)
	protected SortOrder sortOrder;

	public TableColumnIteration() {
		super();
	}

	public TableColumnIteration(final int index, final SortOrder sortOrder) {
		this();
		setIndex(index);
		setSortOrder(sortOrder);
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(final SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
