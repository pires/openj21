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

import java.util.logging.Level;
import java.util.logging.Logger;

import net.openj21.mih.protocol.frame.MIHFrame;
import net.openj21.mih.protocol.frame.MIHHeader;
import net.openj21.mih.protocol.frame.MIHPayload;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class decodes MIH protocol frames.
 */
public class FrameCodec {
    /**
     * The number of bytes that correspond to the frame header.
     */
    public static final int HEADER_BYTES = 8;

    /**
     * Logger for this FrameCodec instance.
     */
    private final Logger logger = Logger.getLogger(FrameCodec.class.getName());

    /**
     * The header decoder.
     */
    private FrameHeaderCodec headerCodec;

    /**
     * The payload decoder.
     */
    private FramePayloadCodec payloadCodec;

    /**
     * Creates a new FrameCodec.
     */
    public FrameCodec() {
        // empty
    }

    /**
     * Sets the decoder that will be used for the MIH protocol frame header.
     *
     * @param headerCodec a FrameHeaderCodec instance
     */
    public void setHeaderCodec(FrameHeaderCodec headerCodec) {
        this.headerCodec = headerCodec;
    }

    /**
     * Sets the codec that will be used for the MIH protocol frame payload.
     *
     * @param payloadCodec a FramePayloadCodec
     */
    public void setPayloadCodec(FramePayloadCodec payloadCodec) {
        this.payloadCodec = payloadCodec;
    }

    /**
     * Attempts to decode a ChannelBuffer instance into a MIHFrame. If this method is unable to decode the encodedFrame, it
     * throws an IOException.
     *
     * @param encodedFrame a datagram that was received
     * @return a MIHFrame
     * @throws FrameDecodeException if the encodedFrame is invalid for some reason
     */
    public MIHFrame decodeFrame(ChannelBuffer encodedFrame) throws FrameDecodeException {
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Received frame: {}", toString(encodedFrame));
        }

        int readableBytes = encodedFrame.readableBytes();
        logger.log(Level.FINE, "Frame has {} bytes", readableBytes);
        if (readableBytes < HEADER_BYTES) {
            throw new FrameDecodeException("Illegal encodedFrame (" + readableBytes + " bytes available)");
        }

        int payloadBytes = readableBytes - HEADER_BYTES;
        if (payloadBytes == 0) {
            throw new FrameDecodeException("Illegal encodedFrame (no payload)");
        }



		MIHHeader header = headerCodec.decodeHeader(encodedFrame.slice(0, HEADER_BYTES));
        MIHPayload payload = payloadCodec.decodePayload(encodedFrame.slice(HEADER_BYTES, payloadBytes));

        MIHFrame mihFrame = new MIHFrame(header, payload);
        logger.log(Level.FINE, "Decoded MIHFrame: {}", mihFrame);

        return mihFrame;
    }

    public ChannelBuffer encodeFrame(MIHFrame frame) {
        logger.log(Level.FINE, "Encoding frame payload: {}", frame.getPayload());
        ChannelBuffer encodedPayload = payloadCodec.encodePayload(frame.getPayload());

        logger.log(Level.FINE, "Encoding frame header: {}", frame.getHeader());
        ChannelBuffer encodedHeader = headerCodec.encodeHeader(frame.getHeader(), encodedPayload.readableBytes());

        ChannelBuffer encodedFrame = ChannelBuffers.wrappedBuffer(encodedHeader, encodedPayload);
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Encoded frame dump: {}", toString(encodedFrame));
        }

        return encodedFrame;
    }

    /**
     * Returns a string representation of the given buffer in hexadecimal format.
     *
     * @param buffer a ChannelBuffer
     * @return a string representation in hexadecimal format
     */
    protected String toString(ChannelBuffer buffer) {
        StringBuilder frameInHex = new StringBuilder(buffer.readableBytes() * 3);
        for (int i = 0; i < buffer.readableBytes(); i++) {
            frameInHex.append(String.format("%02X ", buffer.getByte(i)));
        }

        return frameInHex.toString();
    }
}