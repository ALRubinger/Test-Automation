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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import test.automation.model.html.ResourceType;
import test.automation.model.http.HttpResponse;

/**
 * @author Walter White
 */
@Immutable
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class WebResource extends AbstractEntity {

	@Enumerated
	@Column(nullable = false, updatable = false)
	protected ResourceType resourceType;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected HttpResponse httpResponse;

	public WebResource() {
		super();
	}

	public WebResource(HttpResponse httpResponse, final ResourceType resourceType) {
		this();
		setHttpResponse(httpResponse);
		setResourceType(resourceType);
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
}
