package com.cqeec.by.sos;

import android.telephony.SmsManager;

import java.util.ArrayList;

public class SendSMS {
    String message;
    String phone;

    public SendSMS(String message, String phone) {
        this.message = message;
        this.phone = phone;
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> list = smsManager.divideMessage(message);
        for (String text:list) {
            smsManager.sendTextMessage(phone, null, text, null, null);
        }
    }

}
