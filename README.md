
[![](https://jitpack.io/v/tuuzed/base64file-gradle-plugin.svg)](https://jitpack.io/#tuuzed/base64file-gradle-plugin)

```groovy
buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
        mavenCentral()
    }
    dependencies {
        classpath 'com.github.tuuzed:base64file-gradle-plugin:0.1.2'
    }
}
apply plugin: 'com.github.tuuzed.base64flie'

task test() {
    System.err.println(Base64File.from(project.file("build.gradle")))
}

```
