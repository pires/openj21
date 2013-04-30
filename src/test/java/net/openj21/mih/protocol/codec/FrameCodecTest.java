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