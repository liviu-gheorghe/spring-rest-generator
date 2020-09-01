package rest_components_generator;

import project_generator.ProjectGenerator;
import rest_components_generator.generators.*;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RestComponentsGenerator {

    private final ProjectGenerator projectGenerator;
    private final Models models;
    private final List<ComponentGenerator> generators = new ArrayList<>();

    public RestComponentsGenerator(ProjectGenerator projectGenerator) {

        this.projectGenerator = projectGenerator;

        List<ModelData> mdList = new ArrayList<>();
        projectGenerator.getModels().forEach(
                modelName -> mdList.add(new ModelData(modelName))
        );
        this.models = new Models(mdList,getRestComponentsRootDirectory(), projectGenerator.getPackageName());


        List<Class<ComponentGenerator>> foundGenerators = GeneratorsProvider.getGenerators();
        foundGenerators.forEach(
                c -> {
                    try {
                        Constructor<ComponentGenerator> constructor = c.getConstructor(Models.class);
                        ComponentGenerator componentGenerator = constructor.newInstance(models);
                        generators.add(componentGenerator);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void generate() {
        generators.forEach(ComponentGenerator::generate);
    }

    private String getRestComponentsRootDirectory() {

        ArrayList<String> pathDirs = new ArrayList<>();
        pathDirs.add(projectGenerator.getProjectRootDirectory());
        pathDirs.add(projectGenerator.getBaseDir());
        pathDirs.add("src");
        pathDirs.add("main");
        pathDirs.add("java");

        String[] packageDirs = projectGenerator.getPackageName().split("\\.");
        for(int i=0;i<packageDirs.length;i++) {
            System.out.println(packageDirs[i]);
            packageDirs[i] = packageDirs[i];
            pathDirs.add(packageDirs[i]);
        }
        String[] pathDirsArray = new String[pathDirs.size()];
        for(int i=0;i<pathDirs.size();i++) {
            pathDirsArray[i] = pathDirs.get(i);
        }
        Path controllersPath = Paths.get("",pathDirsArray);
        return controllersPath.toString();
    }
}