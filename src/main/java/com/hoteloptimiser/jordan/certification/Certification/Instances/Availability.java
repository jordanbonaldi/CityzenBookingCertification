package com.hoteloptimiser.jordan.certification.Certification.Instances;

import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Availability {

    private final boolean is_open;

    private final String hosting_type_code;

    private final RateType type;

    private final int sold_count;

    public static Availability newAvailability(JSONObjectCustom obj){
        return new Availability(
                obj.getBoolean("is_open"),
                obj.getString("hosting_type_code"),
                RateType.newRateType(obj.getJSONObject("RateType")),
                obj.getInt("sold_count")
        );
    }
}
