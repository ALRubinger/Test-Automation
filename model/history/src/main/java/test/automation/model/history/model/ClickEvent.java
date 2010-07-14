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
package test.automation.model.history.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import test.automation.model.html.model.Element;
import test.automation.model.html.model.Page;

/**
 * @author Walter White
 */
@PrimaryKeyJoinColumn
@Entity
public class ClickEvent extends NavigationEvent {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected Element clickedHtmlElement;

	public ClickEvent() {
		super();
	}

	public ClickEvent(Element clickedHtmlElement)
	{
		this();
		setClickedHtmlElement(clickedHtmlElement);
	}

	public ClickEvent(Execution execution, Page sourcePage, Element clickedHtmlElement) {
		super(execution, sourcePage);
		setClickedHtmlElement(clickedHtmlElement);
	}

	public Element getClickedHtmlElement() {
		return clickedHtmlElement;
	}

	public void setClickedHtmlElement(Element clickedHtmlElement) {
		this.clickedHtmlElement = clickedHtmlElement;
	}
}
