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

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <h4>Definition</h4> A binary encoded structure for Information Element
 * container. <h4>Binary encoding rule</h4> See 6.5.6.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface INFO_ELEMENT_CONTAINER {
	/**
	 * The Information Element ID is a 32 bit value, defined by table 11 in the
	 * 802.21 specification.
	 * 
	 * @return the information element identifier
	 */
	public abstract int id();
}
