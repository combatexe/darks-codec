/**
 * 
 *Copyright 2014 The Darks Codec Project (Liu lihua)
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */

package darks.codec;

import java.io.IOException;

import darks.codec.iostream.BytesOutputStream;

/**
 * Encoder is the interface to encode message object in codec. Developer can
 * customize encoder in custom codec.<br>
 * 
 * Encoder.java
 * 
 * @version 1.0.0
 * @author Liu lihua
 */
public abstract class Encoder
{

    /**
     * Encode object
     * 
     * @param out Encode bytes stream.
     * @param obj Target object.
     * @param param Codec parameter. It's available in current encode flow.
     * @throws IOException IO exception
     */
    public abstract void encodeObject(BytesOutputStream out, Object obj,
            CodecParameter param) throws IOException;

}
