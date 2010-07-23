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
