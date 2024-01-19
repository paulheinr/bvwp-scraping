package org.tub.vsp.data.type;

import java.util.Map;

public enum Emission {
    CO2, CO, NOX, HC, PM, SO2;

    public static final Map<Emission, String> STRING_IDENTIFIER_BY_EMISSION = Map.of(
            CO2, "Kohlendioxid-Emissionen",
            CO, "Kohlenmonoxid-Emissionen",
            NOX, "Stickoxid-Emissionen",
            HC, "Kohlenwasserstoff-Emissionen",
            PM, "Feinstaub-Emissionen",
            SO2, "Schwefeldioxid-Emissionen"
    );
}
