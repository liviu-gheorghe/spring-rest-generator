import project_generator.ProjectGenerator;
import rest_components_generator.RestComponentsGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliRunner {

    private BufferedReader bufferedReader;
    private ProjectGenerator.Builder builder;
    private ProjectGenerator generator;

    public void run() throws IOException {
        this.builder = new ProjectGenerator.Builder();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        setProjectRootDir();
        setProjectName();
        setModels();
        confirmProjectCreation();
        bufferedReader.close();
    }

    private void confirmProjectCreation() throws IOException {
        showProjectDetails();
        System.out.println("Generate project ? (y/n)");
        String line = bufferedReader.readLine();
        if(line.trim().toLowerCase().equals("y")) {
            generator = builder.build();
            generator.generate();
            confirmRestComponentsCreation();
        }
    }

    private void confirmRestComponentsCreation() throws IOException {
        System.out.println("Generate rest components ? (adapters, controllers, services, repos etc) (y/n)");
        String line = bufferedReader.readLine();
        if(line.trim().toLowerCase().equals("y")) {
            generateRestComponents();
        }
    }

    private void showProjectDetails() {
        System.out.println("A project with the following options  will be generated");

    }

    private void setModels() throws IOException {
        String line;
        int noOfModels;
        System.out.println("Enter number of models");
        line = bufferedReader.readLine();
        try {
            noOfModels = Integer.parseInt(line.trim());
        } catch(NumberFormatException e) {
            noOfModels = 0;
        }
        for(int i=0;i<noOfModels;i++) {
            addModel(i+1);
        }
    }

    private void setProjectRootDir() throws IOException {
        String line;
        System.out.println("Enter a valid path for the project root directory");
        line = bufferedReader.readLine();
        checkIfValidPath();
        builder.setRootDirectory(line);
    }

    private void setProjectName() throws IOException {
        String line;
        System.out.println("Enter project name");
        line = bufferedReader.readLine();
        builder.setName(line);
        builder.setArtifactId(line);
        builder.setBaseDir(line);
    }

    private void addModel(int i) throws IOException {
        System.out.println("Enter name for model " + i);
        String line = bufferedReader.readLine();
        builder.addModel(line.trim());
    }

    private void checkIfValidPath() {

    }

    private void generateRestComponents() {
        RestComponentsGenerator restComponentsGenerator = new RestComponentsGenerator(generator);
        restComponentsGenerator.generate();
    }
}
