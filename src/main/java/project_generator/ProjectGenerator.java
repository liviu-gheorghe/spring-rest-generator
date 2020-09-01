package project_generator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectGenerator {

    private String apiURLString;
    private String language;
    private String bootVersion;
    private String baseDir;
    private String groupId;
    private String artifactId;
    private String description;
    private String name;
    private String packageName;
    private String javaVersion;
    private List<String> dependencies;
    private String projectRootDirectory;
    private final List<String> models = new ArrayList<>();


    public void addModel(String model) {
        models.add(model);
    }

    public List<String> getModels() {
        return models;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBootVersion() {
        return bootVersion;
    }

    public void setBootVersion(String bootVersion) {
        this.bootVersion = bootVersion;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getProjectRootDirectory() {
        return projectRootDirectory;
    }

    public void setProjectRootDirectory(String projectRootDirectory) {
        this.projectRootDirectory = projectRootDirectory;
    }

    private void buildUrl() {

        apiURLString = String.format (
        "https://start.spring.io/starter.zip?" +
            "type=maven-project" +
            "&language=%s" +
            "&bootVersion=%s" +
            "&baseDir=%s" +
            "&groupId=%s" +
            "&artifactId=%s" +
            "&name=%s" +
            "&description=%s" +
            "&packageName=%s" +
            "&packaging=jar" +
            "&javaVersion=%s" +
            "&dependencies=%s",
                language,
                bootVersion,
                baseDir,
                groupId,
                artifactId,
                name,
                description,
                packageName,
                javaVersion,
                listToCsv(dependencies)
        );
        System.out.println(apiURLString);
    }

    private String listToCsv(List<String> l) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<l.size();i++) {
            sb.append(l.get(i));
            if(i+1<l.size()) sb.append(",");
        }
        return sb.toString();
    }

    private ProjectGenerator() throws IOException {
        this.language = "java";
        this.bootVersion = "2.3.3.RELEASE";
        this.baseDir = "demo";
        this.groupId = "com.liviugheorghe";
        this.artifactId = "demo";
        this.name = "demo";
        this.description = "Spring Boot Project";
        this.packageName = groupId+ "." + baseDir;
        this.javaVersion = "1.8";
        this.dependencies = new ArrayList<>(Arrays.asList("web"));
    }

    public void generate() throws IOException {
        InputStream archiveInputStream = getResourceInputStream();
        ZipArchiveDecoder zipArchiveDecoder = new ZipArchiveDecoder(projectRootDirectory);
        zipArchiveDecoder.decode(archiveInputStream);
    }

    private InputStream getResourceInputStream() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(new URL(apiURLString))
                .build();
        Response response = client.newCall(request).execute();
        return response.body().byteStream();
    }

    public static class Builder {

        private final ProjectGenerator generator;

        public Builder() throws IOException {
            generator = new ProjectGenerator();
        }

        public Builder setLanguage(String language) {
            generator.language = language;
            return this;
        }

        public Builder addModel(String model) {
            generator.addModel(model);
            return this;
        }

        public Builder setBootLevel(String bootLevel) {
            generator.bootVersion = bootLevel;
            return this;
        }

        public Builder setBaseDir(String baseDir) {
            generator.baseDir = baseDir;
            generator.packageName = generator.groupId+"."+generator.baseDir;
            return this;
        }

        public Builder setGroupId(String groupId) {
            generator.groupId = groupId;
            return this;
        }

        public Builder setArtifactId(String artifactId) {
            generator.artifactId = artifactId;
            return this;
        }

        public Builder setName(String name) {
            generator.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            generator.description = description;
            return this;
        }

        public Builder setPackageName(String packageName) {
            generator.packageName = packageName;
            return this;
        }

        public Builder setJavaVersion(String javaVersion) {
            generator.javaVersion = javaVersion;
            return this;
        }

        public Builder setDependencies(List<String> dependencies) {
            generator.dependencies = dependencies;
            return this;
        }

        public Builder setRootDirectory(String projectRootDirectory) {
            generator.projectRootDirectory = projectRootDirectory;
            return this;
        }

        public ProjectGenerator build() {
            generator.buildUrl();
            return generator;
        }
    }
}