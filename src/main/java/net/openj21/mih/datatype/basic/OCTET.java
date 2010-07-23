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
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * OCTET fixed-width basic type. This annotation can be used on fields that are
 * already encoded in the necessary binary format. Acceptable types are
 * ChannelBuffer, byte[], or String). <h4>ChannelBuffer</h4> The Netty
 * ChannelBuffer type will be written as-is. <h4>byte[]</h4> Byte arrays are
 * written as-is. <h4>java.lang.String</h4> String instances are encoded using
 * the {@link java.nio.charset.Charset charset} that is configured in the
 * annotation (defaults to US-ASCII). Note that using variable-width charsets
 * such as UTF-8 and other Unicode encodings can be problematic, because the
 * encoded result may not always have the same length, and OCTET elements are
 * fixed-length. In such cases it is probably more appropriate to use the
 * OCTET_STRING encoding.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface OCTET {
	/**
	 * Returns the maximum encoded size of the binary field. The field's number
	 * of bytes (encoded or as-is, depending on the data typye) must be equal to
	 * this size.
	 * 
	 * @return the maximum encoded size
	 */
	int size() default 0;

	/**
	 * The character set to use for encoding String instances. Defaults to
	 * UTF-8.
	 * 
	 * @return the character set to use for encoding String instances
	 * @see java.nio.charset.Charset#forName(String)
	 */
	String charset() default "US-ASCII";
}
