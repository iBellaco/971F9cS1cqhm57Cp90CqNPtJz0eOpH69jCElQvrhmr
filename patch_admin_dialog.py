import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

old_catch = """                            } catch (e: Exception) {
                                videoUrl = pickedUri.toString()
                            } finally {"""

new_catch = """                            } catch (e: Exception) {
                                Toast.makeText(context, "Error en la nube: ${e.message}", Toast.LENGTH_LONG).show()
                                videoUrl = ""
                            } finally {"""

content = content.replace(old_catch, new_catch)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
