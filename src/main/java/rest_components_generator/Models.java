package rest_components_generator;

import java.util.List;

public class Models {

    private List<ModelData> modelsData;
    private String componentsRootDirectory;
    private String projectPackageName;

    public Models(List<ModelData> modelsData, String componentsRootDirectory, String projectPackageName) {
        this.modelsData = modelsData;
        this.componentsRootDirectory = componentsRootDirectory;
        this.projectPackageName = projectPackageName;
    }

    public List<ModelData> getModelsData() {
        return modelsData;
    }

    public String getComponentsRootDirectory() {
        return componentsRootDirectory;
    }

    public void setComponentsRootDirectory(String componentsRootDirectory) {
        this.componentsRootDirectory = componentsRootDirectory;
    }

    public String getProjectPackageName() {
        return projectPackageName;
    }

    public void setProjectPackageName(String projectPackageName) {
        this.projectPackageName = projectPackageName;
    }

    public void setModelsData(List<ModelData> modelsData) {
        this.modelsData = modelsData;
    }
}
