package rest_components_generator;

import org.reflections.Reflections;
import rest_components_generator.generators.ComponentGenerator;
import rest_components_generator.generators.Generator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GeneratorsProvider {

    public static List<Class<ComponentGenerator>> getGenerators() {

        List<Class<ComponentGenerator>> generatorsList = new ArrayList<>();
        Reflections reflections = new Reflections("rest_components_generator");
        Set<Class<?>> generatorClasses = reflections.getTypesAnnotatedWith(Generator.class);
        generatorClasses.forEach(
                c -> {
                    if(ComponentGenerator.class.isAssignableFrom(c)) {
                        generatorsList.add((Class<ComponentGenerator>) c);
                    }
                }
        );
        generatorsList.forEach(
                c -> System.out.println(c.getName())
        );
        return generatorsList;
    }
}
