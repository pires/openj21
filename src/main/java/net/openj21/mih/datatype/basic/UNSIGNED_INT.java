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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <h4>Definition</h4> An unsigned integer of the specified size in number of
 * octets. Each octet has a value of 0x00 to 0xff. <h4>Binary encoding rule</h4>
 * Each octet of an UNSIGNED_INT(N) value [N=1,2,...] is encoded in network byte
 * order into an N-octet field.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Inherited
public @interface UNSIGNED_INT {
	/**
	 * The number of bytes that may be used to encode the field.
	 * 
	 * @return the number of bytes that may be used to encode the field
	 */
	int size();
}
