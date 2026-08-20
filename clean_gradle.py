import re

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

# Remove firebase deps
content = re.sub(r'\s*implementation\(platform\(libs\.firebase\.bom\)\)', '', content)
content = re.sub(r'\s*implementation\(libs\.firebase\..*?\)', '', content)
content = re.sub(r'\s*// implementation\(libs\.firebase\..*?\)', '', content)

# Remove google-services
content = re.sub(r'import com\.google\.gms\.googleservices\.GoogleServicesPlugin\.MissingGoogleServicesStrategy', '', content)
content = re.sub(r'\s*alias\(libs\.plugins\.google\.services\)', '', content)
content = re.sub(r'googleServices\s*\{\s*missingGoogleServicesStrategy.*?\}', '', content)

with open("app/build.gradle.kts", "w") as f:
    f.write(content)
