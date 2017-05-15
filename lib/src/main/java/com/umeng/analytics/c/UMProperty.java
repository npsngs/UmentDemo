//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import com.umeng.analytics.f.IdJournal_b;
import com.umeng.analytics.f.IdSnapshot_c;
import com.umeng.analytics.f.IdTracking;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public abstract class UMProperty {
    private final int a = 10;
    private final int b = 20;
    private final String name;
    private List<IdJournal_b> journalBs;
    private IdSnapshot_c idSnapshot;

    public UMProperty(String name) {
        this.name = name;
    }

    public boolean a() {
        return this.g();
    }

    public String getName() {
        return this.name;
    }

    public boolean isValidIDSnapshot() {
        return this.idSnapshot == null || this.idSnapshot.getVersion() <= 20;
    }

    private boolean g() {
        IdSnapshot_c var1 = this.idSnapshot;
        String var2 = var1 == null?null:var1.getIdentity();
        int var3 = var1 == null?0:var1.getVersion();
        String var4 = this.a(this.getValue());
        if(var4 != null && !var4.equals(var2)) {
            if(var1 == null) {
                var1 = new IdSnapshot_c();
            }

            var1.setIdentity(var4);
            var1.setTS(System.currentTimeMillis());
            var1.a(var3 + 1);
            IdJournal_b var5 = new IdJournal_b();
            var5.b(this.name);
            var5.c(var4);
            var5.b(var2);
            var5.a(var1.getTS());
            if(this.journalBs == null) {
                this.journalBs = new ArrayList(2);
            }

            this.journalBs.add(var5);
            if(this.journalBs.size() > 10) {
                this.journalBs.remove(0);
            }

            this.idSnapshot = var1;
            return true;
        } else {
            return false;
        }
    }

    public IdSnapshot_c getIdSnapshot() {
        return this.idSnapshot;
    }

    public void setIdSnapshot(IdSnapshot_c snapshot) {
        this.idSnapshot = snapshot;
    }

    public List<IdJournal_b> getJournals() {
        return this.journalBs;
    }

    public void setJournals(List<IdJournal_b> var1) {
        this.journalBs = var1;
    }

    public String a(String var1) {
        if(var1 == null) {
            return null;
        } else {
            var1 = var1.trim();
            return var1.length() == 0?null:("0".equals(var1)?null:("unknown".equals(var1.toLowerCase(Locale.US))?null:var1));
        }
    }

    public abstract String getValue();

    public void addJournal(IdTracking idTracking) {
        this.idSnapshot = idTracking.getSnapShots().get(this.name);
        List journals = idTracking.getJournals();
        if(journals != null && journals.size() > 0) {
            if(this.journalBs == null) {
                this.journalBs = new ArrayList();
            }

            Iterator iterator = journals.iterator();

            while(iterator.hasNext()) {
                IdJournal_b idJournal = (IdJournal_b)iterator.next();
                if(this.name.equals(idJournal.domain)) {
                    this.journalBs.add(idJournal);
                }
            }
        }

    }
}
