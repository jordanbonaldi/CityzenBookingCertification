package com.hoteloptimiser.jordan.certification;

import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationManager;
import lombok.val;

public class Main {

    public static void main(String [] args) {
        val manager = new CertificationManager();

        manager.processCertifications().printResults();
    }

}
