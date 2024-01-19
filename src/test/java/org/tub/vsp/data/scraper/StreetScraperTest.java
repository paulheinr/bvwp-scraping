package org.tub.vsp.data.scraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tub.vsp.data.LocalFileAccessor;
import org.tub.vsp.data.container.StreetBaseDataContainer;

import java.io.FileReader;
import java.io.FileWriter;
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
        Assertions.assertNotNull(streetBaseDataContainer.getEmissions());
        Assertions.assertNotNull(streetBaseDataContainer.getPhysicalEffect());
        Assertions.assertNotNull(streetBaseDataContainer.getCostBenefitAnalysis());
    }

    @Test
    void testSerializeJson() throws IOException {
        StreetScraper streetScraper = new StreetScraper();
        StreetBaseDataContainer streetBaseData =
                streetScraper.getStreetBaseData(LocalFileAccessor.getLocalDocument("a20.html"))
                             .get();


        Gson gson = new GsonBuilder().serializeNulls()
                                     .setPrettyPrinting()
                                     .create();

        System.out.println(gson.toJson(streetBaseData));

        String filePath = "src/test/resources/testData/a20.json";
        FileWriter writer = new FileWriter(filePath);
        gson.toJson(streetBaseData, writer);
        writer.flush();
        writer.close();

        StreetBaseDataContainer deserializedContainer = gson.fromJson(new FileReader(filePath),
                StreetBaseDataContainer.class);

        Assertions.assertEquals(streetBaseData, deserializedContainer);
    }
}