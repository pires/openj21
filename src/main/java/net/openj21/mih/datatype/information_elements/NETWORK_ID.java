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
 * A type to represent a network identifier. A non-NULL terminated string whose
 * length shall not exceed 253 octets.
 */
@SEQUENCE
public class NETWORK_ID {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = 253)
	private String value;

	public NETWORK_ID() {
	}

	public NETWORK_ID(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		NETWORK_ID that = (NETWORK_ID) o;

		if (value != null ? !value.equals(that.value) : that.value != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "NETWORK_ID{" + "value='" + value + '\'' + '}';
	}
}
