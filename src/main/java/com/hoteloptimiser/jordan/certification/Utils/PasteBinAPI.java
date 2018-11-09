package com.hoteloptimiser.jordan.certification.Utils;

import me.nrubin29.pastebinapi.CreatePaste;
import me.nrubin29.pastebinapi.ExpireDate;
import me.nrubin29.pastebinapi.PastebinAPI;
import me.nrubin29.pastebinapi.PastebinException;
import me.nrubin29.pastebinapi.PrivacyLevel;
import me.nrubin29.pastebinapi.User;

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
                .withFormat(me.nrubin29.pastebinapi.Format.XML)
                .withPrivacyLevel(PrivacyLevel.UNLISTED)
                .withExpireDate(ExpireDate.NEVER)
                .withText(content);

        try {
            return paste.post();
        } catch (PastebinException e) {
            e.printStackTrace();
        }
        return "bullshit";
    }

}
