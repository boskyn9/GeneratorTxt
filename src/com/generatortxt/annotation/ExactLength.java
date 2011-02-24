/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author boskyn9
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExactLength {
    public int value();
    public char type();
}
