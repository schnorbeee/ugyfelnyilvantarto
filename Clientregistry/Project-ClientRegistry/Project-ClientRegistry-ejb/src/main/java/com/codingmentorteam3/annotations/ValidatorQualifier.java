package com.codingmentorteam3.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author Regulus
 */
@Qualifier
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ValidatorQualifier {   
}
