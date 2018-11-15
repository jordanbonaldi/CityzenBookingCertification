package com.hoteloptimiser.jordan.certification.Certification.Instances;

import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
public class RateType {

    private final int retrocession_time_hour;
    private final int min_night_count;
    private final int retrocession_delay;
    private final String code;
    private final int max_night_count;
    private final boolean open_for_arrival;
    private final boolean open_for_departure;
    private final boolean request_accepted;

    public static RateType newRateType(JSONObjectCustom objectCustom) {
        return new RateType(
                objectCustom.getInt("retrocession_time_hour"),
                objectCustom.getInt("min_night_count"),
                objectCustom.getInt("retrocession_delay"),
                objectCustom.getString("code"),
                objectCustom.getInt("max_night_count"),
                objectCustom.getBoolean("open_for_arrival"),
                objectCustom.getBoolean("open_for_departure"),
                objectCustom.getBoolean("request_accepted"));
    }

}
