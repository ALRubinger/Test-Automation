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
package test.automation.operator;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.hibernate.Session;

import test.automation.model.history.model.NavigationEvent;
import test.automation.model.history.model.Execution;

import test.automation.model.html.model.Page;

/**
 * @author Walter White
 */
@ApplicationScoped
public class ExecutionListener {

	@Inject
	protected Session session;
	@Inject
	protected Execution execution;
	@Inject
	protected HtmlPage currentPage;
	@Inject
	protected Page page;

	//@Transactional
	public void onExecution(@Observes NavigationEvent navigationEvent) {
		session.save(navigationEvent);
	}
}
