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
 * A data type to represent a link-layer address other than the address already
 * defined. For example, SSID.
 */
@CHOICE
public class OTHER_L2_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String otherL2Address;

	public OTHER_L2_ADDR() {
	}

	public OTHER_L2_ADDR(String otherL2Address) {
		this.otherL2Address = otherL2Address;
	}

	public String getOtherL2Address() {
		return otherL2Address;
	}

	public void setOtherL2Address(String otherL2Address) {
		this.otherL2Address = otherL2Address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OTHER_L2_ADDR that = (OTHER_L2_ADDR) o;

		if (otherL2Address != null ? !otherL2Address
				.equals(that.otherL2Address) : that.otherL2Address != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return otherL2Address != null ? otherL2Address.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "OTHER_L2_ADDR{" + "otherL2Address='" + otherL2Address + '\''
				+ '}';
	}
}
