import os
import re

model_dir = "app/src/main/java/com/example/model"
for filename in os.listdir(model_dir):
    if filename.endswith(".kt"):
        filepath = os.path.join(model_dir, filename)
        with open(filepath, "r") as f:
            content = f.read()
        
        if "import kotlinx.serialization.Serializable" not in content:
            content = content.replace("package com.example.model\n", "package com.example.model\n\nimport kotlinx.serialization.Serializable\n")
        
        # Add @Serializable to data classes and enums
        content = re.sub(r'(enum class \w+)', r'@Serializable\n\1', content)
        content = re.sub(r'(data class \w+)', r'@Serializable\n\1', content)
        # remove duplicate @Serializable if any
        content = re.sub(r'(@Serializable\s+)+', '@Serializable\n', content)
        
        with open(filepath, "w") as f:
            f.write(content)
