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
package test.automation.model.validation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Immutable;

import test.automation.model.AbstractEntity;

import test.automation.model.html.model.WebResource;
import test.automation.model.validation.enumeration.ErrorType;

/**
 * @author Walter White
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Immutable
@Entity
public class ValidationError extends AbstractEntity {

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected WebResource webResource;
	@Lob
	@Column(nullable = false, updatable = false)
	protected String message;
	@Enumerated
	@Column(nullable = false, updatable = false)
	protected ErrorType errorType;
	@Lob
	@Column(nullable = false, updatable = false)
	protected String error;
	@Column(nullable = false, updatable = false)
	protected int lineNumber;
	@Column(nullable = false, updatable = false)
	protected int columnNumber;

	public ValidationError() {
		super();
	}

	public ValidationError(WebResource webResource, final String message, final ErrorType errorType, final String error, final int lineNumber, final int columnNumber) {
		this();
		
		setWebResource(webResource);
		setMessage(message);
		setErrorType(errorType);
		setError(error);
		setLineNumber(lineNumber);
		setColumnNumber(columnNumber);
	}

	public WebResource getWebResource() {
		return webResource;
	}

	public void setWebResource(WebResource webResource) {
		this.webResource = webResource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType validationLevel) {
		this.errorType = validationLevel;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
