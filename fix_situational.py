with open('app/src/main/java/com/example/data/SituationalItemAdvisor.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace(
    'itemData?.category?.displayName ?: "Objeto Situacional Adaptativo"',
    'itemData?.category ?: "Objeto Situacional Adaptativo"'
)

with open('app/src/main/java/com/example/data/SituationalItemAdvisor.kt', 'w', encoding='utf-8') as f:
    f.write(content)
