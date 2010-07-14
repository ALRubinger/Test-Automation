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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Immutable;
import test.automation.model.AbstractEntity;
import test.automation.model.html.model.Page;

/**
 * @author Walter White
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Immutable
@Entity
public class NavigationEvent extends AbstractEntity {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected Execution execution;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	protected Date dateExecuted;
	@ManyToOne
	@JoinColumn
	protected Page sourcePage;
	@ManyToOne
	@JoinColumn
	protected Page resultingPage;

	public NavigationEvent() {
		super();
	}

	public NavigationEvent(Execution execution, Page sourcePage) {
		this();
		setExecution(execution);
		setSourcePage(sourcePage);
		setDateExecuted(new Date());
	}

	public Execution getExecution() {
		return execution;
	}

	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	public Page getSourcePage() {
		return sourcePage;
	}

	public void setSourcePage(Page sourcePage) {
		this.sourcePage = sourcePage;
	}

	public Date getDateExecuted() {
		return dateExecuted;
	}

	public void setDateExecuted(Date dateExecuted) {
		this.dateExecuted = dateExecuted;
	}

	public Page getResultingPage() {
		return resultingPage;
	}

	public void setResultingPage(Page resultingPage) {
		this.resultingPage = resultingPage;
	}
}
