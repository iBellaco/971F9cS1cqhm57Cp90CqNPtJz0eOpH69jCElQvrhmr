import sys
with open("firestore.rules", "r") as f:
    content = f.read()

content = content.replace("request.auth.token.admin == true", "request.auth.token.admin == true || resource.data.role == 'admin'")

with open("firestore.rules", "w") as f:
    f.write(content)
