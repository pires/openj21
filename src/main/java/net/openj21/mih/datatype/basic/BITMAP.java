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
package net.openj21.mih.datatype.basic;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <h4>Definition</h4> A bitmap of the specified size. Usually used to represent
 * a list of IDs.
 * <p/>
 * Range: Each bit has a value of '0' or '1'.
 * <h4>Binary encoding rule</h4>
 * A BITMAP(N), where N must be a multiple of 8, is made up of an N/8 octet
 * values and encoded in network byte order.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface BITMAP {
	/**
	 * Returns the maximum size of the BITMAP in bits.
	 * 
	 * @return an int indicating the maximum size of the BITMAP in bits
	 */
	int size();
}
