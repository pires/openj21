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
