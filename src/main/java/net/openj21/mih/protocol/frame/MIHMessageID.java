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

import static java.lang.String.format;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The Message ID is a part of the MIH header, and is a combination of the following 3 fields.
 * <ul>
 * <li>Service identifier (SID). Identifies the different MIH services, possible values are as follows:
 * <pre>
 * 1: Service Management
 * 2: Event Service
 * 3: Command Service
 * 4: Information Service
 * </pre>
 * <li>Operation code (Opcode): Type of operation to be performed with respect to the SID, possible
 * values are as follows:
 * <pre>
 * 1: Request
 * 2: Response
 * 3: Indication
 * </pre>
 * <li>Action identifier (AID):  This indicates the action to be taken with regard to the SID (see
 * Table L.1 for AID assignments).
 * <p/>
 * </ul>
 *
 * @author <a href="mailto:luis.miranda@pdmfc.com">Luis Miranda</a>
 * @author <a href="mailto:paulo.pires@pdmfc.com">Paulo Pires</a>
 */
public enum MIHMessageID {
	////////////////////////////////////////////////////////////////////////////
	// Service Management
	MIH_CAPABILITY_DISCOVER_REQUEST(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_CAPABILITY_DISCOVER, MIHOperation.REQUEST),
	MIH_CAPABILITY_DISCOVER_RESPONSE(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_CAPABILITY_DISCOVER, MIHOperation.RESPONSE),
	
	MIH_REGISTER_REQUEST(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_MIH_REGISTER, MIHOperation.REQUEST),
	MIH_REGISTER_RESPONSE(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_MIH_REGISTER, MIHOperation.RESPONSE),
	
	MIH_DEREGISTER_REQUEST(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_MIH_DEREGISTER, MIHOperation.REQUEST),
	MIH_DEREGISTER_RESPONSE(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_MIH_DEREGISTER, MIHOperation.RESPONSE),
	
