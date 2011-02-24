/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.exception;

/**
 *
 * @author boskyn9
 */
public class MaxLengthException extends Exception {

    public MaxLengthException(String message, String cause) {
        super(message, new Throwable(cause));
    }
}
