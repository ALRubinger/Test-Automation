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
package test.automation.comparator.table.impl;

import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import test.automation.comparator.table.TableRowComparator;

/**
 * @author Walter White
 */
public class DefaultTableRowComparator implements TableRowComparator{

	@Override
	public int compare(HtmlTableRow a, HtmlTableRow b) {
		if(a.getCells().size() != b.getCells().size())
			return(a.getCells().size() - b.getCells().size());
		
		int result = 0;
		for(int column = 0;column < a.getCells().size();column++)
		{
			
		}
	}
}
