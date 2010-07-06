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

import java.util.logging.Logger;

import net.openj21.mih.datatype.basic.INTEGER;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class encodes subclasses of java.lang.Number as binary INTEGER values,
 * as per the MIH specification.
 */
public class IntegerCodec {
	/**
	 * The name of the INTEGER type.
	 */
	private static final String INTEGER = INTEGER.class.getSimpleName();

	/**
	 * Logger for this IntegerCodec instance.
	 */
	private final Logger logger = Logger
			.getLogger(IntegerCodec.class.getName());

	/**
	 * Creates a new IntegerCodec instance.
	 */
	public IntegerCodec() {
		// empty
	}

	/**
	 * Encodes the given number as an INTEGER value occupying 'size' bytes.
	 * 
	 * @param number
	 *            the number to encode
	 * @param size
	 *            the size to occupy, in bytes
	 * @return an encoded INTEGER
	 */
	public ChannelBuffer encodeInteger(long number, int size) {
		if (size <= 0) {
			throw new IllegalArgumentException(String.format(
					"%s size must be positive.", INTEGER));
		}

		long bufferMax = (long) Math.pow(2, (size * 8) - 1) - 1;
		if (number > bufferMax) {
			throw new IllegalArgumentException(format(
					"Value of is too large for %s(%d): %s", INTEGER, size,
					number));
		}

		long bufferMin = (long) Math.pow(2, (size * 8) - 1) * -1;
		if (number < bufferMin) {
			throw new IllegalArgumentException(format(
					"Value of is too large for %s(%d): %s", INTEGER, size,
					number));
		}

		ChannelBuffer result = ChannelBuffers.buffer(size);
		for (int i = size - 1; i >= 0; i--) {
			int shift = i * 8;
			if (shift >= 64) {
				// this is bigger than a long. extend the sign bit
				result
						.writeByte((byte) (((number & 0x8000000000000000L) >> 63)));
				continue;
			}

			long mask = 0xffL << shift;
			result.writeByte((byte) ((number & mask) >> shift));
		}

		return result;
	}
}
