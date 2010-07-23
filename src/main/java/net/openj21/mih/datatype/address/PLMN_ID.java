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
 * The public land mobile network (PLMN) unique identifier. PLMN_ID consists of
 * Mobile Country Code (MCC) and Mobile Network Code (MNC). This is to represent
 * the access network identifier. Coding of PLMN_ID is defined in 3GPP TS
 * 25.413.
 */
@SEQUENCE
public class PLMN_ID {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 3)
	private byte[] plmn_id;

	public PLMN_ID() {
	}

	public PLMN_ID(byte[] plmn_id) {
		this.plmn_id = plmn_id;
	}

	public byte[] getPlmn_id() {
		return plmn_id;
	}

	public void setPlmn_id(byte[] plmn_id) {
		this.plmn_id = plmn_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PLMN_ID plmn_id1 = (PLMN_ID) o;

		if (!Arrays.equals(plmn_id, plmn_id1.plmn_id))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return plmn_id != null ? Arrays.hashCode(plmn_id) : 0;
	}

	@Override
	public String toString() {
		return "PLMN_ID{" + "plmnId=" + Arrays.toString(plmn_id) + '}';
	}
}
