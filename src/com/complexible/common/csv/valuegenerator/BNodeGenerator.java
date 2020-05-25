package com.complexible.common.csv.valuegenerator;

import org.openrdf.model.BNode;

import static com.complexible.common.csv.utilities.OpenRdfValueFactory.FACTORY;

public class BNodeGenerator implements ValueGenerator<BNode> {
    private BNode value = null;
    private int generatedRow = -1;

    public BNode generate(int rowIndex, String[] row) {
        if (value == null || generatedRow != rowIndex) {
            value = FACTORY.createBNode();
            generatedRow = rowIndex;
        }
        return value;
    }
}