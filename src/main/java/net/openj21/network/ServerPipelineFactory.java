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
package net.openj21.network;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Collections;
import java.util.List;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Creates the Netty pipeline for the MIHFDaemon.
 */
public class ServerPipelineFactory implements ChannelPipelineFactory {
    /**
     * Logger for this ServerPipelineFactory instance.
     */
    private final Logger logger = Logger.getLogger(ServerPipelineFactory.class.getName());

    /**
     * The list of ChannelHandler instances that the created pipeline will use.
     */
    private List<ChannelHandler> channelHandlers = Collections.emptyList();

    /**
     * Creates a new UdpPipelineFactory.
     */
    public ServerPipelineFactory() {
        // empty
    }

    /**
     * Sets the list of ChannelHandler instances that the created pipeline will use.
     *
     * @param channelHandlers a List of ChannelHandler
     */
    public void setChannelHandlers(List<ChannelHandler> channelHandlers) {
        this.channelHandlers = channelHandlers;
    }

    public ChannelPipeline getPipeline() throws Exception {
        // create a default pipeline implementation
        ChannelPipeline p = pipeline();

        // add all the handlers
        for (int i = 0; i < channelHandlers.size(); i++) {
            ChannelHandler handler = channelHandlers.get(i);

            logger.log(Level.FINE, "Adding handler: {}", handler);
            p.addLast(String.format("handler_%d", i + 1), handler);
        }
        
        logger.log(Level.INFO, "Returning channel pipeline: {}", p);
        return p;
    }
}
