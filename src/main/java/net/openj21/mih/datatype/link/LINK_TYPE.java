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

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

/**
 * Represents the link type. Number assignments:
 * 
 * <pre>
 * 0: Reserved
 * 1: Wireless - GSM
 * 2: Wireless - GPRS
 * 3: Wireless - EDGE
 * 15: Ethernet
 * 18: Wireless - Other
 * 19: Wireless - IEEE 802.11
 * 22: Wireless - CDMA2000
 * 23: Wireless - UMTS
 * 24: Wireless - cdma2000-HRPD
 * 27: Wireless - IEEE 802.16
 * 28: Wireless - IEEE 802.20
 * 29: Wireless - IEEE 802.22
 * </pre>
 */
@SEQUENCE
public class LINK_TYPE {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private Integer value;

	public LINK_TYPE() {
	}

	public LINK_TYPE(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LINK_TYPE that = (LINK_TYPE) o;

		if (value != null ? !value.equals(that.value)
				: that.value != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "LINK_TYPE{" + "value=" + value + '}';
	}
}
