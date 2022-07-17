# Minecraft Plugin with Gradle build system &amp; Kotlin
By Hevanafa, 17-07-2022

for Version 1.18.2 -- can be changed in `build.gradle`

IntelliJ IDEA automatically fetches the dependencies.

to build, use
```
gradlew shadowJar
```

in case you have `kotlin-stdlib` or Kotlin classpath plugin installed on the server, use:
```
gradlew build
```

then you can find the Jar file in `build\libs`


