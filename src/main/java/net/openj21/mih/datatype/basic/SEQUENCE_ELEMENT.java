/*
 *  OpenJ21 Copyright (C) 2010 Paulo Pires
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 */
package net.openj21.mih.datatype.basic;

import static net.openj21.mih.datatype.basic.Encoding.ATOM;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.openj21.mih.datatype.TLVType;

/**
 * This annotation indicates that a class field should be encoded.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
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
