package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class CertificationManager {

    private List<Class<? extends CertificationListener>> clazz;

    private List<ResultManager> results;

    private HashMap<String, TaskProcessors> taskProcessorsList;

    private long processingTime;

    public CertificationManager() {
        this.taskProcessorsList = new HashMap<>();
        this.load();
        this.processingTime = 15;
        this.results = new ArrayList<>();
        this.taskProcessorsList.put("sender", new TaskProcessors());
    }

    private void load() {
        Reflections reflection = new Reflections("com.hoteloptimiser.jordan.certification.TestCertification");

        this.clazz = new ArrayList<>(reflection.getSubTypesOf(CertificationListener.class));
    }

    @SneakyThrows
    private void addResult(Method m, Class<? extends CertificationListener> clazz) {
        ResultManager result = new ResultManager(m, clazz);

        this.processingTime += m.getAnnotation(Certification.class).sleep() * 1000;

        this.taskProcessorsList.get("sender").add(new TaskSendProcessing(result));

        this.results.add(result);
    }

    public CertificationManager processCertifications() {
        this.clazz.forEach(l -> {
            if (l.isAnnotationPresent(CertificationClassActivation.class) &&
                    l.getAnnotation(CertificationClassActivation.class).classActivated())
                    Arrays.stream(l.getDeclaredMethods())
                            .filter(m ->
                                    m.isAnnotationPresent(Certification.class)
                                            && !m.getAnnotation(Certification.class).ignored()
                                            && m.getParameterTypes().length == 1
                            ).sorted(Comparator.comparingInt(a -> a.getAnnotation(Certification.class).id()))
                                .forEach(e -> addResult(e, l));
        });
        return this;
    }

    private String timeWait() {
        Date date = new Date(System.currentTimeMillis() + this.processingTime);

        return "Work approximatively finished at " + date.toString();
    }

    @SneakyThrows
    public void printResults() {

        System.out.println(timeWait());

        // Get Result
        {
            this.taskProcessorsList.put("result", new TaskProcessors());

            this.results.forEach(e -> this.taskProcessorsList.get("result").add(new TaskSendProcessing(e)));

            this.taskProcessorsList.get("result").invokeAllQueued();

            this.taskProcessorsList.get("result").shutDown();
        }

        System.out.println("Finished at " + new Date(System.currentTimeMillis()).toString());

        int ok = (int) this.results.stream().filter(ResultManager::isSuccess).count();
        int fail = (int) this.results.stream().filter(e -> !e.isSuccess()).count();

        System.out.println("Total of : " + this.results.size());
        System.out.println("Succeed : " + ok);
        System.out.println("Failed : " + fail);
        System.out.println("Result : " + (ok * 100) / this.results.size() + "%");

    }

}
