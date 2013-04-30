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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * An array of arbitrary length octets. The default encoding format is UTF-8 
 * [B23]. If a data type derived from OCTET_STRING uses other encoding 
 * format(s), the encoding format(s) must be specified in the definition of such
 * a data type.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface OCTET_STRING {
    /**
     * The maximum size of the octet string, in bytes.
     *
     * @return the maximum size of the octet string
     */
    int size();
}