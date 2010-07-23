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
package net.openj21.mih.datatype.link;

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

/**
 * Represents the link type. Number assignments:
 * 
 * <pre>
 * 0: Reserved
 * 1: Wireless - GSM
 * 2: Wireless - GPRS
 * 3: Wireless - EDGE
 * 15: Ethernet
 * 18: Wireless - Other
 * 19: Wireless - IEEE 802.11
 * 22: Wireless - CDMA2000
 * 23: Wireless - UMTS
 * 24: Wireless - cdma2000-HRPD
 * 27: Wireless - IEEE 802.16
 * 28: Wireless - IEEE 802.20
 * 29: Wireless - IEEE 802.22
 * </pre>
 */
@SEQUENCE
public class LINK_TYPE {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private Integer value;

	public LINK_TYPE() {
	}

	public LINK_TYPE(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LINK_TYPE that = (LINK_TYPE) o;

		if (value != null ? !value.equals(that.value)
				: that.value != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "LINK_TYPE{" + "value=" + value + '}';
	}
}
