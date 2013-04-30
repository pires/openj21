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
package net.openj21.mih.network;

import static net.openj21.mih.protocol.codec.FrameCodec.HEADER_BYTES;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Helper class that creates a MIH test frame.
 */
public class MIHTestFrame {
    /**
     * Creates a frame to be decoded in the test.
     *
     * @return a ChannelBuffer with a header and a fictitious payload
     */
    public static ChannelBuffer createFrame() {
        ChannelBuffer header = createHeader();
        ChannelBuffer payload = createPayload();

        return ChannelBuffers.wrappedBuffer(header, payload);
    }

    /**
     * Returns the source MIHF_ID.
     *
     * @return the source MIHF_ID
     */
    public static ChannelBuffer getSourceMIHF_ID() {
        return encodeOctetString("source.local");
    }
    
    /**
     * Returns the destination MIHF_ID.
     *
     * @return the destination MIHF_ID
     */
    public static ChannelBuffer getDestinationMIHF_ID() {
        return encodeOctetString("destination.local");
    }

    /**
     * Returns the service-specific TLV part of the test frame.
     *
     * @return the service-specific TLV part of the test frame
     */
    public static ChannelBuffer getServiceSpecificTLV() {
        ChannelBuffer vendorSpecificIE = ChannelBuffers.dynamicBuffer();
        vendorSpecificIE.writeInt(0x7FFFFFFF); // vendor specific IE id

        return vendorSpecificIE;
    }

    public static ChannelBuffer createHeader() {
        ChannelBuffer header = ChannelBuffers.buffer(HEADER_BYTES);
        header.writeLong(0xFFFFFFFFFFFFFFFFL); // nonsense

        return header;
    }

    public static ChannelBuffer createPayload() {
        ChannelBuffer sourceDest = ChannelBuffers.dynamicBuffer();
        sourceDest.writeLong(0xFFFFFFFFFFFFFFFFL);

        return ChannelBuffers.wrappedBuffer(sourceDest, getServiceSpecificTLV());
    }

    /**
     * Prevent instantiation.
     */
	private MIHTestFrame() {
        // empty
    }

    /**
     * Encodes a String into an OCTET_STRING.
     *
     * @param string the String to encode
     * @return a String encoded as OCTET_STRING
     */
    private static ChannelBuffer encodeOctetString(String string) {
        byte[] stringBytes = string.getBytes(Charset.forName("UTF-8"));

        ChannelBuffer length = ChannelBuffers.dynamicBuffer();
        length.writeByte((byte) stringBytes.length);

        ChannelBuffer value = ChannelBuffers.copiedBuffer(stringBytes);

        return ChannelBuffers.wrappedBuffer(length, value);
    }
}