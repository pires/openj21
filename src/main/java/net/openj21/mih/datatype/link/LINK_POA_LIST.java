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

import java.util.List;

import net.openj21.mih.datatype.address.LINK_ADDR;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A list of PoAs for a particular link. The LIST(LINK_ADDR) is a list of PoA
 * link addresses and is sorted from most preferred first to least preferred
 * last.
 */
@SEQUENCE
public class LINK_POA_LIST {
	@SEQUENCE_ELEMENT(order = 1)
	private LINK_ID linkIdentifier;

	@SEQUENCE_ELEMENT(order = 2)
	private List<LINK_ADDR> linkAddressList;

	public LINK_POA_LIST() {
	}

	public LINK_POA_LIST(LINK_ID linkIdentifier, List<LINK_ADDR> linkAddressList) {
		this.linkIdentifier = linkIdentifier;
		this.linkAddressList = linkAddressList;
	}

	public LINK_ID getLinkIdentifier() {
		return linkIdentifier;
	}

	public void setLinkIdentifier(LINK_ID linkIdentifier) {
		this.linkIdentifier = linkIdentifier;
	}

	public List<LINK_ADDR> getLinkAddressList() {
		return linkAddressList;
	}

	public void setLinkAddressList(List<LINK_ADDR> linkAddressList) {
		this.linkAddressList = linkAddressList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((linkAddressList == null) ? 0 : linkAddressList.hashCode());
		result = prime * result
				+ ((linkIdentifier == null) ? 0 : linkIdentifier.hashCode());
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
		LINK_POA_LIST that = (LINK_POA_LIST) obj;
		if (linkAddressList == null) {
			if (that.linkAddressList != null)
				return false;
		} else if (!linkAddressList.equals(that.linkAddressList))
			return false;
		if (linkIdentifier == null) {
			if (that.linkIdentifier != null)
				return false;
		} else if (!linkIdentifier.equals(that.linkIdentifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LINK_POA_LIST [linkIdentifier=" + linkIdentifier
				+ ", linkAddressList=" + linkAddressList + "]";
	}
}