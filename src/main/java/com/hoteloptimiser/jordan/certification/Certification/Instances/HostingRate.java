package com.hoteloptimiser.jordan.certification.Certification.Instances;

import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@Getter
@RequiredArgsConstructor
public class HostingRate {

    private final String type;

    private final double price;

    public static HostingRate newHostingrate(JSONObjectCustom obj) {
        return new HostingRate(obj.getString("hosting_type_code"), obj.getJSONObject("RateByRoom").getDouble("BuyingPrice"));
    }

}
