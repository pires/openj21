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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * See Table F.7 of the standard.
 */
public enum LINK_DN_REASON_ENUM {
	EXPLICIT_DISCONNECT(0),
	PACKET_TIMEOUT(1),
	NO_RESOURCE(2),
	NO_BROADCAST(3),
	AUTH_FAILURE(4),
	BILLING_FAILURE(5);

	private static final Map<Integer, LINK_DN_REASON_ENUM> lookup = 
		new HashMap<Integer, LINK_DN_REASON_ENUM>();

	static {
		for (LINK_DN_REASON_ENUM s : EnumSet.allOf(LINK_DN_REASON_ENUM.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private LINK_DN_REASON_ENUM(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static LINK_DN_REASON_ENUM get(int code) {
		return lookup.get(code);
	}
}