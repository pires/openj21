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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.openj21.mih.datatype.TLVType;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * This class knows how to encode and decode the TLV format.
 * 
 * @see TLVType
 */
public class TLVSerialiser {
	/**
	 * Logger for this TLVSerialiser instance.
	 */
	private final Logger logger = Logger.getLogger(TLVSerialiser.class
			.getName());

	/**
	 * The class that is responsible for encoding/decoding TLV lengths.
	 */
	TLVLengthCodec lengthCodec;

	/**
	 * Creates a new TLVCodec instance.
	 */
	public TLVSerialiser() {
		// empty
	}

	/**
	 * Sets the TLVLengthCodec instance used to encode and decode lengths.
	 * 
	 * @param lengthCodec
	 *            a TLVLengthCodec
	 */
	public void setLengthCodec(TLVLengthCodec lengthCodec) {
		this.lengthCodec = lengthCodec;
	}

	/**
	 * Serialises a list of TLVElement into a TLV-encoded stream of bytes,
	 * stored in a ChannelBuffer.
	 * 
	 * @param elements
	 *            a List of TLVElement
	 * @return a ChannelBuffer
	 */
	public ChannelBuffer serialise(List<TLVElement> elements) {
		throw new UnsupportedOperationException("TODO");
	}

	/**
	 * Consumes a byte buffer and returns a List of TLVElements.
	 * 
	 * @param encoded
	 *            a serialised stream of TLV-encoded elements
	 * @return a List of TLVElement
	 */
	public List<TLVElement> deserialise(ChannelBuffer encoded) {
		logger.log(Level.FINE, "Starting to deserialise a stream of bytes");
		List<TLVElement> elements = new ArrayList<TLVElement>();

		int tlvCount = 0;
		// consume the buffer, building up a list of elements
		while (encoded.readable()) {
			tlvCount++;
			int startIndex = encoded.readerIndex();

			// decode
			TLVType type = TLVType.valueOf(encoded.readUnsignedByte()); // consumes
			// 1
			// byte
			if (logger.isLoggable(Level.FINE))
				logger.log(Level.FINE, String.format("TLV_%02d type=%s",
						tlvCount, type));
			int length = lengthCodec.decodeLength(encoded); // consumes 1+n
			// bytes
			if (logger.isLoggable(Level.FINE))
				logger.log(Level.FINE, String.format("TLV_%02d length=%s",
						tlvCount, length));
			ChannelBuffer value = encoded.slice(encoded.readerIndex(), length);
			logger.log(Level.FINE, String.format("TLV_%02d value=%s", tlvCount,
					value));

			// add to the list
			elements.add(new TLVElement(type, length + encoded.readerIndex()
					- startIndex, value));

			// bump the reader index to the next element
			encoded.readerIndex(encoded.readerIndex() + length);
		}

		return elements;
	}
}
