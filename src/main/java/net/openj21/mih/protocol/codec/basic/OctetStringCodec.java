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
package net.openj21.mih.protocol.codec.basic;

import static java.lang.String.format;

import java.io.UnsupportedEncodingException;

import net.openj21.mih.protocol.codec.TLVLengthCodec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class encodes String instances using the OCTET_STRING encoding defined
 * in the MIH binary protocol.
 */
public class OctetStringCodec {
	/**
	 * The character set to use for encoding OCTET_STRING fields.
	 */
	static final String UTF8 = "UTF-8";

	/**
	 * The TLVLengthCodec instance used for encoding and decoding TLV length.
	 */
	TLVLengthCodec lengthCodec;

	/**
	 * Creates a new OctetStringEncoder.
	 */
	public OctetStringCodec() {
		// empty
	}

	/**
	 * Sets the TLVLengthCodec instance used for encoding and decoding TLV
	 * length.
	 * 
	 * @param lengthCodec
	 *            a TLVLengthCodec instance
	 */
	public void setLengthCodec(TLVLengthCodec lengthCodec) {
		this.lengthCodec = lengthCodec;
	}

	/**
	 * Encodes the given String as an OCTET_STRING (aka LIST(OCTET(1))). A LIST
	 * is encoded as a variable length length field followed by a variable
	 * length value field.
	 * 
	 * @param string
	 *            the String to encode
	 * @param maxLen
	 *            the maximum length allowed
	 * @return a ChannelBuffer containing the encoded OCTET_STRING
	 * @throws IllegalArgumentException
	 *             if the string's encoded length in UTF-8 is larger than the
	 *             maximum allowed length
	 */
	public ChannelBuffer encode(String string, int maxLen)
			throws IllegalArgumentException {
		byte[] stringBytes = new byte[] {};

		try {
			stringBytes = string.getBytes(UTF8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (stringBytes.length > maxLen) {
			throw new IllegalArgumentException(format(
					"Encoded string exceeds maximum length of %d: %s", maxLen,
					string));
		}

		ChannelBuffer encodedLength = lengthCodec
				.encodeLength(stringBytes.length);
		ChannelBuffer encodedValue = ChannelBuffers.wrappedBuffer(stringBytes);

		// return the length + value
		return ChannelBuffers.wrappedBuffer(encodedLength, encodedValue);
	}

	/**
	 * Decodes the given OCTET_STRING into a String.
	 * 
	 * @param octetString
	 *            a ChannelBuffer containing an encoded OCTET_STRING
	 * @return a decoded String
	 */
	public String decode(ChannelBuffer octetString) {
		int length = lengthCodec.decodeLength(octetString);
		if (octetString.readableBytes() < length) {
			throw new IllegalArgumentException(format(
					"Not enough bytes available (expected %d): %d", length,
					octetString.readableBytes()));
		}

		byte[] chars = new byte[length];
		for (int i = 0; i < length; i++) {
			chars[i] = octetString.readByte();
		}

		try {
			return new String(chars, UTF8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
