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
package net.openj21.mih.services;

import net.openj21.mih.protocol.frame.MIHFrame;

import org.jboss.netty.channel.Channel;

import java.net.InetSocketAddress;

/**
 * Command interface for a MIH request.
 */
public interface ServiceRequest {
    /**
     * Sets the value of the MIH frame that was received in the request.
     *
     * @param frame an MIHFrame
     */
    void setRequestFrame(MIHFrame frame);

    /**
     * Sets the channel that will be used for the reply.
     *
     * @param replyChannel a Channel
     */
    void setReplyChannel(Channel replyChannel);

    /**
     * Sets the InetSocketAddress where the reply will be sent. If the
     * reply channel was created by a connectionless transport, then
     * the reply address must be non-null.
     *
     * @param remoteAddress an InetSocketAddress
     * @see org.jboss.netty.channel.Channel#write(Object, java.net.SocketAddress)
     */
    void setReplyAddress(InetSocketAddress remoteAddress);

    /**
     * Executes this command.
     */
    void execute();
}
