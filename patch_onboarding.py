import re

with open('app/src/main/java/com/example/ui/screens/OnboardingScreen.kt', 'r') as f:
    text = f.read()

text = text.replace('title = "Sube a Challenger con Premium"', 'title = "Premium"')

with open('app/src/main/java/com/example/ui/screens/OnboardingScreen.kt', 'w') as f:
    f.write(text)
