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
package net.openj21.mih.datatype.location;

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Civic address elements, as described in IETF RFC 4776.
 */
@CHOICE
public class CIVIC_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String civicAddr;

	public CIVIC_ADDR() {
	}

	public CIVIC_ADDR(String civicAddr) {
		this.civicAddr = civicAddr;
	}

	public String getCivicAddr() {
		return civicAddr;
	}

	public void setCivicAddr(String civicAddr) {
		this.civicAddr = civicAddr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CIVIC_ADDR that = (CIVIC_ADDR) o;

		if (civicAddr != null ? !civicAddr.equals(that.civicAddr)
				: that.civicAddr != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return civicAddr != null ? civicAddr.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "CIVIC_ADDR{" + "civicAddr='" + civicAddr + '\'' + '}';
	}
}