package com.example.spec.broadcastreceiversms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Broadcast Receiver to receive and use URLs.
        Bundle bundle = intent.getExtras();
        Log.d("SmileySmsReceiver", "Yes it calls the onReceive");

        if(bundle != null){
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage [] sms = new SmsMessage[messages.length];

            for(int n = 0; n < messages.length; n++){
                sms[n] = SmsMessage.createFromPdu((byte[])messages[n]);
            }
            //fetch the content of the SMS message

            String SMS = "";

            for(SmsMessage s: sms){
                Log.d("MY_BROADCAST_RECEIVER", s.getMessageBody());
                //logs the SMS message for debugging
                SMS = SMS.concat(s.getMessageBody());
                //put the URL into a string, so we can use add it to our list
            }

            Bundle bundle2 = new Bundle(); //create a bundle to put info in
            bundle2.putString("SMS", SMS);//put the URL into our bundle

            Intent startMyActivityIntent = new Intent(context.getApplicationContext(), MainActivity.class);
            startMyActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startMyActivityIntent.putExtras(bundle2); //put the bundle in the intent
            context.startActivity(startMyActivityIntent);//start the activity
        }//end if
    }//end onReceive
}//end myBroadcastReceiver
