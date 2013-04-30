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
 * The public land mobile network (PLMN) unique identifier. PLMN_ID consists of
 * Mobile Country Code (MCC) and Mobile Network Code (MNC). This is to represent
 * the access network identifier. Coding of PLMN_ID is defined in 3GPP TS
 * 25.413.
 */
@SEQUENCE
public class PLMN_ID {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 3)
	private byte[] plmn_id;

	public PLMN_ID() {
	}

	public PLMN_ID(byte[] plmn_id) {
		this.plmn_id = plmn_id;
	}

	public byte[] getPlmn_id() {
		return plmn_id;
	}

	public void setPlmn_id(byte[] plmn_id) {
		this.plmn_id = plmn_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PLMN_ID plmn_id1 = (PLMN_ID) o;

		if (!Arrays.equals(plmn_id, plmn_id1.plmn_id))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return plmn_id != null ? Arrays.hashCode(plmn_id) : 0;
	}

	@Override
	public String toString() {
		return "PLMN_ID{" + "plmnId=" + Arrays.toString(plmn_id) + '}';
	}
}
