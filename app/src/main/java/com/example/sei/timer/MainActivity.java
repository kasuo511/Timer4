package com.example.sei.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity {
    ProgressBar bar = null;
    Button startButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar)findViewById(R.id.bar);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new ButtonListener());
    }
    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            bar.setVisibility(View.VISIBLE);
            updateBarHandler.sendEmptyMessage(0);
        }
    }
      Handler updateBarHandler = new Handler(){
        int i = 0;
        @Override
        public void handleMessage(Message msg){
        if (msg.what == 0){
            updateBarHandler.removeMessages(0);
            i++;
            Log.d("Sei","i="+i);
            bar.setProgress(i);
            if (i <= 100) {
                updateBarHandler.sendEmptyMessageDelayed(0,100);
            } else {
                i = 0;
                Toast toast = Toast.makeText(getApplicationContext(), "蹲的时间太长了，快起来", Toast.LENGTH_LONG);//显示时间较长
                toast.setGravity(Gravity.CENTER, 0, 0);// 居中显示
                toast.show();
            }
        }
    }
        };
            }
    //线程类,此用匿名内部类的方法进行声明
    /**Runnable updateThread =new Runnable() {
        int i = 0 ;
        @Override
        public void run() {
            i = i + 1 ;
            Message msg = updateBarHandler.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            updateBarHandler.sendMessage(msg);
            if(i == 100) { updateBarHandler.removeCallbacks(updateThread);
        }
    }
    };
        */
