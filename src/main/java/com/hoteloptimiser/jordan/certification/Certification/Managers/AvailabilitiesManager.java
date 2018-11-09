package com.hoteloptimiser.jordan.certification.Certification.Managers;

import com.hoteloptimiser.jordan.certification.Certification.CertificationInterface;
import com.hoteloptimiser.jordan.certification.Certification.Instances.Availability;
import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Getter;

import java.util.List;

@Getter
public class AvailabilitiesManager implements CertificationInterface {

    private List<Availability> availabilities;

    @Override
    public void deserialize(JSONObjectCustom obj) {
        obj = obj.getJSONObject("Availabilities");
        this.availabilities = obj.arrayToList("Availability", Availability::newAvailability);
    }


}
