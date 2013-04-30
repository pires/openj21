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

import java.util.Arrays;

import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Location Area Code (LAC) is a fixed length code (of 2 octets) identifying a
 * location area within a public land mobile network (PLMN). See 3GPP TS 23.003.
 */
@SEQUENCE
public class LAC {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 2)
	private byte[] locationAreaCode;

	public LAC() {
	}

	public LAC(byte[] locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	public byte[] getLocationAreaCode() {
		return locationAreaCode;
	}

	public void setLocationAreaCode(byte[] locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LAC lac = (LAC) o;

		if (!Arrays.equals(locationAreaCode, lac.locationAreaCode))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return locationAreaCode != null ? Arrays.hashCode(locationAreaCode) : 0;
	}

	public String toString() {
		return "LAC{" + "locationAreaCode=" + Arrays.toString(locationAreaCode)
				+ '}';
	}
}
