/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generatortxt.generator.types;

/**
 *
 * @author boskyn9
 */
public class DelimitationType implements TypeGenerator {

    private String delimiter = ";";

    public DelimitationType() {
    }

    public DelimitationType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public boolean hasDelimitator() {
        return true;
    }
}
