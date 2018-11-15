package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationClassActivation;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;

@CertificationClassActivation
public class OtherTest implements CertificationListener {

    @Certification(xml = "createOpen.xml", id = 43, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "XXXX", "true", "0", "BAR-1-999", "1", "0", "0", "true", "true"
    }, success = "error")
    public boolean nonExisting(DailyManager update) {
        return true;
    }

    @Certification(xml = "getInventory.xml", inventory = "getInventory.xml", id = 42, success = "getInventoriesResponse")
    public boolean getInventory(DailyManager update) {
        return true;
    }

}
