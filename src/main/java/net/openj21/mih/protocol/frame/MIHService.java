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
