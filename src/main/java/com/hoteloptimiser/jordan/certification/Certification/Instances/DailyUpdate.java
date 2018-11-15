package com.hoteloptimiser.jordan.certification.Certification.Instances;

import com.hoteloptimiser.jordan.certification.Certification.Managers.AvailabilitiesManager;
import com.hoteloptimiser.jordan.certification.Certification.Managers.RatesManager;
import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Data;

import java.util.List;

@Data
public class DailyUpdate {

    private final String date;

    private final List<Rate> rates;

    private final List<Availability> availabilities;

    public static DailyUpdate newDaily(JSONObjectCustom obj) {
        RatesManager ratesManager = new RatesManager();
        ratesManager.deserialize(obj);

        AvailabilitiesManager availabilitiesManager = new AvailabilitiesManager();
        availabilitiesManager.deserialize(obj);

        return new DailyUpdate(obj.getString("date"), ratesManager.getRates(), availabilitiesManager.getAvailabilities());
    }

}
