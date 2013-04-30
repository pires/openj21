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

import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.openj21.mih.services.ServiceRequest;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * This is the Netty handler class that handles all ServiceRequest instances that are present on the pipeline.
 */
@ChannelPipelineCoverage("all")
public class ServiceRequestHandler extends SimpleChannelUpstreamHandler {
    /**
     * Logger for this ServerHandler instance.
     */
    private final Logger logger = Logger.getLogger(ServiceRequestHandler.class.getName());

    /**
     * A boolean indicating whether the replyAddress property should be set
     * before executing a ServiceRequest.
     */
    private boolean setReplyAddress = true;

    /**
     * Creates a new ServiceRequestHandler.
     */
    public ServiceRequestHandler() {
        // empty
    }

    /**
     * Sets a boolean indicating whether the replyAddress property should
     * be set before executing a ServiceRequest.
     *
     * @param setReplyAddress a boolean indicating whether the replyAddress property should
     *                        be set before executing a ServiceRequest
     */
    public void setSetReplyAddress(boolean setReplyAddress) {
        this.setReplyAddress = setReplyAddress;
    }

    /**
     * Invoked when a message object (e.g: {@link org.jboss.netty.buffer.ChannelBuffer}) was received
     * from a remote peer.
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        // process MIH frames, send everything else upstream
        Object m = e.getMessage();
        if (!(m instanceof ServiceRequest)) {
            ctx.sendUpstream(e);
            return;
        }

        // the frame has already been decoded into a request
        ServiceRequest req = (ServiceRequest) m;
        req.setReplyChannel(e.getChannel());
        if (setReplyAddress) {
            logger.log(Level.FINE, "Setting reply address to: {}", e.getRemoteAddress());
            req.setReplyAddress((InetSocketAddress) e.getRemoteAddress());
        }

        logger.log(Level.INFO, "Executing ServiceRequest: {}", req);
        try {
            req.execute();
        }
        catch (Exception e1) {
            logger.log(Level.SEVERE, "Exception thrown from ServiceRequest", e1);
        }
    }

    /**
     * Logs thrown exceptions and closes the underlying channel.
     *
     * @param ctx a ChannelHandlerContext
     * @param e   the ExceptionEvent
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.log(Level.SEVERE, "Exception raised by IO thread", e.getCause());
        e.getChannel().close();
    }
}
