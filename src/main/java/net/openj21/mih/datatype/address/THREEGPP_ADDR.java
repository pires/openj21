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
 * A data type to represent a 3GPP transport address.
 */
@CHOICE
public class THREEGPP_ADDR extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String threegppAddr;

	public THREEGPP_ADDR() {
	}

	public THREEGPP_ADDR(String threegppAddr) {
		this.threegppAddr = threegppAddr;
	}

	public String getThreegppAddr() {
		return threegppAddr;
	}

	public void setThreegppAddr(String threegppAddr) {
		this.threegppAddr = threegppAddr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		THREEGPP_ADDR that = (THREEGPP_ADDR) o;

		if (threegppAddr != null ? !threegppAddr.equals(that.threegppAddr)
				: that.threegppAddr != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return threegppAddr != null ? threegppAddr.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "THREEGPP_ADDR{" + "threegppAddr='" + threegppAddr + '\'' + '}';
	}
}
