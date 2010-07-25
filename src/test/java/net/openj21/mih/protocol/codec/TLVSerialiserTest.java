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