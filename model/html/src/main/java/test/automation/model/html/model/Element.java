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
package test.automation.model.html.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import test.automation.model.AbstractEntity;

/**
 * @author Walter White
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Element extends AbstractEntity {

	@ManyToOne
	@JoinColumn
	protected Page page;
	@ManyToOne
	@JoinColumn
	protected Element parent;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "htmlElement")
	protected Set<Attribute> htmlAttributes;

	public Element() {
		super();
		setHtmlAttributes(new HashSet<Attribute>());
	}

	public Element(Page page, Element parent) {
		this();
		setPage(page);
		setParent(parent);
		//parent.getChildren().add(this);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Set<Attribute> getHtmlAttributes() {
		return htmlAttributes;
	}

	public void setHtmlAttributes(final Set<Attribute> htmlAttributes) {
		this.htmlAttributes = htmlAttributes;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}

	public Attribute getHtmlAttribute(final String attributeName) {
		for (Attribute htmlAttribute : htmlAttributes) {
			if (htmlAttribute.getName().equals(attributeName)) {
				return (htmlAttribute);
			}
		}

		return (null);
	}
}
