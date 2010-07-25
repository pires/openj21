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
package net.openj21.mih.datatype.link.param;

import net.openj21.mih.datatype.basic.CHOICE;

/**
 * Measurable link parameter for which thresholds are being set.
 */
@CHOICE({ LINK_PARAM_GEN.class, LINK_PARAM_QOS.class, LINK_PARAM_GG.class,
		LINK_PARAM_EDGE.class, LINK_PARAM_ETH.class, LINK_PARAM_802_11.class,
		LINK_PARAM_C2K.class, LINK_PARAM_FDD.class, LINK_PARAM_HRPD.class,
		LINK_PARAM_802_16.class, LINK_PARAM_802_20.class,
		LINK_PARAM_802_22.class })
public abstract class LINK_PARAM_TYPE {
	protected LINK_PARAM_TYPE() {
	}
}