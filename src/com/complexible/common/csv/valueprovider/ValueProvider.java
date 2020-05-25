package com.complexible.common.csv.valueprovider;

import com.complexible.common.csv.extensions.IO;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.util.UUID;

public abstract class ValueProvider {

    public final String placeholder = UUID.randomUUID().toString();
    public boolean isHash;

    public String provide(int rowIndex, String[] row) {
        String value = provideValue(rowIndex, row);
        if (value != null && isHash) {
            HashCode hash = Hashing.sha1().hashString(value, IO.OUTPUT_CHARSET);
            value = BaseEncoding.base32Hex().omitPadding().lowerCase().encode(hash.asBytes());
        }
        return value;
    }

    protected abstract String provideValue(int rowIndex, String[] row);
}