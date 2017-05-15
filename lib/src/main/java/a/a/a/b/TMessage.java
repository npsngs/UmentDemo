//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

public final class TMessage {
    public final String name;
    public final byte type;
    public final int seqid;

    public TMessage() {
        this("", (byte)0, 0);
    }

    public TMessage(String name, byte type, int seqid) {
        this.name = name;
        this.type = type;
        this.seqid = seqid;
    }

    public String toString() {
        return "<TMessage domain:\'" + this.name + "\' type: " + this.type + " seqid:" + this.seqid + ">";
    }

    public boolean equals(Object var1) {
        return var1 instanceof TMessage ?this.isEqual((TMessage)var1):false;
    }

    public boolean isEqual(TMessage tMessage) {
        return this.name.equals(tMessage.name) && this.type == tMessage.type && this.seqid == tMessage.seqid;
    }
}
