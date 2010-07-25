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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * See Table F.8 of the standard.
 */
public enum LINK_GD_REASON_ENUM {
	EXPLICIT_DISCONNECT(0),
	LINK_PARAM_DEGRADING(1),
	LOW_POWER(2),
	NO_RESOURCE(3);

	private static final Map<Integer, LINK_GD_REASON_ENUM> lookup = new HashMap<Integer, LINK_GD_REASON_ENUM>();

	static {
		for (LINK_GD_REASON_ENUM s : EnumSet.allOf(LINK_GD_REASON_ENUM.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private LINK_GD_REASON_ENUM(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static LINK_GD_REASON_ENUM get(int code) {
		return lookup.get(code);
	}
}
