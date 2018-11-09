package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProcessingByThread implements Runnable {

    private final ResultManager manager;


    @Override
    public void run() {

        if (!this.manager.getCertification().inventory().equalsIgnoreCase(""))
            try {
                Thread.sleep(this.manager.getSleep() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        this.manager.getResults();

        System.out.println();
        System.out.println("========== " + this.manager.getName() + " ==========");

        System.out.println("    Lien envoie : " + this.manager.getSendLink());

        System.out.println("    Lien result : " + this.manager.getResultLink());

        System.out.println("    Certification : " + (this.manager.isSuccess() ? "OK" : "KO"));

        System.out.println("========== " + this.manager.getName() + " ==========");
        System.out.println();

    }
}
