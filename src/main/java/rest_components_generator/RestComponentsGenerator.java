package rest_components_generator;

import project_generator.ProjectGenerator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RestComponentsGenerator {

    private final ProjectGenerator projectGenerator;

    private final RestControllersGenerator restControllersGenerator;
    private final RestServicesGenerator restServicesGenerator;
    private final RepositoriesGenerator repositoriesGenerator;
    private final ModelsGenerator modelsGenerator;

    public RestComponentsGenerator(ProjectGenerator projectGenerator) {
        this.projectGenerator = projectGenerator;
        this.restControllersGenerator = new RestControllersGenerator(projectGenerator);
        this.restServicesGenerator = new RestServicesGenerator(projectGenerator);
        this.modelsGenerator = new ModelsGenerator(projectGenerator);
        this.repositoriesGenerator = new RepositoriesGenerator(projectGenerator);
    }

    public void generate() {
        String restComponentsRootDirectory = getRestComponentsRootDirectory();
        restControllersGenerator.generate(restComponentsRootDirectory);
        restServicesGenerator.generate(restComponentsRootDirectory);
        modelsGenerator.generate(restComponentsRootDirectory);
        repositoriesGenerator.generate(restComponentsRootDirectory);
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