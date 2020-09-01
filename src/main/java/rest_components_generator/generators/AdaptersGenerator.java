package rest_components_generator.generators;

import rest_components_generator.Models;

@Generator
public class AdaptersGenerator extends ComponentGenerator {

    public AdaptersGenerator(Models models) {
        super(models);
    }

    @Override
    String getComponentPackageName() {
        return "adapters";
    }

    @Override
    String getComponentTemplateFileLocation() {
        return "/templates/adapters/Adapter";
    }

    @Override
    String getComponentFileSuffix() {
        return "Adapter";
    }
}
