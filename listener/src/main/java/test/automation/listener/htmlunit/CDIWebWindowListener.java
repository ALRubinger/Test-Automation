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
package test.automation.listener.htmlunit;

import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import test.automation.listener.cdi.annotation.WindowChanged;
import test.automation.listener.cdi.annotation.WindowClosed;
import test.automation.listener.cdi.annotation.WindowOpened;
import test.automation.test.annotation.scope.ClientScoped;

/**
 * @author Walter White
 */
@ClientScoped
public class CDIWebWindowListener implements WebWindowListener {

	@Inject
	protected Event<WebWindowEvent> webWindowEventProducer;

	@Override
	public void webWindowOpened(final WebWindowEvent webWindowEvent) {
		webWindowEventProducer.select(new AnnotationLiteral<WindowOpened>(){}).fire(webWindowEvent);
	}

	@Override
	public void webWindowClosed(final WebWindowEvent webWindowEvent) {
		webWindowEventProducer.select(new AnnotationLiteral<WindowClosed>(){}).fire(webWindowEvent);
	}

	@Override
	public void webWindowContentChanged(final WebWindowEvent webWindowEvent) {
		webWindowEventProducer.select(new AnnotationLiteral<WindowChanged>(){}).fire(webWindowEvent);
	}
}
