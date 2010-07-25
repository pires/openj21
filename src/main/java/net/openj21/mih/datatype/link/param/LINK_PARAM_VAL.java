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
 * The current value of the parameter. The format of the media-dependent value
 * is defined in the respective media specification standard and the equivalent
 * number of bits (i.e., first bits) of this data type is used. In case that
 * there are remaining unused bits in the data type, these are marked as
 * all-zeros (‘0’).
 * 
 * Valid Range: 0..65535
 */
@SEQUENCE
public class LINK_PARAM_VAL {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 2)
	private Long value;

	public LINK_PARAM_VAL() {
	}

	public LINK_PARAM_VAL(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
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
		LINK_PARAM_VAL that = (LINK_PARAM_VAL) obj;
		if (value == null) {
			if (that.value != null)
				return false;
		} else if (!value.equals(that.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LINK_PARAM_VAL [value=" + value + "]";
	}
}