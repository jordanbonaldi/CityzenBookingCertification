package com.hoteloptimiser.jordan.certification.ProcessManagers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class CertificationManager {

    private List<Class<? extends CertificationListener>> clazz;

    private List<ResultManager> results;

    public CertificationManager() {
        this.load();
        this.results = new ArrayList<>();
    }

    private void load() {
        Reflections reflection = new Reflections("com.hoteloptimiser.jordan.certification.TestCertification");

        this.clazz = new ArrayList<>(reflection.getSubTypesOf(CertificationListener.class));
    }

    @SneakyThrows
    private void addResult(Method m, Class<? extends CertificationListener> clazz) {
        ResultManager result = new ResultManager(m, clazz);

        result.sendXml();

        this.results.add(result);
    }

    public CertificationManager processCertifications() {
        this.clazz.forEach(l -> Arrays.stream(l.getDeclaredMethods())
                        .filter(m ->
                                m.isAnnotationPresent(Certification.class)
                                    && !m.getAnnotation(Certification.class).ignored()
                                    && m.getParameterTypes().length == 1
                        ).forEach(e -> addResult(e, l)));
        return this;
    }

    public void printResults() {
        this.results.forEach(e -> {

            new Thread(new ProcessingByThread(e)).start();

        });
    }

}
