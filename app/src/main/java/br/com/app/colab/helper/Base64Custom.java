package br.com.app.colab.helper;

import android.util.Base64;

public class Base64Custom {

    public static String Base64Encoder(String email) {

        return new String(Base64.encodeToString(email.getBytes(), Base64.DEFAULT)).replace("\n|\r", "");
    }

    public static String Base64Decoder(String emailEncoded) {

        return new String(Base64.decode(emailEncoded, Base64.DEFAULT));
    }
}
