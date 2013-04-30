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

import net.openj21.mih.datatype.basic.CHOICE;

/**
 * Measurable link parameter for which thresholds are being set.
 */
@CHOICE({ LINK_PARAM_GEN.class, LINK_PARAM_QOS.class, LINK_PARAM_GG.class,
		LINK_PARAM_EDGE.class, LINK_PARAM_ETH.class, LINK_PARAM_802_11.class,
		LINK_PARAM_C2K.class, LINK_PARAM_FDD.class, LINK_PARAM_HRPD.class,
		LINK_PARAM_802_16.class, LINK_PARAM_802_20.class,
		LINK_PARAM_802_22.class })
public abstract class LINK_PARAM_TYPE {
	protected LINK_PARAM_TYPE() {
	}
}