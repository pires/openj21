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
 * Represents the reason of a link going down.
 */
@SEQUENCE
public class LINK_GD_REASON {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private LINK_GD_REASON_ENUM value;

	public LINK_GD_REASON() {
	}

	public LINK_GD_REASON(LINK_GD_REASON_ENUM value) {
		this.value = value;
	}

	public LINK_GD_REASON_ENUM getValue() {
		return value;
	}

	public void setValue(LINK_GD_REASON_ENUM value) {
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
		LINK_GD_REASON that = (LINK_GD_REASON) obj;
		if (value != that.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LINK_GD_REASON [value=" + value + "]";
	}
}