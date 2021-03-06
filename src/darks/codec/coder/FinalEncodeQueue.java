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

package darks.codec.coder;

import java.util.LinkedList;

import darks.codec.CodecParameter;
import darks.codec.Encoder;
import darks.codec.iostream.BytesOutputStream;
import darks.codec.wrap.Wrapper;

/**
 * Final encode queue will be called in final encode handle.
 * 
 * FinalEncodeQueue.java
 * 
 * @version 1.0.0
 * @author Liu lihua
 */
public class FinalEncodeQueue
{

    LinkedList<FinalPair> pairs = new LinkedList<FinalEncodeQueue.FinalPair>();

    /**
     * Add final called wrapper.
     * 
     * @param wrap {@linkplain darks.codec.wrap.Wrapper Wrapper} object.
     * @param extern Additional data.
     */
    public void addWrap(Wrapper wrap, Object extern)
    {
        pairs.add(new FinalPair(wrap, extern));
    }

    /**
     * Call final wrapper to encoding final bytes.
     * 
     * @param encoder {@linkplain darks.codec.Encoder Encoder} object.
     * @param out Encoding IO stream
     * @param param parameters
     * @throws Exception exception.
     */
    public void doFinal(Encoder encoder, BytesOutputStream out,
            CodecParameter param) throws Exception
    {
        FinalPair pair = null;
        while ((pair = pairs.poll()) != null)
        {
            pair.wrap.finalEncode(encoder, out, param, pair.extern);
        }
    }

    static class FinalPair
    {
        Wrapper wrap;

        Object extern;

        public FinalPair(Wrapper wrap, Object extern)
        {
            super();
            this.wrap = wrap;
            this.extern = extern;
        }

    }
}
