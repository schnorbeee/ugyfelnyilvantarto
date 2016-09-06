package com.codingmentorteam3.annotations;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author istvan.mosonyi
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Validate {
    
}
