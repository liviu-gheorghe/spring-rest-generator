package rest_components_generator;

import project_generator.ProjectGenerator;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RestControllersGenerator {

    private ProjectGenerator projectGenerator;
    private String controllersRootDirectory;
    private String controllersDirPathString;
    private String controllerTemplateText = null;

    public RestControllersGenerator(ProjectGenerator projectGenerator) {
        this.projectGenerator = projectGenerator;
    }


    private String capitalizeString(String s) {
        if(s == null) return null;
        if(s.length() == 1) return  s.toUpperCase();
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }

    public void generate(String controllersRootDirectory) {
        this.controllersRootDirectory = controllersRootDirectory;
        createControllersDirectoryIfNotExists();
        generateControllers();
    }

    private void generateControllers() {
        projectGenerator.getModels().forEach(this::generateControllerForModel);
    }

    private void generateControllerForModel(String model) {
        ModelData modelData = new ModelData(model);
        createControllerFile(modelData,projectGenerator.getPackageName());
    }


    private void createControllerFile(ModelData modelData,String packageName) {


        try {
            injectModelVariables(modelData, packageName);
        } catch(IOException e) {
            return;
        }


    }

    private String injectModelVariables(ModelData modelData, String packageName) throws IOException {
        String controllerTemplateText = "";
        controllerTemplateText = getControllerTemplate();
        controllerTemplateText = controllerTemplateText.replace("{packageName}", packageName);
        controllerTemplateText = controllerTemplateText.replace("{modelName}", modelData.getModelName());
        controllerTemplateText = controllerTemplateText.replace("{modelNamePlural}", modelData.getModelNamePlural());
        controllerTemplateText = controllerTemplateText.replace("{modelNameCapitalized}", modelData.getModelNameCapitalized());
        controllerTemplateText = controllerTemplateText.replace("{modelNamePluralCapitalized}", modelData.getModelNamePluralCapitalized());

        return controllerTemplateText;
    }

    private String getControllerTemplate() throws IOException {
        if(controllerTemplateText != null) return controllerTemplateText;
        String path = "";
        try {
            path = getClass().getResource("/templates/controllers/Controller").toURI().getPath();
        } catch(URISyntaxException ignored) {
        }
        File controllerTemplateFile = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(controllerTemplateFile));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        controllerTemplateText = stringBuilder.toString();
        return controllerTemplateText;
    }


    private void createControllersDirectoryIfNotExists() {
        Path controllersDirPath = Paths.get(controllersRootDirectory,"controllers");
        this.controllersDirPathString = controllersDirPath.toString();
        if(Files.exists(controllersDirPath)) return;
        File dir = new File(controllersRootDirectory,"controllers");
        dir.mkdir();
    }


    private class ModelData {
        private String modelName;
        private String modelNamePlural;
        private String modelNamePluralCapitalized;
        private String modelNameCapitalized;

        public ModelData(String modelName) {
            this.modelName = modelName;
            setModelData();
        }

        private void setModelData() {
            modelNamePlural = modelName+"s";
            modelNameCapitalized = capitalizeString(modelName);
            modelNamePluralCapitalized = capitalizeString(modelNamePlural);
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getModelNamePlural() {
            return modelNamePlural;
        }

        public void setModelNamePlural(String modelNamePlural) {
            this.modelNamePlural = modelNamePlural;
        }

        public String getModelNamePluralCapitalized() {
            return modelNamePluralCapitalized;
        }

        public void setModelNamePluralCapitalized(String modelNamePluralCapitalized) {
            this.modelNamePluralCapitalized = modelNamePluralCapitalized;
        }

        public String getModelNameCapitalized() {
            return modelNameCapitalized;
        }

        public void setModelNameCapitalized(String modelNameCapitalized) {
            this.modelNameCapitalized = modelNameCapitalized;
        }
    }


}