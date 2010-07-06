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

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class encodes and decodes objects as OCTET as defined in the MIH binary
 * protocol.
 */
public class OctetCodec {
	/**
	 * Creates a new OctetCodec.
	 */
	public OctetCodec() {
		// empty
	}

	/**
	 * Encodes the given ChannelBuffer instance as an OCTET. The size of the
	 * OCTET is specified by the size parameter.
	 * 
	 * @param string
	 *            the String to encode
	 * @param charset
	 *            the character set to use when encoding
	 * @return an encoded OCTET(size)
	 */
	public ChannelBuffer encode(String string, String charset) {
		if (string == null)
			throw new NullPointerException("string");

		ChannelBuffer buffer = encodeString(string, charset);
		return ChannelBuffers.unmodifiableBuffer(buffer);
	}

	/**
	 * Decodes the entire contents of the given ChannelBuffer into a String
	 * using the character set with the given name.
	 * 
	 * @param buffer
	 *            the ChannelBuffer to decode
	 * @param charsetName
	 *            the name of the character set to use
	 * @return a decoded String
	 * @see Charset#forName(String)
	 */
	public String decode(ChannelBuffer buffer, String charsetName) {
		try {
			CharsetDecoder decoder = Charset.forName(charsetName).newDecoder();
			CharBuffer decodedBuffer = decoder.decode(buffer.toByteBuffer());

			return String.valueOf(decodedBuffer);
		} catch (CharacterCodingException e) {
			throw new IllegalArgumentException(format(
					"Error decoding buffer as OCTET(charset=%s): %s",
					charsetName, buffer), e);
		}
	}

	/**
	 * Encodes the given String as an OCTET.
	 * 
	 * @param string
	 *            a String
	 * @param charsetName
	 *            the name of the Charset to encode as
	 * @return a ChannelBuffer containing the encoded String
	 * @throws IllegalArgumentException
	 *             if the String cannot be encoded with the desired Charset, or
	 *             the encoded string is larger than the size of the OCTET
	 */
	private ChannelBuffer encodeString(String string, String charsetName) {
		try {
			CharsetEncoder encoder = Charset.forName(charsetName).newEncoder();
			ByteBuffer encoded = encoder.encode(CharBuffer.wrap(string));
			if (encoded.hasArray()) {
				return ChannelBuffers.unmodifiableBuffer(ChannelBuffers
						.wrappedBuffer(encoded.array()));
			} else {
				ChannelBuffer buffer = ChannelBuffers
						.buffer(encoded.capacity());
				while (encoded.hasRemaining()) {
					buffer.writeByte(encoded.get());
				}

				return buffer;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(format(
					"Error encoding string as %s: %s", charsetName, string), e);
		}
	}
}