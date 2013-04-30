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
package net.openj21.mih.datatype.location;

import net.openj21.mih.datatype.address.CELL_ID;
import net.openj21.mih.datatype.basic.CHOICE;

/**
 * A type to represent the format and value of the location information. The
 * location can be civic location, geospacial location, or a cellular ID value
 * as reference location.
 */
@CHOICE({ CIVIC_LOC.class, GEO_LOC.class, CELL_ID.class })
public abstract class LOCATION {
	protected LOCATION() {
	}
}