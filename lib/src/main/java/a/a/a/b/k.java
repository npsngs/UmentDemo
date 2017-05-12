//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.j;
import a.a.a.b.c;
import a.a.a.b.d;
import a.a.a.b.e;
import a.a.a.b.h;
import a.a.a.b.l;
import a.a.a.b.b.a;

public class k {
    private static int a = 2147483647;

    public k() {
    }

    public static void a(int var0) {
        a = var0;
    }

    public static void a(h var0, byte var1) throws j {
        a(var0, var1, a);
    }

    public static void a(h var0, byte var1, int var2) throws j {
        if(var2 <= 0) {
            throw new j("Maximum skip depth exceeded");
        } else {
            switch(var1) {
                case 2:
                    var0.t();
                    break;
                case 3:
                    var0.u();
                    break;
                case 4:
                    var0.y();
                case 5:
                case 7:
                case 9:
                default:
                    break;
                case 6:
                    var0.v();
                    break;
                case 8:
                    var0.w();
                    break;
                case 10:
                    var0.x();
                    break;
                case 11:
                    var0.A();
                    break;
                case 12:
                    var0.j();

                    while(true) {
                        c var7 = var0.l();
                        if(var7.b == 0) {
                            var0.k();
                            return;
                        }

                        a(var0, var7.b, var2 - 1);
                        var0.m();
                    }
                case 13:
                    e var3 = var0.n();

                    for(int var8 = 0; var8 < var3.c; ++var8) {
                        a(var0, var3.a, var2 - 1);
                        a(var0, var3.b, var2 - 1);
                    }

                    var0.o();
                    break;
                case 14:
                    l var4 = var0.r();

                    for(int var9 = 0; var9 < var4.b; ++var9) {
                        a(var0, var4.a, var2 - 1);
                    }

                    var0.s();
                    break;
                case 15:
                    d var5 = var0.p();

                    for(int var6 = 0; var6 < var5.b; ++var6) {
                        a(var0, var5.a, var2 - 1);
                    }

                    var0.q();
            }

        }
    }

    public static a.a.a.b.j a(byte[] var0, a.a.a.b.j var1) {
        return (a.a.a.b.j)(var0[0] > 16?new a():(var0.length > 1 && (var0[1] & 128) != 0?new a():var1));
    }
}
