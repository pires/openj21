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
