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

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.apache.commons.beanutils.BeanUtilsBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.automation.model.history.model.Execution;
import test.automation.model.history.model.NavigationEvent;
import test.automation.model.history.model.TypeEvent;
import test.automation.model.html.model.Element;
import test.automation.model.html.model.Page;

/**
 * @author Walter White
 */
public class AbstractReflectionFormHandler implements FormHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReflectionFormHandler.class);
	
	@Inject
	protected Event<NavigationEvent> navigationEvent;
	@Inject
	protected Map<Class, PropertyEditorSupport> propertyEditors;
	@Inject
	protected Execution execution;
	@Inject
	protected HtmlPage htmlPage;
	@Inject
	protected Page page;
	@Inject
	protected Operator operator;

	/**
	 * Sets the HTML Form from values contained in this object.
	 * @param formData
	 * @throws Exception
	 */
	public final void set(final Object formData) throws Exception {
		for (final Field field : formData.getClass().getDeclaredFields()) {
			set(field, formData);
		}
	}

	protected HtmlElement getHtmlField(final Field field) {
		return (htmlPage.getElementById(field.getName()));
	}

	/**
	 * With the given search criteria, attempt to set all of the fields in the HTML form.
	 * @param formData
	 * @param field
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void set(final Field field, final Object formData) throws Exception {
		LOGGER.trace("currentPage:" + htmlPage);
		LOGGER.trace("field:" + field);

		LOGGER.trace("getting field:" + field.getName());
		final HtmlElement htmlElement = getHtmlField(field);

		if (htmlElement != null) {
			final Object value = get(field, formData);
			if (value != null) {

				final String textValue = getValueAsText(field, value);
				if (htmlElement instanceof HtmlSelect) {
					operator.select((HtmlSelect) htmlElement, textValue);
				} else {
					operator.type((HtmlInput) htmlElement, textValue);
				}

				// at this point, we can now test if we see that the field did change in the UI.
				//events.raiseEvent(FIELD_CHANGED_EVENT, currentPage, formData, field, value);
				Element element = null;
				navigationEvent.fire(new TypeEvent(execution, page, element, textValue));
			}
		}
	}

	/**
	 * Gets the current value for the specified field with the given form data.
	 * @param formData
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public Object get(final Field field, final Object formData) throws Exception {
		return (BeanUtilsBean.getInstance().getPropertyUtils().getNestedProperty(formData, field.getName()));
	}

	public Object get(final Field field) throws Exception {
		final HtmlElement htmlElement = getHtmlField(field);

		if (htmlElement instanceof HtmlInput) {
			final HtmlInput htmlInput = (HtmlInput) htmlElement;
			return (getValueAsObject(field, htmlInput.getValueAttribute()));
		}
		if (htmlElement instanceof HtmlSelect) {
			final HtmlSelect htmlSelect = (HtmlSelect) htmlElement;
			final List<HtmlOption> selectedOptions = htmlSelect.getSelectedOptions();
			if (selectedOptions.size() == 1) {
				return (getValueAsObject(field, selectedOptions.get(0).getValueAttribute()));
			}
		}

		return (null);
	}

	/**
	 * Useful for converting a date into a string representation, or serializing an entity, etc..
	 * @param field the field in the POJO.
	 * @param entity the entity holding the POJO's state.
	 * @return
	 */
	public String getValueAsText(final Field field, final Object value) {
		// convert the Object into a string representation using a property editor.
		if (propertyEditors != null) {
			final PropertyEditorSupport propertyEditor = propertyEditors.get(field.getType());
			if (propertyEditor != null) {
				propertyEditor.setValue(value);
				return (propertyEditor.getAsText());
			}
		}

		return (value.toString());
	}

	public Object getValueAsObject(final Field field, final String textValue) {
		// convert the Object into a string representation using a property editor.
		if (propertyEditors != null) {
			final PropertyEditorSupport propertyEditor = propertyEditors.get(field.getType());
			if (propertyEditor != null) {
				propertyEditor.setAsText(textValue);
				return (propertyEditor.getValue());
			}
		}

		return (textValue);
	}

	@Override
	public void reset(Object formData) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void submit(Object formData) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
