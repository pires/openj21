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

import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.information_elements.CNTRY_CODE;
import net.openj21.mih.datatype.location.BIN_CIVIC_LOC;
import net.openj21.mih.datatype.location.CIVIC_ADDR;
import net.openj21.mih.datatype.location.LOCATION;
import net.openj21.mih.datatype.location.XML_CIVIC_LOC;
import net.openj21.mih.protocol.codec.basic.BitmapCodec;
import net.openj21.mih.protocol.codec.basic.IntegerCodec;
import net.openj21.mih.protocol.codec.basic.OctetCodec;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.codec.basic.UnsignedIntCodec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.Test;

/**
 * Tests that ensure the GenericEncoder encodes CHOICE fields properly.
 */
public class GenericEncoderChoiceTest {

	/**
	 * Tests that the BIN_CIVIC_LOCATION is correctly encoded when being used
	 * through a polymorphic association.
	 */
	@Test
	public void testEncodeBinCivicLocation() {
		LOC_CONTAINER container = new LOC_CONTAINER(new BIN_CIVIC_LOC(
				new CNTRY_CODE("PT"), new CIVIC_ADDR("Street Address")));
		ChannelBuffer encoded = createGenericEncoder().encodeObject(container);

		assertEquals(0, encoded.readByte()); // LOCATION selector
		assertEquals(0, encoded.readByte()); // CIVIC_LOC selector
		assertEqualsCharAndByte('P', encoded.readByte());
		assertEqualsCharAndByte('T', encoded.readByte());
		assertEquals(14, encoded.readByte()); // OCTET_STRING length
		assertEqualsCharAndByte('S', encoded.readByte());
		assertEqualsCharAndByte('t', encoded.readByte());
		assertEqualsCharAndByte('r', encoded.readByte());
		assertEqualsCharAndByte('e', encoded.readByte());
		assertEqualsCharAndByte('e', encoded.readByte());
		assertEqualsCharAndByte('t', encoded.readByte());
		assertEqualsCharAndByte(' ', encoded.readByte());
		assertEqualsCharAndByte('A', encoded.readByte());
		assertEqualsCharAndByte('d', encoded.readByte());
		assertEqualsCharAndByte('d', encoded.readByte());
		assertEqualsCharAndByte('r', encoded.readByte());
		assertEqualsCharAndByte('e', encoded.readByte());
		assertEqualsCharAndByte('s', encoded.readByte());
		assertEqualsCharAndByte('s', encoded.readByte());
	}

	/**
	 * Tests that the XML_CIVIC_LOCATION is correctly encoded when being used
	 * through a polymorphic association.
	 */
	@Test
	public void testEncodeXmlCivicLocation() {
		LOC_CONTAINER container = new LOC_CONTAINER(new XML_CIVIC_LOC("<xml/>"));
		ChannelBuffer encoded = createGenericEncoder().encodeObject(container);

		assertEquals(0, encoded.readByte()); // LOCATION selector
		assertEquals(1, encoded.readByte()); // CIVIC_LOC selector
		assertEquals(6, encoded.readByte()); // OCTET_STRING length
		assertEqualsCharAndByte('<', encoded.readByte());
		assertEqualsCharAndByte('x', encoded.readByte());
		assertEqualsCharAndByte('m', encoded.readByte());
		assertEqualsCharAndByte('l', encoded.readByte());
		assertEqualsCharAndByte('/', encoded.readByte());
		assertEqualsCharAndByte('>', encoded.readByte());
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

	/**
	 * Asserts that the given character, when encoded using UTF-8, is equal to
	 * the given byte.
	 * 
	 * @param c
	 *            a character
	 * @param b
	 *            a byte
	 */
	private void assertEqualsCharAndByte(char c, byte b) {
		assertEquals(("" + c).getBytes(Charset.forName("UTF-8"))[0], b);
	}

	/**
	 * Test class for testing polymorphic encoding.
	 */
	@SEQUENCE
	static class LOC_CONTAINER {
		@SEQUENCE_ELEMENT(order = 1)
		LOCATION location;

		public LOC_CONTAINER(LOCATION bin_civic_loc) {
			location = bin_civic_loc;
		}
	}
}