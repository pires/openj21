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

import java.util.Arrays;

import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A type to represent a binary-formatted geospatial location. See Table F.11.
 */
@SEQUENCE
public class BIN_GEO_LOC extends GEO_LOC {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 2)
	private byte[] binGeoLoc;

	public BIN_GEO_LOC() {
	}

	public BIN_GEO_LOC(byte[] binGeoLoc) {
		super();
		this.binGeoLoc = binGeoLoc;
	}

	public byte[] getBinGeoLoc() {
		return binGeoLoc;
	}

	public void setBinGeoLoc(byte[] binGeoLoc) {
		this.binGeoLoc = binGeoLoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(binGeoLoc);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BIN_GEO_LOC that = (BIN_GEO_LOC) obj;
		if (!Arrays.equals(binGeoLoc, that.binGeoLoc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BIN_GEO_LOC [binGeoLoc=" + Arrays.toString(binGeoLoc) + "]";
	}
}