	MIH_EVENT_SUBSCRIBE_REQUEST(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_EVENT_SUBSCRIBE, MIHOperation.REQUEST),
	MIH_EVENT_SUBSCRIBE_RESPONSE(MIHService.SERVICE_MANAGEMENT, MIHAction.IS_EVENT_SUBSCRIBE, MIHOperation.RESPONSE),
	////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////
	// Event Service
	MIH_LINK_DETECTED_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_DETECTED, MIHOperation.INDICATION),
	MIH_LINK_UP_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_UP, MIHOperation.INDICATION),
	MIH_LINK_DOWN_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_DOWN, MIHOperation.INDICATION),
	MIH_LINK_PARAMETERS_REPORT_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_PARAMETERS_REPORT, MIHOperation.INDICATION),
	MIH_LINK_GOING_DOWN_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_GOING_DOWN, MIHOperation.INDICATION),
	MIH_LINK_HO_IMMINENT_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_HANDOVER_IMMINENT, MIHOperation.INDICATION),
	MIH_LINK_HO_COMPLETE_INDICATION(MIHService.EVENT_SERVICE, MIHAction.IS_LINK_HANDOVER_COMPLETE, MIHOperation.INDICATION),
	
	////////////////////////////////////////////////////////////////////////////
	// Command Service
	MIH_LINK_GET_PARAMETERS_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_LINK_GET_PARAMETERS, MIHOperation.REQUEST),
	MIH_LINK_GET_PARAMETERS_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_LINK_GET_PARAMETERS, MIHOperation.RESPONSE),
	
	MIH_LINK_CONFIGURE_THRESHOLDS_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_LINK_CONFIGURE_THRESHOLDS, MIHOperation.REQUEST),
	MIH_LINK_CONFIGURE_THRESHOLDS_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_LINK_CONFIGURE_THRESHOLDS, MIHOperation.RESPONSE),
	
	MIH_LINK_ACTIONS_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_LINK_ACTIONS, MIHOperation.REQUEST),
	MIH_LINK_ACTIONS_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_LINK_ACTIONS, MIHOperation.RESPONSE),
	
	MIH_NET_HO_CANDIDATE_QUERY_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_NET_HO_CANDIDATE_QUERY, MIHOperation.REQUEST),
	MIH_NET_HO_CANDIDATE_QUERY_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_NET_HO_CANDIDATE_QUERY, MIHOperation.RESPONSE),
	
	MIH_MN_HO_CANDIDATE_QUERY_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_MN_HO_CANDIDATE_QUERY, MIHOperation.REQUEST),
	MIH_MN_HO_CANDIDATE_QUERY_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_MN_HO_CANDIDATE_QUERY, MIHOperation.RESPONSE),
	
	MIH_N2N_HO_QUERY_RESOURCES_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_N2N_HO_QUERY_RESOURCES, MIHOperation.REQUEST),
	MIH_N2N_HO_QUERY_RESOURCES_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_N2N_HO_QUERY_RESOURCES, MIHOperation.RESPONSE),
	
	MIH_MN_HO_COMMIT_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_MN_HO_COMMIT, MIHOperation.REQUEST),
	MIH_MN_HO_COMMIT_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_MN_HO_COMMIT, MIHOperation.RESPONSE),
	
	MIH_NET_HO_COMMIT_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_NET_HO_COMMIT, MIHOperation.REQUEST),
	MIH_NET_HO_COMMIT_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_NET_HO_COMMIT, MIHOperation.RESPONSE),
	
	MIH_N2N_HO_COMMIT_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_N2N_HO_COMMIT, MIHOperation.REQUEST),
	MIH_N2N_HO_COMMIT_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_N2N_HO_COMMIT, MIHOperation.RESPONSE),
	
	MIH_MN_HO_COMPLETE_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_MN_HO_COMPLETE, MIHOperation.REQUEST),
	MIH_MN_HO_COMPLETE_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_MN_HO_COMPLETE, MIHOperation.RESPONSE),
	
	MIH_N2N_HO_COMPLETE_REQUEST(MIHService.COMMAND_SERVICE, MIHAction.IS_N2N_HO_COMPLETE, MIHOperation.REQUEST),
	MIH_N2N_HO_COMPLETE_RESPONSE(MIHService.COMMAND_SERVICE, MIHAction.IS_N2N_HO_COMPLETE, MIHOperation.RESPONSE),
	////////////////////////////////////////////////////////////////////////////
	
    ////////////////////////////////////////////////////////////////////////////
	// Information Service
	MIH_GET_INFORMATION_REQUEST(MIHService.INFORMATION_SERVICE, MIHAction.IS_GET_INFORMATION, MIHOperation.REQUEST),
	MIH_GET_INFORMATION_INDICATION(MIHService.INFORMATION_SERVICE, MIHAction.IS_GET_INFORMATION, MIHOperation.INDICATION),
    MIH_GET_INFORMATION_RESPONSE(MIHService.INFORMATION_SERVICE, MIHAction.IS_GET_INFORMATION, MIHOperation.RESPONSE);

    /**
     * The enum instances mapped by value.
     */
    private static final Map<InstanceKey, MIHMessageID> INSTANCES_BY_VALUE = Collections.unmodifiableMap(new HashMap<InstanceKey, MIHMessageID>() {{
        for (MIHMessageID mid : MIHMessageID.values()) {
            put(new InstanceKey(mid.sid, mid.opcode, mid.aid), mid);
        }
    }});

    /**
     * Returns the MIHMessageID instance that has the given SID, OPCODE, and AID combination.
     *
     * @param sid    the service identifier (SID)
     * @param opCode the operation code (OPCODE)
     * @param aid    the action identifier (AID)
     * @return a MIHMessageID
     * @throws IllegalArgumentException if there is no enum constant with the given SID+OPCODE+AID combination
     */
    public static MIHMessageID valueOf(MIHService sid, MIHOperation opCode, MIHAction aid) throws IllegalArgumentException {
        InstanceKey key = new InstanceKey(sid, opCode, aid);
        if (INSTANCES_BY_VALUE.containsKey(key)) {
            return INSTANCES_BY_VALUE.get(key);
        }

        throw new IllegalArgumentException(format("No enum const MIHMessageID with values (SID=%s, OPCODE=%s, AID=%s)", sid, opCode, aid));
    }

    /**
     * Service identifier (4 bits).
     */
    private MIHService sid;

    /**
     * Operation code (2 bits).
     */
    private MIHOperation opcode;

    /**
     * Action identifier (10 bits).
     */
    private MIHAction aid;

    /**
     * Constructs a new MIHMessageID instance.
     *
     * @param sid    the service identifier
     * @param aid    the action identifier
     * @param opcode the operation code
     */
    private MIHMessageID(MIHService sid, MIHAction aid, MIHOperation opcode) {
        this.sid = sid;
        this.opcode = opcode;
        this.aid = aid;
    }

    /**
     * Returns the service identifier.
     *
     * @return a MIHService
     */
    public MIHService getSid() {
        return sid;
    }

    /**
     * Returns the opcode.
     *
     * @return a MIHOperation
     */
    public MIHOperation getOpcode() {
        return opcode;
    }

    /**
     * Returns the action identifier (AID).
     *
     * @return the action identifier (AID)
     */
    public MIHAction getAID() {
        return aid;
    }

    /**
     * The class used as the key in the instances map.
     */
    static class InstanceKey {
        final MIHService service;
        final MIHOperation operation;
        final MIHAction action;

        InstanceKey(MIHService service, MIHOperation operation, MIHAction action) {
            this.service = service;
            this.action = action;
            this.operation = operation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            InstanceKey instanceKey = (InstanceKey) o;

            if (action != instanceKey.action) return false;
            if (operation != instanceKey.operation) return false;
            if (service != instanceKey.service) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = service.hashCode();
            result = 31 * result + action.hashCode();
            result = 31 * result + operation.hashCode();
            return result;
        }
    }
}