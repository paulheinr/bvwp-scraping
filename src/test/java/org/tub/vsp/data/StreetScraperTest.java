package org.tub.vsp.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tub.vsp.StreetScraper;
import org.tub.vsp.data.container.StreetBaseDataContainer;

import java.io.IOException;
import java.util.Optional;

class StreetScraperTest {
    @Test
    void testStreetScraper() throws IOException {
        StreetScraper streetScraper = new StreetScraper();
        Optional<StreetBaseDataContainer> streetBaseData =
                streetScraper.getStreetBaseData(LocalFileAccessor.getLocalDocument("a20.html"));

        Assertions.assertTrue(streetBaseData.isPresent());
        StreetBaseDataContainer streetBaseDataContainer = streetBaseData.get();

        Assertions.assertNotNull(streetBaseDataContainer.getProjectInformation());
        Assertions.assertNotNull(streetBaseDataContainer.getPhysicalEffect());
        Assertions.assertNotNull(streetBaseDataContainer.getCostBenefitAnalysis());
    }
}