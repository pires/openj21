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
 * A type to represent a generic link parameter that is applicable to any link
 * type.
 * 
 * <pre>
 * 0: Data Rate—the parameter value is represented as a DATA_RATE.
 * 1: Signal Strength—the parameter value is represented as a SIG_STRENGTH.
 * 2: Signal over interference plus noise ratio (SINR)—the parameter value is
 * represented as an UNSIGNED_INT(2).
 * 3:Throughput (the number of bits successfully received divided by the time it
 * took to transmit them over the medium) - the parameter value is represented 
 * as an UNSIGNED_INT(2).
 * 4: Packet Error Rate (representing the ratio between the number of frames
 * received in error and the total number of frames transmitted in a link 
 * population of interest) - the parameter value is represented as a PERCENTAGE.
 * 5–255: (Reserved)
 * </pre>
 */
public class LINK_PARAM_GEN extends ABSTRACT_LINK_PARAM {
	@Override
	public String toString() {
		return "LINK_PARAM_GEN [value=" + this.getValue() + "]";
	}
}