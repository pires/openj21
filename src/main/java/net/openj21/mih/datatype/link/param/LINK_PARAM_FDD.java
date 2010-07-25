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
package net.openj21.mih.datatype.link.param;

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

/**
 * A type to represent a link parameter forUMTS. See 3GPP TS 25.215.
 * 
 * <pre>
 * 0: CPICH RSCP
 * 1: PCCPCH RSCP
 * 2: UTRA carrier RSSI
 * 3: GSM carrier RSSI
 * 4: CPICH Ec/No
 * 5: Transport channel BLER
 * 6: user equipment (UE) transmitted power
 * 7–255: (Reserved)
 * </pre>
 */
@SEQUENCE
public class LINK_PARAM_FDD {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private Integer value;

	public LINK_PARAM_FDD() {
	}

	public LINK_PARAM_FDD(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LINK_PARAM_FDD that = (LINK_PARAM_FDD) obj;
		if (value == null) {
			if (that.value != null)
				return false;
		} else if (!value.equals(that.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LINK_PARAM_FDD [value=" + value + "]";
	}
}
