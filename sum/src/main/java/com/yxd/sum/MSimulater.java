package com.yxd.sum;

import android.content.Context;
import com.umeng.analytics.c.UMEnvelopeData;
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.ULog;
import com.yxd.sum.bean.Response;
import com.yxd.sum.coder.NBeanUnpacker;
import com.yxd.sum.coder.OBeanCoder;
import com.yxd.sum.report.HttpSender;

import org.json.JSONException;
import org.json.JSONObject;

public class MSimulater {

    public static void send(final Context context, final String deviceId){
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                try {
                    EntityBuilder entityBuilder = new EntityBuilder();
                    JSONObject entity = entityBuilder.build(deviceId);
                    byte[] bytes = String.valueOf(entity).getBytes();
                    if(bytes == null) {
                        return;
                    }
                    ConfigGetter configGetter = new ConfigGetter(deviceId);
                    UMEnvelopeData envelopeData = UMEnvelopeData.create(context, configGetter.getAppkey(), bytes);
                    byte[] data = envelopeData.envelope();
                    HttpSender httpSender = new HttpSender(context);
                    byte[] respData = httpSender.send(data);
                    if(respData != null) {
                        parseResponse(respData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static int parseResponse(byte[] respData) {
        Response response = new Response();
        NBeanUnpacker beanUnpacker = new NBeanUnpacker(new OBeanCoder.UMBeanCoder_a_Builder());

        try {
            beanUnpacker.unpack(response, respData);
            ULog.c("parseResponse respCode="+response.respCode);
            ULog.c("parseResponse log:" + response.getMsg());
        } catch (Throwable t) {
        }

        return response.respCode == 1?2:3;
    }

}
