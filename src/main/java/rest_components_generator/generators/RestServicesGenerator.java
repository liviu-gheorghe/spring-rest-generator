package rest_components_generator.generators;

import rest_components_generator.Models;

@Generator
public class RestServicesGenerator extends ComponentGenerator {

    public RestServicesGenerator(Models models) {
        super(models);
    }

    @Override
    String getComponentPackageName() {
        return "services";
    }

    @Override
    String getComponentTemplateFileLocation() {
        return "/templates/services/Service";
    }

    @Override
    String getComponentFileSuffix() {
        return "Service";
    }
}