package com.hoteloptimiser.jordan.certification;

import com.hoteloptimiser.jordan.certification.ProcessManagers.CertificationManager;

public class Main {

    public static void main(String [] args) throws Exception {
        CertificationManager manager = new CertificationManager();

        manager.processCertifications().printResults();
    }

}
