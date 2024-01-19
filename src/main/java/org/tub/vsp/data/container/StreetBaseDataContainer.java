package org.tub.vsp.data.container;

public class StreetBaseDataContainer {
    private String url;

    private ProjectInformationDataContainer projectInformation;
    private PhysicalEffectDataContainer physicalEffect;
    private EmissionsDataContainer emissions;
    private CostBenefitAnalysisDataContainer costBenefitAnalysis;

    public String getUrl() {
        return url;
    }

    public StreetBaseDataContainer setUrl(String url) {
        this.url = url;
        return this;
    }

    public ProjectInformationDataContainer getProjectInformation() {
        return projectInformation;
    }

    public StreetBaseDataContainer setProjectInformation(ProjectInformationDataContainer projectInformation) {
        this.projectInformation = projectInformation;
        return this;
    }

    public PhysicalEffectDataContainer getPhysicalEffect() {
        return physicalEffect;
    }

    public StreetBaseDataContainer setPhysicalEffect(PhysicalEffectDataContainer physicalEffect) {
        this.physicalEffect = physicalEffect;
        return this;
    }

    public EmissionsDataContainer getEmissions() {
        return emissions;
    }

    public StreetBaseDataContainer setEmissions(EmissionsDataContainer emissions) {
        this.emissions = emissions;
        return this;
    }

    public CostBenefitAnalysisDataContainer getCostBenefitAnalysis() {
        return costBenefitAnalysis;
    }

    public StreetBaseDataContainer setCostBenefitAnalysis(CostBenefitAnalysisDataContainer costBenefitAnalysis) {
        this.costBenefitAnalysis = costBenefitAnalysis;
        return this;
    }
}
