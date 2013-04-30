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
