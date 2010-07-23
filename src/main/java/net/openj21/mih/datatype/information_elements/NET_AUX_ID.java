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
package net.openj21.mih.datatype.information_elements;

import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A type to represent an auxiliary access network identifier. This is HESSID if
 * network type is IEEE 802.11.
 */
@SEQUENCE
public class NET_AUX_ID {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = 253)
	private String netAuxId;

	public NET_AUX_ID() {
	}

	public NET_AUX_ID(String netAuxId) {
		this.netAuxId = netAuxId;
	}

	public String getNetAuxId() {
		return netAuxId;
	}

	public void setNetAuxId(String netAuxId) {
		this.netAuxId = netAuxId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		NET_AUX_ID that = (NET_AUX_ID) o;

		if (netAuxId != null ? !netAuxId.equals(that.netAuxId)
				: that.netAuxId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return netAuxId != null ? netAuxId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "NET_AUX_ID{" + "netAuxId='" + netAuxId + '\'' + '}';
	}
}
