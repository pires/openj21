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
package net.openj21.mih.protocol.codec;

import net.openj21.mih.datatype.TLVType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * A type, length, value element.
 */
public class TLVElement {
	/**
	 * The type of this TLV element.
	 */
	private final TLVType type;

	/**
	 * The length of this TLV element, including the variable-length 'length'
	 * field.
	 */
	private final int length;

	/**
	 * The value of this TLV element.
	 */
	private final ChannelBuffer value;

	/**
	 * Creates a new TLVElement with the given type, length, and value
	 * components.
	 * 
	 * @param type
	 *            the type
	 * @param length
	 *            the length
	 * @param value
	 *            the value
	 */
	public TLVElement(TLVType type, int length, ChannelBuffer value) {
		this.type = type;
		this.length = length;
		this.value = ChannelBuffers.unmodifiableBuffer(value);
	}

	/**
	 * Returns the type of this TLV element.
	 * 
	 * @return the type of this TLV element
	 */
	public TLVType getType() {
		return type;
	}

	/**
	 * Returns the length of this TLV element. The length indicates the length
	 * of this entire TLV element, including the bytes occupied by the type id
	 * field and the variable-length 'length' field.
	 * 
	 * @return the length of this TLV element
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns the value of this TLV element.
	 * 
	 * @return the value of this TLV element
	 */
	public ChannelBuffer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "TLVElement{" + "type=" + type + ", length=" + length
				+ ", value=" + value + '}';
	}
}
