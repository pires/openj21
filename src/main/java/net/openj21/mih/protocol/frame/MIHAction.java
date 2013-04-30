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

import net.openj21.utils.SimpleImmutableEntry;

/**
 * The action identifier (AID) value for MIH messages (10 bits).
 */
public enum MIHAction {
	////////////////////////////////////////////////////////////////////////////
	// Service Management
	
	/**
	 * MIH_Capability_Discover
	 */
	IS_CAPABILITY_DISCOVER(MIHService.SERVICE_MANAGEMENT, 1),
	
	/**
	 * MIH_Register
	 */
	IS_MIH_REGISTER(MIHService.SERVICE_MANAGEMENT, 2),
	
	/**
	 * MIH_DeRegister
	 */
	IS_MIH_DEREGISTER(MIHService.SERVICE_MANAGEMENT, 3),
	
	/**
	 * MIH_Event_Subscribe
	 */
	IS_EVENT_SUBSCRIBE(MIHService.SERVICE_MANAGEMENT, 4),
	
	/**
	 * MIH_Event_Unsubscribe
	 */
	IS_EVENT_UNSUBSCRIBE(MIHService.SERVICE_MANAGEMENT, 5),
	////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////
	// Event Service
	/**
	 * MIH_Link_Detected
	 */
	IS_LINK_DETECTED(MIHService.EVENT_SERVICE, 1),
	
	/**
	 * MIH_Link_Up
	 */
	IS_LINK_UP(MIHService.EVENT_SERVICE, 2),
	
	/**
	 * MIH_Link_Down
	 */
	IS_LINK_DOWN(MIHService.EVENT_SERVICE, 3),
	
	/**
	 * MIH_Link_Parameters_Report
	 */
	IS_LINK_PARAMETERS_REPORT(MIHService.EVENT_SERVICE, 4),
	
	/**
	 * MIH_Link_Going_Down
	 */
	IS_LINK_GOING_DOWN(MIHService.EVENT_SERVICE, 5),
	
	/**
	 * MIH_Link_Handover_Imminent
	 */
	IS_LINK_HANDOVER_IMMINENT(MIHService.EVENT_SERVICE, 6),
	
	/**
	 * MIH_Link_Handover_Complete
	 */
	IS_LINK_HANDOVER_COMPLETE(MIHService.EVENT_SERVICE, 7),
	////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////
	// Command Service
	/**
	 * MIH_Link_Get_Parameters
	 */
	IS_LINK_GET_PARAMETERS(MIHService.COMMAND_SERVICE, 1),
	
	/**
	 * MIH_Link_Configure_Thresholds
	 */
	IS_LINK_CONFIGURE_THRESHOLDS(MIHService.COMMAND_SERVICE, 2),
	
	/**
	 * MIH_Link_Actions
	 */
	IS_LINK_ACTIONS(MIHService.COMMAND_SERVICE, 3),
	
	/**
	 * MIH_Net_HO_Candidate_Query
	 */
	IS_NET_HO_CANDIDATE_QUERY(MIHService.COMMAND_SERVICE, 4),
	
	/**
	 * MIH_MN_HO_Candidate_Query
	 */
	IS_MN_HO_CANDIDATE_QUERY(MIHService.COMMAND_SERVICE, 5),
	
	/**
	 * MIH_N2N_HO_Query_Resources
	 */
	IS_N2N_HO_QUERY_RESOURCES(MIHService.COMMAND_SERVICE, 6),
	
	/**
	 * MIH_MN_HO_Commit
	 */
	IS_MN_HO_COMMIT(MIHService.COMMAND_SERVICE, 7),
	
	/**
	 * MIH_Net_HO_Commit
	 */
	IS_NET_HO_COMMIT(MIHService.COMMAND_SERVICE, 8),
	
	/**
	 * MIH_N2N_HO_Commit
	 */
	IS_N2N_HO_COMMIT(MIHService.COMMAND_SERVICE, 9),
	
	/**
	 * MIH_MN_HO_Complete
	 */
	IS_MN_HO_COMPLETE(MIHService.COMMAND_SERVICE, 10),
	
	/**
	 * MIH_N2N_HO_Complete
	 */
	IS_N2N_HO_COMPLETE(MIHService.COMMAND_SERVICE, 11),	
	////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////
	// Information Service
	/**
	 * MIH_Get_Information.
	 */
	IS_GET_INFORMATION(MIHService.INFORMATION_SERVICE, 1),
	
	/**
	 * MIH_Push_Information.
	 */
	IS_PUSH_INFORMATION(MIHService.INFORMATION_SERVICE, 2);
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * The service.
	 */
	private MIHService svc;

	/**
	 * The numeric AID value (range 0..1023).
	 */
	private final int aid;

	/**
	 * The value to instance map.
	 */
	private static final Map<Map.Entry<Integer, Integer>, MIHAction> INSTANCES_BY_VALUE = Collections
			.unmodifiableMap(new HashMap<Map.Entry<Integer, Integer>, MIHAction>() {
				{
					for (MIHAction action : MIHAction.values()) {
						put(new SimpleImmutableEntry<Integer, Integer>(
								action.svc.value(), action.value()), action);
					}
				}
			});

	/**
	 * Returns the MIHAction instance that has the given value.
	 * 
	 * @param service
	 *            the service
	 * @param value
	 *            the AID value
	 * @return a MIHAction
	 * @throws IllegalArgumentException
	 *             if the given AID is invalid
	 * @see #value()
	 */
	public static MIHAction valueOf(MIHService service, int value)
			throws IllegalArgumentException {
		Map.Entry<Integer, Integer> svcIDPlusAID = new SimpleImmutableEntry<Integer, Integer>(
				service.value(), value);
		if (INSTANCES_BY_VALUE.containsKey(svcIDPlusAID)) {
			return INSTANCES_BY_VALUE.get(svcIDPlusAID);
		}

		throw new IllegalArgumentException(
				"No enum const MIHAction with value: " + value);
	}

	/**
	 * Creates a new MIHAction with the given AID.
	 * 
	 * @param service
	 *            the MIHService
	 * @param aid
	 *            the AID value (range 0..1023)
	 */
	private MIHAction(MIHService service, int aid) {
		if (service == null)
			throw new NullPointerException("service");
		if (aid < 0 || aid > 1023)
			throw new IllegalArgumentException("AID is out of range: " + aid);
		this.svc = service;
		this.aid = aid;
	}

	/**
	 * Returns the AID value (range 0..1023).
	 * 
	 * @return the AID value
	 */
	public int value() {
		return aid;
	}
}