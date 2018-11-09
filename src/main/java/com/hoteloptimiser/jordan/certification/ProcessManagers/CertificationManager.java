package com.hoteloptimiser.jordan.certification.ProcessManagers;

import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import lombok.Getter;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CertificationManager {

    @Getter
    private List<Class<? extends CertificationListener>> clazz;

    @Getter
    private List<ResultManager> results;

    public CertificationManager() {
        this.load();
        this.results = new ArrayList<>();
    }

    private void load() {
        Reflections reflection = new Reflections("com.hoteloptimiser.jordan.certification.TestCertification");

        this.clazz = new ArrayList<>(reflection.getSubTypesOf(CertificationListener.class));
    }

    private void addResult(Method m, Class<? extends CertificationListener> clazz) {
        ResultManager result = new ResultManager(m, clazz);

        try {
            result.sendXml();
        } catch (Exception ignored) { }

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
