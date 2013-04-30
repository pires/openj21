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
package net.openj21.mih.datatype.location;

import java.util.Arrays;

import net.openj21.mih.datatype.basic.OCTET;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A type to represent a binary-formatted geospatial location. See Table F.11.
 */
@SEQUENCE
public class BIN_GEO_LOC extends GEO_LOC {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET(size = 2)
	private byte[] binGeoLoc;

	public BIN_GEO_LOC() {
	}

	public BIN_GEO_LOC(byte[] binGeoLoc) {
		super();
		this.binGeoLoc = binGeoLoc;
	}

	public byte[] getBinGeoLoc() {
		return binGeoLoc;
	}

	public void setBinGeoLoc(byte[] binGeoLoc) {
		this.binGeoLoc = binGeoLoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(binGeoLoc);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BIN_GEO_LOC that = (BIN_GEO_LOC) obj;
		if (!Arrays.equals(binGeoLoc, that.binGeoLoc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BIN_GEO_LOC [binGeoLoc=" + Arrays.toString(binGeoLoc) + "]";
	}
}
