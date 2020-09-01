package rest_components_generator;

@Generator
public class ModelsGenerator extends ComponentGenerator {

    public ModelsGenerator(Models models) {
        super(models);
    }

    @Override
    String getComponentPackageName() {
        return "models";
    }

    @Override
    String getComponentTemplateFileLocation() {
        return "/templates/models/Model";
    }

    @Override
    String getComponentFileSuffix() {
        return "Model";
    }
}
