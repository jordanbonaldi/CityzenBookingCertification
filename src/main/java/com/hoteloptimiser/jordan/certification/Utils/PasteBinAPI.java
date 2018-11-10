package com.hoteloptimiser.jordan.certification.Utils;

import com.hoteloptimiser.jordan.certification.Utils.API.*;

import java.io.IOException;

public class PasteBinAPI {

    private static final String DEV_KEY = System.getenv("devkey_pastebin");

    public static String newPasteBin(String title, String content) {
        PastebinAPI api = new PastebinAPI(DEV_KEY);

        User user = null;
        try {
            user = api.getUser(System.getenv("user_pastebin"), System.getenv("password_pastebin"));
        } catch (PastebinException e) {
            e.printStackTrace();
        }

        assert user != null;

        CreatePaste paste = user.createPaste()
                .withName(title)
                .withFormat(Format.XML)
                .withPrivacyLevel(PrivacyLevel.UNLISTED)
                .withExpireDate(ExpireDate.NEVER)
                .withText(content);

        try {
            return paste.post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "bullshit";
    }

}
