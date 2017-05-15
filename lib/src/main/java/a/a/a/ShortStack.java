//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

public class ShortStack {
    private short[] a;
    private int top = -1;

    public ShortStack(int len) {
        this.a = new short[len];
    }

    public short pop() {
        return this.a[this.top--];
    }

    public void push(short var1) {
        if(this.a.length == this.top + 1) {
            this.resize();
        }

        this.a[++this.top] = var1;
    }

    private void resize() {
        short[] var1 = new short[this.a.length * 2];
        System.arraycopy(this.a, 0, var1, 0, this.a.length);
        this.a = var1;
    }

    public short peek() {
        return this.a[this.top];
    }

    public void clear() {
        this.top = -1;
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("<ShortStack vector:[");

        for(int var2 = 0; var2 < this.a.length; ++var2) {
            if(var2 != 0) {
                var1.append(" ");
            }

            if(var2 == this.top) {
                var1.append(">>");
            }

            var1.append(this.a[var2]);
            if(var2 == this.top) {
                var1.append("<<");
            }
        }

        var1.append("]>");
        return var1.toString();
    }
}
