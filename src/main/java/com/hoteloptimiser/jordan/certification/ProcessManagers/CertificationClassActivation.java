package com.hoteloptimiser.jordan.certification.ProcessManagers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CertificationClassActivation {
    boolean classActivated() default true;
}
