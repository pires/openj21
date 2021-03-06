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
 * A type to represent a link parameter for CDMA2000.
 * 
 * <pre>
 * 0: PILOT_STRENGTH
 * 1–255: (Reserved)
 * </pre>
 */
public class LINK_PARAM_C2K extends ABSTRACT_LINK_PARAM {
	@Override
	public String toString() {
		return "LINK_PARAM_C2K [value=" + this.getValue() + "]";
	}
}