package com.crewkingstudio.dashboard;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class loading_fragment extends Fragment {
    TextView state;
    LinearLayout textbox;
    EditText text1;
    Button send;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.loading_fragment, container, false);
        state = root.findViewById(R.id.state);
        textbox = root.findViewById(R.id.textbox);
        text1 = root.findViewById(R.id.editText1);
        send = root.findViewById(R.id.buttonSend);

        state.setText("Connecting ...");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text1.getText().toString().equals("123456")){
                    mqttHelper.publish("H.hw", "main");
                    fragment_select(true);
                }
                else{
                    state.setText("try again ...");
                }
            }
        });

        startMQTT();

        return root;
    }
    boolean d = true;
    boolean dd = false;
    public static MQTTHelper mqttHelper;
    private void startMQTT(){
        mqttHelper = new MQTTHelper(getActivity().getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.e("mess", message.toString());
                //if (topic == "H.hw"){
                    if (message.toString().equals("login_connected")){
                        state.setText("Connected ...");
                        dd = true;
                    }
                    if (dd == true) {
                        if (message.toString().equals("login_seccessfull")) {
                            state.setText("Login Seccessfull ...");
                            textbox.setVisibility(View.VISIBLE);
                            mqttHelper.publish("H.hw", "sendmess");
                            //fragment_select(true);

                        }
                        if (message.toString().equals("login_fail")) {
                            state.setText("Login fail ...");

                        }
                    }

                //}
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
