package rest_components_generator;

@Generator
public class RepositoriesGenerator extends ComponentGenerator{

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
