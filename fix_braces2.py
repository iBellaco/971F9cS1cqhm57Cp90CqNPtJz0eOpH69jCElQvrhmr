import sys
with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    content = f.read()

bad = """                }
            }
        }
            }
        }

        // 3. ASIGNACIÓN POR ROL PRIMARIO DEL CAMPEÓN"""

good = """                }
            }
        }
        }

        // 3. ASIGNACIÓN POR ROL PRIMARIO DEL CAMPEÓN"""

content = content.replace(bad, good)
with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "w") as f:
    f.write(content)
