package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationClassActivation;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;

@CertificationClassActivation
public class PrivateSingleCertification implements CertificationListener {

    @Certification(xml = "createOpen.xml", inventory = "getInventory.xml", sleep = 20, id = 7, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
        }, replacement = {
            "SGL1", "false", "0", "PV1", "1", "0", "0", "true", "true"
        })
    public boolean createClosedAvail(DailyManager update) {
        return update.getUpdates()
                .get(0)
                .getAvailabilities()
                .stream()
                .filter(e ->
                        e.getType().getCode().equalsIgnoreCase("PV1")
                        && !e.is_open()
                        && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                )
                .findFirst().orElse(null) != null;
    }

    @Certification(xml = "getInventory.xml", inventory = "getInventory.xml", sleep = 20, id = 8, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "false", "0", "PV1", "1", "0", "0", "true", "true"
    }, success = "getInventoriesResponse")
    public boolean getInventory(DailyManager update) {
        return update.getUpdates()
                .get(0)
                .getAvailabilities()
                .stream()
                .filter(e ->
                        e.getType().getCode().equalsIgnoreCase("PV1")
                                && !e.is_open()
                                && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                )
                .findFirst().orElse(null) != null;
    }

    @Certification(xml = "createOpen.xml", inventory = "getInventory.xml", sleep = 15, id = 9, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "false", "0", "PV1", "2", "0", "0", "true", "true"
    })
    public boolean modifyRetroDelay(DailyManager update) {
        return update.getUpdates()
                .get(0)
                .getAvailabilities()
                .stream()
                .filter(e ->
                        e.getType().getCode().equalsIgnoreCase("PV1")
                                && !e.is_open()
                                && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                                && e.getType().getRetrocession_delay() == 2
                )
                .findFirst().orElse(null) != null;
    }

    @Certification(xml = "createOpen.xml", inventory = "getInventory.xml", sleep = 15, id = 11, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "true", "0", "PV1", "2", "0", "0", "true", "true"
    })
    public boolean openAvail(DailyManager update) {
        return update.getUpdates()
                .get(0)
                .getAvailabilities()
                .stream()
                .filter(e ->
                        e.getType().getCode().equalsIgnoreCase("PV1")
                                && e.is_open()
                                && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                                && e.getType().getRetrocession_delay() == 2
                )
                .findFirst().orElse(null) != null;
    }

    @Certification(xml = "createOpen.xml", inventory = "getInventory.xml", sleep = 15, id = 12, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "true", "0", "PV1", "2", "2", "0", "true", "true"
    })
    public boolean minimumStay(DailyManager update) {
        return update.getUpdates()
                .get(0)
                .getAvailabilities()
                .stream()
                .filter(e ->
                        e.getType().getCode().equalsIgnoreCase("PV1")
                                && e.is_open()
                                && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                                && e.getType().getRetrocession_delay() == 2
                                && e.getType().getMin_night_count() == 2
                )
                .findFirst().orElse(null) != null;
    }

    @Certification(xml = "createOpen.xml", inventory = "getInventory.xml", sleep = 15, id = 13, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure"
    }, replacement = {
            "SGL1", "true", "0", "PV1", "2", "2", "0", "true", "false"
    })
    public boolean closeDeparture(DailyManager update) {
        return update.getUpdates()
                .get(0)
                .getAvailabilities()
                .stream()
                .filter(e ->
                        e.getType().getCode().equalsIgnoreCase("PV1")
                                && e.is_open()
                                && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                                && e.getType().getRetrocession_delay() == 2
                                && e.getType().getMin_night_count() == 2
                                && !e.getType().isOpen_for_departure()
                )
                .findFirst().orElse(null) != null;
    }
}
