package io.github.tuuzed.base64flie;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

public class Base64FileGradlePlugin implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        project.getExtensions().add("Base64File", new Base64File());
    }
}
