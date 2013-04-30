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

import net.openj21.mih.datatype.basic.OCTET_STRING;
import net.openj21.mih.datatype.basic.SEQUENCE;
import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;

/**
 * A type to represent an XML-formatted geospatial location.
 * Geo address elements as described in IETF RFC 4119.
 * For example,
 * <pre>
 * <gml:location>
 * 	 <gml:Point gml:id=“point1” srsName=“epsg:4326”>
 *     <gml:coordinates>37:46:30N 122:25:10W</gml:coordinates>
 *   </gml:Point>
 *   </gml:location>
 * </pre>
 */
@SEQUENCE
public class XML_GEO_LOC extends GEO_LOC {
	@SEQUENCE_ELEMENT(order = 1, basicType = true)
	@OCTET_STRING(size = Integer.SIZE)
	String xmlGeoLoc;

	public XML_GEO_LOC() {
	}

	public XML_GEO_LOC(String xmlGeoLoc) {
		super();
		this.xmlGeoLoc = xmlGeoLoc;
	}

	public String getXmlGeoLoc() {
		return xmlGeoLoc;
	}

	public void setXmlGeoLoc(String xmlGeoLoc) {
		this.xmlGeoLoc = xmlGeoLoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((xmlGeoLoc == null) ? 0 : xmlGeoLoc.hashCode());
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
		XML_GEO_LOC that = (XML_GEO_LOC) obj;
		if (xmlGeoLoc == null) {
			if (that.xmlGeoLoc != null)
				return false;
		} else if (!xmlGeoLoc.equals(that.xmlGeoLoc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "XML_GEO_LOC [xmlGeoLoc=" + xmlGeoLoc + "]";
	}
}
