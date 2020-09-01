package rest_components_generator;

import util.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;


@Generator
public class ErrorsGenerator extends ComponentGenerator {

    public ErrorsGenerator(Models models) {
        super(models);
    }


    @Override
    public void generate() {
        try {
            FileUtils.setDefaultFileExtension(".java");
            FileUtils.copyDirectory(
                    getClass().getResource("/templates/errors").toURI().getPath(),
                    models.getComponentsRootDirectory()
            );
        } catch(IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    String getComponentPackageName() {
        return null;
    }

    @Override
    String getComponentTemplateFileLocation() {
        return null;
    }

    @Override
    String getComponentFileSuffix() {
        return null;
    }
}
