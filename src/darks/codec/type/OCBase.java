package darks.codec.type;

import java.io.IOException;

import darks.codec.CodecParameter;
import darks.codec.Decoder;
import darks.codec.Encoder;
import darks.codec.basetype.BaseType;
import darks.codec.basetype.BaseTypeFactory;
import darks.codec.helper.ReflectHelper;
import darks.codec.iostream.BytesInputStream;
import darks.codec.iostream.BytesOutputStream;

public abstract class OCBase implements IOCSerializable
{
    /**
     */
    private static final long serialVersionUID = -4006955537635420445L;

    private OCInteger lenType;

    private int bytePos;

    public OCBase()
    {
    }

    public OCBase(OCInteger lenType)
    {
        this.lenType = lenType;
    }

    protected void writeBytes(Encoder encoder, BytesOutputStream out,
            byte[] bytes, CodecParameter param) throws IOException
    {
        if (bytes == null)
        {
            return;
        }
        writeDynamicLength(bytes.length, encoder, out, param);
        setBytePos(out.size());
        out.write(bytes);
    }

    protected void writeDynamicLength(int length, Encoder encoder,
            BytesOutputStream out, CodecParameter param) throws IOException
    {
        if (lenType != null)
        {
            lenType.setValue(length);
            int pos = lenType.getBytePos();
            out.setCursor(pos);
            lenType.writeObject(encoder, out, param);
            out.moveLast();
        }
    }

    @SuppressWarnings("unchecked")
    protected <E> E getGenerticValue(Decoder decoder, BytesInputStream in,
            Class<E> genericType, CodecParameter param) throws IOException
    {
        E obj = null;
        BaseType baseType = BaseTypeFactory.getCodec(genericType);
        if (baseType != null)
        {
            obj = (E) baseType.decode(in, null, param);
        }
        else
        {
            obj = ReflectHelper.newInstance(genericType);
            decoder.decodeObject(in, obj, param);
        }
        return obj;
    }

    protected void writeAutoLength(Encoder encoder, BytesOutputStream out,
            CodecParameter param) throws IOException
    {
        if (param.isAutoLength() && lenType == null)
        {
            lenType = new OCInt32();
            lenType.writeObject(encoder, out, param);
        }
    }

    protected void readAutoLength(Decoder decoder, BytesInputStream in,
            CodecParameter param) throws IOException
    {
        if (param.isAutoLength() && lenType == null)
        {
            lenType = new OCInt32();
            lenType.readObject(decoder, in, param);
        }
    }

    public boolean isDynamicLength()
    {
        return lenType != null;
    }

    public OCInteger getLenType()
    {
        return lenType;
    }

    public void setLenType(OCInteger lenType)
    {
        this.lenType = lenType;
    }

    public int getBytePos()
    {
        return bytePos;
    }

    public void setBytePos(int bytePos)
    {
        this.bytePos = bytePos;
    }

}