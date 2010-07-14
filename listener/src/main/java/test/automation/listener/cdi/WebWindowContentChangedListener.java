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
package test.automation.listener.cdi;

import com.gargoylesoftware.htmlunit.WebWindowEvent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import test.automation.listener.cdi.annotation.WindowChanged;
import test.automation.test.annotation.scope.ClientScoped;

/**
 * @author Walter White
 */
@ClientScoped
public class WebWindowContentChangedListener {

	@Inject
	protected HttpLoggingService httpLoggingService;
	@Inject
	protected HtmlMarkingService htmlMarkingService;
	@Inject
	protected HtmlLoggingService htmlLoggingService;

	public void onWebWindowContentChanged(@Observes @WindowChanged final WebWindowEvent webWindowEvent)
	{
		httpLoggingService.log(webWindowEvent);
		htmlMarkingService.mark(webWindowEvent);
		htmlLoggingService.log(webWindowEvent);
	}
}
