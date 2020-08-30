import project_generator.ProjectGenerator;
import rest_components_generator.RestComponentsGenerator;

import java.io.IOException;

public class Main {

    private static final String PROJECT_ROOT_DIRECTORY = "/home/liviu/Desktop/spr";

    public static void main(String[] args) throws IOException {

        ProjectGenerator.Builder generatorBuilder = new ProjectGenerator.Builder();
        ProjectGenerator generator = generatorBuilder
                .setBaseDir("Books_Api")
                .setArtifactId("BooksApi")
                .setName("BooksApi")
                .setRootDirectory(PROJECT_ROOT_DIRECTORY)
                .build();
        generator.addModel("book");
        generator.addModel("author");
        generator.generate();
        //Add REST components
        RestComponentsGenerator restComponentsGenerator = new RestComponentsGenerator(generator);
        restComponentsGenerator.generate();
    }
}