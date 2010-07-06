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
    @SuppressWarnings("unchecked")
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