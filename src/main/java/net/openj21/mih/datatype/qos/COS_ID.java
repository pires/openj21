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
package net.openj21.mih.datatype.qos;

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

/**
 * A type to represent a class of service identifier. Valid Range: 0–255.
 */
@SEQUENCE
public class COS_ID {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 1)
	private Integer cosId;

	public COS_ID() {
	}

	public COS_ID(Integer cosId) {
		this.cosId = cosId;
	}

	public Integer getCosId() {
		return cosId;
	}

	public void setCosId(Integer cosId) {
		this.cosId = cosId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		COS_ID that = (COS_ID) o;

		if (cosId != null ? !cosId.equals(that.cosId) : that.cosId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return cosId != null ? cosId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "COS_ID{" + "cosId=" + cosId + '}';
	}
}