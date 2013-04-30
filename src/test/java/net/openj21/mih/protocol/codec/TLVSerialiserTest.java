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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import net.openj21.mih.datatype.TLVType;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * TLVSerialiser tests.
 */
public class TLVSerialiserTest {
	/**
	 * Mock TLVLengthCodec.
	 */
	@Mock
	private TLVLengthCodec lengthCodec;

	/**
	 * Tests that the deserialisation of a "long" (200 byte) element is correct.
	 */
	@Test
	public void testDeserialiseLongElement() {
		final int repeat = 200;
		final char z = 'z';

		// prepare the buffer
		ChannelBuffer buffer = ChannelBuffers.buffer(1 + 2 + repeat);
		buffer.writeByte(TLVType.VENDOR_SPECIFIC.byteValue());
		buffer.writeByte((byte) 0x81);
		buffer.writeByte((byte) 200);
		for (int i = repeat; i > 0; i--) {
			buffer.writeByte((byte) z);
		}

		// stubbing
		when(lengthCodec.decodeLength(any(ChannelBuffer.class)))
				.thenCallRealMethod();

		// wiring
		TLVSerialiser serialiser = new TLVSerialiser();
		serialiser.setLengthCodec(lengthCodec);

		List<TLVElement> tlvs = serialiser.deserialise(ChannelBuffers
				.unmodifiableBuffer(buffer));
		assertEquals(1, tlvs.size());

		// verify the element contents
		TLVElement elem = tlvs.get(0);
		assertEquals(TLVType.VENDOR_SPECIFIC, elem.getType());
		assertEquals(buffer.readableBytes(), elem.getLength());
		assertEquals(repeat, elem.getValue().readableBytes());
		while (elem.getValue().readable()) {
			assertEquals((byte) z, elem.getValue().readByte());
		}
	}

	/**
	 * Tests that a sequence of 2 elements is properly decoded.
	 */
	@Test
	public void testDeserialiseTwoElements() {
		final char charA = 'a';
		final char charB = 'b';

		ChannelBuffer elem1 = ChannelBuffers.wrappedBuffer(new byte[] {
				TLVType.VENDOR_SPECIFIC.byteValue(), 1, charA });
		ChannelBuffer elem2 = ChannelBuffers.wrappedBuffer(new byte[] {
				TLVType.VENDOR_SPECIFIC.byteValue(), 1, charB });

		// stubbing
		when(lengthCodec.decodeLength(any(ChannelBuffer.class)))
				.thenCallRealMethod();

		// wiring
		TLVSerialiser serialiser = new TLVSerialiser();
		serialiser.setLengthCodec(lengthCodec);

		// test
		List<TLVElement> tlvs = serialiser.deserialise(ChannelBuffers
				.wrappedBuffer(elem1, elem2));
		assertEquals(2, tlvs.size());

		// elem1
		assertEquals(TLVType.VENDOR_SPECIFIC, tlvs.get(0).getType());
		assertEquals(elem1.readableBytes(), tlvs.get(0).getLength());
		assertEquals(charA, tlvs.get(0).getValue().readByte());

		// elem2
		assertEquals(TLVType.VENDOR_SPECIFIC, tlvs.get(1).getType());
		assertEquals(elem2.readableBytes(), tlvs.get(1).getLength());
		assertEquals(charB, tlvs.get(1).getValue().readByte());
	}

	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
}