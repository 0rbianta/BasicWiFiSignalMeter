package com.com.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

/*
            In summary:

            WifiManager WM = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            int strength = WM.getConnectionInfo().getRssi();
            System.out.println(strength);

            strength is variable representing Wi-Fi signal strength.
*/


public class MainActivity extends AppCompatActivity {

    private Boolean GO,x=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            public void run() {
                //loop

                while(x){
                    ConnectivityManager CM = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(CM.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //True
                        GO=true;
                        scan();
                    }else{

                        //False
                        GO=false;
                        scan();
                    }
                }


            }
        }).start();

    }

    private void scan(){
        if(GO==true){
            WifiManager WM = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            int strength = WM.getConnectionInfo().getRssi();
            System.out.println(strength);
            TextView txtStrength=(TextView)findViewById(R.id.txtStrength);
            txtStrength.setText(Integer.toString(strength));
        }else{
            TextView txtStrength=(TextView)findViewById(R.id.txtStrength);
            txtStrength.setText("N/A");
        }
    }
}