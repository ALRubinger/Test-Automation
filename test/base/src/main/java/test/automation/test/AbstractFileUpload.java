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
package test.automation.test;

import com.gargoylesoftware.htmlunit.WebClient;
import java.io.File;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.model.test.model.configuration.Client;
import test.automation.model.test.model.test.TestDefinition;
import test.automation.test.annotation.event.After;
import test.automation.test.annotation.event.Before;
import test.automation.test.event.FileUploadEvent;

/**
 * @author Walter White
 */
public abstract class AbstractFileUpload extends AbstractHtmlTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFileUpload.class);
	private static final String UPLOAD_FILE = "upload";
	private final File uploadFile;
	@Inject
	protected Event<FileUploadEvent> fileUploadEvent;

	public AbstractFileUpload(final TestDefinition testDefinition, final WebClient webClient, final Client client) {
		super();
		this.uploadFile = new File(resourcesDirectoryFile.getAbsolutePath() + File.separator + UPLOAD_FILE);
	}

	protected void upload() {

		fileUploadEvent.select(new AnnotationLiteral<Before>() {
		}).fire(new FileUploadEvent(uploadFile));
		// upload the file
		fileUploadEvent.select(new AnnotationLiteral<After>() {
		}).fire(new FileUploadEvent(uploadFile));
	}

	public File getUploadFile() {
		return uploadFile;
	}
}
