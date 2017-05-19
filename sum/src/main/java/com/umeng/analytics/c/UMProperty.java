//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import com.yxd.sum.bean.IdJournal;
import com.yxd.sum.bean.IdSnapshot;
import com.yxd.sum.bean.IdTracking;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public abstract class UMProperty {
    private final int a = 10;
    private final int b = 20;
    private final String name;
    private List<IdJournal> journalBs;
    private IdSnapshot idSnapshot;

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
        IdSnapshot idSnapshot = this.idSnapshot;
        String identity = idSnapshot == null?null:idSnapshot.getIdentity();
        int version = idSnapshot == null?0:idSnapshot.getVersion();
        String value = this.parseValue(this.getValue());
        if(value != null && !value.equals(identity)) {
            if(idSnapshot == null) {
                idSnapshot = new IdSnapshot();
            }

            idSnapshot.setIdentity(value);
            idSnapshot.setTS(System.currentTimeMillis());
            idSnapshot.setVersion(version + 1);
            IdJournal idJournal = new IdJournal();
            idJournal.b(this.name);
            idJournal.c(value);
            idJournal.b(identity);
            idJournal.a(idSnapshot.getTS());
            if(this.journalBs == null) {
                this.journalBs = new ArrayList(2);
            }

            this.journalBs.add(idJournal);
            if(this.journalBs.size() > 10) {
                this.journalBs.remove(0);
            }

            this.idSnapshot = idSnapshot;
            return true;
        } else {
            return false;
        }
    }

    public IdSnapshot getIdSnapshot() {
        return this.idSnapshot;
    }

    public void setIdSnapshot(IdSnapshot snapshot) {
        this.idSnapshot = snapshot;
    }

    public List<IdJournal> getJournals() {
        return this.journalBs;
    }

    public void setJournals(List<IdJournal> var1) {
        this.journalBs = var1;
    }

    public String parseValue(String value) {
        if(value == null) {
            return null;
        } else {
            value = value.trim();
            return value.length() == 0?null:("0".equals(value)?null:("unknown".equals(value.toLowerCase(Locale.US))?null:value));
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
                IdJournal idJournal = (IdJournal)iterator.next();
                if(this.name.equals(idJournal.domain)) {
                    this.journalBs.add(idJournal);
                }
            }
        }

    }
}
