package com.hoteloptimiser.jordan.certification.Certification.Instances;

import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Rate {

    private final String type;

    private final List<HostingRate> rates;

    private final String currency;

    private final List<MealRate> meals;

    public static Rate newRate(JSONObjectCustom obj) throws JSONException {

        List<HostingRate> rates = obj.getJSONObject("HostingRates").arrayToList("HostingRate", HostingRate::newHostingrate);

        List<MealRate> meals = obj.isExists("MealRates") ? obj.getJSONObject("MealRates").arrayToList("MealRate", MealRate::newMealRate) : new ArrayList<>();

        return new Rate(obj.getString("rate_type_code"), rates, obj.getString("currency_code"), meals);
    }
}
