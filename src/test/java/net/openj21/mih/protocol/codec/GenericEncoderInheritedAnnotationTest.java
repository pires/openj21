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

import java.lang.annotation.Inherited;
import java.nio.charset.Charset;

import net.openj21.mih.datatype.link.param.ABSTRACT_LINK_PARAM;
import net.openj21.mih.datatype.link.param.LINK_PARAM_802_11;
import net.openj21.mih.protocol.codec.AnnotationUtils;
import net.openj21.mih.protocol.codec.GenericEncoder;
import net.openj21.mih.protocol.codec.TLVLengthCodec;
import net.openj21.mih.protocol.codec.basic.BitmapCodec;
import net.openj21.mih.protocol.codec.basic.IntegerCodec;
import net.openj21.mih.protocol.codec.basic.OctetCodec;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.codec.basic.UnsignedIntCodec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.Test;

/**
 * This test was designed to assert annotation inheritance.
 * 
 * LINK_PARAM_802_11 is the class to be tested. If successful, it means all
 * ABSTRACT_LINK_PARAM derived classes are working and therefore that annotation
 * inheritance works.
 * 
 * @see Inherited
 * @see ABSTRACT_LINK_PARAM
 * @see LINK_PARAM_802_11
 */
public class GenericEncoderInheritedAnnotationTest {

	@Test
	public void testEncoding() {
		LINK_PARAM_802_11 that = new LINK_PARAM_802_11(5);
		ChannelBuffer encoded = createGenericEncoder().encodeObject(that);

		assertEquals(5, encoded.readByte());
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