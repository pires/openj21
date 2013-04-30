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