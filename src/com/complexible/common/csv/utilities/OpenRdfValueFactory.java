package com.complexible.common.csv.utilities;

import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class OpenRdfValueFactory {
    public static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();
}
