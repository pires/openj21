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
 * A type to represent a link parameter for IEEE 802.11.
 * 
 * <pre>
 * 0: RSSI of the beacon channel, as defined in IEEE Std 802.11-2007. (This is 
 * applicable only for an MN.)
 * 1: No QoS resource available. The corresponding LINK_PARAM_VAL is BOOLEAN set
 * to TRUE when no QoS resources available. (This applicable when the traffic 
 * stream to be transmitted is on an access category configured for mandatory 
 * admission control and the request for bandwidth was denied by the available 
 * APs in the access network).
 * 2: Multicast packet loss rate.
 * 3–255: (Reserved)
 * </pre>
 */
public class LINK_PARAM_802_11 extends ABSTRACT_LINK_PARAM {
	
	public LINK_PARAM_802_11(int value){
		this.setValue(value);
	}
	
	@Override
	public String toString() {
		return "LINK_PARAM_802_11 [value=" + this.getValue() + "]";
	}
}