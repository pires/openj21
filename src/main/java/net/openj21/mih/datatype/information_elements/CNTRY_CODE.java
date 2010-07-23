/*
 *  OpenJ21 Copyright (C) 2010 Paulo Pires
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
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