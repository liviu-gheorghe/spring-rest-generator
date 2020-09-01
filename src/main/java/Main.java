import project_generator.ProjectGenerator;
import rest_components_generator.RestComponentsGenerator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CliRunner runner = new CliRunner();
        runner.run();
    }
}