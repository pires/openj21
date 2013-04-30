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