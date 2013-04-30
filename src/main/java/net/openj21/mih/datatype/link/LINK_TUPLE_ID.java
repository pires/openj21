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