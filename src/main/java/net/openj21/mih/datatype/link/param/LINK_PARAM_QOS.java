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
 * A type to represent QOS_LIST parameters.
 * 
 * <pre>
 * 0: Maximum number of differentiable classes of service supported.
 * 1: Minimum packet transfer delay for all CoS, the minimum delay over a class
 * population of interest.
 * 2: Average packet transfer delay for all CoS, the arithmetic mean of the 
 * delay over a class population of interest. (See B.3.4)
 * 3: Maximum packet transfer delay for all CoS, the maximum delay over a
 * class population of interest.
 * 4: Packet transfer delay jitter for all CoS, the standard deviation of the
 * delay over a class population of interest. (See B.3.5.)
 * 5: Packet loss rate for all CoS, the ratio between the number of frames that
 * are transmitted but not received and the total number of frames transmitted 
 * over a class population of interest.(See B.3.2.)
 * 6–255: (Reserved)
 * </pre>
 */
public class LINK_PARAM_QOS extends ABSTRACT_LINK_PARAM {
	@Override
	public String toString() {
		return "LINK_PARAM_QOS [value=" + this.getValue() + "]";
	}
}