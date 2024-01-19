package org.tub.vsp.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tub.vsp.StreetScraper;
import org.tub.vsp.data.LocalFileAccessor;
import org.tub.vsp.data.container.StreetBaseDataContainer;

import java.io.IOException;

class JsonIoTest {
    @Test
    void testSerializeJson() throws IOException {
        StreetScraper streetScraper = new StreetScraper();
        StreetBaseDataContainer streetBaseData =
                streetScraper.getStreetBaseData(LocalFileAccessor.getLocalDocument("a20.html"))
                             .get();

        //write Json to file
        Gson gson = new GsonBuilder().serializeNulls()
                                     .setPrettyPrinting()
                                     .create();
        JsonIo jsonIo = new JsonIo(gson);
        jsonIo.writeJson(streetBaseData, "src/test/resources/testData/a20.json");

        //read Json from file
        StreetBaseDataContainer deserializedContainer = jsonIo.readJson("src/test/resources/testData/a20.json",
                StreetBaseDataContainer.class);
        Assertions.assertEquals(streetBaseData, deserializedContainer);
    }
}