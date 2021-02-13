package com.crewkingstudio.dashboard;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragment_select(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fragment_select (boolean i){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;

        if (i == true) {
            fragment = new main_fragment();
        } else if (i == false) {
            fragment = new loading_fragment();
        }

        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

}