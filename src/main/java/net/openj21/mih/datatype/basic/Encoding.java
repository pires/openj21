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
package net.openj21.mih.datatype.basic;

/**
 * Indicates how a sequence element should be encoded.
 */
public enum Encoding {
    ATOM(false, false),
    CONCAT(true, false),
    LIST(true, true);

    private final boolean hasMany;
    private final boolean hasCount;

    private Encoding(boolean hasMany, boolean hasCount) {
        this.hasMany = hasMany;
        this.hasCount = hasCount;
    }

    public boolean isHasMany() {
        return hasMany;
    }

    public boolean isHasCount() {
        return hasCount;
    }
}