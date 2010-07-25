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
