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
import java.io.File;
import test.automation.state.handler.AbstractStateHandler;

/**
 * Object state is stored in a file.
 * @author Walter White
 */
public abstract class AbstractFileBasedStateHandler extends AbstractStateHandler{

	protected File getTestResourceFile() {
		final Extension extension = getClass().getAnnotation(Extension.class);

		for (int index = 0; index < extension.value().length; index++) {
			final File resource = new File(resourcesDirectoryFile.getAbsolutePath() + extension.value()[index]);
			if (resource.exists()) {
				return (resource);
			}
		}

		throw (new IllegalStateException("Unable to find resource."));
	}
}
