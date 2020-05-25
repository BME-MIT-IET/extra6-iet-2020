// Copyright (c) 2014, Clark & Parsia, LLC. <http://www.clarkparsia.com>

package com.complexible.common.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.complexible.common.csv.extensions.IO;
import com.google.common.base.Preconditions;

import com.google.common.io.Files;
import io.airlift.command.Arguments;
import io.airlift.command.Cli;
import io.airlift.command.Command;
import io.airlift.command.Option;
import io.airlift.command.Help;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static com.complexible.common.csv.extensions.Casting.toChar;
import static com.complexible.common.csv.extensions.IO.INPUT_CHARSET;
import static com.complexible.common.csv.extensions.Logger.logger;

/**
 * Converts a CSV file to RDF based on a given template
 *
 * @author Evren Sirin
 */
@Command(name = "convert", description = "Runs the conversion.")
public class CSV2RDF implements Runnable {

	@Option(name = "--no-header", arity = 0, description = "If csv file does not contain a header row")
	public static boolean noHeader = false;

	@Option(name = { "-s", "--separator" }, description = "Seperator character used in the csv file or ',' by default.")
	String separator = String.valueOf(CSVReader.DEFAULT_SEPARATOR);

	@Option(name = { "-q", "--quote" }, description = "Quote character used in the csv file or '\"' by default.")
	String quote = String.valueOf(CSVReader.DEFAULT_QUOTE_CHARACTER);

	@Option(name = { "-e", "--escape" }, description = "Escape character used in the csv file or '\\' by default.")
	String escape = String.valueOf(CSVReader.DEFAULT_ESCAPE_CHARACTER);

	@Arguments(required = true, description = "File arguments. The extension of template file and output file determines the RDF format that will be used for them (.ttl = Turtle, .nt = N-Triples, .rdf = RDF/XML)", title = {
			"templateFile", "csvFile", "outputFile" })
	public List<String> files;
	public static int inputRows = 0;
	public static int outputTriples = 0;



	public void run() {
		Preconditions.checkArgument(files.size() >= 3, "Missing arguments");
		Preconditions.checkArgument(files.size() <= 3, "Too many arguments");

		File templateFile = new File(files.get(0));
		File inputFile = new File(files.get(1));
		File outputFile =  new File(files.get(2));
		logger.log(Level.INFO, "CSV to RDF conversion started...");
		logger.log(Level.INFO, "Template: {0}", templateFile);
		logger.log(Level.INFO, "Input   : {0}", inputFile);
		logger.log(Level.INFO,"Output  : {0}", outputFile);

		try (Reader in = Files.newReader(inputFile, INPUT_CHARSET);
			 CSVReader reader = new CSVReader(in, toChar(separator), toChar(quote), toChar(escape));
			 Writer out = Files.newWriter(outputFile, IO.OUTPUT_CHARSET))
		{


			String[] row = reader.readNext();

			Preconditions.checkNotNull(row, "Input file is empty!");

			RDFWriter writer = Rio.createWriter(RDFFormat.forFileName(outputFile.getName(), RDFFormat.TURTLE), out);

			Template template = new Template(Arrays.asList(row), templateFile, writer);

			if (noHeader) {
				template.generate(row, writer);
			}

			while ((row = reader.readNext()) != null) {
				template.generate(row, writer);
			}

			writer.endRDF();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		logger.log(Level.INFO, "Converted {0} rows to {1} triples\n", new Object[] {inputRows, outputTriples});
	}

	public static void main(String[] args) {
		try {
			Cli.<Runnable> builder("csv2rdf").withDescription("Converts a CSV file to RDF based on a given template")
					.withDefaultCommand(CSV2RDF.class).withCommand(CSV2RDF.class).withCommand(Help.class)
					.build().parse(args).run();
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}