package com.hoteloptimiser.jordan.certification.Certification.Managers;

import com.hoteloptimiser.jordan.certification.Certification.CertificationInterface;
import com.hoteloptimiser.jordan.certification.Certification.Instances.Rate;
import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Getter
public class RatesManager implements CertificationInterface {

    private List<Rate> rates;

    @Override
    public void deserialize(JSONObjectCustom obj) throws JSONException {
        obj = obj.getJSONObject("Rates");
        this.rates = obj.arrayToList("Rate", Rate::newRate);
    }
}
