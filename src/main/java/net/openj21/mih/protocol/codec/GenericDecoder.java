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

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.openj21.mih.datatype.TLVType;
import net.openj21.mih.datatype.basic.BITMAP;
import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.Encoding;
import net.openj21.mih.datatype.basic.INFO_ELEMENT;
import net.openj21.mih.datatype.basic.INFO_ELEMENT_CONTAINER;
import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;
import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.protocol.codec.basic.BitmapCodec;
import net.openj21.mih.protocol.codec.basic.OctetCodec;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.codec.basic.UnsignedIntCodec;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * This class is used to decode any class that is marked with the required
 * annotations.
 */
public class GenericDecoder {
	/**
	 * Logger for this GenericDecoder instance.
	 */
	private final Logger logger = Logger.getLogger(GenericDecoder.class
			.getName());

	/**
	 * The TLVLengthCodec instance.
	 */
	private TLVLengthCodec lengthCodec;

	/**
	 * The TLVSerialiser instance.
	 */
	private TLVSerialiser tlvSerialiser;

	/**
	 * The AnnotationUtils instance.
	 */
	private AnnotationUtils annotationUtils;

	/**
	 * The UnsignedIntCodec instance.
	 */
	private UnsignedIntCodec unsignedIntCodec;

	/**
	 * The BitmapCodec instance.
	 */
	private BitmapCodec bitmapCodec;

	/**
	 * The OctetStringCodec instance.
	 */
	private OctetStringCodec octetStringCodec;

	/**
	 * The class used to decode OCTET.
	 */
	private OctetCodec octetCodec;

	/**
	 * Creates a new GenericDecoder instance.
	 */
	public GenericDecoder() {
		// empty
	}

	/**
	 * Sets the TLVLengthCodec.
	 * 
	 * @param lengthCodec
	 *            a TLVLengthCodec instance
	 */
	public void setLengthCodec(TLVLengthCodec lengthCodec) {
		this.lengthCodec = lengthCodec;
	}

	/**
	 * Sets the TLVSerialiser intance.
	 * 
	 * @param serialiser
	 *            a TLVSerialiser
	 */
	public void setTlvSerialiser(TLVSerialiser serialiser) {
		this.tlvSerialiser = serialiser;
	}

	/**
	 * Sets the AnnotationUtils instance.
	 * 
	 * @param annotationUtils
	 *            an AnnotationUtils instance
	 */
	public void setAnnotationUtils(AnnotationUtils annotationUtils) {
		this.annotationUtils = annotationUtils;
	}

	/**
	 * Sets the UnsignedIntCodec instance.
	 * 
	 * @param unsignedIntCodec
	 *            an UnsignedIntCodec instance
	 */
	public void setUnsignedIntCodec(UnsignedIntCodec unsignedIntCodec) {
		this.unsignedIntCodec = unsignedIntCodec;
	}

	/**
	 * Sets the BitmapCodec instance.
	 * 
	 * @param bitmapCodec
	 *            a BitmapCodec instance
	 */
	public void setBitmapCodec(BitmapCodec bitmapCodec) {
		this.bitmapCodec = bitmapCodec;
	}

	/**
	 * Sets the OctetStringCodec instance.
	 * 
	 * @param octetStringCodec
	 *            an OctetStringCodec
	 */
	public void setOctetStringCodec(OctetStringCodec octetStringCodec) {
		this.octetStringCodec = octetStringCodec;
	}

	/**
	 * Sets the class used to decode OCTET fields.
	 * 
	 * @param octetCodec
	 *            the class used to decode OCTET fields
	 */
	public void setOctetCodec(OctetCodec octetCodec) {
		this.octetCodec = octetCodec;
	}

	public <T> T decodeBuffer(ChannelBuffer buffer, Class<T> expectedClass) {
		INFO_ELEMENT infoElement = annotationUtils.getAnnotation(expectedClass,
				INFO_ELEMENT.class);
		if (infoElement != null) {
			throw new UnsupportedOperationException("TODO");
		}

		INFO_ELEMENT_CONTAINER ieContainer = annotationUtils.getAnnotation(
				expectedClass, INFO_ELEMENT_CONTAINER.class);
		if (ieContainer != null) {
			throw new UnsupportedOperationException("TODO");
		}

		return decodeEmbedded(expectedClass, buffer);
	}

