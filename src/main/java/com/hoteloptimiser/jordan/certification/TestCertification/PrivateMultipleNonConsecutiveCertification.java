package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Instances.Availability;
import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationClassActivation;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;

import java.util.function.Function;
import java.util.function.Predicate;

@CertificationClassActivation(classActivated = false)
public class PrivateMultipleNonConsecutiveCertification implements CertificationListener {

    private Boolean consumeAndApply(DailyManager update, Predicate<Availability> predicate) {
        Function<Integer, Boolean> consumer = (i) -> update.getUpdates()
                .get(i)
                .getAvailabilities()
                .stream()
                .filter(predicate)
                .findFirst().orElse(null) != null;

        return consumer.apply(0) && consumer.apply(2);
    }

    @Certification(xml = "createOpenMultiple.xml", inventory = "getInventory.xml", sleep = 20, id = 34, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
        }, replacement = {
            "SGL1", "false", "0", "PV1", "1", "0", "0", "true", "true", "02", "04"
        })
    public boolean createClosedAvail(DailyManager update) {
        return this.consumeAndApply(update, e ->
                e.getType().getCode().equalsIgnoreCase("PV1")
                        && !e.is_open()
                        && e.getHosting_type_code().equalsIgnoreCase("SGL1"));
    }

    @Certification(xml = "createOpenMultiple.xml", inventory = "getInventory.xml", sleep = 15, id = 35, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "false", "0", "PV1", "2", "0", "0", "true", "true", "02", "04"
    })
    public boolean modifyRetroDelay(DailyManager update) {
        return this.consumeAndApply(update, e ->
                e.getType().getCode().equalsIgnoreCase("PV1")
                        && !e.is_open()
                        && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                        && e.getType().getRetrocession_delay() == 2);
    }

    @Certification(xml = "createOpenMultiple.xml", inventory = "getInventory.xml", sleep = 15, id = 37, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "true", "0", "PV1", "2", "0", "0", "true", "true", "02", "04"
    })
    public boolean openAvail(DailyManager update) {
        return this.consumeAndApply(update, e ->
                e.getType().getCode().equalsIgnoreCase("PV1")
                        && e.is_open()
                        && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                        && e.getType().getRetrocession_delay() == 2);
    }

    @Certification(xml = "createOpenMultiple.xml", inventory = "getInventory.xml", sleep = 15, id = 38, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "true", "0", "PV1", "2", "2", "0", "true", "true", "02", "04"
    })
    public boolean minimumStay(DailyManager update) {
        return this.consumeAndApply(update, e -> e.getType().getCode().equalsIgnoreCase("PV1")
                && e.is_open()
                && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                && e.getType().getRetrocession_delay() == 2
                && e.getType().getMin_night_count() == 2);
    }

    @Certification(xml = "createOpenMultiple.xml", inventory = "getInventory.xml", sleep = 15, id = 39, values = {
            "@hosting", "@open", "@stock", "@code", "@delay", "@min_night", "@max_night", "@arrival", "@departure", "@day1", "@day2"
    }, replacement = {
            "SGL1", "true", "0", "PV1", "2", "2", "0", "true", "false", "02", "04"
    })
    public boolean closeDeparture(DailyManager update) {
        return this.consumeAndApply(update, e ->
                e.getType().getCode().equalsIgnoreCase("PV1")
                        && e.is_open()
                        && e.getHosting_type_code().equalsIgnoreCase("SGL1")
                        && e.getType().getRetrocession_delay() == 2
                        && e.getType().getMin_night_count() == 2
                        && !e.getType().isOpen_for_departure());
    }
}
