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
package net.openj21.mih.datatype.ipconfig;

import net.openj21.mih.datatype.basic.BOOLEAN;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Indicates whether MN’s IP address needs to be changed or not.
 * 
 * TRUE: Change required. FALSE: Change not required.
 */
@SEQUENCE
public class IP_RENEWAL_FLAG {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@BOOLEAN
	private Boolean value;

	public IP_RENEWAL_FLAG() {
	}

	public IP_RENEWAL_FLAG(Boolean value) {
		super();
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
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
		IP_RENEWAL_FLAG that = (IP_RENEWAL_FLAG) obj;
		if (value == null) {
			if (that.value != null)
				return false;
		} else if (!value.equals(that.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IP_RENEWAL_FLAG [value=" + value + "]";
	}
}
