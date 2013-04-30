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
package net.openj21.mih.datatype.location;

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.information_elements.CNTRY_CODE;

/**
 * A type to represent a binary-formatted civic address. See CNTRY_CODE and
 * CIVIC_ADDR definitions.
 */
@CHOICE
public class BIN_CIVIC_LOC extends CIVIC_LOC {
	@SEQUENCE_ELEMENT(order = 1)
	CNTRY_CODE countryCode;

	@SEQUENCE_ELEMENT(order = 2)
	CIVIC_ADDR civicAddr;

	public BIN_CIVIC_LOC() {
	}

	public BIN_CIVIC_LOC(CNTRY_CODE countryCode, CIVIC_ADDR civicAddr) {
		this.civicAddr = civicAddr;
		this.countryCode = countryCode;
	}

	public CIVIC_ADDR getCivicAddr() {
		return civicAddr;
	}

	public void setCivicAddr(CIVIC_ADDR civicAddr) {
		this.civicAddr = civicAddr;
	}

	public CNTRY_CODE getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CNTRY_CODE countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BIN_CIVIC_LOC that = (BIN_CIVIC_LOC) o;

		if (civicAddr != null ? !civicAddr.equals(that.civicAddr)
				: that.civicAddr != null)
			return false;
		if (countryCode != null ? !countryCode.equals(that.countryCode)
				: that.countryCode != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = countryCode != null ? countryCode.hashCode() : 0;
		result = 31 * result + (civicAddr != null ? civicAddr.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "BIN_CIVIC_LOC{" + "countryCode=" + countryCode + ", civicAddr="
				+ civicAddr + '}';
	}
}