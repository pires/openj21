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