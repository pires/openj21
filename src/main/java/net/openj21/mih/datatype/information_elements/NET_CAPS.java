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
package net.openj21.mih.datatype.information_elements;

import java.util.BitSet;

import net.openj21.mih.datatype.basic.BITMAP;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * These bits provide high level capabilities supported on a network. Bitmap
 * Values:
 * 
 * <pre>
 * Bit 0: Security – Indicates that some level of security
 * is supported when set.
 * Bit 1: QoS Class 0 – Indicates that QoS for class 0 is
 * supported when set.a
 * Bit 2: QoS Class 1 – Indicates that QoS for class 1 is
 * supported when set. a
 * Bit 3: QoS Class 2 – Indicates that QoS for class 2 is
 * supported when set; Otherwise, no QoS for class 2
 * support is available.
 * Bit 4: QoS Class 3 – Indicates that QoS for class 3 is
 * supported when set; Otherwise, no QoS for class 3
 * support is available.
 * Bit 5: QoS Class 4 – Indicates that QoS for class 4 is
 * supported when set; Otherwise, no QoS for class 4
 * support is available.
 * Bit 6: QoS Class 5 – Indicates that QoS for class 5 is
 * supported when set; Otherwise, no QoS for class 5
 * support is available.
 * Bit 7: Internet Access – Indicates that Internet access
 * is supported when set; Otherwise, no Internet access
 * support is available.
 * Bit 8: Emergency Services – Indicates that some level
 * of emergency services is supported when set; Other-
 * wise, no emergency service support is available.
 * Bit 9: MIH Capability – Indicates that MIH is sup-
 * ported when set; Otherwise, no MIH support is avail-
 * able.
 * Bit 10–31: (Reserved)
 * </pre>
 */
@SEQUENCE
public class NET_CAPS {
	/**
	 * The size of the BITMAP.
	 */
	private static final int SIZE = 32;

	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@BITMAP(size = SIZE)
	private BitSet netCaps = new BitSet(SIZE);

	public NET_CAPS() {
	}

	public BitSet getNetCaps() {
		return netCaps;
	}

	public void setNetCaps(BitSet netCaps) {
		this.netCaps = netCaps;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		NET_CAPS that = (NET_CAPS) o;

		if (netCaps != null ? !netCaps.equals(that.netCaps)
				: that.netCaps != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return netCaps != null ? netCaps.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "NET_CAPS{" + "netCaps=" + netCaps + '}';
	}
}
