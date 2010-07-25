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
package net.openj21.mih.datatype.qos;

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

/**
 * A type to represent a class of service identifier. Valid Range: 0–255.
 */
@SEQUENCE
public class COS_ID {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private Integer cosId;

	public COS_ID() {
	}

	public COS_ID(Integer cosId) {
		this.cosId = cosId;
	}

	public Integer getCosId() {
		return cosId;
	}

	public void setCosId(Integer cosId) {
		this.cosId = cosId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		COS_ID that = (COS_ID) o;

		if (cosId != null ? !cosId.equals(that.cosId) : that.cosId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return cosId != null ? cosId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "COS_ID{" + "cosId=" + cosId + '}';
	}
}