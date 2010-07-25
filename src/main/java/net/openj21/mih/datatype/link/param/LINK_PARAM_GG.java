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
package net.openj21.mih.datatype.link.param;

/**
 * A type to represent a link parameter for GSM and GPRS. See 3GPP TS 25.008.
 * 
 * 0: RxQual
 * 1: RsLev
 * 2: Mean BEP
 * 3: StDev BEP
 * 4-255: (Reserved)
 * </pre>
 */
public class LINK_PARAM_GG extends ABSTRACT_LINK_PARAM {
	@Override
	public String toString() {
		return "LINK_PARAM_GG [value=" + this.getValue() + "]";
	}
}