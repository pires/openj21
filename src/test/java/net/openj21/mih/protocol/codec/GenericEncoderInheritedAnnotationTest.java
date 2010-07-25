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