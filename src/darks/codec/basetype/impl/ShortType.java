package darks.codec.basetype.impl;

import java.io.IOException;

import darks.codec.CodecParameter;
import darks.codec.basetype.BaseType;
import darks.codec.iostream.BytesInputStream;
import darks.codec.iostream.BytesOutputStream;
import darks.codec.logs.Logger;

public class ShortType extends BaseType
{
    
    private static Logger log = Logger.getLogger(ShortType.class);
    
    @Override
    public void encode(BytesOutputStream out, Object obj, CodecParameter param)
    {
        Short v = (Short)obj;
        try
        {
//            byte[] bytes = ByteHelper.convertInt16(s, param.isLittleEndian());
            out.writeShort(v);
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
//            byte[] bytes = ByteHelper.readBytes(in, 2, param.isLittleEndian());
//            short value = (short)ByteHelper.convertToInt16(bytes);
            return in.readShort();
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return 0;
    }
    
}
