package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationClassActivation;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;

@CertificationClassActivation
public class SharedMultipleCertification implements CertificationListener {

    @Certification(xml = "createOpen.xml", id = 16, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
        }, replacement = {
            "SGL1", "true", "0", "BAR-1-999-FLEX", "1", "0", "0", "true", "true", "02", "03"
        })
    public boolean createOpenAvail(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 17, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "true", "1", "BAR-1-999-FLEX", "1", "0", "0", "true", "true", "02", "03"
    })
    public boolean changeStock(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 18, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "false", "1", "BAR-1-999-FLEX", "1", "0", "0", "true", "true", "02", "03"
    })
    public boolean closeAvail(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 19, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "false", "1", "BAR-1-999-FLEX", "1", "0", "2", "true", "true", "02", "03"
    })
    public boolean modifyMaxStay(DailyManager update) {
        return true;
    }

    @Certification(xml = "createOpen.xml", id = 20, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "false", "1", "BAR-1-999-FLEX", "1", "0", "2", "false", "true", "02", "03"
    })
    public boolean closeArrival(DailyManager update) {
        return true;
    }
}
