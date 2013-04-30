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

import java.util.BitSet;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class encodes and decodes BitSet instances using the BITMAP binary
 * representation defined in the MIH protocol.
 */
public class BitmapCodec {
	/**
	 * Logger for this BitmapCodec instance.
	 */
	private final Logger logger = Logger.getLogger(BitmapCodec.class.getName());

	/**
	 * Creates a new BitmapCodec.
	 */
	public BitmapCodec() {
		// empty
	}

	/**
	 * Encodes the given BitSet as a BITMAP.
	 * 
	 * @param bitmap
	 *            a BitSet instance
	 * @param size
	 *            the size of the BITMAP, in bits
	 * @return a binary-encoded BITMAP
	 */
	public ChannelBuffer encodeBitmap(BitSet bitmap, int size) {
		if ((size % 8) != 0) {
			throw new IllegalArgumentException(
					"BITMAP size must be a multiple of 8");
		}
		if (bitmap.length() > size) {
			throw new IllegalArgumentException(format(
					"Object has too many bits to encode as BITMAP(%d): %s",
					size, bitmap));
		}
		if ((bitmap.size() % 8) != 0) {
			throw new IllegalArgumentException(
					"BitSet size must be a multiple of 8: " + bitmap);
		}

		int bytes = size / 8;

		// encode in network order (big endian)
		ChannelBuffer encodedBitmap = ChannelBuffers.buffer(bytes);
		for (int i = bytes; i > 0; i--) {
			int startOfByte = (i * 8) - 8;
			encodedBitmap.writeByte((byte) (((bitmap.get(startOfByte + 7) ? 1
					: 0) << 7)
					| ((bitmap.get(startOfByte + 6) ? 1 : 0) << 6)
					| ((bitmap.get(startOfByte + 5) ? 1 : 0) << 5)
					| ((bitmap.get(startOfByte + 4) ? 1 : 0) << 4)
					| ((bitmap.get(startOfByte + 3) ? 1 : 0) << 3)
					| ((bitmap.get(startOfByte + 2) ? 1 : 0) << 2)
					| ((bitmap.get(startOfByte + 1) ? 1 : 0) << 1) | ((bitmap
					.get(startOfByte) ? 1 : 0))));
		}

		return encodedBitmap;
	}

	/**
	 * Decodes a BITMAP from a ChannelBuffer, returning it in the form of a
	 * BitSet.
	 * 
	 * @param buffer
	 *            the ChannelBuffer to read the encoded BITMAP from
	 * @param size
	 *            the size of the BITMAP, in bits
	 * @return a BitSet representing the decoded BITMAP
	 */
	public BitSet decodeBitmap(ChannelBuffer buffer, int size) {
		if ((size % 8) != 0) {
			throw new IllegalArgumentException(
					"BITMAP size must be a multiple of 8");
		}

		int bytes = size / 8;
		BitSet bitset = new BitSet(size);
		for (int currentByte = bytes - 1; currentByte >= 0; currentByte--) {
			int octet = buffer.readUnsignedByte();
			int offset = currentByte * 8;

			// set the bits one by one
			for (int bit = 7; bit >= 0; bit--) {
				if (((octet >> bit) & 0x01) == 1) {
					bitset.set(offset + bit);
				}
			}
		}

		return bitset;
	}
}
