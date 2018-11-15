package com.hoteloptimiser.jordan.certification.TestCertification;

import com.hoteloptimiser.jordan.certification.Certification.Instances.Rate;
import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.ProcessManagers.Certification;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationClassActivation;
import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationListener;
import lombok.val;

@CertificationClassActivation
public class RatesMealCertification implements CertificationListener {

    private Rate getRates(DailyManager update, int x) {
        val rates = update.getUpdates()
                .get(x)
                .getRates()
                .stream()
                .filter(e -> e.getType().equalsIgnoreCase("PV1")).findFirst().orElse(null);

        if (rates == null)
            return null;
        return rates;
    }

    private boolean checkDay(DailyManager update, int x) {
        Rate rate = getRates(update, x);

        if (rate == null)
            return false;

        return rate
                .getMeals()
                .stream()
                .filter(e -> e.getCode() == 11 && e.getPrice() == 10)
                .findFirst()
                .orElse(null) != null;
    }

    @Certification(xml = "Meal.xml", inventory = "getInventory.xml", sleep = 15, id = 14, values = {
            "@code", "@hosting", "@price", "@mealCode", "@mealPrice"
    }, replacement = {
            "PV1", "SGL1", "70", "11", "10"
    })
    public boolean createMeal(DailyManager update) {
        return checkDay(update, 0);
    }

    @Certification(xml = "MealMultiple.xml", inventory = "getInventory.xml", sleep = 15, id = 28, values = {
            "@code", "@hosting", "@price", "@mealCode", "@mealPrice", "@day1", "@day2"
    }, replacement = {
            "PV1", "SGL1", "70", "11", "10", "02", "03"
    })
    public boolean createMealTwoDays(DailyManager update) {
        return checkDay(update, 0) && checkDay(update, 1);
    }

    @Certification(xml = "MealMultiple.xml", inventory = "getInventory.xml", sleep = 15, id = 41, values = {
            "@code", "@hosting", "@price", "@mealCode", "@mealPrice", "@day1", "@day2"
    }, replacement = {
            "PV1", "SGL1", "70", "11", "10", "02", "04"
    })
    public boolean createMealTwoNonConsecutiveDays(DailyManager update) {
        return checkDay(update, 0) && checkDay(update, 2);
    }
}
