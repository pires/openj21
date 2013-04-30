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
package net.openj21.mih.datatype.link;

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.INTEGER;
import net.openj21.mih.datatype.basic.PERCENTAGE;

/**
 * Represents the signal strength in dBm unit or its relative value in an
 * arbitrary percentage scale.
 */
@CHOICE({ INTEGER.class, PERCENTAGE.class })
public abstract class SIG_STRENGTH {
	protected SIG_STRENGTH() {
	}
}