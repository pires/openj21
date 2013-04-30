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