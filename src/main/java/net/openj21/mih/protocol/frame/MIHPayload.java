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
package net.openj21.mih.protocol.frame;

import net.openj21.mih.datatype.TLVType;
import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.identification.MIHF_ID;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * This class contains MIH protocol payload.
 */
@SEQUENCE
public class MIHPayload {
    @SEQUENCE_ELEMENT(order = 1, tlv = TLVType.SOURCE_MIHF_ID)
    MIHF_ID source = new MIHF_ID("");

    @SEQUENCE_ELEMENT(order = 2, tlv = TLVType.DESTINATION_MIHF_ID)
    MIHF_ID destination = new MIHF_ID("");

    @SEQUENCE_ELEMENT(order = 3, basicType = true)
    @OCTET
    ChannelBuffer serviceSpecificTLVs;

    public MIHPayload() {
        // empty
    }

    public MIHPayload(MIHF_ID source, MIHF_ID destination, ChannelBuffer serviceSpecificTLVs) {
        this.source = source;
        this.destination = destination;
        this.serviceSpecificTLVs = serviceSpecificTLVs;
    }

    public MIHF_ID getSource() {
        return source;
    }

    public void setSource(MIHF_ID source) {
        this.source = source;
    }

    public MIHF_ID getDestination() {
        return destination;
    }

    public void setDestination(MIHF_ID destination) {
        this.destination = destination;
    }

    public ChannelBuffer getServiceSpecificTLVs() {
        return serviceSpecificTLVs;
    }

    public void setServiceSpecificTLVs(ChannelBuffer serviceSpecificTLVs) {
        this.serviceSpecificTLVs = serviceSpecificTLVs;
    }

    @Override
    public String toString() {
        return "MIHPayload{" +
                "source=" + source +
                ", destination=" + destination +
                ", serviceSpecificTLVs=" + serviceSpecificTLVs +
                '}';
    }
}