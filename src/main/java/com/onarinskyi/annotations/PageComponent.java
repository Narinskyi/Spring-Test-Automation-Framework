package com.onarinskyi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//Autowire
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PageComponent {}
