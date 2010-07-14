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
package test.automation.state.handler.file;

import test.automation.state.annotation.Extension;
import java.io.FileNotFoundException;
import javax.enterprise.context.ApplicationScoped;

/**
 * Reads an object's state from an XML file.
 * @author Walter White
 */
@Extension("xml")
@ApplicationScoped
public class XMLStateHandler extends AbstractFileBasedStateHandler {

	@Override
	public Object read() throws FileNotFoundException {
		throw(new UnsupportedOperationException("not yet supported"));
	}

	@Override
	public void write(Object state) throws FileNotFoundException {
		throw(new UnsupportedOperationException("not yet supported"));
	}
}
