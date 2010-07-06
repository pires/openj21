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

import net.openj21.mih.protocol.frame.MIHAction;
import net.openj21.mih.protocol.frame.MIHHeader;
import net.openj21.mih.protocol.frame.MIHMessageID;
import net.openj21.mih.protocol.frame.MIHOperation;
import net.openj21.mih.protocol.frame.MIHService;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Decodes MIH protocol frame headers.
 */
public class FrameHeaderCodec {
    /**
     * Logger for this FrameHeaderCodec instance.
     */
    private final Logger logger = Logger.getLogger(FrameHeaderCodec.class.getName());

    /**
     * Creates a new HeaderDecoder.
     */
    public FrameHeaderCodec() {
        // empty
    }

    public MIHHeader decodeHeader(ChannelBuffer channelBuffer) throws FrameDecodeException {
        int byte2 = channelBuffer.getByte(2);
        int byte3 = channelBuffer.getByte(3);

        int sid = (byte2 & 0xf0) >> 4;
        int opcode = (byte2 & 0x0c) >> 2;
        int aid = ((byte2 & 0x03) << 8) | (byte3 & 0xff);

        MIHService service = MIHService.valueOf(sid);
        MIHOperation operation = MIHOperation.valueOf(opcode);
        MIHAction action = MIHAction.valueOf(service, aid);

        MIHMessageID mid = MIHMessageID.valueOf(service, operation, action);
        logger.log(Level.FINE, "Decoded MIHMessageID: {}", mid);

        MIHHeader header = new MIHHeader();
        header.setMessageID(mid);
        return header;
    }

    public ChannelBuffer encodeHeader(MIHHeader header, Integer payloadLength) {
        if (payloadLength > 65535) {
            throw new IllegalArgumentException("Payload is too large: " + payloadLength);
        }


        byte octet1 = (byte) ((header.getVersion() & 0x0f << 4) |
                        (((header.isAckReq() ? 1 : 0) & 0x01) << 3) |
                        (((header.isAckRsp() ? 1 : 0) & 0x01) << 2) |
                        (((header.isUnauthenticatedInfoReq() ? 1 : 0) & 0x01 << 1) |
                                (((header.isMoreFragment() ? 1 : 0) & 0x01))));
        byte octet2 = (byte) ((header.getFragmentNumber() & 0x7f) << 1 | (header.getReserved1() & 0x01));
        byte octet3 = (byte) (((header.getMessageID().getSid().value() & 0x0f) << 4) |
                        ((header.getMessageID().getOpcode().value() & 0x03) << 2) |
                        (header.getMessageID().getAID().value() & 0x0300));
        byte octet4 = (byte) (header.getMessageID().getAID().value() & 0xff);
        byte octet5 = (byte) (((header.getReserved2() & 0x0f) << 4) | ((header.getTransactionID() & 0x0f00) >> 8));
        byte octet6 = (byte) (header.getTransactionID() & 0xff);
        byte octet7 = (byte) ((payloadLength & 0xff00) >> 8);
        byte octet8 = (byte) (payloadLength & 0xff);

        ChannelBuffer encodedHeader = ChannelBuffers.buffer(8);
        encodedHeader.writeByte(octet1);
        encodedHeader.writeByte(octet2);
        encodedHeader.writeByte(octet3);
        encodedHeader.writeByte(octet4);
        encodedHeader.writeByte(octet5);
        encodedHeader.writeByte(octet6);
        encodedHeader.writeByte(octet7);
        encodedHeader.writeByte(octet8);

        return encodedHeader;
    }
}
