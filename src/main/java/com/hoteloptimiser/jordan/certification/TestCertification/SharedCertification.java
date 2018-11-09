package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Instances.Availability;
import com.hoteloptimiser.jordan.certification.Certification.Instances.DailyUpdate;
import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;

public class SharedCertification implements CertificationListener {

    @Certification(xml = "createOpen.xml", inventory="getInventory.xml", sleep = 10, id = 1)
    public boolean createOpenAvail(DailyManager update) {
        DailyUpdate day = update.getUpdates().get(0);

        Availability avail = day.getAvailabilities().stream().filter(e -> e.getHosting_type_code().equalsIgnoreCase("SGL1")).findFirst().orElse(null);

        if (avail == null)
            return false;

        return avail.getSold_count() == 6;
    }

}
