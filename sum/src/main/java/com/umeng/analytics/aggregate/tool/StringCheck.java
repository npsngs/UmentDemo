//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.tool;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class StringCheck {
    private StringCheck() {
    }

    public static StringCheck getInstance() {
        return StringCheckInner.STRING_CHECK;
    }

    public boolean a(String input) {
        return "".equals(input)?true:(input == null?false:input.getBytes().length < 160 && this.a(input, 48));
    }

    public boolean b(String input) {
        return TextUtils.isEmpty(input)?false:(input.length() >= 16?false:this.a(input, 48));
    }

    public boolean a(List<String> list) {
        if(list == null) {
            return false;
        } else {
            if(list.size() > 1) {
                for(int i = 1; i < list.size(); ++i) {
                    if(TextUtils.isEmpty(list.get(i))) {
                        return false;
                    }

                    if(!this.a(list.get(i), 48)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private boolean a(String input, int var2) {
        for(int i = 0; i < input.length(); ++i) {
            char charAt = input.charAt(i);
            if(charAt < var2) {
                return false;
            }
        }

        return true;
    }

    public int b() {
        return 8;
    }

    public int c() {
        return 128;
    }

    public int d() {
        return 512;
    }

    public boolean b(List<String> var1) {
        if(var1 == null) {
            return false;
        } else if(var1.size() <= 0) {
            return false;
        } else {
            int var2 = 0;

            String var4;
            for(Iterator var3 = var1.iterator(); var3.hasNext(); var2 += var4.getBytes().length) {
                var4 = (String)var3.next();
            }

            return var2 < 256;
        }
    }

    private static class StringCheckInner {
        private static final StringCheck STRING_CHECK = new StringCheck();

        private StringCheckInner() {
        }
    }
}
