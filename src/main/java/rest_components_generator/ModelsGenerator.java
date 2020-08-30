package rest_components_generator;

import project_generator.ProjectGenerator;

public class ModelsGenerator {

    private ProjectGenerator projectGenerator;
    private String modelsRootDirectory;

    public ModelsGenerator(ProjectGenerator projectGenerator) {
        this.projectGenerator = projectGenerator;
    }

    public void generate(String modelsRootDirectory) {
        this.modelsRootDirectory = modelsRootDirectory;
    }
}
