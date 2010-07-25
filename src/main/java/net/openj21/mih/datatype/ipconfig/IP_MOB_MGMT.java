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

import java.util.BitSet;

import net.openj21.mih.datatype.basic.BITMAP;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Indicates the supported mobility management protocols.
 * 
 * <pre>
 * Bit 0: Mobile IPv4 (IETF RFC 3344)
 * Bit 1: Mobile IPv4 Regional Registration (IETF RFC 4857)
 * Bit 2: Mobile IPv6 (IETF RFC 3775)
 * Bit 3: Hierarchical Mobile IPv6 (IETF RFC 4140)
 * Bit 4: Low Latency Handoffs (IETF RFC 4881)
 * Bit 5: Mobile IPv6 Fast Handovers (IETF RFC 5268)
 * Bit 6: IKEv2 Mobility and Multihoming Protocol (IETF RFC4555)
 * Bit 7–15: (Reserved)
 * </pre>
 */
@SEQUENCE
public class IP_MOB_MGMT {
	/**
	 * The size of the BITMAP.
	 */
	private static final int SIZE = 8;

	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@BITMAP(size = SIZE)
	private BitSet ipMobMgmt = new BitSet(SIZE);

	public IP_MOB_MGMT() {
	}

	public IP_MOB_MGMT(BitSet ipMobMgmt) {
		super();
		this.ipMobMgmt = ipMobMgmt;
	}

	public BitSet getIpMobMgmt() {
		return ipMobMgmt;
	}

	public void setIpMobMgmt(BitSet ipMobMgmt) {
		this.ipMobMgmt = ipMobMgmt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ipMobMgmt == null) ? 0 : ipMobMgmt.hashCode());
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
		IP_MOB_MGMT that = (IP_MOB_MGMT) obj;
		if (ipMobMgmt == null) {
			if (that.ipMobMgmt != null)
				return false;
		} else if (!ipMobMgmt.equals(that.ipMobMgmt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IP_MOB_MGMT [ipMobMgmt=" + ipMobMgmt + "]";
	}
}
