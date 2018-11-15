package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Data
public class TaskSendProcessing implements TaskProcess {

    private final ResultManager manager;

    @Override
    @SneakyThrows
    public void run() {

        this.manager.sendXml();

        if (!this.manager.getCertification().inventory().equalsIgnoreCase(""))
            Thread.sleep(this.manager.getSleep() * 1000);
        
        this.manager.getResults();
    }

    public void action() {
        System.out.println();
        System.out.println("========== " + this.manager.getName() + " of Class " + this.manager.getClazz().getSimpleName() + " ==========");

        System.out.println("    Send Link : " + this.manager.getSendLink());

        System.out.println("    Result Link : " + this.manager.getResultLink());

        System.out.println("    Certified : " + (this.manager.isSuccess() ? "YES" : "NO"));

        System.out.println("========== " + this.manager.getName() + "of Class " + this.manager.getClazz().getSimpleName() + " ==========");
        System.out.println();
    }
}
