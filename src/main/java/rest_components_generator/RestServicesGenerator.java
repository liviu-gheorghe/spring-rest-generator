package rest_components_generator;

import project_generator.ProjectGenerator;

public class RestServicesGenerator {

    private ProjectGenerator projectGenerator;
    private String servicesRootDirectory;

    public RestServicesGenerator(ProjectGenerator projectGenerator) {
        this.projectGenerator = projectGenerator;
    }

    public void generate(String servicesRootDirectory) {
        this.servicesRootDirectory = servicesRootDirectory;
    }

}
