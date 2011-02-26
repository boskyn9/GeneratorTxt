/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generatortxt.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author boskyn9
 */
public enum NoNavegable {

    INTEGER {

        @Override
        public String toString() {
            return Integer.class.getName();
        }
    },
    FLOAT {

        @Override
        public String toString() {
            return Float.class.getName();
        }
    },
    STRING {

        @Override
        public String toString() {
            return String.class.getName();
        }
    },
    DOUBLE {

        @Override
        public String toString() {
            return Double.class.getName();
        }
    },
    BIGDECIMAL {

        @Override
        public String toString() {
            return BigDecimal.class.getName();
        }
    },
    NUMBER {

        @Override
        public String toString() {
            return Number.class.getName();
        }
    },
    BIGINTEGER {

        @Override
        public String toString() {
            return BigInteger.class.getName();
        }
    },

    CHARACTER{

        @Override
        public String toString() {
            return Character.class.getName();
        }
    },

    DATE{

        @Override
        public String toString() {
            return Date.class.getName();
        }
    },

    PACK_LANG{

        @Override
        public String toString() {
            return String.class.getPackage().getName();
        }
    },

    PACK_MATH{

        @Override
        public String toString() {
            return BigDecimal.class.getPackage().getName();
        }
    },

    PACK_UTIL{

        @Override
        public String toString() {
            return Date.class.getPackage().getName();
        }
    }
}
