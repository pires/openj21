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
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.general.OCTET_STRING;

/**
 * A data type to represent a 3GPP transport address.
 */
@CHOICE
public class THREEGPP_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String threegppAddr;

	public THREEGPP_ADDR() {
	}

	public THREEGPP_ADDR(String threegppAddr) {
		this.threegppAddr = threegppAddr;
	}

	public String getThreegppAddr() {
		return threegppAddr;
	}

	public void setThreegppAddr(String threegppAddr) {
		this.threegppAddr = threegppAddr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		THREEGPP_ADDR that = (THREEGPP_ADDR) o;

		if (threegppAddr != null ? !threegppAddr.equals(that.threegppAddr)
				: that.threegppAddr != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return threegppAddr != null ? threegppAddr.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "THREEGPP_ADDR{" + "threegppAddr='" + threegppAddr + '\'' + '}';
	}
}
