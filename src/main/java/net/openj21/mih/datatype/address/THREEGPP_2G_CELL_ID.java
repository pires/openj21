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

/**
 * A data type to represent a 3GPP 2G cell identifier.
 */
@CHOICE
public class THREEGPP_2G_CELL_ID extends LINK_ADDR {
	@SEQUENCE_ELEMENT(order = 1)
	private PLMN_ID plmnId;

	@SEQUENCE_ELEMENT(order = 2)
	private LAC lac;

	@SEQUENCE_ELEMENT(order = 3)
	private CI ci;

	public THREEGPP_2G_CELL_ID() {
	}

	public THREEGPP_2G_CELL_ID(PLMN_ID plmn_id, LAC lac, CI ci) {
		this.plmnId = plmn_id;
		this.lac = lac;
		this.ci = ci;
	}

	public PLMN_ID getPlmnId() {
		return plmnId;
	}

	public void setPlmnId(PLMN_ID plmnId) {
		this.plmnId = plmnId;
	}

	public LAC getLac() {
		return lac;
	}

	public void setLac(LAC lac) {
		this.lac = lac;
	}

	public CI getCi() {
		return ci;
	}

	public void setCi(CI ci) {
		this.ci = ci;
	}

	@Override
	public String toString() {
		return "THREEGPP_2G_CELL_ID{" + "plmnId=" + plmnId + ", lac=" + lac
				+ ", ci=" + ci + '}';
	}
}
