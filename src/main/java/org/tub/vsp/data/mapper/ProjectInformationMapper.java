package org.tub.vsp.data.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.tub.vsp.data.container.ProjectInformationDataContainer;
import org.tub.vsp.data.type.Severity;

import java.util.Optional;

public class ProjectInformationMapper implements DocumentMapper<ProjectInformationDataContainer> {
    private static final Logger logger = LogManager.getLogger(ProjectInformationMapper.class);

    @Override
    public ProjectInformationDataContainer mapDocument(Document document) {
        ProjectInformationDataContainer projectInformation = new ProjectInformationDataContainer();

        String projectNumber = extractInformation(document, 0, "Projektnummer");
        String street = extractInformation(document, 0, "Straße");
        String severity = extractInformation(document, 1, "Dringlichkeitseinstufung");

        return projectInformation.setProjectNumber(projectNumber)
                                 .setStreet(street)
                                 .setSeverity(Severity.getFromString(severity));
    }

    private static String extractInformation(Document document, int tableIndex, String key) {
        Optional<String> info = document.select("table.table_grunddaten")
                                        .get(tableIndex)
                                        .select("tr")
                                        .stream()
                                        .filter(r -> r.text()
                                                      .contains(key))
                                        .findFirst()
                                        .map(r -> r.child(1)
                                                   .text());
        if (info.isEmpty()) {
            logger.warn("Could not find information for key {} in table {}", key, tableIndex);
        }
        return info.orElse(null);
    }
}
