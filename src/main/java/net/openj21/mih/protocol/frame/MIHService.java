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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This enum identifies the various MIH services.
 */
public enum MIHService {
    /**
     * MIH Service Management.
     */
    SERVICE_MANAGEMENT(1),

    /**
     * MIH Event Service.
     */
    EVENT_SERVICE(2),

    /**
     * MIH Command Service.
     */
    COMMAND_SERVICE(3),

    /**
     * MIH Information Service.
     */
    INFORMATION_SERVICE(4);

    /**
     * The enum instances mapped by their value.
     */
    private static final Map<Integer, MIHService> INSTANCES_BY_VALUE = Collections.unmodifiableMap(new HashMap<Integer, MIHService>() {{
        for (MIHService service : MIHService.values()) {
            put(service.value(), service);
        }
    }});

    /**
     * Returns the MIHService instance that has the given SID.
     *
     * @param sid the service identifier
     * @return a MIHService
     * @throws IllegalArgumentException if there is no MIHService with the given SID
     */
    public static MIHService valueOf(int sid) throws IllegalArgumentException {
        if (INSTANCES_BY_VALUE.containsKey(sid)) {
            return INSTANCES_BY_VALUE.get(sid);
        }

        throw new IllegalArgumentException("No enum const MIHService with sid: " + sid);
    }

    /**
     * The service id.
     */
    private final Integer id;

    /**
     * Constructs a new ServiceIdentifier having the given id.
     *
     * @param id the SID
     */
    private MIHService(Integer id) {
        this.id = id;
    }

    /**
     * Returns this service's identifier.
     *
     * @return this service's identifier
     */
    public Integer value() {
        return id;
    }
}
