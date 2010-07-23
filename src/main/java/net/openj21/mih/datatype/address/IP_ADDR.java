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
package net.openj21.mih.datatype.address;

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;
import net.openj21.mih.datatype.general.OCTET_STRING;

/**
 * Represents an IP address. The Address Type is either 1 (IPv4) or 2 (IPv6).
 */
@SEQUENCE
public class IP_ADDR {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 2)
	private Integer addressType;

	@SEQUENCE_ELEMENT(order = 2, basicType = true)
	@OCTET_STRING(size = Integer.MAX_VALUE)
	private String address;

	public IP_ADDR() {
	}

	public IP_ADDR(Integer addressType, String address) {
		this.addressType = addressType;
		this.address = address;
	}

	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		IP_ADDR ip_addr = (IP_ADDR) o;

		if (address != null ? !address.equals(ip_addr.address)
				: ip_addr.address != null)
			return false;
		if (addressType != null ? !addressType.equals(ip_addr.addressType)
				: ip_addr.addressType != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = addressType != null ? addressType.hashCode() : 0;
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "IP_ADDR{" + "addressType=" + addressType + ", address='"
				+ address + '\'' + '}';
	}
}
