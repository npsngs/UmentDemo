package com.yxd.ums;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.umeng.analytics.f.Imprint;
import com.umeng.analytics.f.Response;
import com.umeng.analytics.g.UMEnvelope;
import com.umeng.tool.EncodeUtil;
import com.yxd.ums.ios.IOSBox;
import com.yxd.ums.ios.UMBeanPacker_old;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import a.a.a.UMBeanPacker;
import a.a.a.UMBeanUnpacker;
import a.a.a.b.UMBeanCoder_a;

public class Simulator {
    private Context context;
    private JSONObject body;

    public static int count = 10;
    public static String header = "z";
    public Simulator(Context context) {
        this.context = context;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }

    public void report(){
        if(body == null){
            body = readFromCache(context);
        }
        report(body);
    }

    public void report(JSONObject body){
        if(body == null){
            return;
        }

        for(int i=0;i<count;i++){
            try {
                report(body, header+i);
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.e("SReport", "Seed:"+header+i);
        }
    }

    public void report(JSONObject body,String seed){
        try {
            SBox sBox = new SBox(context, seed);
            UMEnvelope envelope = sBox.buildEnvelopeWithBody(body);
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


    public static void writeToCache(Context context, JSONObject body) {
        if(body != null) {
            try {
                byte[] data = body.toString().getBytes("utf-8");
                EncodeUtil.writeToFile(new File(context.getFilesDir(), ".sbd"), data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONObject readFromCache(Context context) {
        try {
            File file = new File(context.getFilesDir(), ".sbd");
            if(file.exists()) {
                FileInputStream fis = null;
                byte[] data = null;
                try {
                    fis = context.openFileInput(".sbd");
                    data = EncodeUtil.readData(fis);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    EncodeUtil.close(fis);
                }

                if(data != null) {
                    String body = new String(data, "utf-8");
                    JSONObject jsonBody = new JSONObject(body);
                    return jsonBody;
                }

                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public void addIosNew(String seed){
        try {
            IOSBox sBox = new IOSBox(context, seed);
            UMEnvelope envelope = sBox.buildIosEnvelope();
            byte[] enveloped_data = new UMBeanPacker_old().pack2Bytes(envelope);

            LogSender sender = new LogSender(context, sBox.getIosConfig());

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
                        sBox.getIosConfig().updateImprintToDB(imprintStr);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}