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

/**
 * MIH header structure, consisting of 8 octets.
 */
public class MIHHeader {
    /**
     * This field is used to specify the version of MIH protocol used.
     * <pre>
     * 0: Not to be used
     * 1: First version
     * 2–15: (Reserved)
     * </pre>
     * The version number will be incremented only when a fundamental
     * incompatibility exists between a new revision and the prior edition
     * of the standard. An MIH node that receives an MIH message with a
     * higher version number than it supports will discard the frame with-
     * out indication to the sending MIH node.
     */
    private int version = 1;

    /**
     * This field is used for requesting an acknowledgement for the
     * message.
     */
    private boolean ackReq = false;

    /**
     * This field is used for responding to the request for an acknowledgement
     * for the message.
     */
    private boolean ackRsp = false;

    /**
     * This field is used by the MIH Information Service to indicate if the
     * protocol message is sent in pre-authentication/pre-association state
     * so that the length of the response message can be limited. The UIR
     * bit should be set to '1' by the originator when making an MIH
     * information service request over a certain link in the un-associated/
     * unauthenticated or unregistered state.
     * <p/>
     * In all other cases, this bit is set to '0'.
     */
    private boolean unauthenticatedInfoReq = false;

    /**
     * This field is used for indicating that the message is a fragment to be
     * followed by another fragment. It is set to '0' for a message that is not
     * fragmented and for the last fragment. The two 0 valued conditions
     * are differentiated by the FN field. It is set to '1' for a fragment that is
     * not the last one.
     */
    private boolean moreFragment = false;

    /**
     * This field is used for indicating that the message is a fragment to be
     * followed by another fragment. It is set to '0' for a message that is not
     * fragmented and for the last fragment. The two 0 valued conditions
     * are differentiated by the FN field. It is set to '1' for a fragment that is
     * not the last one.
     */
    private int fragmentNumber = 0;

    /**
     * This field is intentionally kept reserved. When not used, this bit is
     * set to '0'.
     */
    private int reserved1 = 0;

    /**
     * MIH message ID.
     */
    private MIHMessageID messageID = null;

    /**
     * This field is intentionally kept reserved. When not used, all the bits
     * of this field are to be set to '0'.
     */
    private int reserved2 = 0;

    /**
     * This field is used for matching Request and Response, as well as
     * matching Request, Response and Indication to an ACK.
     */
    private int transactionID = 0;

    /**
     * Constructs a new MIHHeader.
     */
    public MIHHeader() {
        // empty
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isAckReq() {
        return ackReq;
    }

    public void setAckReq(boolean ackReq) {
        this.ackReq = ackReq;
    }

    public boolean isAckRsp() {
        return ackRsp;
    }

    public void setAckRsp(boolean ackRsp) {
        this.ackRsp = ackRsp;
    }

    public boolean isUnauthenticatedInfoReq() {
        return unauthenticatedInfoReq;
    }

    public void setUnauthenticatedInfoReq(boolean unauthenticatedInfoReq) {
        this.unauthenticatedInfoReq = unauthenticatedInfoReq;
    }

    public boolean isMoreFragment() {
        return moreFragment;
    }

    public void setMoreFragment(boolean moreFragment) {
        this.moreFragment = moreFragment;
    }

    public int getFragmentNumber() {
        return fragmentNumber;
    }

    public void setFragmentNumber(int fragmentNumber) {
        this.fragmentNumber = fragmentNumber;
    }

    public int getReserved1() {
        return reserved1;
    }

    public void setReserved1(int reserved1) {
        this.reserved1 = reserved1;
    }

    public MIHMessageID getMessageID() {
        return messageID;
    }

    public void setMessageID(MIHMessageID messageID) {
        this.messageID = messageID;
    }

    public int getReserved2() {
        return reserved2;
    }

    public void setReserved2(int reserved2) {
        this.reserved2 = reserved2;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    @Override
    public String toString() {
        return "MIHHeader{" +
                "version=" + version +
                ", ackReq=" + ackReq +
                ", ackRsp=" + ackRsp +
                ", unauthenticatedInfoReq=" + unauthenticatedInfoReq +
                ", moreFragment=" + moreFragment +
                ", fragmentNumber=" + fragmentNumber +
                ", reserved1=" + reserved1 +
                ", messageID=" + messageID +
                ", reserved2=" + reserved2 +
                ", transactionID=" + transactionID +
                '}';
    }
}