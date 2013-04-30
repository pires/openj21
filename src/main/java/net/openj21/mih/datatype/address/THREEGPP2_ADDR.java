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
 * A data type to represent a 3GPP2 transport address.
 */
@CHOICE
public class THREEGPP2_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String threegpp2_addr;

	public THREEGPP2_ADDR() {
	}

	public THREEGPP2_ADDR(String threegpp2_addr) {
		this.threegpp2_addr = threegpp2_addr;
	}

	public String getThreegpp2_addr() {
		return threegpp2_addr;
	}

	public void setThreegpp2_addr(String threegpp2_addr) {
		this.threegpp2_addr = threegpp2_addr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		THREEGPP2_ADDR that = (THREEGPP2_ADDR) o;

		if (threegpp2_addr != null ? !threegpp2_addr
				.equals(that.threegpp2_addr) : that.threegpp2_addr != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return threegpp2_addr != null ? threegpp2_addr.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "THREEGPP2_ADDR{" + "threegpp2_addr='" + threegpp2_addr + '\''
				+ '}';
	}
}
