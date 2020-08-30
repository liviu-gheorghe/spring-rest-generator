package rest_components_generator;

import project_generator.ProjectGenerator;

public class RepositoriesGenerator {

    private ProjectGenerator projectGenerator;
    private String repositoriesRootDirectory;

    public RepositoriesGenerator(ProjectGenerator projectGenerator) {
        this.projectGenerator = projectGenerator;
    }

    public void generate(String repositoriesRootDirectory) {
        this.repositoriesRootDirectory = repositoriesRootDirectory;
    }
}
