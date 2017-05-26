package com.yxd.umentdemo;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.f.IdTracking;
import com.umeng.analytics.f.Imprint;
import com.umeng.analytics.g.UMEnvelope;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.ZipTool;
import com.yxd.ums.Simulator;

import org.json.JSONObject;


import java.io.File;
import java.io.FileInputStream;


import a.a.a.UMBeanUnpacker;
import a.a.a.UMBeanUnpacker_old;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        MobclickAgent.setDebugMode( true );
        MobclickAgent.enableEncrypt(false);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                parseIosEnvelope();
//                parseEnvelope();
//                parseImprint();
//            }
//        }).start();


        TaskExecutor.scheduleExecute(new SafeRunnable() {
            @Override
            public void safeRun() {
                Log.e("SafeRun", System.currentTimeMillis()+"");
//                Simulator.count = 200;
//                Simulator.header = "yo";
//                Simulator simulator = new Simulator(getApplicationContext());
//                simulator.report();
                Simulator simulator = new Simulator(getApplicationContext());

                for(int i =0;i<2000;i++){
                    simulator.addIosNew(""+i);
                }
            }
        });
    }

    private void parseEnvelope() {
        UMBeanUnpacker unpacker = new UMBeanUnpacker();
        try {
            AssetManager assetManager = getAssets();
            byte[] bytes = EncodeUtil.readData(assetManager.open("524_2.et"));

            UMEnvelope envelope = new UMEnvelope();
            unpacker.unpack(envelope, bytes);
            byte[] entity = envelope.entity.array();
            byte[] src = ZipTool.inflater(entity);
            String jsonEntity = new String(src);
            Log.d("entity",jsonEntity);


            JSONObject json = new JSONObject(jsonEntity);
            JSONObject header = json.getJSONObject("header");
            String idTrackingStr = header.getString("id_tracking");
            byte[] idTk = Base64.decode(idTrackingStr,0);
            IdTracking idTracking = new IdTracking();
            unpacker.unpack(idTracking, idTk);

            Log.d("idTracking",idTracking.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void parseIosEnvelope() {
        UMBeanUnpacker_old unpacker = new UMBeanUnpacker_old();
        try {
            AssetManager assetManager = getAssets();
            byte[] bytes = EncodeUtil.readData(assetManager.open("524_ios1.et"));
            UMEnvelope envelope = new UMEnvelope();
            unpacker.unpack(envelope, bytes);
            byte[] entity = envelope.entity.array();
            int pos = envelope.entity.position();
            int limit = envelope.entity.limit();
            entity = ZipTool.inflater(entity, pos, limit-pos);

            String jsonEntity = new String(entity, "utf-8");
            Log.d("entity",jsonEntity);


            JSONObject json = new JSONObject(jsonEntity);
            JSONObject header = json.getJSONObject("header");
            String idTrackingStr = header.getString("id_tracking");
            byte[] idTk = Base64.decode(idTrackingStr,0);
            IdTracking idTracking = new IdTracking();
            unpacker.unpack(idTracking, idTk);

            Log.d("idTracking",idTracking.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseImprint() {
        UMBeanUnpacker unpacker = new UMBeanUnpacker();
        File f = new File(Environment.getExternalStorageDirectory()+"/DUOYOU", "um.imprint");
        if(f.exists()){
            try {
                FileInputStream fis = new FileInputStream(f);
                byte[] bytes = EncodeUtil.readData(fis);
                Imprint imprint = new Imprint();
                unpacker.unpack(imprint, bytes);
                Log.d("imprint",imprint.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void parseIdTracking() {
        UMBeanUnpacker unpacker = new UMBeanUnpacker();
        File f = new File(Environment.getExternalStorageDirectory()+"/DUOYOU", "umeng_it.cache");
        if(f.exists()){
            try {
                FileInputStream fis = new FileInputStream(f);
                byte[] bytes = EncodeUtil.readData(fis);
                IdTracking idTracking = new IdTracking();
                unpacker.unpack(idTracking, bytes);
                Log.d("imprint",idTracking.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify pack parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
