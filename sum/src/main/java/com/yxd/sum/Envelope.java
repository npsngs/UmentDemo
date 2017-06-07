package com.yxd.sum;

import a.a.a.UMException;
import java.nio.ByteBuffer;

public class Envelope{
    private static final TField VERSION = new TField("version", (byte)11, (short)1);
    private static final TField ADDRESS = new TField("address", (byte)11, (short)2);
    private static final TField SIGNATURE = new TField("signature", (byte)11, (short)3);
    private static final TField SERIAL_NUM = new TField("serial_num", (byte)8, (short)4);
    private static final TField TS_SECS = new TField("ts_secs", (byte)8, (short)5);
    private static final TField LENGTH = new TField("length", (byte)8, (short)6);
    private static final TField ENTITY = new TField("entity", (byte)11, (short)7);
    private static final TField GUID = new TField("guid", (byte)11, (short)8);
    private static final TField CHECKSUM = new TField("checksum", (byte)11, (short)9);
    private static final TField CODEX = new TField("codex", (byte)8, (short)10);
    public String version;
    public String address;
    public String signature;
    public int serial_num;
    public int ts_secs;
    public int length;
    public ByteBuffer entity;
    public String guid;
    public String checksum;
    public int codex;

    public Envelope(String version, String address, String signature, int serial_num, int ts_secs, int length, ByteBuffer entity, String guid, String checksum) {
        this.version = version;
        this.address = address;
        this.signature = signature;
        this.serial_num = serial_num;
        this.ts_secs = ts_secs;
        this.length = length;
        this.entity = entity;
        this.guid = guid;
        this.checksum = checksum;
    }




    public void unpackFrom(BeanCoder umBeanCoder) throws UMException {
        while(true) {
            TField tField = umBeanCoder.readTField();
            if(tField.type == 0) {
                return;
            }

            switch(tField.id) {
                case 1:
                    if(tField.type == 11) {
                        version = umBeanCoder.readString();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 2:
                    if(tField.type == 11) {
                        address = umBeanCoder.readString();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 3:
                    if(tField.type == 11) {
                        signature = umBeanCoder.readString();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 4:
                    if(tField.type == 8) {
                        serial_num = umBeanCoder.readSignedInt();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 5:
                    if(tField.type == 8) {
                        ts_secs = umBeanCoder.readSignedInt();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 6:
                    if(tField.type == 8) {
                        length = umBeanCoder.readSignedInt();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 7:
                    if(tField.type == 11) {
                        entity = umBeanCoder.readByteBuffer();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 8:
                    if(tField.type == 11) {
                        guid = umBeanCoder.readString();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 9:
                    if(tField.type == 11) {
                        checksum = umBeanCoder.readString();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                case 10:
                    if(tField.type == 8) {
                        codex = umBeanCoder.readSignedInt();
                    } else {
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                    }
                    break;
                default:
                    BeanCoderSkiper.skip(umBeanCoder, tField.type);
            }
        }
    }



    public void packTo(BeanCoder umBeanCoder) throws UMException {
        if(version != null) {
            umBeanCoder.writeTField(Envelope.VERSION);
            umBeanCoder.writeString(version);
        }

        if(address != null) {
            umBeanCoder.writeTField(Envelope.ADDRESS);
            umBeanCoder.writeString(address);
        }

        if(signature != null) {
            umBeanCoder.writeTField(Envelope.SIGNATURE);
            umBeanCoder.writeString(signature);
        }

        umBeanCoder.writeTField(Envelope.SERIAL_NUM);
        umBeanCoder.writeUnsignedInt(serial_num);


        umBeanCoder.writeTField(Envelope.TS_SECS);
        umBeanCoder.writeUnsignedInt(ts_secs);

        umBeanCoder.writeTField(Envelope.LENGTH);
        umBeanCoder.writeUnsignedInt(length);

        if(entity != null) {
            umBeanCoder.writeTField(Envelope.ENTITY);
            umBeanCoder.writeByteBuffer(entity);
        }

        if(guid != null) {
            umBeanCoder.writeTField(Envelope.GUID);
            umBeanCoder.writeString(guid);
        }

        if(checksum != null) {
            umBeanCoder.writeTField(Envelope.CHECKSUM);
            umBeanCoder.writeString(checksum);
        }

        umBeanCoder.writeTField(Envelope.CODEX);
        umBeanCoder.writeUnsignedInt(codex);

        umBeanCoder.writeDivider();
    }
}




