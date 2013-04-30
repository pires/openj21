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
 * The BSS and cell within the BSS are identified by Cell Identity (CI). See
 * 3GPP TS 23.003.
 */
@SEQUENCE
public class CI {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 2)
	private byte[] cellIdentity;

	public CI() {
	}

	public CI(byte[] cellIdentity) {
		this.cellIdentity = cellIdentity;
	}

	public byte[] getCellIdentity() {
		return cellIdentity;
	}

	public void setCellIdentity(byte[] cellIdentity) {
		this.cellIdentity = cellIdentity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CI ci = (CI) o;

		if (!Arrays.equals(cellIdentity, ci.cellIdentity))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return cellIdentity != null ? Arrays.hashCode(cellIdentity) : 0;
	}

	@Override
	public String toString() {
		return "CI{" + "cellIdentity=" + Arrays.toString(cellIdentity) + '}';
	}
}
