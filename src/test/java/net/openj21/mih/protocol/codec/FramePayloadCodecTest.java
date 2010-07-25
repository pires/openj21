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
import static org.mockito.Mockito.when;

import java.util.Arrays;

import net.openj21.mih.datatype.TLVType;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.frame.MIHPayload;
import net.openj21.mih.services.ServiceRequest;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Tests for MIH protocol payload decoder.
 */
public class FramePayloadCodecTest {
	/**
	 * TLVSerializer mock.
	 */
	@Mock
	private TLVSerialiser serialiser;

	/**
	 * OctetStringCodec mock.
	 */
	@Mock
	private OctetStringCodec stringCodec;

	/**
	 * Verifies that out of a MIH protocol frame, the service-specific TLVs are
	 * returned as-is.
	 * 
	 * @throws FrameDecodeException
	 *             if the test fails
	 */
	@Ignore("won't pass because GetInformationRequest is not implemented.")
	@Test
	public void testServiceSpecificTLVsForwarded() throws FrameDecodeException {
		byte[] sourceType = (new byte[] { TLVType.SOURCE_MIHF_ID.byteValue() });
		byte[] sourceLen = (new byte[] { 1 });
		byte[] sourceValue = (new byte[] { 'S' });
		ChannelBuffer source = ChannelBuffers.wrappedBuffer(sourceType,
				sourceLen, sourceValue);

		byte[] destType = (new byte[] { TLVType.DESTINATION_MIHF_ID.byteValue() });
		byte[] destLen = (new byte[] { 1 });
		byte[] destValue = (new byte[] { 'D' });
		ChannelBuffer dest = ChannelBuffers.wrappedBuffer(destType, destLen,
				destValue);

		byte[] vSpecType = new byte[] { TLVType.VENDOR_SPECIFIC.byteValue() };
		byte[] vSpecLen = (new byte[] { 2 });
		byte[] vSpecValue = (new byte[] { 100, -100 });
		ChannelBuffer vSpec = ChannelBuffers.wrappedBuffer(vSpecType, vSpecLen,
				vSpecValue);

		// the whole payload
		ChannelBuffer payload = ChannelBuffers.wrappedBuffer(source, dest,
				vSpec);

		// mock
		when(serialiser.deserialise(payload)).thenReturn(
				Arrays.asList(
						new TLVElement(TLVType.SOURCE_MIHF_ID, source
								.readableBytes(), source),
						new TLVElement(TLVType.DESTINATION_MIHF_ID, dest
								.readableBytes(), dest), new TLVElement(
								TLVType.VENDOR_SPECIFIC, vSpec.readableBytes(),
								vSpec)));
		when(stringCodec.decode(source)).thenReturn("S");
		when(stringCodec.decode(dest)).thenReturn("D");

		// wire
		// TODO
		// FramePayloadCodec<GetInformationRequest> payloadCodec =
		// createDecoder();
		FramePayloadCodec payloadCodec = createDecoder();
		payloadCodec.setTlvSerialiser(serialiser);
		payloadCodec.setStringCodec(stringCodec);

		// test that the service specific TLV is returned as-is
		MIHPayload result = payloadCodec.decodePayload(payload);
		assertEquals(vSpec, result.getServiceSpecificTLVs());
	}

	/**
	 * Set up Mockito mocks.
	 */
	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Creates a new FramePayloadCodec instance for testing.
	 * 
	 * @param <T>
	 *            the generic parameter
	 * @return a new FramePayloadCodec instance
	 */
	protected <T extends ServiceRequest> FramePayloadCodec<T> createDecoder() {
		return new FramePayloadCodec<T>();
	}
}