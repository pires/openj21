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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.openj21.mih.datatype.TLVType;
import net.openj21.mih.datatype.identification.MIHF_ID;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.frame.MIHPayload;
import net.openj21.mih.services.ServiceRequest;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Decodes MIH protocol frame payloads. The payload contains 3 elements:
 * <ol>
 * <li>The source MIHF_ID
 * <li>The destination MIHF_ID
 * <li>The service-specific TLV
 * </ol>
 * This class decodes 1 and 2 but it passes 3 as-is into the created MIHPayload.
 */
public class FramePayloadCodec<T extends ServiceRequest> {
	/**
	 * Logger for this FramePayloadCodec instance.
	 */
	private final Logger logger = Logger.getLogger(FramePayloadCodec.class
			.getName());

	/**
	 * The TLV serializer instance.
	 */
	private TLVSerialiser tlvSerialiser;

	/**
	 * The GenericEncoder instance.
	 */
	private GenericEncoder genericEncoder;

	/**
	 * The OctetStringCodec instance.
	 */
	private OctetStringCodec stringCodec;

	/**
	 * Creates a new HeaderDecoder.
	 */
	public FramePayloadCodec() {
		// empty
	}

	/**
	 * Sets the TLVSerialiser instance.
	 * 
	 * @param tlvSerialiser
	 *            the TLVSerialiser instance
	 */
	public void setTlvSerialiser(TLVSerialiser tlvSerialiser) {
		this.tlvSerialiser = tlvSerialiser;
	}

	/**
	 * Sets the GenericEncoder instance.
	 * 
	 * @param genericEncoder
	 *            the GenericEncoder instance
	 */
	public void setGenericEncoder(GenericEncoder genericEncoder) {
		this.genericEncoder = genericEncoder;
	}

	/**
	 * Sets the OctetStringCodec instance.
	 * 
	 * @param stringCodec
	 *            a OctetStringCodec
	 */
	public void setStringCodec(OctetStringCodec stringCodec) {
		this.stringCodec = stringCodec;
	}

	public MIHPayload decodePayload(ChannelBuffer channelBuffer)
			throws FrameDecodeException {
		int payloadBytes = channelBuffer.readableBytes();

		// step 1: deserialise
		List<TLVElement> tlvs = tlvSerialiser.deserialise(channelBuffer);
		validatePayload(tlvs);

		// for now, don't decode these
		TLVElement srcTLV = tlvs.get(0);
		MIHF_ID source = new MIHF_ID(stringCodec.decode(srcTLV.getValue()));
		logger.log(Level.FINE, "Source is: {}", source);

		TLVElement dstTLV = tlvs.get(1);
		MIHF_ID destination = new MIHF_ID(stringCodec.decode(dstTLV.getValue()));
		logger.log(Level.FINE, "Destination is: {}", destination);

		// the service-specific TLV starts after the source and destination
		// MIHF_ID's. don't decode it
		int ssTLVStart = srcTLV.getLength() + dstTLV.getLength();
		ChannelBuffer serviceSpecificTLV = channelBuffer.slice(ssTLVStart,
				payloadBytes - ssTLVStart);

		// mih service specific tlv's
		MIHPayload result = new MIHPayload();
		result.setSource(source);
		result.setDestination(destination);
		result.setServiceSpecificTLVs(serviceSpecificTLV);
		logger.log(Level.FINE, "Decoded MIHPayload: {}", result);

		return result;
	}

	public ChannelBuffer encodePayload(MIHPayload payload) {
		return genericEncoder.encodeObject(payload);
	}

	private void validatePayload(List<TLVElement> tlvs)
			throws FrameDecodeException {
		if (tlvs.size() < 3) {
			throw new FrameDecodeException(
					"Expecting at least 3 TLV elements. Received: " + tlvs);
		}

		if (tlvs.get(0).getType() != TLVType.SOURCE_MIHF_ID) {
			throw new FrameDecodeException("Expecting "
					+ TLVType.SOURCE_MIHF_ID
					+ " as first TLV element. Received: " + tlvs.get(0));
		}

		if (tlvs.get(1).getType() != TLVType.DESTINATION_MIHF_ID) {
			throw new FrameDecodeException("Expecting "
					+ TLVType.DESTINATION_MIHF_ID
					+ " as second TLV element. Received: " + tlvs.get(1));
		}
	}
}