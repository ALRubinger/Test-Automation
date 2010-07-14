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
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import test.automation.model.AbstractEntity;
import test.automation.model.history.model.NavigationEvent;

/**
 * @author Walter White
 */
@Embeddable
@MappedSuperclass
public abstract class AbstractIteration extends AbstractEntity {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected NavigationEvent htmlCommand;
	@Column(nullable = false, updatable = false)
	protected int index;

	public NavigationEvent getHtmlCommand() {
		return htmlCommand;
	}

	public void setHtmlCommand(NavigationEvent htmlCommand) {
		this.htmlCommand = htmlCommand;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
