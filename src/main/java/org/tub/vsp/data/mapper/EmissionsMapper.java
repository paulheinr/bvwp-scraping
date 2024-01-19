package org.tub.vsp.data.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.tub.vsp.data.container.EmissionsDataContainer;
import org.tub.vsp.data.type.Emission;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmissionsMapper implements DocumentMapper<EmissionsDataContainer> {
    private static final Logger logger = LogManager.getLogger(EmissionsMapper.class);

    private static final Map<Emission, Integer> TABLE_ROW_INDEX_BY_EMISSION_TYPE = Map.of(
            Emission.NOX, 1,
            Emission.CO, 2,
            Emission.CO2, 3,
            Emission.HC, 4,
            Emission.PM, 5,
            Emission.SO2, 6
    );

    @Override
    public EmissionsDataContainer mapDocument(Document document) {
        EmissionsDataContainer emissions = EmissionsDataContainer.empty();

        Optional<Element> table = getEmissionsTable(document);
        if (table.isEmpty()) {
            return emissions;
        }

        Map<Emission, Double> collect =
                TABLE_ROW_INDEX_BY_EMISSION_TYPE.entrySet()
                                                .stream()
                                                .map(e -> {
                                                    Double v;
                                                    try {
                                                        v = mapEmissionFromRow(table.get(), e.getValue());
                                                    } catch (ParseException ex) {
                                                        logger.warn("Could not parse emission value for {}",
                                                                e.getKey());
                                                        return Optional.<Map.Entry<Emission, Double>>empty();
                                                    }
                                                    return Optional.of(Map.entry(e.getKey(), v));
                                                })
                                                .filter(Optional::isPresent)
                                                .map(Optional::get)
                                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new EmissionsDataContainer(collect);
    }

    private Double mapEmissionFromRow(Element table, int rowIndex) throws ParseException {
        return NumberFormat.getInstance(Locale.GERMANY)
                           .parse(table.select("tr")
                                       .get(rowIndex)
                                       .child(3)
                                       .text())
                           .doubleValue();
    }

    private Optional<Element> getEmissionsTable(Document document) {
        List<Element> list = document.select("table.table_wirkung_strasse")
                                     .stream()
                                     .filter(this::isEmissionTable)
                                     .toList();

        if (list.isEmpty()) {
            logger.warn("Could not find any emission table.");
            return Optional.empty();
        } else if (list.size() > 1) {
            logger.warn("Found more than one emission table.");
            return Optional.empty();
        }

        return Optional.of(list.getFirst());
    }

    private boolean isEmissionTable(Element element) {
        return element.select("tr")
                      .stream()
                      .anyMatch(r -> r.text()
                                      .contains("Veränderung der Abgasemissionen"));
    }
}