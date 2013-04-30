/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.openj21.mih.datatype.location;

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A type to represent an XML-formatted civic location. Civic address elements,
 * as described in IETF RFC 4119.
 */
@CHOICE
public class XML_CIVIC_LOC extends CIVIC_LOC {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String xmlCivicLoc;

	public XML_CIVIC_LOC() {
	}

	public XML_CIVIC_LOC(String xmlCivicLoc) {
		this.xmlCivicLoc = xmlCivicLoc;
	}

	public String getXmlCivicLoc() {
		return xmlCivicLoc;
	}

	public void setXmlCivicLoc(String xmlCivicLoc) {
		this.xmlCivicLoc = xmlCivicLoc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		XML_CIVIC_LOC that = (XML_CIVIC_LOC) o;

		if (xmlCivicLoc != null ? !xmlCivicLoc.equals(that.xmlCivicLoc)
				: that.xmlCivicLoc != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return xmlCivicLoc != null ? xmlCivicLoc.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "XML_CIVIC_LOC{" + "xmlCivicLoc='" + xmlCivicLoc + '\'' + '}';
	}
}