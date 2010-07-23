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
package net.openj21.mih.datatype.capabilities;

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;
import net.openj21.mih.datatype.information_elements.NETWORK_ID;
import net.openj21.mih.datatype.information_elements.NET_AUX_ID;
import net.openj21.mih.datatype.information_elements.NET_CAPS;
import net.openj21.mih.datatype.link.LINK_DATA_RATE;
import net.openj21.mih.datatype.link.LINK_MIHCAP_FLAGS;
import net.openj21.mih.datatype.link.LINK_TUPLE_ID;
import net.openj21.mih.datatype.link.SIG_STRENGTH;

@SEQUENCE
public class LINK_DET_INFO {
	@SEQUENCE_ELEMENT(order = 1)
	private LINK_TUPLE_ID linkTupleId;

	@SEQUENCE_ELEMENT(order = 2)
	private NETWORK_ID networkId;

	@SEQUENCE_ELEMENT(order = 3)
	private NET_AUX_ID netAuxId;

	@SEQUENCE_ELEMENT(order = 4)
	private SIG_STRENGTH sigStrength;

	@SEQUENCE_ELEMENT(order = 5, basicType = true)
	@UNSIGNED_INT(size = 2)
	private Integer sinr;

	@SEQUENCE_ELEMENT(order = 6)
	private LINK_DATA_RATE linkDataRate;

	@SEQUENCE_ELEMENT(order = 7)
	private LINK_MIHCAP_FLAGS linkMihCapFlags;

	@SEQUENCE_ELEMENT(order = 8)
	private NET_CAPS netCaps;

	public LINK_DET_INFO() {
	}

	public LINK_DET_INFO(LINK_TUPLE_ID linkTupleId, NETWORK_ID networkId,
			NET_AUX_ID netAuxId, SIG_STRENGTH sigStrength, Integer sinr,
			LINK_DATA_RATE linkDataRate, LINK_MIHCAP_FLAGS linkMihCapFlags,
			NET_CAPS netCaps) {
		this.linkTupleId = linkTupleId;
		this.networkId = networkId;
		this.netAuxId = netAuxId;
		this.sigStrength = sigStrength;
		this.sinr = sinr;
		this.linkDataRate = linkDataRate;
		this.linkMihCapFlags = linkMihCapFlags;
		this.netCaps = netCaps;
	}

	public LINK_TUPLE_ID getLinkTupleId() {
		return linkTupleId;
	}

	public void setLinkTupleId(LINK_TUPLE_ID linkTupleId) {
		this.linkTupleId = linkTupleId;
	}

	public NETWORK_ID getNetworkId() {
		return networkId;
	}

	public void setNetworkId(NETWORK_ID networkId) {
		this.networkId = networkId;
	}

	public NET_AUX_ID getNetAuxId() {
		return netAuxId;
	}

	public void setNetAuxId(NET_AUX_ID netAuxId) {
		this.netAuxId = netAuxId;
	}

	public SIG_STRENGTH getSigStrength() {
		return sigStrength;
	}

	public void setSigStrength(SIG_STRENGTH sigStrength) {
		this.sigStrength = sigStrength;
	}

	public Integer getSinr() {
		return sinr;
	}

	public void setSinr(Integer sinr) {
		this.sinr = sinr;
	}

	public LINK_DATA_RATE getLinkDataRate() {
		return linkDataRate;
	}

	public void setLinkDataRate(LINK_DATA_RATE linkDataRate) {
		this.linkDataRate = linkDataRate;
	}

	public LINK_MIHCAP_FLAGS getLinkMihCapFlags() {
		return linkMihCapFlags;
	}

	public void setLinkMihCapFlags(LINK_MIHCAP_FLAGS linkMihCapFlags) {
		this.linkMihCapFlags = linkMihCapFlags;
	}

	public NET_CAPS getNetCaps() {
		return netCaps;
	}

	public void setNetCaps(NET_CAPS netCaps) {
		this.netCaps = netCaps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((linkDataRate == null) ? 0 : linkDataRate.hashCode());
		result = prime * result
				+ ((linkMihCapFlags == null) ? 0 : linkMihCapFlags.hashCode());
		result = prime * result
				+ ((linkTupleId == null) ? 0 : linkTupleId.hashCode());
		result = prime * result
				+ ((netAuxId == null) ? 0 : netAuxId.hashCode());
		result = prime * result + ((netCaps == null) ? 0 : netCaps.hashCode());
		result = prime * result
				+ ((networkId == null) ? 0 : networkId.hashCode());
		result = prime * result
				+ ((sigStrength == null) ? 0 : sigStrength.hashCode());
		result = prime * result + ((sinr == null) ? 0 : sinr.hashCode());
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
		LINK_DET_INFO that = (LINK_DET_INFO) obj;
		if (linkDataRate == null) {
			if (that.linkDataRate != null)
				return false;
		} else if (!linkDataRate.equals(that.linkDataRate))
			return false;
		if (linkMihCapFlags == null) {
			if (that.linkMihCapFlags != null)
				return false;
		} else if (!linkMihCapFlags.equals(that.linkMihCapFlags))
			return false;
		if (linkTupleId == null) {
			if (that.linkTupleId != null)
				return false;
		} else if (!linkTupleId.equals(that.linkTupleId))
			return false;
		if (netAuxId == null) {
			if (that.netAuxId != null)
				return false;
		} else if (!netAuxId.equals(that.netAuxId))
			return false;
		if (netCaps == null) {
			if (that.netCaps != null)
				return false;
		} else if (!netCaps.equals(that.netCaps))
			return false;
		if (networkId == null) {
			if (that.networkId != null)
				return false;
		} else if (!networkId.equals(that.networkId))
			return false;
		if (sigStrength == null) {
			if (that.sigStrength != null)
				return false;
		} else if (!sigStrength.equals(that.sigStrength))
			return false;
		if (sinr == null) {
			if (that.sinr != null)
				return false;
		} else if (!sinr.equals(that.sinr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LINK_DET_INFO [linkTupleId=" + linkTupleId + ", networkId="
				+ networkId + ", netAuxId=" + netAuxId + ", sigStrength="
				+ sigStrength + ", sinr=" + sinr + ", linkDataRate="
				+ linkDataRate + ", linkMihCapFlags=" + linkMihCapFlags
				+ ", netCaps=" + netCaps + "]";
	}
}
