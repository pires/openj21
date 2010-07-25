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

import java.nio.charset.Charset;

import net.openj21.mih.datatype.information_elements.CNTRY_CODE;
import net.openj21.mih.protocol.codec.basic.BitmapCodec;
import net.openj21.mih.protocol.codec.basic.IntegerCodec;
import net.openj21.mih.protocol.codec.basic.OctetCodec;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.codec.basic.UnsignedIntCodec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests that ensure the GenericEncoder encodes OCTET fields properly.
 */
public class GenericEncoderOctetTest {

	/**
	 * Unmappable chars.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEncodeUnmappableChars() {
		ChannelBuffer encoded = createGenericEncoder().encodeObject(
				new CNTRY_CODE("ÛK"));
	}

	/**
	 * String is too big.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEncodeTooLarge() {
		ChannelBuffer encoded = createGenericEncoder().encodeObject(
				new CNTRY_CODE("USA"));
	}

	/**
	 * String is too short.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEncodeTooSmall() {
		ChannelBuffer encoded = createGenericEncoder().encodeObject(
				new CNTRY_CODE("I"));
	}

	@Test
	public void testEncodeAscii() {
		final String cc = "PT";
		final byte[] expected = cc.getBytes(Charset.forName("US-ASCII"));

		ChannelBuffer encoded = createGenericEncoder().encodeObject(
				new CNTRY_CODE(cc));
		assertEquals(expected.length, encoded.readableBytes());
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], encoded.getByte(i));
		}
	}

	/**
	 * Returns a new GenericEncoder encoder instance, already wired with the
	 * necessary dependencies (not mocked).
	 * 
	 * @return a GenericEncoder
	 */
	private GenericEncoder createGenericEncoder() {
		TLVLengthCodec lengthCodec = new TLVLengthCodec();
		OctetStringCodec stringCodec = new OctetStringCodec();
		stringCodec.setLengthCodec(lengthCodec);
		GenericEncoder encoder = new GenericEncoder();
		encoder.setLengthCodec(lengthCodec);
		encoder.setStringCodec(stringCodec);
		encoder.setOctetCodec(new OctetCodec());
		encoder.setBitmapCodec(new BitmapCodec());
		encoder.setIntegerCodec(new IntegerCodec());
		encoder.setUnsignedIntCodec(new UnsignedIntCodec());
		encoder.setAnnotationUtils(new AnnotationUtils());

		return encoder;
	}
}