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
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

/**
 * Represents a MAC address. The Address Type contains the one used for a
 * specific link layer.
 */
@CHOICE
public class MAC_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 2)
	private Integer addressType;

	@SEQUENCE_ELEMENT(order = 2, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String address;

	public MAC_ADDR() {
	}

	public MAC_ADDR(Integer addressType, String address) {
		this.addressType = addressType;
		this.address = address;
	}

	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MAC_ADDR that = (MAC_ADDR) o;

		if (address != null ? !address.equals(that.address)
				: that.address != null)
			return false;
		if (addressType != null ? !addressType.equals(that.addressType)
				: that.addressType != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = addressType != null ? addressType.hashCode() : 0;
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "MAC_ADDR{" + "addressType=" + addressType + ", address='"
				+ address + '\'' + '}';
	}
}
