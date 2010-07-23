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

import net.openj21.mih.datatype.basic.CHOICE;
import net.openj21.mih.datatype.basic.INTEGER;
import net.openj21.mih.datatype.basic.PERCENTAGE;

/**
 * Represents the signal strength in dBm unit or its relative value in an
 * arbitrary percentage scale.
 */
@CHOICE({ INTEGER.class, PERCENTAGE.class })
public abstract class SIG_STRENGTH {
	protected SIG_STRENGTH() {
	}
}