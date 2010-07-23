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
package net.openj21.mih.datatype.link;

import java.util.BitSet;

import net.openj21.mih.datatype.basic.BITMAP;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * Represents if MIH capability is supported or not. If the bit is set, it
 * indicates that the capability is supported.
 * 
 * <pre>
 * Bitmap values:
 * Bit 1: event service (ES) supported
 * Bit 2: command service (CS) supported
 * Bit 3: information service (IS) supported
 * Bit 0, 4–7: (Reserved)
 * </pre>
 */
@SEQUENCE
public class LINK_MIHCAP_FLAGS {
	/**
	 * The size of the BITMAP.
	 */
	private static final int SIZE = 8;
	
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@BITMAP(size = SIZE)
	private BitSet linkMihCapFlags = new BitSet(SIZE);

	public LINK_MIHCAP_FLAGS() {
	}

	public LINK_MIHCAP_FLAGS(BitSet linkMihCapFlags) {
		this.linkMihCapFlags = linkMihCapFlags;
	}

	public BitSet getLinkMihCapFlags() {
		return linkMihCapFlags;
	}

	public void setLinkMihCapFlags(BitSet linkMihCapFlags) {
		this.linkMihCapFlags = linkMihCapFlags;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LINK_MIHCAP_FLAGS that = (LINK_MIHCAP_FLAGS) o;

		if (linkMihCapFlags != null ? !linkMihCapFlags
				.equals(that.linkMihCapFlags) : that.linkMihCapFlags != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return linkMihCapFlags != null ? linkMihCapFlags.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "LINK_MIHCAP_FLAGS{" + "linkMihCapFlags=" + linkMihCapFlags
				+ '}';
	}
}