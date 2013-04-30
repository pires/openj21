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

/**
 * A data type to represent a 3GPP 3G cell identifier.
 */
@CHOICE
public class THREEGPP_3G_CELL_ID extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1)
	PLMN_ID plmnId;

	/**
	 * This data type identifies a cell uniquely within 3GPP UTRAN and consists
	 * of radio network controller (RNC)-ID and C-ID as defined in 3GPP TS
	 * 25.401.
	 * <p/>
	 * Valid Range: 0..268435455
	 */
	@SEQUENCE_ELEMENT(order = 2, basicType = true)
	@UNSIGNED_INT(size = 4)
	Long cellId;

	public THREEGPP_3G_CELL_ID() {
	}

	public THREEGPP_3G_CELL_ID(PLMN_ID plmnId, Long cell_id) {
		this.plmnId = plmnId;
		this.cellId = cell_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		THREEGPP_3G_CELL_ID that = (THREEGPP_3G_CELL_ID) o;

		if (cellId != null ? !cellId.equals(that.cellId) : that.cellId != null)
			return false;
		if (plmnId != null ? !plmnId.equals(that.plmnId) : that.plmnId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = plmnId != null ? plmnId.hashCode() : 0;
		result = 31 * result + (cellId != null ? cellId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "THREEGPP_3G_CELL_ID{" + "plmnId=" + plmnId + ", cellId="
				+ cellId + '}';
	}
}
