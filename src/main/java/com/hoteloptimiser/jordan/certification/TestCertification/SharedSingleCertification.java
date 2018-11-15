package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationClassActivation;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;

@CertificationClassActivation(classActivated = false)
public class SharedSingleCertification implements CertificationListener {

    @Certification(xml = "createOpen.xml", id = 1, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
        }, replacement = {
            "SGL1", "true", "0", "BAR-1-999-FLEX", "1", "0", "0", "true", "true"
        })
    public boolean createOpenAvail(DailyManager update) {
        return true;
    }

    @Certification(xml = "getInventory.xml", inventory = "getInventory.xml", id = 2, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "true", "0", "BAR-1-999-FLEX", "1", "0", "0", "true", "true"
    }, success = "getInventoriesResponse")
    public boolean getInventory(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 3, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "true", "1", "BAR-1-999-FLEX", "1", "0", "0", "true", "true"
    })
    public boolean changeStock(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 4, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "false", "1", "BAR-1-999-FLEX", "1", "0", "0", "true", "true"
    })
    public boolean closeAvail(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 5, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "false", "1", "BAR-1-999-FLEX", "1", "0", "2", "true", "true"
    })
    public boolean modifyMaxStay(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 6, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "false", "1", "BAR-1-999-FLEX", "1", "0", "2", "false", "true"
    })
    public boolean closeArrival(DailyManager update) {
        return true;
    }
}
