package org.tub.vsp.data.container;

import org.tub.vsp.data.type.Benefit;
import org.tub.vsp.data.type.Cost;
import org.tub.vsp.data.type.EmissionType;

import java.util.Map;

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
}
