package rest_components_generator.generators;

import rest_components_generator.Models;

@Generator
public class RepositoriesGenerator extends ComponentGenerator {

    public RepositoriesGenerator(Models models) {
        super(models);
    }

    @Override
    String getComponentPackageName() {
        return "repositories";
    }

    @Override
    String getComponentTemplateFileLocation() {
        return "/templates/repositories/Repository";
    }

    @Override
    String getComponentFileSuffix() {
        return "Repository";
    }
}
