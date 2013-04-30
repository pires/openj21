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
