//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.social;

import android.text.TextUtils;
import com.umeng.tool.ULog;
import java.util.Locale;

public class UMPlatformData {
    private UMPlatformData.UMedia a;
    private String b = "";
    private String c = "";
    private String d;
    private UMPlatformData.GENDER e;

    public UMPlatformData(UMPlatformData.UMedia var1, String var2) {
        if(var1 != null && !TextUtils.isEmpty(var2)) {
            this.a = var1;
            this.b = var2;
        } else {
            ULog.e("parameter is not valid");
        }
    }

    public String getWeiboId() {
        return this.c;
    }

    public void setWeiboId(String var1) {
        this.c = var1;
    }

    public UMPlatformData.UMedia getMeida() {
        return this.a;
    }

    public String getUsid() {
        return this.b;
    }

    public String getName() {
        return this.d;
    }

    public void setName(String var1) {
        this.d = var1;
    }

    public UMPlatformData.GENDER getGender() {
        return this.e;
    }

    public void setGender(UMPlatformData.GENDER var1) {
        this.e = var1;
    }

    public boolean isValid() {
        return this.a != null && !TextUtils.isEmpty(this.b);
    }

    public String toString() {
        return "UMPlatformData [meida=" + this.a + ", usid=" + this.b + ", weiboId=" + this.c + ", name=" + this.d + ", gender=" + this.e + "]";
    }

    public static enum GENDER {
        MALE(0) {
            public String toString() {
                return String.format(Locale.US, "Male:%loadChannel", new Object[]{Integer.valueOf(this.value)});
            }
        },
        FEMALE(1) {
            public String toString() {
                return String.format(Locale.US, "Female:%loadChannel", new Object[]{Integer.valueOf(this.value)});
            }
        };

        public int value;

        private GENDER(int var3) {
            this.value = var3;
        }
    }

    public static enum UMedia {
        SINA_WEIBO {
            public String toString() {
                return "sina";
            }
        },
        TENCENT_WEIBO {
            public String toString() {
                return "tencent";
            }
        },
        TENCENT_QZONE {
            public String toString() {
                return "qzone";
            }
        },
        TENCENT_QQ {
            public String toString() {
                return "qq";
            }
        },
        WEIXIN_FRIENDS {
            public String toString() {
                return "wxsesion";
            }
        },
        WEIXIN_CIRCLE {
            public String toString() {
                return "wxtimeline";
            }
        },
        RENREN {
            public String toString() {
                return "renren";
            }
        },
        DOUBAN {
            public String toString() {
                return "douban";
            }
        };

        private UMedia() {
        }
    }
}
