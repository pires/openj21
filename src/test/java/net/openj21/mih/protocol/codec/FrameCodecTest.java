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

import static net.openj21.mih.protocol.codec.FrameCodec.HEADER_BYTES;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.openj21.mih.network.MIHTestFrame;
import net.openj21.mih.protocol.frame.MIHHeader;
import net.openj21.mih.protocol.frame.MIHMessageID;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Tests for decoding the header in a MIH frame.
 */
public class FrameCodecTest {
    /**
     * Header decoder mock.
     */
    @Mock
    private FrameHeaderCodec headerCodec;

    /**
     * Payload codec mock.
     */
    @Mock
    private FramePayloadCodec payloadCodec;

    /**
     * Test that an empty MIH frame generates an exception.
     * @throws FrameDecodeException 
     */
    @Test(expected = FrameDecodeException.class)
    public void testDecodeEmptyFrameDropped() throws FrameDecodeException {
        ChannelBuffer frame = ChannelBuffers.buffer(0);
        createFrameCodec().decodeFrame(frame);
    }

    /**
     * Tests that a MIHP frame header is forwarded to the correct decoder.
     *
     * @throws FrameDecodeException if anything goes wrong
     */
    @Test
    public void testDecodeHeaderForwarded() throws FrameDecodeException {
        ChannelBuffer frame = MIHTestFrame.createFrame();
        MIHHeader header = new MIHHeader();
        header.setMessageID(MIHMessageID.MIH_GET_INFORMATION_INDICATION);
        when(headerCodec.decodeHeader(any(ChannelBuffer.class))).thenReturn(header);

        // wiring
        FrameCodec frameCodec = createFrameCodec();

        // test
        frameCodec.decodeFrame(frame);
        verify(headerCodec).decodeHeader(frame.slice(0, 8));
    }

    /**
     * Tests that a MIH protocol frame payload is forwarded to the service specific decoder.
     *
     * @throws FrameDecodeException if anything goes wrong
     */
    @Test
    public void testDecodePayloadForwarded() throws FrameDecodeException {
        ChannelBuffer frame = MIHTestFrame.createFrame();

        // mock
        MIHHeader header = new MIHHeader();
        header.setMessageID(MIHMessageID.MIH_GET_INFORMATION_INDICATION);
        when(headerCodec.decodeHeader(any(ChannelBuffer.class))).thenReturn(header);

        // wiring
        FrameCodec frameCodec = createFrameCodec();

        // test
        frameCodec.decodeFrame(frame);
        verify(payloadCodec).decodePayload(frame.slice(HEADER_BYTES, frame.readableBytes() - HEADER_BYTES));
    }
    
    /**
     * Helper method
     * 
     * @return the FrameCodec
     */
    private FrameCodec createFrameCodec() {
        FrameCodec frameCodec = new FrameCodec();
        frameCodec.setHeaderCodec(headerCodec);
        frameCodec.setPayloadCodec(payloadCodec);
        return frameCodec;
    }

    /**
     * Set up Mockito mocks.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}