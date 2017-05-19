package com.yxd.sum;

import java.nio.ByteBuffer;

public class Envelope implements SerialData{
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




    @Override
    public void packTo(Serializer serializer) {

    }

    @Override
    public void unpackFrom(Serializer serializer) {

    }


    @Override
    public void assertValid() throws Exception {
        if(this.version == null) {
            throw new Exception("Required field \'version\' was not present! Struct: " + this.toString());
        } else if(this.address == null) {
            throw new Exception("Required field \'address\' was not present! Struct: " + this.toString());
        } else if(this.signature == null) {
            throw new Exception("Required field \'signature\' was not present! Struct: " + this.toString());
        } else if(this.entity == null) {
            throw new Exception("Required field \'entity\' was not present! Struct: " + this.toString());
        } else if(this.guid == null) {
            throw new Exception("Required field \'guid\' was not present! Struct: " + this.toString());
        } else if(this.checksum == null) {
            throw new Exception("Required field \'checksum\' was not present! Struct: " + this.toString());
        }
    }
}
