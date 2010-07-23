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
 * The identifier of a link that is not associated with the peer node. The
 * LINK_ADDR contains the address of this link.
 */
@SEQUENCE
public class LINK_ID {
	@SEQUENCE_ELEMENT(order = 1)
	private LINK_TYPE linkType;
	
	@SEQUENCE_ELEMENT(order = 2)
	private LINK_ADDR linkAddr;

	public LINK_ID() {
	}

	public LINK_ID(LINK_TYPE link_type, LINK_ADDR link_addr) {
		this.linkType = link_type;
		this.linkAddr = link_addr;
	}
	
	public LINK_TYPE getLink_type() {
		return linkType;
	}

	public void setLink_type(LINK_TYPE link_type) {
		this.linkType = link_type;
	}

	public LINK_ADDR getLink_addr() {
		return linkAddr;
	}

	public void setLink_addr(LINK_ADDR link_addr) {
		this.linkAddr = link_addr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LINK_ID that = (LINK_ID) o;

		if (linkType != null ? !linkType.equals(that.linkType)
				: that.linkType != null)
			return false;
		
		if (linkAddr != null ? !linkAddr.equals(that.linkAddr)
				: that.linkAddr != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = linkType != null ? linkType.hashCode() : 0;
		result = 31 * result + (linkAddr != null ? linkAddr.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "LINK_ID{" +
				"link_type=" + linkType +
				",link_addr=" + linkAddr +
				'}';
	}
}