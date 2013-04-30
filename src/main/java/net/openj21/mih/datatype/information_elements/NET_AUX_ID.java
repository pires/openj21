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
