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
 * <h4>Definition</h4> A data type that consists of only one of the data types
 * listed: DATATYPE1,DATATYPE2[,…]. <h4>Binary encoding rule</h4> A one-octet
 * Selector field, followed by a variable length Value field. The Selector value
 * determines the data type. If Selector==i, (i+1)-th data type in the list of
 * data types DATATYPE1,DATATYPE2[,…] is selected. The Selector value is encoded
 * as UNSIGNED_INT(1). The Value field is encoded using the encoding rule for
 * the selected data type.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface CHOICE {
	Class[] value() default {};
}
