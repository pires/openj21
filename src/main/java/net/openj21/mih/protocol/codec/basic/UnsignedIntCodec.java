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
import net.openj21.mih.datatype.basic.UNSIGNED_INT;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class encodes numbers as UNSIGNED_INT, as per the MIH specification.
 */
public class UnsignedIntCodec {
	/**
	 * The name of the encoded type.
	 */
	private static final String UNSIGNED_INT = UNSIGNED_INT.class
			.getSimpleName();

	/**
	 * Creates a new UnsignedIntCodec.
	 */
	public UnsignedIntCodec() {
		// empty
	}

	/**
	 * Encodes the given number as an UNSIGNED_INT value occupying the 'size'
	 * bytes.
	 * 
	 * @param number
	 *            the number to encode
	 * @param size
	 *            the size to occupy, in bytes
	 * @return an encoded UNSIGNED_INT
	 */
	public ChannelBuffer encodeUnsignedInt(long number, int size) {
		long bufferMax = (long) Math.pow(2, size * 8) - 1;
		if (number > bufferMax) {
			throw new IllegalArgumentException(format(
					"Value is too large for %s(%d): %d", UNSIGNED_INT, size,
					number));
		}

		if (number < 0) {
			throw new IllegalArgumentException(format(
					"Can't encode negative value as %s(%d): %d", UNSIGNED_INT,
					size, number));
		}

		ChannelBuffer result = ChannelBuffers.buffer(size);
		for (int i = size - 1; i >= 0; i--) {
			int shift = i * 8;
			if (shift > 63) {
				// this is bigger than a long. pad with zero
				result.writeByte((byte) 0);
				continue;
			}

			long mask = 0xffL << shift;
			result.writeByte((byte) ((number & mask) >> shift));
		}

		return result;
	}

	/**
	 * Decodes an UNSIGNED_INT value of the given size from a ChannelBuffer, and
	 * returns the corresponding value as a long.
	 * 
	 * @param buffer
	 *            a ChannelBuffer
	 * @param size
	 *            the size that the UNSIGNED_INT occupies, in bytes
	 * @return a long containing the numeric value of the UNSIGNED_INT
	 */
	public long decodeUnsignedInt(ChannelBuffer buffer, int size) {
		if (size > 7) {
			throw new IllegalArgumentException(format(
					"Value is too large to be stored in  long: %s(%d)",
					UNSIGNED_INT, size));
		}

		long decoded = 0;
		for (int i = size - 1; i >= 0; i--) {
			int shift = i * 8;
			decoded |= (((long) buffer.readByte()) << shift);
		}

		return decoded;
	}
}
