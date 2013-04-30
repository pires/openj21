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