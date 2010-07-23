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