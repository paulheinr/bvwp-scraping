package org.tub.vsp.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tub.vsp.data.container.analysis.StreetAnalysisDataContainer;
import org.tub.vsp.data.container.base.CostBenefitAnalysisDataContainer;
import org.tub.vsp.data.container.base.StreetBaseDataContainer;
import org.tub.vsp.data.type.Benefit;
import org.tub.vsp.data.type.Cost;
import org.tub.vsp.data.type.Emission;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class StreetCsvWriter {
    private static final Logger logger = LogManager.getLogger(StreetCsvWriter.class);
    private final String outputPath;

    private static final Map<Emission, String> EMISSION_COLUMNS = initMap();

    private static Map<Emission, String> initMap() {
        Map<Emission, String> map = new LinkedHashMap<>();
        map.put(Emission.CO2, "co2");
        map.put(Emission.CO, "co");
        map.put(Emission.NOX, "nox");
        map.put(Emission.PM, "pm");
        map.put(Emission.HC, "hc");
        map.put(Emission.SO2, "so2");
        return map;
    }

    public StreetCsvWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    public void writeCsv(List<StreetAnalysisDataContainer> analysisDataContainers) {
        logger.info("Writing csv.");
        List<String> headers = getHeaders(analysisDataContainers);

        //make sure that the directory exists
        Path path = Paths.get(outputPath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            logger.error("Could not create directory for file {}", path);
            throw new RuntimeException(e);
        }

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputPath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.Builder.create()
                                                                                .setNullString("")
                                                                                .setHeader(headers.toArray(new String[0]))
                                                                                .setDelimiter(';')
                                                                                .build())) {
            for (StreetAnalysisDataContainer analysisDataContainer : analysisDataContainers) {
                logger.info("Writing csv record for {}", analysisDataContainer.getStreetBaseDataContainer()
                                                                              .getUrl());
                csvPrinter.printRecord(getCsvRecord(analysisDataContainer));
            }
            csvPrinter.flush();
            logger.info("Finished writing csv.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Object> getCsvRecord(StreetAnalysisDataContainer analysisDataContainer) {
        StreetBaseDataContainer baseDataContainer = analysisDataContainer.getStreetBaseDataContainer();

        List<Object> record = new ArrayList<>();
        //general info
        record.add(baseDataContainer.getProjectInformation()
                                    .getProjectNumber());
        record.add(baseDataContainer.getUrl());

        //co2 equivalents
        record.add(baseDataContainer.getPhysicalEffect()
                                    .getEmission(Emission.CO2_OVERALL_EQUIVALENTS));
        record.add(baseDataContainer.getCostBenefitAnalysis()
                                    .getCo2EquivalentBenefit()
                                    .annual());
        record.add(baseDataContainer.getCostBenefitAnalysis()
                                    .getCo2EquivalentBenefit()
                                    .overall());

        //emissions
        for (Emission emission : EMISSION_COLUMNS.keySet()) {
            addEmissionsAnnualOverallBenefit(baseDataContainer, record, emission);
        }

        //overall benefit and cost
        record.add(Optional.ofNullable(baseDataContainer.getCostBenefitAnalysis())
                           .map(CostBenefitAnalysisDataContainer::getOverallBenefit)
                           .map(Benefit::overall)
                           .orElse(null));
        record.add(Optional.ofNullable(baseDataContainer.getCostBenefitAnalysis())
                           .map(CostBenefitAnalysisDataContainer::getCost)
                           .map(Cost::overallCosts)
                           .orElse(null));

        record.addAll(analysisDataContainer.getNkvByChange()
                                           .values());

        return record;
    }

    private static List<String> getHeaders(List<StreetAnalysisDataContainer> analysisDataContainers) {
        //assert that all entries of new nkv have the same keys
        assert analysisDataContainers.stream()
                                     .allMatch(a -> {
                                         Set<String> thisKeys = a.getNkvByChange()
                                                                 .keySet();
                                         Set<String> firstKeys = analysisDataContainers.getFirst()
                                                                                       .getNkvByChange()
                                                                                       .keySet();
                                         return thisKeys.containsAll(firstKeys) && thisKeys.size() == firstKeys.size();
                                     }) : "Not all nkv have the same keys";

        List<String> headers = new ArrayList<>();
        headers.add("project name");
        headers.add("link");

        headers.add("co2-equivalents-emissions");
        headers.add("co2-equivalents-annual");
        headers.add("co2-equivalents-overall");

        for (String colName : EMISSION_COLUMNS.values()) {
            headers.add(colName + "-emissions");
            headers.add(colName + "-annual");
            headers.add(colName + "-overall");
        }

        headers.add("overall-benefit");
        headers.add("overall-cost");

        for (String s : analysisDataContainers.getFirst()
                                              .getNkvByChange()
                                              .keySet()) {
            headers.add("nkv_" + s);
        }
        return headers;
    }

    private static void addEmissionsAnnualOverallBenefit(StreetBaseDataContainer baseDataContainer,
                                                         List<Object> record, Emission emission) {
        record.add(baseDataContainer.getPhysicalEffect()
                                    .getEmission(emission));
        record.add(Optional.ofNullable(baseDataContainer.getCostBenefitAnalysis())
                           .map(CostBenefitAnalysisDataContainer::getNa)
                           .map(m -> m.get(emission))
                           .map(Benefit::annual)
                           .orElse(null));
        record.add(Optional.ofNullable(baseDataContainer.getCostBenefitAnalysis())
                           .map(CostBenefitAnalysisDataContainer::getNa)
                           .map(m -> m.get(emission))
                           .map(Benefit::overall)
                           .orElse(null));
    }
}
