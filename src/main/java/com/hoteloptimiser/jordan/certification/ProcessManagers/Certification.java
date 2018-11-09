package com.hoteloptimiser.jordan.certification.ProcessManagers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Certification {

    boolean ignored() default false;

    String xml() default "";

    String inventory() default "";

    int sleep() default 0;

    int id() default -1;
}
