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
 * Civic address elements, as described in IETF RFC 4776.
 */
@CHOICE
public class CIVIC_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String civicAddr;

	public CIVIC_ADDR() {
	}

	public CIVIC_ADDR(String civicAddr) {
		this.civicAddr = civicAddr;
	}

	public String getCivicAddr() {
		return civicAddr;
	}

	public void setCivicAddr(String civicAddr) {
		this.civicAddr = civicAddr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CIVIC_ADDR that = (CIVIC_ADDR) o;

		if (civicAddr != null ? !civicAddr.equals(that.civicAddr)
				: that.civicAddr != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return civicAddr != null ? civicAddr.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "CIVIC_ADDR{" + "civicAddr='" + civicAddr + '\'' + '}';
	}
}