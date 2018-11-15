package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Data;
import lombok.experimental.Delegate;

import java.util.HashMap;

@Data
public class Replacement {

    private interface adder {
         String put(String a, String b);
    }

    @Delegate(types = adder.class)
    private HashMap<String, String> datas = new HashMap<>();
}
