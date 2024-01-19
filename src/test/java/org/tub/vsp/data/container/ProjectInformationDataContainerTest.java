package org.tub.vsp.data.container;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tub.vsp.data.scraper.ProjectInformationMapper;
import org.tub.vsp.data.type.Severity;

import java.io.File;
import java.io.IOException;

class ProjectInformationDataContainerTest {
    @Test
    void testMapping() throws IOException {
        ProjectInformationMapper mapper = new ProjectInformationMapper();
        ProjectInformationDataContainer mappingResult = mapper.mapDocument(getLocalDocument());

        Assertions.assertEquals("A20-G10-NI-SH", mappingResult.getProjectNumber());
        Assertions.assertEquals("A 20", mappingResult.getStreet());
        Assertions.assertEquals(Severity.VB, mappingResult.getSeverity());
    }

    private Document getLocalDocument() throws IOException {
        File input = new File("src/test/resources/testData/a20.html");
        return Jsoup.parse(input, "UTF-8");
    }

}