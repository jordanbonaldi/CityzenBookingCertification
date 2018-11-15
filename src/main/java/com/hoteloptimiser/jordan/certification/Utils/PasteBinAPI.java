package com.hoteloptimiser.jordan.certification.Utils;

import com.hoteloptimiser.jordan.certification.Utils.API.*;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasteBinAPI {

    private static final String DEV_KEY = System.getenv("devkey_pastebin");

    @SneakyThrows
    public static String newPasteBin(String title, String content) {
        PastebinAPI api = new PastebinAPI(DEV_KEY);

        User user = api.getUser(System.getenv("user_pastebin"), System.getenv("password_pastebin"));

        assert user != null;

        CreatePaste paste = user.createPaste()
                .withName(title)
                .withFormat(Format.XML)
                .withPrivacyLevel(PrivacyLevel.UNLISTED)
                .withExpireDate(ExpireDate.NEVER)
                .withText(content);

        return paste.post();
    }

}
