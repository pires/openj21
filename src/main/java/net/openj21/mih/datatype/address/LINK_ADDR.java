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
package net.openj21.mih.datatype.address;

import net.openj21.mih.datatype.basic.CHOICE;

/**
 * A data type to represent an address of any link layer.
 */
@CHOICE( { MAC_ADDR.class, THREEGPP_3G_CELL_ID.class,
		THREEGPP_2G_CELL_ID.class, THREEGPP_ADDR.class, THREEGPP2_ADDR.class,
		OTHER_L2_ADDR.class })
public abstract class LINK_ADDR {
	protected LINK_ADDR() {
	}
}