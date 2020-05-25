package com.complexible.common.csv;

import com.complexible.common.csv.valuegenerator.ValueGenerator;
import com.complexible.common.csv.extensions.OpenRDFValueFactory;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;


public class StatementGenerator {

    private final ValueGenerator<Resource> subject;
    private final ValueGenerator<URI> predicate;
    private final ValueGenerator<Value> object;

    public StatementGenerator(ValueGenerator<Resource> s, ValueGenerator<URI> p, ValueGenerator<Value> o) {
        this.subject = s;
        this.predicate = p;
        this.object = o;
    }

    public Statement generate(int rowIndex, String[] row) {
        Resource s = subject.generate(rowIndex, row);
        URI p = predicate.generate(rowIndex, row);
        Value o = object.generate(rowIndex, row);
        return OpenRDFValueFactory.FACTORY.createStatement(s, p, o);
    }
}