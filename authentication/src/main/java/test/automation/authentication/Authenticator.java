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

import test.automation.model.http.User;

/**
 * Authenticates into the web application.
 * @author Walter White
 */
public interface Authenticator {

	/**
	 * Login to the web application.
	 * @return true if the user is logged in.
	 * @throws Exception thrown if we cannot login.
	 */
	boolean login() throws Exception;

	/**
	 * Logs out of the web application.
	 * @return true if the user is logged out.
	 * @throws Exception thrown if we cannot logout.
	 */
	boolean logout() throws Exception;

	/**
	 * Returns true if the user is currently logged in.
	 * @return true if the user is currently logged in, false otherwise.
	 */
	boolean isLoggedIn();

	/**
	 * The current user.
	 * @return the current user.
	 */
	User getUser();
}
