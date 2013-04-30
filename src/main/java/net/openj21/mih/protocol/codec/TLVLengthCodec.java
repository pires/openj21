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

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class is responsible for encoding and decoding the length of a
 * TLVElement from and to binary form (see 6.5.6.2).
 */
public class TLVLengthCodec {
    /**
     * Creates a new TLVLengthCodec instance.
     */
    public TLVLengthCodec() {
        // empty
    }

    /**
     * Decodes a list length, as specified in 6.5.6.2 of the 802.21
     * specification. This method advances the given channel buffer's reader
     * index by the number of bytes in the length field (i.e. upon return of
     * this method, the read index is at the first position of the value part).
     *
     * @param buffer the encoded TLV
     * @return the decoded length
     */
    public int decodeLength(ChannelBuffer buffer) {
        // check the first byte
        int b1 = buffer.readUnsignedByte();
        if (b1 <= 128) {
            // the length field is a single byte
            return b1;
        }

        // find out the size of the variable length field
        int lengthBytes = b1 & 0x7f;
        if (lengthBytes > 3) {
            // TODO allow the (exotic?) case where additionalBytes > 3 but an int can still be used
            throw new IllegalArgumentException("Length is too large to store in int: " + lengthBytes);
        }

        int b2 = buffer.readUnsignedByte();
        if (lengthBytes == 1) return b2;

        int b3 = buffer.readUnsignedByte();
        if (lengthBytes == 2) return (b2 << 8) | b3;

        int b4 = buffer.readUnsignedByte();
        return (b2 << 16) | (b3 << 8) | b4;
    }

    /**
     * Encodes the list length as a variable length sequence of bytes, as per the following rules:
     * <p/>
     * <h4>Case 1</h4>
     * If the number of list elements in the Value field is less than 128, the size of the Length field is always
     * one octet and the MSB of the octet is set to the value ‘0’. The values of the other seven bits of this octet indi-
     * cate the actual number of list elements in the Value field.
     * <h4>Case 2</h4>
     * If the number of list elements in the Value field is exactly 128, the size of the Length field is one
     * octet. The MSB of the Length octet is set to the value '1' and the other seven bits of this octet are all set to the
     * value ‘0’.
     * <h4>Case 3</h4>
     * If the number of list elements in the Value field is greater than 128, then the Length field is always
     * greater than one octet. The MSB of the first octet of the Length field is set to the value ‘1’ and the remaining
     * seven bits of the first octet indicate the number of octets that are appended further. The number represented
     * by the 2nd and subsequent octets of the Length field, when added to 128, indicates the total number of list
     * elements in the Value field.
     *
     * @param length the length of the list
     * @return a ChannelBuffer containing the encoded length
     */
    public ChannelBuffer encodeLength(long length) {
        ChannelBuffer encodedLength = ChannelBuffers.dynamicBuffer(1);
        if (length <= 128) { // cases 1 and 2
            encodedLength.writeByte((byte) (length & 0x000000ff));
        }
        else { // case 3
            final long lengthMinus128 = length - 128;
            int additionalOctets = unsignedByteCount(lengthMinus128);

            // The MSB of the first octet of the Length field is set to the
            // value ‘1’ and the remaining seven bits of the first octet indicate
            // the number of octets that are appended further.
            encodedLength.writeByte((byte) (additionalOctets | 0x00000080)); // byte 1

            // write bytes 2..additionalOctets
            while (additionalOctets-- > 0) {
                encodedLength.writeByte((byte) ((lengthMinus128 >> (8 * additionalOctets))));
            }
        }

        return encodedLength;
    }

    /**
     * Returns the number of bytes that necessary to represent the given value as an unsigned integer.
     *
     * @param x the value to encode
     * @return the number of bytes necessary to represent the value as an unsigned integer
     */
    static private int unsignedByteCount(long x) {
        // logarithm change of base
        double log2 = Math.log(x + 1) / Math.log(2);

        return (int) Math.ceil(Math.ceil(log2) / 8);
    }
}
