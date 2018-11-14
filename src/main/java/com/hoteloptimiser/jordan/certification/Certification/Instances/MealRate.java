package com.hoteloptimiser.jordan.certification.Certification.Instances;

import com.hoteloptimiser.jordan.certification.Certification.CertificationInterface;
import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@Data
public class MealRate {

    private final int code;

    private final double price;

    public static MealRate newMealRate(JSONObjectCustom obj) {
        return new MealRate(obj.getInt("meal_plan_code"), obj.getDouble("adult_price"));
    }

}
