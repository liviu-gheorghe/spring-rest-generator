package rest_components_generator;


@Generator
public class RestControllersGenerator extends ComponentGenerator {


    public RestControllersGenerator(Models models) {
        super(models);
    }

    @Override
    String getComponentPackageName() {
        return "controllers";
    }

    @Override
    String getComponentTemplateFileLocation() {
        return "/templates/controllers/Controller";
    }

    @Override
    String getComponentFileSuffix() {
        return "Controller";
    }
}