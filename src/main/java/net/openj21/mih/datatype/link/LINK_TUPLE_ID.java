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

import net.openj21.mih.datatype.address.LINK_ADDR;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * The identifier of a link that is associated with a PoA. The LINK_ID contains
 * the MN LINK_ADDR. The optional LINK_ADDR contains a link address of PoA.
 * 
 */
@SEQUENCE
public class LINK_TUPLE_ID {
	@SEQUENCE_ELEMENT(order = 1)
	private LINK_ID linkId;

	@SEQUENCE_ELEMENT(order = 2, choice = true)
	private LINK_ADDR linkAddr;

	public LINK_TUPLE_ID() {
	}

	public LINK_TUPLE_ID(LINK_ID link_id, LINK_ADDR link_addr) {
		this.linkId = link_id;
		this.linkAddr = link_addr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LINK_TUPLE_ID that = (LINK_TUPLE_ID) o;

		if (linkId != null ? !linkId.equals(that.linkId)
				: that.linkId != null)
			return false;
		
		if (linkAddr != null ? !linkAddr.equals(that.linkAddr)
				: that.linkAddr != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = linkId != null ? linkId.hashCode() : 0;
		result = 31 * result + (linkAddr != null ? linkAddr.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "LINK_TUPLE_ID{" + 
				"link_id=" + linkId + 
				", link_addr=" + linkAddr +
				'}';
	}
}