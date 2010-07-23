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
package net.openj21.mih.datatype.location;

import net.openj21.mih.datatype.address.CELL_ID;
import net.openj21.mih.datatype.basic.CHOICE;

/**
 * A type to represent the format and value of the location information. The
 * location can be civic location, geospacial location, or a cellular ID value
 * as reference location.
 */
@CHOICE({ CIVIC_LOC.class, GEO_LOC.class, CELL_ID.class })
public abstract class LOCATION {
	protected LOCATION() {
	}
}