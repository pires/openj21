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
 * This class contains a MIH protocol frame.
 */
public class MIHFrame {
    /**
     * The MIH frame header.
     */
    private MIHHeader header;

    /**
     * The MIH frame payload.
     */
    private MIHPayload payload;

    /**
     * Creates a new MIHFrame with the given header and payload.
     *
     * @param header  the frame header
     * @param payload the frame payload
     */
    public MIHFrame(MIHHeader header, MIHPayload payload) {
        this.header = header;
        this.payload = payload;
    }

    /**
     * Returns the MIH frame header.
     *
     * @return the MIH frame header
     */
    public MIHHeader getHeader() {
        return header;
    }

    /**
     * Returns the MIH frame payload.
     *
     * @return the MIH frame payload
     */
    public MIHPayload getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "MIHFrame{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }
}