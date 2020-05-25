package com.complexible.common.csv.valuegenerator;

import org.openrdf.model.Value;

public interface ValueGenerator<V extends Value> {
    V generate(int rowIndex, String[] row);
}