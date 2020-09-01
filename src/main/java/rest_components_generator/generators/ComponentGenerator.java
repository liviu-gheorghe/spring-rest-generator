package rest_components_generator.generators;

import rest_components_generator.ModelData;
import rest_components_generator.Models;
import util.Pair;
import util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ComponentGenerator {

    protected Models models;
    private String componentTemplateText = null;

    public ComponentGenerator(Models models) {
        this.models = models;
    }

    abstract String getComponentPackageName();
    abstract String getComponentTemplateFileLocation();
    abstract String getComponentFileSuffix();


    public void generate() {
        createComponentsDirectoryIfNotExists();
        generateComponents();
    }

    private void createComponentsDirectoryIfNotExists() {
        Path controllersDirPath = Paths.get(models.getComponentsRootDirectory(),getComponentPackageName());
        if(Files.exists(controllersDirPath)) return;
        File dir = new File(models.getComponentsRootDirectory(),getComponentPackageName());
        dir.mkdir();
    }

    private void generateComponents() {
        models.getModelsData().forEach(this::generateComponentForModel);
    }

    private void generateComponentForModel(ModelData modelData) {
        createComponentFile(modelData);
    }

    private void createComponentFile(ModelData modelData) {

        String componentFileText = "";
        String componentFileName = modelData.getModelNamePluralCapitalized()+getComponentFileSuffix()+".java";
        Path componentFilePath = Paths.get(
                models.getComponentsRootDirectory(),
                getComponentPackageName(),
                componentFileName
        );
        if(Files.exists(componentFilePath)) return;
        try {
            componentFileText = injectComponentVariables(modelData, models.getProjectPackageName());
        } catch(IOException e) {
            return;
        }
        File componentFile = componentFilePath.toFile();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(componentFile));
            bufferedWriter.write(componentFileText);
            bufferedWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String injectComponentVariables(ModelData modelData, String packageName) throws IOException {
        String componentTemplateText = "";
        componentTemplateText = getComponentTemplate();

        return StringUtils.replaceSequencesInString(
                componentTemplateText,
                new Pair<>("{packageName}", packageName),
                new Pair<>("{modelName}", modelData.getModelName()),
                new Pair<>("{modelNamePlural}", modelData.getModelNamePlural()),
                new Pair<>("{modelNameCapitalized}", modelData.getModelNameCapitalized()),
                new Pair<>("{modelNamePluralCapitalized}", modelData.getModelNamePluralCapitalized())
        );
    }

    private String getComponentTemplate() throws IOException {
        if(componentTemplateText != null) return componentTemplateText;
        String path = "";
        try {
            path = getClass().getResource(getComponentTemplateFileLocation()).toURI().getPath();
        } catch(URISyntaxException e) {
            System.out.println("URI Syntax Exception when trying to read the template file for the following component" + getComponentTemplateFileLocation());
        }
        File componentTemplateFile = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(componentTemplateFile));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        componentTemplateText = stringBuilder.toString();
        return componentTemplateText;
    }
}