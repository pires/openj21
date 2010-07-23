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

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;
import net.openj21.mih.datatype.location.LOCATION;

/**
 * This data type identifies a cell uniquely within 3GPP UTRAN and consists of
 * radio network controller (RNC)-ID and C-ID as defined in 3GPP TS 25.401.
 * Valid Range: 0..268435455
 * <p/>
 * Derived from: UNSIGNED_INT(4)
 */
@CHOICE
public class CELL_ID extends LOCATION {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@UNSIGNED_INT(size = 4)
	private Long cellId;

	public CELL_ID() {
	}

	public CELL_ID(Long cellId) {
		this.cellId = cellId;
	}

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CELL_ID cell_id = (CELL_ID) o;

		if (cellId != null ? !cellId.equals(cell_id.cellId)
				: cell_id.cellId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return cellId != null ? cellId.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "CELL_ID{" + "cellId=" + cellId + '}';
	}
}
