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
import java.util.ArrayList;
import java.util.BitSet;
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
import net.openj21.mih.datatype.basic.INTEGER;
import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.mih.datatype.basic.UNSIGNED_INT;
import net.openj21.mih.datatype.general.BOOLEAN;
import net.openj21.mih.datatype.general.OCTET_STRING;
import net.openj21.mih.protocol.codec.basic.BitmapCodec;
import net.openj21.mih.protocol.codec.basic.IntegerCodec;
import net.openj21.mih.protocol.codec.basic.OctetCodec;
import net.openj21.mih.protocol.codec.basic.OctetStringCodec;
import net.openj21.mih.protocol.codec.basic.UnsignedIntCodec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * This class is used to encode IE_CONTAINER_NETWORK instances.
 * <p/>
 * TODO (LGM) Add a kind of encoding stack so that we can report pretty errors.
 * TODO (LGM) Add loads of more logging.
 */
public class GenericEncoder {
	/**
	 * An unmodifiable buffer containing 1 zero-valued byte.
	 */
	private static final ChannelBuffer ZERO = ChannelBuffers
			.unmodifiableBuffer(ChannelBuffers.wrappedBuffer(new byte[] { 0 }));

	/**
	 * An unmodifiable buffer containing 1 one-valued byte.
	 */
	private static final ChannelBuffer ONE = ChannelBuffers
			.unmodifiableBuffer(ChannelBuffers.wrappedBuffer(new byte[] { 1 }));

	/**
	 * An unmodifiable empty buffer.
	 */
	private static final ChannelBuffer EMPTY_BUFFER = ChannelBuffers
			.unmodifiableBuffer(ChannelBuffers.buffer(0));

	/**
	 * Logger for this GenericEncoder instance.
	 */
	private final Logger logger = Logger.getLogger(GenericEncoder.class
			.getName());

	/**
	 * The TLVLengthCodec instance.
	 */
	private TLVLengthCodec lengthCodec;

	/**
	 * The instance used to encode and decode strings.
	 */
	private OctetStringCodec stringCodec;

	/**
	 * The codec to use for OCTET.
	 */
	private OctetCodec octetCodec;

	/**
	 * The BitmapCodec.
	 */
	private BitmapCodec bitmapCodec;

	/**
	 * The IntegerCodec.
	 */
	private IntegerCodec integerCodec;

	/**
	 * The UnsignedIntCodec.
	 */
	private UnsignedIntCodec unsignedIntCodec;

	/**
	 * The AnnotationUtils instance.
	 */
	private AnnotationUtils annotationUtils;

	/**
	 * Creates a new GenericEncoder.
	 */
	public GenericEncoder() {
		// empty
	}

	/**
	 * Sets the TLVLengthCodec instance used for encoding and decoding lengths.
	 * 
	 * @param lengthCodec
	 *            a TLVLengthCodec
	 */
	public void setLengthCodec(TLVLengthCodec lengthCodec) {
		this.lengthCodec = lengthCodec;
	}

	/**
	 * Sets the StringCodec instance used for encoding and decoding strings.
	 * 
	 * @param stringCodec
	 *            a StringCodec
	 */
	public void setStringCodec(OctetStringCodec stringCodec) {
		this.stringCodec = stringCodec;
	}

	/**
	 * Sets the OctetCodec instance used for encoding OCTET.
	 * 
	 * @param octetCodec
	 *            the OctetCodec instance used for encoding OCTET
	 */
	public void setOctetCodec(OctetCodec octetCodec) {
		this.octetCodec = octetCodec;
	}

	/**
	 * Sets the BitmapCodec instance.
	 * 
	 * @param bitmapCodec
	 *            a BitmapCodec
	 */
	public void setBitmapCodec(BitmapCodec bitmapCodec) {
		this.bitmapCodec = bitmapCodec;
	}

	/**
	 * Sets the IntegerCodec instance.
	 * 
	 * @param integerCodec
	 *            an IntegerCodec
	 */
	public void setIntegerCodec(IntegerCodec integerCodec) {
		this.integerCodec = integerCodec;
	}

