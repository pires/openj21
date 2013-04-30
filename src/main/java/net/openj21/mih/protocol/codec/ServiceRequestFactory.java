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
package net.openj21.mih.protocol.codec;

import net.openj21.mih.protocol.frame.MIHMessageID;
import net.openj21.mih.services.ServiceRequest;

/**
 * Interface for a factory of ServiceRequest objects. Normally this class will
 * not be implemented directly, but it is implemented by the Spring container's
 * ServiceLocatorFactoryBean (see Spring configuration file).
 */
public interface ServiceRequestFactory {
	/**
	 * Creates a new ServiceRequest instance for the frame having the given
	 * message identifier. The identifier is the string representation of the
	 * relevant MIHMessageID
	 * 
	 * @param messageId
	 *            a MIHMessageID
	 * @return a new ServiceRequest instance
	 */
	ServiceRequest createInstance(MIHMessageID messageId);
}
