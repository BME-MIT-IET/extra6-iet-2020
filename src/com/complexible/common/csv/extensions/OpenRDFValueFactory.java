package com.complexible.common.csv.extensions;

import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class OpenRDFValueFactory {
    public static final ValueFactory FACTORY = ValueFactoryImpl.getInstance();
}
