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

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.operator.Operator;
import test.automation.test.annotation.FormData;
import test.automation.test.annotation.TabularData;
import test.automation.util.FormUtil;
import test.automation.util.TableUtil;

/**
 * @author Walter White
 */
public abstract class AbstractEdit extends AbstractAdd {

	private static Logger LOGGER = LoggerFactory.getLogger(AbstractEdit.class);
	/**
	 * These properties are configured via a properties file.
	 * componentName.canSave.
	 */
	protected boolean canSave;
	/**
	 * componentName.canEdit.
	 */
	protected boolean canUpdate;
	/**
	 * componentName.canDelete
	 */
	protected boolean canDelete;

	@Inject
	protected Operator operator;
	@Inject
	protected HtmlPage htmlPage;

	public void save() throws Exception {
		if (canSave) {
			final HtmlInput saveButton = (HtmlInput)htmlPage.getElementByName("Save");
			operator.click(saveButton);
		}

		throw (new UnsupportedOperationException("Saving is not supported."));
	}

	public void update() {
		if (canUpdate) {
		}

		throw (new UnsupportedOperationException("Updating is not supported."));
	}

	public void delete() {
		if (canDelete) {
		}

		throw (new UnsupportedOperationException("Deleting is not supported."));
	}

	/*
	public final void runTestOnRow(final Method wrappedMethod) throws Exception {

		final HtmlPage originalPage = currentPage;

		edit();
		checkValues();

		testDefinition.getTestMethod().invoke(this);

		back(originalPage);
	}
	 */

	/*
	//@Observer(AbstractForm.BACK_EVENT)
	public void onBack(final HtmlPage originalPage, final HtmlPage currentPage) {
		// make sure the tables match
		// @TODO, since we updated a field, we need to know what the mapping is so we can assert it was indeed changed.
		//TableUtil.equals(SearchResultUtil.getTable(getCurrentPage()), SearchResultUtil.getTable(originalPage));
	}
	 */

	/*
	private final void checkValues() {
		final String[] tabularValues = getTabularValues();
		final String[] formValues = getFormValues();

		if (tabularValues == null) {
			throw (new IllegalArgumentException("Please configure tabular data correctly: no columns parsed."));
		}
		if (formValues == null) {
			throw (new IllegalArgumentException("Please configure form data correctly: no fields parsed."));
		}
		if (tabularValues.length != formValues.length) {
			throw (new IllegalArgumentException("Please configure tabular data to match form data: columns don't match the number of fields."));
		}

		final Set<Integer> invalidIndeces = new HashSet<Integer>();
		for (int index = 0; index < tabularValues.length; index++) {
			if (!tabularValues[index].equals(formValues[index])) {
				invalidIndeces.add(index);
			}
		}

		if (invalidIndeces.size() > 0) {
			for (final Integer index : invalidIndeces) {
				LOGGER.error("data does not match:" + tabularValues[index] + "->" + formValues[index]);
			}

			Assert.fail("Tabular data doesn't match form data.");
		}
	 */

	/**
	 * Implemented by the subclass, depending on the type of test, we may have to click a link or click a checkbox and then click the edit button.
	 * @throws IOException
	 */
	//protected abstract void edit() throws Exception;

	/**
	 * Gets the values from the table.
	 * @return
	 */
	/*
	protected String[] getTabularValues() {
		final TabularData tabularData = getClass().getAnnotation(TabularData.class);
		final String[] tabularValues = new String[tabularData.value().length];

		int index = 0;
		for (final int columnIndex : tabularData.value()) {
			tabularValues[index++] = TableUtil.getCellText(currentRow, columnIndex);
		}

		return (tabularValues);
	}
	 */

	/**
	 * Gets the values from the form.
	 * @return
	 */
	/*
	protected String[] getFormValues() {
		final FormData formDataAnnotation = getClass().getAnnotation(FormData.class);
		final String[] formValues = new String[formDataAnnotation.value().length];

		int index = 0;
		for (final String formField : formDataAnnotation.value()) {
			if (!FormUtil.isXPath(formField)) {
				//@TODO:implement this ...
				formValues[index++] = null;//formHandler.getFieldValue(currentPage, formField);
			} else {
				formValues[index++] = FormUtil.getValueByXPath(currentPage, formField);
			}
		}

		return (formValues);
	}
	 */

	/*
	@Override
	protected void save(boolean successful) throws Exception {
		clickSaveButton();
		if (successful) {
			savedSuccessfully(currentPage);
		}
	}
	 */

	/**
	 * Changes the values and navigates away, asserts that we received an alert.
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	/*
	private final void pageIsDirty() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// randomly navigate away
		//DirtyPageFlagAssertion.assertPageDirty(currentPage);
		//DirtyPageFlagAssertion.assertWarned(currentPage);
	}
	 */

	/**
	 * Updates the command to the values specified, these values be unique and will be tested on *every* row.
	 */
	//protected abstract void updateFormValues();
}
