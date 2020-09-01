package rest_components_generator;

import static util.StringUtils.capitalizeString;

public class ModelData {
    private String modelName;
    private String modelNamePlural;
    private String modelNamePluralCapitalized;
    private String modelNameCapitalized;

    public ModelData(String modelName) {
        this.modelName = modelName;
        setModelData();
    }

    private void setModelData() {
        modelNamePlural = modelName+"s";
        modelNameCapitalized = capitalizeString(modelName);
        modelNamePluralCapitalized = capitalizeString(modelNamePlural);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNamePlural() {
        return modelNamePlural;
    }

    public void setModelNamePlural(String modelNamePlural) {
        this.modelNamePlural = modelNamePlural;
    }

    public String getModelNamePluralCapitalized() {
        return modelNamePluralCapitalized;
    }

    public void setModelNamePluralCapitalized(String modelNamePluralCapitalized) {
        this.modelNamePluralCapitalized = modelNamePluralCapitalized;
    }

    public String getModelNameCapitalized() {
        return modelNameCapitalized;
    }

    public void setModelNameCapitalized(String modelNameCapitalized) {
        this.modelNameCapitalized = modelNameCapitalized;
    }
}