	private <T> T decodeEmbedded(Class<T> expectedClass, ChannelBuffer buffer) {
		// sequence
		SEQUENCE sequenceAnnotation = expectedClass
				.getAnnotation(SEQUENCE.class);
		if (sequenceAnnotation != null) {
			return decodeSequence(buffer, expectedClass);
		}

		CHOICE choiceAnnotation = expectedClass.getAnnotation(CHOICE.class);
		if (choiceAnnotation != null) {
			return decodeChoice(buffer, choiceAnnotation, expectedClass);
		}

		// choice
		throw new IllegalStateException("TODO");
	}

	@SuppressWarnings("unchecked")
	private <T> T decodeChoice(ChannelBuffer buffer, CHOICE choiceAnnotation,
			Class<T> expectedClass) {
		if (choiceAnnotation.value().length == 0) {
			return decodeSequence(buffer, expectedClass);
		}

		// read the selector, and then recurse
		int selector = buffer.readUnsignedByte();
		Class<?> subChoice = choiceAnnotation.value()[selector];
		CHOICE subChoiceAnnotation = subChoice.getAnnotation(CHOICE.class);
		if (subChoiceAnnotation == null) {
			throw new IllegalStateException("Subclass must have "
					+ CHOICE.class.getSimpleName() + " annotation: "
					+ subChoice);
		}

		return (T) decodeChoice(buffer, subChoiceAnnotation, subChoice);
	}

	private <T> T decodeSequence(ChannelBuffer buffer, Class<T> sequenceClass) {
		Collection<Field> toDecodeFields = annotationUtils
				.getSequenceFields(sequenceClass);
		if (toDecodeFields.isEmpty()) {
			throw new IllegalArgumentException(format(
					"Class %s has no @%s fields", sequenceClass.getName(),
					SEQUENCE_ELEMENT.class.getSimpleName()));
		}

		try {
			T decoded = sequenceClass.newInstance();
			for (Field field : toDecodeFields) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}

				logger.logp(Level.FINE, "Decoding field {}.{}", sequenceClass
						.getName(), field.getName());
				field.set(decoded, decodeSequenceElement(field
						.getAnnotation(SEQUENCE_ELEMENT.class), field, buffer));
			}

