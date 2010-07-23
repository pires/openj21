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

import java.util.Arrays;

import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Location Area Code (LAC) is a fixed length code (of 2 octets) identifying a
 * location area within a public land mobile network (PLMN). See 3GPP TS 23.003.
 */
@SEQUENCE
public class LAC {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 2)
	private byte[] locationAreaCode;

	public LAC() {
	}

	public LAC(byte[] locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	public byte[] getLocationAreaCode() {
		return locationAreaCode;
	}

	public void setLocationAreaCode(byte[] locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LAC lac = (LAC) o;

		if (!Arrays.equals(locationAreaCode, lac.locationAreaCode))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return locationAreaCode != null ? Arrays.hashCode(locationAreaCode) : 0;
	}

	public String toString() {
		return "LAC{" + "locationAreaCode=" + Arrays.toString(locationAreaCode)
				+ '}';
	}
}
