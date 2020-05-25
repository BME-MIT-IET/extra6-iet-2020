package com.complexible.common.csv.valuegenerator.templatevaluegenerator;

import com.complexible.common.csv.valuegenerator.TemplateValueGenerator;
import com.complexible.common.csv.valueprovider.ValueProvider;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;

import static com.complexible.common.csv.utilities.OpenRdfValueFactory.FACTORY;

public class TemplateLiteralGenerator extends TemplateValueGenerator<Literal> {
		private final URI datatype;
		private final String lang;

		public TemplateLiteralGenerator(Literal literal, ValueProvider[] providers) {
			super(literal.getLabel(), providers);

			this.datatype = literal.getDatatype();
			this.lang = literal.getLanguage();
		}

		public Literal generate(int rowIndex, String[] row) {
			String value = applyTemplate(rowIndex, row);
			if (datatype == null)
				return lang == null ? FACTORY.createLiteral(value) : FACTORY.createLiteral(value, lang);
			else return FACTORY.createLiteral(value, datatype);
		}
	}