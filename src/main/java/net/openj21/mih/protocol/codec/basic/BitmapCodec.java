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
