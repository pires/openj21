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