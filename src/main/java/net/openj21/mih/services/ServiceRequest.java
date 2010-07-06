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
