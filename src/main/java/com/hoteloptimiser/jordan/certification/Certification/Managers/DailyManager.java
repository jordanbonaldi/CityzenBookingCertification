package com.hoteloptimiser.jordan.certification.Certification.Managers;

import com.hoteloptimiser.jordan.certification.Certification.CertificationInterface;
import com.hoteloptimiser.jordan.certification.Certification.Instances.DailyUpdate;
import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Getter;
import org.json.JSONException;

import java.util.List;

@Getter
public class DailyManager implements CertificationInterface {

    private List<DailyUpdate> updates;

    @Override
    public void deserialize(JSONObjectCustom obj) throws JSONException {
        this.updates = obj.arrayToList("DailyUpdate", DailyUpdate::newDaily);
    }
}
