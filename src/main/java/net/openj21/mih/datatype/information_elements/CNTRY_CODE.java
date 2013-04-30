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

import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Country code, represented as two letter ISO 3166-1 country code in capital
 * ASCII letters.
 */
@SEQUENCE
public class CNTRY_CODE {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 2, charset = "US-ASCII")
	String countryCode;

	public CNTRY_CODE() {
	}

	public CNTRY_CODE(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CNTRY_CODE that = (CNTRY_CODE) o;

		if (countryCode != null ? !countryCode.equals(that.countryCode)
				: that.countryCode != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return countryCode != null ? countryCode.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "CNTRY_CODE{" + "countryCode='" + countryCode + '\'' + '}';
	}
}