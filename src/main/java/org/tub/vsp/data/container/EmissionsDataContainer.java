package org.tub.vsp.data.container;

import org.tub.vsp.data.type.EmissionType;

import java.util.Map;

public record EmissionsDataContainer(Map<EmissionType, Double> emissions) {

    public static EmissionsDataContainer empty() {
        return new EmissionsDataContainer(Map.of());
    }
}
