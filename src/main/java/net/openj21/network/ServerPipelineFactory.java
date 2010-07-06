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
