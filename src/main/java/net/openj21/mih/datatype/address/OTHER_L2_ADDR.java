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
package net.openj21.mih.datatype.address;

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A data type to represent a link-layer address other than the address already
 * defined. For example, SSID.
 */
@CHOICE
public class OTHER_L2_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String otherL2Address;

	public OTHER_L2_ADDR() {
	}

	public OTHER_L2_ADDR(String otherL2Address) {
		this.otherL2Address = otherL2Address;
	}

	public String getOtherL2Address() {
		return otherL2Address;
	}

	public void setOtherL2Address(String otherL2Address) {
		this.otherL2Address = otherL2Address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OTHER_L2_ADDR that = (OTHER_L2_ADDR) o;

		if (otherL2Address != null ? !otherL2Address
				.equals(that.otherL2Address) : that.otherL2Address != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return otherL2Address != null ? otherL2Address.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "OTHER_L2_ADDR{" + "otherL2Address='" + otherL2Address + '\''
				+ '}';
	}
}
