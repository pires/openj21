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