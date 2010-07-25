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
 * A type to represent some characteristic of packets in a QOS_LIST.
 */
@SEQUENCE
public class COS_PK_VALUE {
	@SEQUENCE_ELEMENT(order = 1)
	private COS_ID cosId;

	@SEQUENCE_ELEMENT(order = 2, basicType = true)
	@UNSIGNED_INT(size = 2)
	private Integer value;

	public COS_PK_VALUE() {
	}

	public COS_PK_VALUE(COS_ID cosId, Integer value) {
		this.cosId = cosId;
		this.value = value;
	}

	public COS_ID getCosId() {
		return cosId;
	}

	public void setCosId(COS_ID cosId) {
		this.cosId = cosId;
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

		COS_PK_VALUE that = (COS_PK_VALUE) o;

		if (cosId != null ? !cosId.equals(that.cosId) : that.cosId != null)
			return false;
		if (value != null ? !value.equals(that.value) : that.value != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = cosId != null ? cosId.hashCode() : 0;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "COS_PK_VALUE{" + "cosId=" + cosId + ", value=" + value + '}';
	}
}