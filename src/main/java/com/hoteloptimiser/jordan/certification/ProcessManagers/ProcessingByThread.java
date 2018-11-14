package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Data
public class ProcessingByThread implements Runnable {

    private final ResultManager manager;

    @Override
    @SneakyThrows
    public void run() {

        if (!this.manager.getCertification().inventory().equalsIgnoreCase(""))
            Thread.sleep(this.manager.getSleep() * 1000);

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
