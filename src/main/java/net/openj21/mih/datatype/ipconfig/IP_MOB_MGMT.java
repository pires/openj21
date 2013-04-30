/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
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
