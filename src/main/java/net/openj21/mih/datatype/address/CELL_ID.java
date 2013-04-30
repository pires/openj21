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
