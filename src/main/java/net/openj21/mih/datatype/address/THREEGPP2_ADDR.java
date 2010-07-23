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
package net.openj21.mih.datatype.address;

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A data type to represent a 3GPP2 transport address.
 */
@CHOICE
public class THREEGPP2_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String threegpp2_addr;

	public THREEGPP2_ADDR() {
	}

	public THREEGPP2_ADDR(String threegpp2_addr) {
		this.threegpp2_addr = threegpp2_addr;
	}

	public String getThreegpp2_addr() {
		return threegpp2_addr;
	}

	public void setThreegpp2_addr(String threegpp2_addr) {
		this.threegpp2_addr = threegpp2_addr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		THREEGPP2_ADDR that = (THREEGPP2_ADDR) o;

		if (threegpp2_addr != null ? !threegpp2_addr
				.equals(that.threegpp2_addr) : that.threegpp2_addr != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return threegpp2_addr != null ? threegpp2_addr.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "THREEGPP2_ADDR{" + "threegpp2_addr='" + threegpp2_addr + '\''
				+ '}';
	}
}
