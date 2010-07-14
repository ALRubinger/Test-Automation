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
package test.automation.test.cases;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

import test.automation.model.test.enumeration.TestType;
import test.automation.test.AbstractEdit;
import test.automation.test.annotation.TestCase;

import test.automation.test.annotation.assertion.HasChanges;
import test.automation.test.annotation.assertion.Saved;
import test.automation.test.annotation.event.After;
import test.automation.test.annotation.scope.TestCaseScoped;
import test.automation.test.event.SaveEvent;

/**
 * This is an example test case that is implemented by the provider.
 * It depends entirely on the specifications as this is a mapping or flowchart of how the application is supposed to work.
 * @author Walter White
 */
@TestCaseScoped
public class DefaultEditTestCase {

	protected final AbstractEdit edit;
	@Inject
	protected Object originalState;

	protected DefaultEditTestCase(final AbstractEdit edit) {
		this.edit = edit;
	}

	//final Object originalState = BeanUtils.cloneBean(edit.getFormData());
	/**
	 * save, check for success message.
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Saved
	@TestCase(type = TestType.Positive)
	public void saveNoChanges(@Observes @After SaveEvent saveEvent) throws Exception {
	}

	/**
	 * edit value, save, check for success message.
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Saved
	@HasChanges
	@TestCase(type = TestType.Positive)
	public void saveChanges(@Observes @After SaveEvent saveEvent) throws Exception {
		//edit.updateFormValues();
	}

	/**
	 * change value back, save, check for success message.
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Saved
	@HasChanges
	@TestCase(type = TestType.Positive)
	public void saveOriginalValues(@Observes @After SaveEvent saveEvent) throws Exception {
		// copy the original state to the form data.
		BeanUtils.copyProperties(edit.getFormData(), originalState);
	}
}
