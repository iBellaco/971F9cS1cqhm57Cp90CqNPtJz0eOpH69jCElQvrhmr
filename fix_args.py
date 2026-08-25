with open("app/build.gradle.kts", "r") as f:
    text = f.read()

text = text.replace('kotlinOptions {\n        freeCompilerArgs += listOf("-Xannotation-default-target=param-property")\n    }', 'compilerOptions {\n        freeCompilerArgs.add("-Xannotation-default-target=param-property")\n    }')

with open("app/build.gradle.kts", "w") as f:
    f.write(text)
