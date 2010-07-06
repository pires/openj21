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
package net.openj21.mih.protocol.frame;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

/**
 */
public enum MIHOperation {
    /**
     * Request.
     */
    REQUEST(1),

    /**
     * Response.
     */
    RESPONSE(2),

    /**
     * Indication.
     */
    INDICATION(3);

    /**
     * The instances mapped by value.
     */
    private static final Map<Integer, MIHOperation> INSTANCES_BY_VALUE = unmodifiableMap(new HashMap<Integer, MIHOperation>(){{
        for (MIHOperation operation : MIHOperation.values()) {
            put(operation.value(), operation);
        }
    }});

    /**
     * Returns the MIHOperation instance that has the given operation code.
     *
     * @param opCode the operation code
     * @return a MIHOperation instance
     * @throws IllegalArgumentException if there is no MIHOperation with the given operation code.
     */
    public static MIHOperation valueOf(int opCode) throws IllegalArgumentException {
        if (INSTANCES_BY_VALUE.containsKey(opCode)) {
            return INSTANCES_BY_VALUE.get(opCode);
        }

        throw new IllegalArgumentException("No enum const MIHOperation with opCode: " + opCode);
    }

    /**
     * The operation code.
     */
    private final Integer code;

    /**
     * Creates a new MIHOperation with the given code.
     *
     * @param code the operation code
     */
    private MIHOperation(Integer code) {
        this.code = code;
    }

    /**
     * Returns this operation's code.
     *
     * @return this operation's code
     */
    public Integer value() {
        return code;
    }
}