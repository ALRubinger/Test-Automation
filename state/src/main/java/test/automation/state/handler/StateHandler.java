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
package test.automation.state.handler;

/**
 * Provides a simple means to read/write state.
 * This is used when injecting values into a form for test cases.
 * The data may be changed on the fly.
 * @author Walter White
 */
public interface StateHandler {
	/**
	 * Reads an object from a given medium back into a POJO.
	 * @return the POJO representing the object's state.
	 * @throws Exception thrown if the object cannot be restored into a POJO.
	 */
	Object read() throws Exception;

	/**
	 * Write the object to the underlying provider.
	 * @param state the object state to store.
	 * @throws Exception thrown if the state cannot be stored.
	 */
	void write(Object state) throws Exception;
}
