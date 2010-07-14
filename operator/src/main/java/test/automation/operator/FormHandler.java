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

import java.lang.reflect.Field;

/**
 * @author Walter White
 */
public interface FormHandler {

	/**
	 * Sets all of the fields in HTML from the given POJO populated with the data.
	 * @param formData the form data to set.
	 * @throws Exception thrown if the form cannot be set.
	 */
	void set(final Object formData) throws Exception;

	/**
	 * Sets a field from the given Java field object and the given form data.
	 * @param field the field in the POJO
	 * @param formData the POJO containing the data / values.
	 * @throws Exception thrown if the field cannot be set.
	 */
	void set(final Field field, final Object formData) throws Exception;

	/**
	 * Gets the value for the given field.
	 * @param field the field to get the value for.
	 * @param formData the form data.
	 * @return the value for the specified field.
	 */
	Object get(final Field field, final Object formData) throws Exception;

	/**
	 * The field to get the current value for.
	 * Returns the current value in the HTML form.
	 * @param field the field to get the value for.
	 * @return the current value in HTML.
	 */
	Object get(final Field field) throws Exception;

	/**
	 * Uses a property editor to convert the Object value into its textual representation.
	 * @param field the field to convert.
	 * @param value the value to convert.
	 * @return the textual representation of the field.
	 */
	String getValueAsText(final Field field, final Object value);

	Object getValueAsObject(final Field field, final String textValue);

	/**
	 * Reset the form back to the default values.
	 * @param formData
	 */
	void reset(final Object formData);

	/**
	 * Submits the given form.
	 * @param formData
	 */
	void submit(final Object formData);
}
