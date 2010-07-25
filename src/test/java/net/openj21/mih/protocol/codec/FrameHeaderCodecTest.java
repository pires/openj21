package net.openj21.mih.protocol.codec;

import static org.junit.Assert.assertEquals;
import net.openj21.mih.protocol.frame.MIHAction;
import net.openj21.mih.protocol.frame.MIHHeader;
import net.openj21.mih.protocol.frame.MIHMessageID;
import net.openj21.mih.protocol.frame.MIHOperation;
import net.openj21.mih.protocol.frame.MIHService;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;

/**
 * Test for the FrameHeaderCodec class.
 */
public class FrameHeaderCodecTest {

	/**
	 * Tests that the message id (MID) is properly decoded.
	 * 
	 * @throws FrameDecodeException
	 *             if there is a decoding error
	 */
	@Test
	public void testDecodeMessageID() throws FrameDecodeException {
		FrameHeaderCodec codec = new FrameHeaderCodec();
		MIHMessageID mid = MIHMessageID.MIH_GET_INFORMATION_INDICATION;

		// test
		MIHHeader header = codec.decodeHeader(createHeader(mid));
		assertEquals(mid, header.getMessageID());
	}

	@Test
	public void testEncodeHeaderLengthSet() {
		MIHHeader header = new MIHHeader();
		header.setMessageID(MIHMessageID.MIH_GET_INFORMATION_INDICATION);

		final int encodedPayloadLength = 256;

		// test
		FrameHeaderCodec codec = new FrameHeaderCodec();
		ChannelBuffer encodedHeader = codec.encodeHeader(header,
				encodedPayloadLength);

		// the 7th and 8th bytes contain the length
		byte byte7 = encodedHeader.getByte(6);
		byte byte8 = encodedHeader.getByte(7);
		assertEquals(encodedPayloadLength, (byte7 << 8) | byte8);
	}

	/**
	 * Creates a header with the message id (MID) set. Everything else is set to
	 * 0.
	 * 
	 * @param mid
	 *            the message id to use for creating the frame
	 * @return a ChannelBuffer containing a MIH protocol frame header
	 */
	private static ChannelBuffer createHeader(MIHMessageID mid) {
		final MIHService sid = mid.getSid(); // 4 bits
		final MIHOperation opCode = mid.getOpcode(); // 2 bits
		final MIHAction aid = mid.getAID(); // 10 bits

		ChannelBuffer header = ChannelBuffers.buffer(8);
		header.writeZero(8);

		// bytes 2-3 contain the MID
		header.setByte(
				2,
				(byte) (((sid.value() & 0x0f) << 4)
						| ((opCode.value() & 0x03) << 2) | ((aid.value() & 0x300) >> 8)));
		header.setByte(3, (byte) ((aid.value() & 0xff)));
		return header;
	}
}