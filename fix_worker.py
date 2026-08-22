import re

with open('app/src/main/java/com/example/WildRiftApplication.kt', 'r') as f:
    content = f.read()

target = """        val dailyWorkRequest = PeriodicWorkRequestBuilder<MetaScrapingWorker>(
            24, TimeUnit.HOURS, // Se ejecuta una vez cada 24 horas
            flexTimeInterval = 2, timeUnit = TimeUnit.HOURS // Margen de ejecución
        )
        .setConstraints(constraints)
        .build()"""

replacement = """        val dailyWorkRequest = PeriodicWorkRequestBuilder<MetaScrapingWorker>(
            24, TimeUnit.HOURS, 
            2, TimeUnit.HOURS
        )
        .setConstraints(constraints)
        .build()"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/WildRiftApplication.kt', 'w') as f:
    f.write(content)
