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
