package com.crewkingstudio.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class main_fragment extends Fragment {

    TextView tmp, hum, ppm, name, ID, birth,spec;

    Button btnlogut, btnlcd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        tmp = root.findViewById(R.id.temperture);
        hum = root.findViewById(R.id.hum);
        ppm = root.findViewById(R.id.ppm);
        name = root.findViewById(R.id.name);
        ID = root.findViewById(R.id.ID);
        birth = root.findViewById(R.id.birth);
        spec = root.findViewById(R.id.spec);
        btnlogut = root.findViewById(R.id.btnlogut);
        btnlcd = root.findViewById(R.id.btnlcd);

        btnlcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                lcddinalog_fragment d = new lcddinalog_fragment();
                d.show(fragmentManager, "btn");
            }
        });

        btnlogut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_select(false);
            }
        });

        startMQTT();


        return root;
    }
    public static MQTTHelper mqttHelper;
    private void startMQTT(){
        mqttHelper = new MQTTHelper(getActivity().getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                //mqttHelper.publish("H.hw", "main");
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.e("mess", message.toString());

                if (topic.equals("H.hw")) {
                    Pattern p;
                    Matcher m;

                    p = Pattern.compile("name=([\\w ]+[^#])#id=([\\w ]+[^#])#birt=([\\w ]+[^#])#spec=([\\w ]+[^#])#");
                    m = p.matcher(message.toString());
                    while (m.find()){
                        name.setText(m.group(1));
                        ID.setText(m.group(2));
                        birth.setText(m.group(3));
                        spec.setText(m.group(4));
                    }
                    p = Pattern.compile("t=([+-]?([0-9]*[.])?[0-9]+)h=([+-]?([0-9]*[.])?[0-9]+)k=([+-]?([0-9]*[.])?[0-9]+)");
                    m = p.matcher(message.toString());
                    while (m.find()){
                        tmp.setText(m.group(1) + " C");
                        hum.setText(m.group(3) + " %");
                        ppm.setText(m.group(5) + " ppm");
                    }

                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

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