	/**
	 * Sets the UnsignedIntCodec instance.
	 * 
	 * @param unsignedIntCodec
	 *            an UnsignedIntCodec
	 */
	public void setUnsignedIntCodec(UnsignedIntCodec unsignedIntCodec) {
		this.unsignedIntCodec = unsignedIntCodec;
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

	public ChannelBuffer encodeObject(Object encodeObject) {
		if (encodeObject == null) {
			return EMPTY_BUFFER;
		}

		Class<?> encodeClass = encodeObject.getClass();
		final ChannelBuffer encoded = encodeEmbedded(encodeClass, encodeObject);
		logger.log(Level.FINE, "Encoding: {}", encodeObject);

		INFO_ELEMENT ie = annotationUtils.getAnnotation(encodeClass,
				INFO_ELEMENT.class);
		if (ie != null) {
			logger
					.log(Level.FINE, "Wrapping in INFO_ELEMENT: {}",
							encodeObject);
			return wrapInInfoElementTLV(ie.id(), encoded);
		}

		INFO_ELEMENT_CONTAINER ieContainer = annotationUtils.getAnnotation(
				encodeClass, INFO_ELEMENT_CONTAINER.class);
		if (ieContainer != null) {
			return wrapInInfoElementTLV(ieContainer.id(), encoded);
		}

		return encoded;
	}

	protected ChannelBuffer wrapInInfoElementTLV(int id,
			ChannelBuffer infoElementValue) {
		ChannelBuffer infoElementLength = lengthCodec
				.encodeLength(infoElementValue.readableBytes());
		ChannelBuffer infoElementID = ChannelBuffers.wrappedBuffer(new byte[] {
				(byte) ((id & 0xff000000) >> 24),
				(byte) ((id & 0x00ff0000) >> 16),
				(byte) ((id & 0x0000ff00) >> 8), (byte) ((id & 0x000000ff)) });

		// return the whole encoded lot
		return ChannelBuffers.wrappedBuffer(infoElementID, infoElementLength,
				infoElementValue);
	}

	protected ChannelBuffer wrapInTLV(byte id, ChannelBuffer tlvValue) {
		ChannelBuffer tlvLength = lengthCodec.encodeLength(tlvValue
				.readableBytes());
		ChannelBuffer tlvId = ChannelBuffers.wrappedBuffer(new byte[] { id });

		// return the whole encoded lot
		return ChannelBuffers.wrappedBuffer(tlvId, tlvLength, tlvValue);
	}

	/**
	 * Encodes a field marked with @SEQUENCE_ELEMENT.
	 * 
	 * @param sequenceElement
	 *            the SEQUENCE_ELEMENT annotation
	 * @param encodeField
	 *            the Field to encode
	 * @param encodeValue
	 *            the Object instance to encode
	 * @return a ChannelBuffer containing the encoded field
	 */
	protected ChannelBuffer encodeSequenceElement(
			SEQUENCE_ELEMENT sequenceElement, Field encodeField,
			Object encodeValue) {
		if (encodeValue == null) {
			if (sequenceElement.choice()) {
				logger
						.log(Level.FINE,
								"CHOICE field is null, returning zero: {}",
								encodeField);
				return ZERO;
			}
		}

		final Encoding encoding = sequenceElement.encodeAs();

		// this code builds up a chain of encoder instances according to the
		// meta-data that is present on the field.
		Encoder encoder = sequenceElement.basicType() ? new BasicEncoder(
				encodeField) : new EmbeddedEncoder();
		if (encoding.isHasMany()) {
			if (!(encodeValue instanceof List)) {
				throw new IllegalArgumentException(
						"Field type must implement java.util.List: "
								+ encodeField);
			}

			// use either LIST or CONCAT
			encoder = encoding.isHasCount() ? new ListEncoder(encoder)
					: new ConcatEncoder(encoder);
		}

		// now encode the value of the field itself
		TLVType tlv = sequenceElement.tlv();
		if (tlv != TLVType.NONE) {
			encoder = new TlvEncoder(tlv.byteValue(), encoder);
		}

		// if the field is encoded as CHOICE(NULL, DATATYPE) then insert
		// the encoder that adds the selector prefix.
		if (sequenceElement.choice()) {
			encoder = new ChoiceElementEncoder(ONE, encoder);
		}

		return encoder.encode(encodeValue);
	}

	private ChannelBuffer encodeSequence(Class<?> encodeClass,
			Object encodeObject) {
		// collect all the fields that should be encoded
		Collection<Field> toEncodeFields = annotationUtils
				.getSequenceFields(encodeClass);
		if (toEncodeFields.isEmpty()) {
			throw new IllegalArgumentException(format(
					"Class %s has no @%s fields", encodeClass.getName(),
					SEQUENCE_ELEMENT.class.getSimpleName()));
		}

		// encode contained information element one-by-one
		logger.log(Level.FINE, "Encoding as @SEQUENCE: {}", encodeObject);
		List<ChannelBuffer> encodedFields = new ArrayList<ChannelBuffer>(
				toEncodeFields.size());
		for (Field field : toEncodeFields) {
			try {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}

				logger.logp(Level.FINE, "Encoding field {}.{}", encodeClass
						.getName(), field.getName());
				ChannelBuffer encodedField = encodeSequenceElement(field
						.getAnnotation(SEQUENCE_ELEMENT.class), field, field
						.get(encodeObject));

				encodedFields.add(encodedField);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Couldn't reflect on field: "
						+ field, e);
			}
		}

