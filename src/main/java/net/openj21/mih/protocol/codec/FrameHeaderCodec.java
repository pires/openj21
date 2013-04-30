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
