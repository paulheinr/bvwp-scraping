package org.tub.vsp.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StreetCsvWriter {
    public static void writeCsv() throws IOException {

        //TODO
        BufferedWriter writer = new BufferedWriter(new FileWriter("output/street_data.csv"));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create()
                                                                        .setHeader()
                                                                        .build());

        csvPrinter.printRecord();

        csvPrinter.close();

    }
}
