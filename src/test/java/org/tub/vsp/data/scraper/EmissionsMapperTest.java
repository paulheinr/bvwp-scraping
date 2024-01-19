package org.tub.vsp.data.scraper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tub.vsp.data.LocalFileAccessor;
import org.tub.vsp.data.container.EmissionsDataContainer;
import org.tub.vsp.data.type.EmissionType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class EmissionsMapperTest {
    @Test
    void testEmissionsMapping() throws IOException {
        EmissionsDataContainer emissionsDataContainer =
                new EmissionsMapper().mapDocument(LocalFileAccessor.getLocalDocument("a20.html"));

        Map<EmissionType, Double> expected = new HashMap<>();
        expected.put(EmissionType.NOX, 66.85);
        expected.put(EmissionType.CO, 1305.85);
        expected.put(EmissionType.CO2, 48689.94);
        expected.put(EmissionType.HC, 11.08);
        expected.put(EmissionType.PM, 2.86);
        expected.put(EmissionType.SO2, 0.63);

        Assertions.assertEquals(expected, emissionsDataContainer.emissions());
    }
}