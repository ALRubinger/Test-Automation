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

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import java.io.IOException;
import java.net.URL;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.model.history.model.ClickEvent;
import test.automation.model.history.model.Execution;
import test.automation.model.history.model.NavigationEvent;
import test.automation.model.history.model.OpenEvent;
import test.automation.model.history.model.TypeEvent;
import test.automation.model.html.model.Page;

import test.automation.model.test.model.configuration.Client;

/**
 * @author Walter White
 */
public abstract class AbstractOperator implements Operator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractOperator.class);
	
	@Inject
	protected WebClient webClient;
	@Inject
	protected Client client;
	@Inject
	protected Event<NavigationEvent> navigationEvent;
	@Inject
	protected Execution execution;
	@Inject
	protected Page page;
	protected test.automation.model.html.model.Element element;
	@Inject
	protected HtmlPage htmlPage;
	/**
	 * How fast the user types characters.
	 */
	protected int charactersPerMinute;
	/**
	 * Computed from the charactersPerMinute configuration.
	 */
	protected transient int typeDelay;

	public void click(final HtmlElement htmlElement) throws Exception {
		delay();

		final HtmlPage originalPage = htmlPage;
		//events.raiseEvent(PRE_CLICK_EVENT, htmlElement, originalPage);
		htmlPage = htmlElement.click();

		//events.raiseEvent(POST_CLICK_EVENT, htmlElement, originalPage, currentPage);
		navigationEvent.fire(new ClickEvent(execution, page, element));
	}

	protected void delay() {
		delay(client.getDelay());
	}

	protected void delay(int delay) {
		if (delay > 0) {
			LOGGER.trace("waiting:" + delay + " milliseconds.");
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				LOGGER.error("unable to sleep for:" + delay + " milliseconds.");
			}
		}
	}

	public void get() throws Exception {
		get(webClient.getHomePage());
	}

	public void get(final String url) throws Exception {
		LOGGER.trace("going to:" + url);
		delay(client.getDelay());

		htmlPage = webClient.getPage(url);
		navigationEvent.fire(new OpenEvent(execution, page, new URL(url)));
	}

	@Override
	public void select(final HtmlSelect htmlSelect, final String value) {
		htmlSelect.getOptionByValue(value);
	}

	@Override
	public void type(final HtmlInput htmlInput, final String value) throws IOException{
		computeTypeDelay();
		//events.raiseEvent(PRE_TYPE_EVENT, htmlInput, value);
		typeAll(htmlInput, value);
		//events.raiseEvent(POST_TYPE_EVENT, htmlInput, value);
		navigationEvent.fire(new TypeEvent(execution, page, element, value));
	}

	private void typeAll(final HtmlInput htmlInput, final String value) throws IOException {
		htmlInput.focus();

		for (final char character : value.toCharArray()) {
			htmlInput.type(character);
			delay(typeDelay);
		}
	}

	/**
	 * Computes the time delay it takes to type each character.
	 */
	protected void computeTypeDelay() {
		typeDelay = 60 * 1000 / charactersPerMinute;
	}
}
