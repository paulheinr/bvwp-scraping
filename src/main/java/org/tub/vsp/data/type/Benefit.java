package org.tub.vsp.data.type;

import java.util.Objects;

public record Benefit(Double annualBenefits, Double overallBenefits) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Benefit benefit = (Benefit) o;

        if (!Objects.equals(annualBenefits, benefit.annualBenefits)) {
            return false;
        }
        return Objects.equals(overallBenefits, benefit.overallBenefits);
    }

    @Override
    public int hashCode() {
        int result = annualBenefits != null ? annualBenefits.hashCode() : 0;
        result = 31 * result + (overallBenefits != null ? overallBenefits.hashCode() : 0);
        return result;
    }
}
