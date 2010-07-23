/*
 *  OpenJ21 Copyright (C) 2010 Paulo Pires
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
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