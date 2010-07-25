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
package net.openj21.mih.datatype.ipconfig;

import net.openj21.mih.datatype.basic.BOOLEAN;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Indicates whether MN’s IP address needs to be changed or not.
 * 
 * TRUE: Change required. FALSE: Change not required.
 */
@SEQUENCE
public class IP_RENEWAL_FLAG {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@BOOLEAN
	private Boolean value;

	public IP_RENEWAL_FLAG() {
	}

	public IP_RENEWAL_FLAG(Boolean value) {
		super();
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
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
		IP_RENEWAL_FLAG that = (IP_RENEWAL_FLAG) obj;
		if (value == null) {
			if (that.value != null)
				return false;
		} else if (!value.equals(that.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IP_RENEWAL_FLAG [value=" + value + "]";
	}
}
