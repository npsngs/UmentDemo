//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.obj;

public final class TMessage {
    public final String domain;
    public final byte type;
    public final int seqid;

    public TMessage() {
        this("", (byte)0, 0);
    }

    public TMessage(String name, byte type, int seqid) {
        this.domain = name;
        this.type = type;
        this.seqid = seqid;
    }

    public String toString() {
        return "<TMessage domain:\'" + this.domain + "\' type: " + this.type + " seqid:" + this.seqid + ">";
    }

    public boolean equals(Object var1) {
        return var1 instanceof TMessage ?this.isEqual((TMessage)var1):false;
    }

    public boolean isEqual(TMessage tMessage) {
        return this.domain.equals(tMessage.domain) && this.type == tMessage.type && this.seqid == tMessage.seqid;
    }
}
