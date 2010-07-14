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
package test.automation.authentication;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.automation.model.http.User;
import test.automation.model.http.annotation.LoginField;
import test.automation.model.http.annotation.LoginForm;

/**
 * @author Walter White
 */
public abstract class AbstractAuthenticator implements Authenticator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAuthenticator.class);
	
	@Inject
	protected User user;
	@Inject
	protected WebClient webClient;
	@Inject
	protected Properties testProperties;
	@Inject
	protected HtmlPage currentPage;
	@Inject
	protected Event<User> userEvent;
	protected final URL loginUrl;
	protected boolean loggedIn;

	protected AbstractAuthenticator() throws MalformedURLException {
		loginUrl = new URL(System.getProperty("loginUrl"));
	}

	public boolean login() throws Exception {
		fetch();
		return (doLogin());
	}

	protected void fetch() throws IOException {
		currentPage = webClient.getPage(getLoginUrl());
	}

	public URL getLoginUrl() {
		return loginUrl;
	}

	public Properties getTestProperties() {
		return testProperties;
	}

	public WebClient getWebClient() {
		return webClient;
	}

	protected boolean doLogin() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
		final LoginForm loginForm = user.getClass().getAnnotation(LoginForm.class);
		setFields(loginForm);

		clickLoginButton(loginForm.loginButtonValue());
		final boolean isAuthenticated = isAuthenticated();
		if (isAuthenticated) {
			userEvent.select(new AnnotationLiteral<LoggedIn>(){}).fire(user);
		}

		return (isAuthenticated);
	}

	protected abstract boolean isAuthenticated();

	protected void setFields(final LoginForm loginForm) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		for (final LoginField loginField : loginForm.fields()) {
			setField(currentPage, loginField);
		}
	}

	protected void setField(final HtmlPage currentPage, final LoginField loginField) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		final HtmlInput htmlInput = (HtmlInput) currentPage.getElementByName(loginField.fieldName());

		final Field field = User.class.getDeclaredField(loginField.propertyName());
		final Object value = field.get(user);

		if (value != null) {
			htmlInput.setValueAttribute(value.toString());
		}
	}

	protected void clickLoginButton(final String loginButtonValue) throws IOException {
		for (final HtmlForm form : currentPage.getForms()) {
			final HtmlInput htmlInput = form.getInputByValue(loginButtonValue);
			currentPage = htmlInput.click();
		}

		throw (new IllegalStateException("The button was not found."));
	}

	public boolean isLoggedIn() {
		return (loggedIn);
	}

	public void setLoggedIn(final boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getUser() {
		return (user);
	}
}