		return ChannelBuffers.wrappedBuffer(encodedFields
				.toArray(new ChannelBuffer[encodedFields.size()]));
	}

	private ChannelBuffer encodeChoice(Class<?> encodeClass, Object encodeObject) {
		Encoder enc = new SequenceEncoder(encodeClass); // the leaf CHOICE class
		// gets encoded as a
		// SEQUENCE

		// build up the chain of selector values
		superClassLoop: for (Class<?> cls = encodeClass.getSuperclass(); cls != null; cls = cls
				.getSuperclass()) {
			CHOICE choice = cls.getAnnotation(CHOICE.class);
			if (choice != null) {
				Class[] choices = choice.value();
				for (int i = 0; i < choices.length; i++) {
					if (choices[i].isAssignableFrom(encodeClass)) {
						byte uint1 = (byte) (i & 0xff); // encode as unsigned
						// byte

						enc = new ChoiceElementEncoder(ChannelBuffers
								.wrappedBuffer(new byte[] { uint1 }), enc);
						continue superClassLoop; // skip to next iteration of
						// the superclass loop
					}
				}

				throw new IllegalStateException("Class is not among "
						+ CHOICE.class.getSimpleName() + " values: "
						+ encodeClass);
			}
		}

		// call the encoder chain
		return enc.encode(encodeObject);
	}

	private ChannelBuffer encodeEmbedded(Class<?> encodeClass,
			Object encodeObject) {
		SEQUENCE sequence = annotationUtils.getAnnotation(encodeClass,
				SEQUENCE.class);
		if (sequence != null) {
			return encodeSequence(encodeClass, encodeObject);
		}

		CHOICE choice = annotationUtils
				.getAnnotation(encodeClass, CHOICE.class);
		if (choice != null && choice.value().length == 0) {
			return encodeChoice(encodeClass, encodeObject);
		}

		throw new IllegalArgumentException("Don't know how to encode: "
				+ encodeClass);
	}

	private ChannelBuffer encodeBasic(Field field, Object fieldValue) {
		UNSIGNED_INT unsignedIntAnnotation = field
				.getAnnotation(UNSIGNED_INT.class);
		if (unsignedIntAnnotation != null) {
			return encodeUnsignedInt(unsignedIntAnnotation, fieldValue);
		}

		INTEGER integerAnnotation = field.getAnnotation(INTEGER.class);
		if (integerAnnotation != null) {
			return encodeInteger(integerAnnotation, fieldValue);
		}

		OCTET_STRING octetStringAnnotation = field
				.getAnnotation(OCTET_STRING.class);
		if (octetStringAnnotation != null) {
			return encodeOctetString(octetStringAnnotation, fieldValue);
		}

		OCTET octetAnnotation = field.getAnnotation(OCTET.class);
		if (octetAnnotation != null) {
			return encodeOctet(octetAnnotation, fieldValue);
		}

		BITMAP bitmapAnnotation = field.getAnnotation(BITMAP.class);
		if (bitmapAnnotation != null) {
			return encodeBitmap(bitmapAnnotation, fieldValue);
		}

		BOOLEAN booleanAnnotation = field.getAnnotation(BOOLEAN.class);
		if (booleanAnnotation != null) {
			return encodeBoolean(fieldValue);
		}

		throw new IllegalArgumentException("Don't know how to encode: " + field);
	}

	private ChannelBuffer encodeOctet(OCTET octetAnnotation, Object fieldValue) {
		ChannelBuffer buffer = null;
		if (fieldValue instanceof ChannelBuffer) {
			buffer = (ChannelBuffer) fieldValue;
		}

		if (fieldValue instanceof byte[]) {
			buffer = ChannelBuffers.wrappedBuffer((byte[]) fieldValue);
		}

		if (fieldValue instanceof String) {
			buffer = octetCodec.encode((String) fieldValue, octetAnnotation
					.charset());
		}

		if (buffer == null) {
			throw new IllegalArgumentException(format(
					"Class '%s' can't be encoded as %s", fieldValue.getClass()
							.getName(), octetAnnotation));
		}

		if (octetAnnotation.size() != 0
				&& buffer.readableBytes() != octetAnnotation.size()) {
			throw new IllegalArgumentException(format(
					"Buffer has wrong size (%d) for encoding as OCTET(%d): %s",
					buffer.readableBytes(), octetAnnotation.size(), buffer));
		}

		return ChannelBuffers.unmodifiableBuffer(buffer);
	}

	private ChannelBuffer encodeList(List<?> encodeList, Encoder encoder) {
		List<ChannelBuffer> encodedElements = new ArrayList<ChannelBuffer>(
				encodeList.size());
		for (Object listElement : encodeList) {
			ChannelBuffer encodedElement = encoder.encode(listElement);
			encodedElements.add(encodedElement);
		}

		ChannelBuffer listValue = ChannelBuffers.wrappedBuffer(encodedElements
				.toArray(new ChannelBuffer[encodedElements.size()]));
		ChannelBuffer listLength = lengthCodec.encodeLength(encodedElements
				.size());
		return ChannelBuffers.wrappedBuffer(listLength, listValue);
	}

	private ChannelBuffer encodeConcat(List<?> encodeList, Encoder encoder) {
		List<ChannelBuffer> encodedElements = new ArrayList<ChannelBuffer>(
				encodeList.size());
		for (Object listElement : encodeList) {
			ChannelBuffer encodedElement = encoder.encode(listElement);
			encodedElements.add(encodedElement);
		}

		return ChannelBuffers.wrappedBuffer(encodedElements
				.toArray(new ChannelBuffer[encodedElements.size()]));
	}

	private ChannelBuffer encodeBitmap(BITMAP bitmapAnnotation,
			Object fieldValue) {
		if (!(fieldValue instanceof BitSet)) {
			throw new IllegalArgumentException("Object must be a "
					+ BitSet.class.getName());
		}

		BitSet bitmap = (BitSet) fieldValue;
		return bitmapCodec.encodeBitmap(bitmap, bitmapAnnotation.size());
	}

	private ChannelBuffer encodeBoolean(Object fieldValue) {
		if (!(fieldValue instanceof Boolean)) {
			throw new IllegalArgumentException("Object must be a "
					+ Boolean.class.getName());
		}

		return ((Boolean) fieldValue) ? ONE : ZERO;
	}

	private ChannelBuffer encodeInteger(INTEGER integerAnnotation,
			Object numberInstance) {
		if (!(numberInstance instanceof Number)) {
			throw new IllegalArgumentException(
					"Object must be a java.lang.Number: " + numberInstance);
		}

		return integerCodec
				.encodeInteger(((Number) numberInstance).longValue(),
						integerAnnotation.size());
	}

	/**
	 * Encodes a field that is marked with the UNSIGNED_INT annotation.
	 * 
	 * @param annotation
	 *            an UNSIGNED_INT annotation
	 * @param numberInstance
	 *            an instance of Number
	 * @return a ChannelBuffer containing the binary representation of the
	 *         number
	 */
	protected ChannelBuffer encodeUnsignedInt(UNSIGNED_INT annotation,
			Object numberInstance) {
		if (numberInstance == null)
			throw new NullPointerException("numberInstance");
		if (!(numberInstance instanceof Number)) {
			throw new IllegalArgumentException(
					"Object must be a java.lang.Number: " + numberInstance);
		}

		return unsignedIntCodec.encodeUnsignedInt(((Number) numberInstance)
				.longValue(), annotation.size());
	}

	/**
	 * Encodes a field that is marked with OCTET_STRING (the value of the
	 * encodeInstance's toString() method is used).
	 * 
	 * @param annotation
	 *            an OCTET_STRING annotation
	 * @param encodeInstance
	 *            an Object that can be encoded as a String
	 * @return a ChannelBuffer containing the given instance encoded as
	 *         OCTET_STRING 
	 */
	protected ChannelBuffer encodeOctetString(OCTET_STRING annotation,
			Object encodeInstance) {
		if (encodeInstance == null) {
			throw new NullPointerException("encodeInstance");
		}

		// encode
		return stringCodec.encode(encodeInstance.toString(), annotation.size());
	}

	interface Encoder {

		ChannelBuffer encode(Object encodeObject);
	}

	class ListEncoder implements Encoder {
		private final Encoder elementEncoder;

		ListEncoder(Encoder elementEncoder) {
			this.elementEncoder = elementEncoder;
		}

		public ChannelBuffer encode(Object encodeObject) {
			return encodeList((List<?>) encodeObject, elementEncoder);
		}
	}

	class ConcatEncoder implements Encoder {
		private final Encoder elementEncoder;

		public ConcatEncoder(Encoder elementEncoder) {
			this.elementEncoder = elementEncoder;
		}

		public ChannelBuffer encode(Object encodeList) {
			return encodeConcat((List<?>) encodeList, elementEncoder);
		}
	}

	class EmbeddedEncoder implements Encoder {
		public ChannelBuffer encode(Object listElement) {
			return encodeObject(listElement);
		}
	}

	class SequenceEncoder implements Encoder {
		private final Class<?> encodeClass;

		public SequenceEncoder(Class<?> encodeClass) {
			this.encodeClass = encodeClass;
		}

		public ChannelBuffer encode(Object encodeObject) {
			return encodeSequence(encodeClass, encodeObject);
		}
	}

	class BasicEncoder implements Encoder {
		private final Field listField;

		BasicEncoder(Field listField) {
			this.listField = listField;
		}

		public ChannelBuffer encode(Object listElement) {
			return encodeBasic(listField, listElement);
		}
	}

	private class TlvEncoder implements Encoder {
		private final byte tlv;
		private final Encoder encoder;

		public TlvEncoder(byte tlv, Encoder encoder) {
			this.tlv = tlv;
			this.encoder = encoder;
		}

		public ChannelBuffer encode(Object encodeObject) {
			return wrapInTLV(tlv, encoder.encode(encodeObject));
		}
	}

	private class ChoiceElementEncoder implements Encoder {
		private final ChannelBuffer selector;
		private final Encoder encoder;

		public ChoiceElementEncoder(ChannelBuffer selector, Encoder encoder) {
			this.selector = selector;
			this.encoder = encoder;
		}

		public ChannelBuffer encode(Object encodeObject) {
			return ChannelBuffers.wrappedBuffer(selector, encoder
					.encode(encodeObject));
		}
	}
}
