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
 * Represents the reason of a link down event.
 */
@SEQUENCE
public class LINK_DN_REASON {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private LINK_DN_REASON_ENUM value;

	public LINK_DN_REASON() {
	}

	public LINK_DN_REASON(LINK_DN_REASON_ENUM value) {
		this.value = value;
	}

	public LINK_DN_REASON_ENUM getValue() {
		return value;
	}

	public void setValue(LINK_DN_REASON_ENUM value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		LINK_DN_REASON that = (LINK_DN_REASON) obj;
		if (value != that.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LINK_DN_REASON [value=" + value + "]";
	}
}