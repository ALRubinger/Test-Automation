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
package test.automation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Walter White
 */
@Entity
public class CrawlJob extends AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	protected Date dateStarted;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = false)
	protected Date dateEnded;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "crawlJob")
	protected List<Page> pagesCrawled;
	//protected Page currentPage;

	public CrawlJob() {
		super();
		setDateStarted(new Date());
		setPagesCrawled(new ArrayList<Page>());
	}

	public Date getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(Date dateEnded) {
		this.dateEnded = dateEnded;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public List<Page> getPagesCrawled() {
		return pagesCrawled;
	}

	public void setPagesCrawled(List<Page> pagesCrawled) {
		this.pagesCrawled = pagesCrawled;
	}
}
