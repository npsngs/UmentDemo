package com.yxd.ums;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.umeng.analytics.f.Imprint;
import com.umeng.analytics.f.Response;
import com.umeng.analytics.g.UMEnvelope;
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.TaskExecutor;

import a.a.a.UMBeanPacker;
import a.a.a.UMBeanUnpacker;
import a.a.a.b.UMBeanCoder_a;

public class Simulator {
    private Context context;

    public Simulator(Context context) {
        this.context = context;

    }

    public void addNews(){
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                for(int i=0;i<100;i++){
                    addNew("1"+i);
                }
            }
        });
    }

    public void addNew(String seed){
        try {
            SBox sBox = new SBox(context, seed);
            UMEnvelope envelope = sBox.buildEnvelope();
            byte[] enveloped_data = new UMBeanPacker().pack2Bytes(envelope);

            LogSender sender = new LogSender(context, sBox.getSeedConfig(),sBox.getPublicConfig());

            byte[] respData = sender.send(enveloped_data);
            if(respData != null) {
                Response response = new Response();
                UMBeanUnpacker beanUnpacker = new UMBeanUnpacker(new UMBeanCoder_a.UMBeanCoder_a_Builder());

                beanUnpacker.unpack(response, respData);
                if(response.respCode == 1) {
                    Imprint imprint = response.getImprint();
                    if(imprint != null) {
                        byte[] imprintData = (new UMBeanPacker()).pack2Bytes(imprint);
                        String imprintStr = Base64.encodeToString(imprintData, 0);
                        sBox.getSeedConfig().updateImprintToDB(imprintStr);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