			return decoded;
		} catch (InstantiationException e) {
			throw new RuntimeException("Error creating instance of "
					+ sequenceClass, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error creating instance of "
					+ sequenceClass, e);
		}
	}

	private Object decodeSequenceElement(SEQUENCE_ELEMENT seqElement,
			Field field, ChannelBuffer buffer) {
		Encoding encoding = seqElement.encodeAs();
		Class decodeClass = encoding.isHasMany() ? getListType(field) : field
				.getType();
		Decoder decoder = seqElement.basicType() ? new BasicTypeDecoder(field)
				: new EmbeddedDecoder(decodeClass);
		if (encoding.isHasMany()) {
			decoder = new ManyDecoder(encoding.isHasCount(), decoder);
		}

		if (seqElement.tlv() != TLVType.NONE) {
			decoder = new TlvDecoder(seqElement.tlv(), decoder);
		}

		if (seqElement.choice()) {
			decoder = new NullableFieldDecoder(decoder);
		}

		// basic
		// embedded
		// choice
		// infoElementId
		// basicType
		// hasMany
		// listInfoElementId
		// hasCount
		// tlv
		return decoder.decode(buffer);
	}

	private Object decodeBasic(Field field, ChannelBuffer buffer) {
		UNSIGNED_INT unsignedInt = field.getAnnotation(UNSIGNED_INT.class);
		if (unsignedInt != null) {
			return decodeUnsignedInt(buffer, field, unsignedInt.size());
		}

		BITMAP bitmap = field.getAnnotation(BITMAP.class);
		if (bitmap != null) {
			return bitmapCodec.decodeBitmap(buffer, bitmap.size());
		}

		OCTET_STRING octetString = field.getAnnotation(OCTET_STRING.class);
		if (octetString != null) {
			return octetStringCodec.decode(buffer);
		}

		OCTET octet = field.getAnnotation(OCTET.class);
		if (octet != null) {
			return decodeOctet(buffer, field, octet.size(), octet.charset());
		}

		throw new IllegalArgumentException("Don't know how to decode: " + field);
	}

	private Object decodeUnsignedInt(ChannelBuffer buffer, Field field, int size) {
		Long uint = unsignedIntCodec.decodeUnsignedInt(buffer, size);

		// narrow the type, if necessary
		if (field.getType() == Short.class) {
			if (uint > Short.MAX_VALUE) {
				throw new IllegalStateException(format(
						"Detected overflow for decoded %s(%d): %s",
						UNSIGNED_INT.class.getName(), size, field));
			}

			return uint.shortValue();
		}

		if (field.getType() == Integer.class) {
			if (uint > Integer.MAX_VALUE) {
				throw new IllegalStateException(format(
						"Detected overflow for decoded %s(%d): %s",
						UNSIGNED_INT.class.getName(), size, field));
			}

			return uint.intValue();
		}

		return uint;
	}

	private Object decodeOctet(ChannelBuffer buffer, Field field, int size,
			String charsetName) {
		// read the bytes corresponding to the OCTET(size)
		ChannelBuffer octetBuffer = buffer.slice(buffer.readerIndex(), size);
		buffer.readerIndex(buffer.readerIndex() + size);

		if (field.getType() == ChannelBuffer.class) {
			return octetBuffer;
		}

		if (field.getType() == byte[].class) {
			return octetBuffer.toByteBuffer().array();
		}

		if (field.getType() == String.class) {
			return octetCodec.decode(octetBuffer, charsetName);
		}

		throw new IllegalArgumentException(format(
				"Can't decode OCTET(charset=%s, size=%d) into field: %s.%s",
				charsetName, size, field.getDeclaringClass().getName(), field
						.getName()));
	}

	protected Class getListType(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) {
			throw new IllegalStateException("Must be a java.util.List: "
					+ field);
		}

		if (!(field.getGenericType() instanceof ParameterizedType)) {
			throw new IllegalStateException(
					"java.util.List has no generic information: " + field);
		}

		ParameterizedType paramType = (ParameterizedType) field
				.getGenericType();
		return (Class) paramType.getActualTypeArguments()[0];
	}

	interface Decoder {
		Object decode(ChannelBuffer buffer);
	}

	private class ManyDecoder implements Decoder {
		private final boolean hasCount;
		private final Decoder elementDecoder;

		public ManyDecoder(boolean hasCount, Decoder elementDecoder) {
			this.hasCount = hasCount;
			this.elementDecoder = elementDecoder;
		}

		public Object decode(ChannelBuffer buffer) {
			int count = hasCount ? lengthCodec.decodeLength(buffer)
					: Integer.MAX_VALUE;
			List<Object> list = hasCount ? new ArrayList<Object>(count)
					: new ArrayList<Object>();

			for (int i = 0; i < count && buffer.readable(); i++) {
				list.add(elementDecoder.decode(buffer));
			}

			return list;
		}
	}

	private class TlvDecoder implements Decoder {
		private final TLVType tlvType;
		private final Decoder decoder;

		public TlvDecoder(TLVType tlvType, Decoder decoder) {
			this.tlvType = tlvType;
			this.decoder = decoder;
		}

		public Object decode(ChannelBuffer buffer) {
			List<TLVElement> elems = tlvSerialiser.deserialise(buffer);
			if (elems.size() < 1) {
				throw new IllegalArgumentException(
						"Buffer must contain at least 1 TLV element");
			}

			TLVElement e = elems.get(0);
			if (e.getType() != tlvType) {
				throw new IllegalArgumentException(format(
						"Element does not have expected TLVType.%s: %s",
						tlvType, e));
			}

			// pass the payload on to the next elementDecoder
			return decoder.decode(e.getValue());
		}
	}

	private class EmbeddedDecoder implements Decoder {

		private final Class<?> decodeClass;

		public EmbeddedDecoder(Class<?> decodeClass) {
			this.decodeClass = decodeClass;
		}

		public Object decode(ChannelBuffer buffer) {
			return decodeEmbedded(decodeClass, buffer);
		}
	}

	private class BasicTypeDecoder implements Decoder {
		private final Field field;

		public BasicTypeDecoder(Field field) {
			this.field = field;
		}

		public Object decode(ChannelBuffer buffer) {
			return decodeBasic(field, buffer);
		}
	}

	private class NullableFieldDecoder implements Decoder {
		private final Decoder decoder;

		public NullableFieldDecoder(Decoder decoder) {
			this.decoder = decoder;
		}
		
		public Object decode(ChannelBuffer buffer) {
			short selector = buffer.readUnsignedByte();
			if (selector == 0) {
				return null;
			} else if (selector == 1) {
				return decoder.decode(buffer);
			}

			throw new IllegalStateException(format(
					"Unexpected selector value: 0x%02X", selector));
		}
	}
}