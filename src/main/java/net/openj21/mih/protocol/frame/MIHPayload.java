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