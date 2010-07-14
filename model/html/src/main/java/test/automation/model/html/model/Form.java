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
package test.automation.model.html.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

/**
 * @author Walter White
 */
@Immutable
@PrimaryKeyJoinColumn
@Entity
public class Form extends Element {
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "form")
	protected Set<Input> inputs;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "form")
	protected Set<Button> buttons;

	public Form() {
		super();
		setInputs(new HashSet<Input>());
		setButtons(new HashSet<Button>());
	}

	public Form(Page page, Element parent) {
		super(page, parent);
	}

	public Set<Button> getButtons() {
		return buttons;
	}

	public void setButtons(Set<Button> buttons) {
		this.buttons = buttons;
	}

	public Set<Input> getInputs() {
		return inputs;
	}

	public void setInputs(Set<Input> inputs) {
		this.inputs = inputs;
	}
}
