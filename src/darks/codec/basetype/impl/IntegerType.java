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

package darks.codec.basetype.impl;

import java.io.IOException;

import darks.codec.CodecParameter;
import darks.codec.basetype.BaseType;
import darks.codec.iostream.BytesInputStream;
import darks.codec.iostream.BytesOutputStream;
import darks.codec.logs.Logger;

public class IntegerType extends BaseType
{
    
    private static Logger log = Logger.getLogger(IntegerType.class);
    
    @Override
    public void encode(BytesOutputStream out, Object obj, CodecParameter param)
    {
        Integer v = (Integer)obj;
        try
        {
            out.writeInt(v);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    @Override
    public Object decode(BytesInputStream in, Object obj, CodecParameter param)
    {
        try
        {
            return in.readInt();
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return 0;
    }
    
}
