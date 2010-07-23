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
