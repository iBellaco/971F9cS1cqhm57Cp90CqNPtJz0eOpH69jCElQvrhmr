with open("app/build.gradle.kts", "r") as f:
    content = f.read()

start = content.find("secrets {")
end = content.find("}", start)
print(content[start:end+1])
