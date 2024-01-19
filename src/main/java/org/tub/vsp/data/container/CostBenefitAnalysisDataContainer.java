package org.tub.vsp.data.container;

import org.tub.vsp.data.type.Benefit;
import org.tub.vsp.data.type.Cost;
import org.tub.vsp.data.type.EmissionType;

import java.util.Map;
import java.util.Objects;

public class CostBenefitAnalysisDataContainer {
    private Benefit nb;
    private Benefit nw;
    private Benefit ns;
    private Benefit nrz;
    private Benefit ntz;
    private Benefit ni;
    private Benefit nl;
    private Benefit ng;
    private Map<EmissionType, Benefit> na;
    private Benefit nt;
    private Benefit nz;
    private Cost cost;

    public Benefit overallBenefits() {
        //TODO
        return null;
    }

    public double costBenefitRation() {
        //TODO
        return 0.;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CostBenefitAnalysisDataContainer that = (CostBenefitAnalysisDataContainer) o;

        if (!Objects.equals(nb, that.nb)) {
            return false;
        }
        if (!Objects.equals(nw, that.nw)) {
            return false;
        }
        if (!Objects.equals(ns, that.ns)) {
            return false;
        }
        if (!Objects.equals(nrz, that.nrz)) {
            return false;
        }
        if (!Objects.equals(ntz, that.ntz)) {
            return false;
        }
        if (!Objects.equals(ni, that.ni)) {
            return false;
        }
        if (!Objects.equals(nl, that.nl)) {
            return false;
        }
        if (!Objects.equals(ng, that.ng)) {
            return false;
        }
        if (!Objects.equals(na, that.na)) {
            return false;
        }
        if (!Objects.equals(nt, that.nt)) {
            return false;
        }
        if (!Objects.equals(nz, that.nz)) {
            return false;
        }
        return Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        int result = nb != null ? nb.hashCode() : 0;
        result = 31 * result + (nw != null ? nw.hashCode() : 0);
        result = 31 * result + (ns != null ? ns.hashCode() : 0);
        result = 31 * result + (nrz != null ? nrz.hashCode() : 0);
        result = 31 * result + (ntz != null ? ntz.hashCode() : 0);
        result = 31 * result + (ni != null ? ni.hashCode() : 0);
        result = 31 * result + (nl != null ? nl.hashCode() : 0);
        result = 31 * result + (ng != null ? ng.hashCode() : 0);
        result = 31 * result + (na != null ? na.hashCode() : 0);
        result = 31 * result + (nt != null ? nt.hashCode() : 0);
        result = 31 * result + (nz != null ? nz.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
