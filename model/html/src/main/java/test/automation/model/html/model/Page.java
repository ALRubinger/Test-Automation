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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

/**
 * @author Walter White
 */
@Immutable
@PrimaryKeyJoinColumn
@Entity
public class Page extends WebResource {

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "page")
	protected List<Element> htmlElements;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany
	protected Set<WebResource> webResources;

	public Page() {
		super();
		setHtmlElements(new ArrayList<Element>());
		setWebResources(new HashSet<WebResource>());
	}

	public List<Element> getHtmlElements() {
		return htmlElements;
	}

	public void setHtmlElements(List<Element> htmlElements) {
		this.htmlElements = htmlElements;
	}

	public Set<WebResource> getWebResources() {
		return webResources;
	}

	public void setWebResources(Set<WebResource> webResources) {
		this.webResources = webResources;
	}

	public Element getElementById(final String id) {
		for (Element htmlElement : htmlElements) {
			Attribute idAttribute = htmlElement.getHtmlAttribute("id");
			if (idAttribute != null && idAttribute.getValue().equals(id)) {
				return (htmlElement);
			}
		}

		return (null);
	}
}
