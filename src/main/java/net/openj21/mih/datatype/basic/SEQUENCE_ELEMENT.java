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

import static net.openj21.mih.datatype.basic.Encoding.ATOM;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.openj21.mih.datatype.TLVType;

/**
 * This annotation indicates that a class field should be encoded.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Inherited
public @interface SEQUENCE_ELEMENT {
	/**
	 * Specifies the order in which an SEQUENCE_ELEMENT is is written to the
	 * output stream.
	 * 
	 * @return the order in which an SEQUENCE_ELEMENT is written
	 */
	int order();

	/**
	 * Indicates whether the field is optional in the binary encoding. Optional
	 * fields are encoded as CHOICE(NULL, DATATYPE) where DATATYPE is the
	 * field's own encoded data.
	 * 
	 * @return a boolean indicating whether the field is optional
	 */
	boolean choice() default false;

	/**
	 * Indicates that the field should be encoded as one of the basic MIH types.
	 * In this case, it is necessary for the field to be annotated with some
	 * additional annotations indicating how the encoding should be done for
	 * that field.
	 * 
	 * @return a boolean indicating that the field should be encoded as one of
	 *         the basic types
	 */
	boolean basicType() default false;

	/**
	 * Indicates how the class field should be encoded.
	 * 
	 * @return the encoding for this sequence element
	 * @see Encoding
	 */
	Encoding encodeAs() default ATOM;

	/**
	 * Indicates the TLVType that should be used as the type in the TLV when
	 * encoding in TLV.
	 * 
	 * @return the TLVType that should be used for encoding
	 */
	TLVType tlv() default TLVType.NONE;
}
