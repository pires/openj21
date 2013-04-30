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

import static org.junit.Assert.assertEquals;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;

/**
 * Tests for Length field encoding.
 */
public class TLVLengthCodecTest {

	/**
	 * Tests that the length 1 is being well encoded.
	 */
	@Test
	public void testEncodeLength_1() {
		ChannelBuffer encoded = new TLVLengthCodec().encodeLength(1);

		// expected: 0000 0001
		assertEquals(1, encoded.readableBytes());
		assertEquals(1, encoded.readByte());
	}

	/**
	 * Tests that the length 127 is being well encoded.
	 */
	@Test
	public void testEncodeLength_127() {
		ChannelBuffer encoded = new TLVLengthCodec().encodeLength(127);

		// expected 0111 1111
		assertEquals(1, encoded.readableBytes());
		assertEquals(127, encoded.readByte());
	}

	/**
	 * Tests that the length 128 is being well encoded.
	 */
	@Test
	public void testEncodeLength_128() {
		ChannelBuffer encoded = new TLVLengthCodec().encodeLength(128);

		// expected: 1000 0000
		assertEquals(1, encoded.readableBytes());
		assertEquals(0x80, encoded.readByte() & 0xff);
	}

	/**
	 * Tests that the length 129 is being well encoded.
	 */
	@Test
	public void testEncodeLength_129() {
		ChannelBuffer encoded = new TLVLengthCodec().encodeLength(129);

		// expected: 1000 0001 0000 0001
		assertEquals(2, encoded.readableBytes());
		assertEquals(0x81, encoded.readByte() & 0xff);
		assertEquals(0x01, encoded.readByte() & 0xff);
	}

	/**
	 * Tests that the length 383 is being well encoded.
	 */
	@Test
	public void testEncodeLength_383() {
		ChannelBuffer encoded = new TLVLengthCodec().encodeLength(383);

		// expected: 1000 0001 1111 1111
		assertEquals(2, encoded.readableBytes());
		assertEquals(0x81, encoded.readByte() & 0xff);
		assertEquals(0xff, encoded.readByte() & 0xff);
	}

	/**
	 * Tests that the length 384 is being well encoded.
	 */
	@Test
	public void testEncodeLength_384() {
		ChannelBuffer encoded = new TLVLengthCodec().encodeLength(384);

		// expected: 1000 0002 0000 0001 0000 0000
		assertEquals(3, encoded.readableBytes());
		assertEquals(0x82, encoded.readByte() & 0xff);
		assertEquals(0x01, encoded.readByte() & 0xff);
		assertEquals(0x00, encoded.readByte() & 0xff);
	}

	/**
	 * Tests that an element of length 1 is properly decoded.
	 */
	@Test
	public void testDecodeLength1() {
		int len = new TLVLengthCodec().decodeLength(ChannelBuffers
				.wrappedBuffer(new byte[] { 1 }));
		assertEquals(1, len);
	}

	/**
	 * Tests that an element of length 128 is properly decoded.
	 */
	@Test
	public void testDecodeLength128() {
		int len = new TLVLengthCodec().decodeLength(ChannelBuffers
				.wrappedBuffer(new byte[] { (byte) 0x80 }));
		assertEquals(128, len);
	}

	/**
	 * Tests that an element with a length of 200 is properly decoded.
	 */
	@Test
	public void testDecodeLength200() {
		int len = new TLVLengthCodec()
				.decodeLength(ChannelBuffers.wrappedBuffer(new byte[] {
						(byte) 0x81, (byte) (200 & 0xff) }));
		assertEquals(200, len);
	}

	/**
	 * Try to decode a length that can not be stored in an int. This should
	 * throw an exception, as otherwise we would overflow the length field.
	 */
	@Test(expected = RuntimeException.class)
	public void testDecodeLengthOverflow() {
		new TLVLengthCodec()
				.decodeLength(ChannelBuffers.wrappedBuffer(new byte[] {
						(byte) 0x84, 0x01, 0x01, 0x01, 0x01 }));
	}
}