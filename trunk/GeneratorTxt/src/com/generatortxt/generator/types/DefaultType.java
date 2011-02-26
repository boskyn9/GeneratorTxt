/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.generator.types;

/**
 *
 * @author boskyn9
 */
public class DefaultType implements TypeGenerator {

    private String leftSide = "0";
    private String rightSide = " ";

    public DefaultType() {
    }

    public DefaultType(String leftSide, String rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }

    public boolean hasDelimitator() {
        return false;
    }

}
