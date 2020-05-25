package com.complexible.common.csv.valuegenerator.templatevaluegenerator;

import com.complexible.common.csv.valuegenerator.TemplateValueGenerator;
import com.complexible.common.csv.valueprovider.ValueProvider;
import org.openrdf.model.URI;

import static com.complexible.common.csv.utilities.OpenRdfValueFactory.FACTORY;

public class TemplateURIGenerator extends TemplateValueGenerator<URI> {

    public TemplateURIGenerator(String template, ValueProvider[] providers) {
        super(template, providers);
    }

    public URI generate(int rowIndex, String[] row) {
        return FACTORY.createURI(applyTemplate(rowIndex, row));
    }
}