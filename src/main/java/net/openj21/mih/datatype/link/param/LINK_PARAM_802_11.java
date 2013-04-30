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