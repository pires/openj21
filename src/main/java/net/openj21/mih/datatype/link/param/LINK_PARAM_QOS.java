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