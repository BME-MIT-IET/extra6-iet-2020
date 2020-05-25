package com.complexible.common.csv.extensions;

import com.google.common.base.Preconditions;

public class Casting {

    public static char toChar(String value) {
        Preconditions.checkArgument(value.length() == 1, "Expecting a single character but got %s", value);
        return value.charAt(0);
    }
}
