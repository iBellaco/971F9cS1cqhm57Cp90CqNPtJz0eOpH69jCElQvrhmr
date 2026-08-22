import re

with open('app/src/main/java/com/example/WildRiftApplication.kt', 'r') as f:
    content = f.read()

import_statement = """import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.ExistingPeriodicWorkPolicy
import java.util.concurrent.TimeUnit
import com.example.service.MetaScrapingWorker"""

content = content.replace("import android.util.Log", import_statement)

oncreate_work = """    override fun onCreate() {
        super.onCreate()
        
        setupDailyScraping()
    }
    
    private fun setupDailyScraping() {
        // Ejecutar diariamente solo si hay red (ahorra batería)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
            
        val dailyWorkRequest = PeriodicWorkRequestBuilder<MetaScrapingWorker>(
            24, TimeUnit.HOURS, // Se ejecuta una vez cada 24 horas
            flexTimeInterval = 2, timeUnit = TimeUnit.HOURS // Margen de ejecución
        )
        .setConstraints(constraints)
        .build()
        
        // Política KEEP mantiene la solicitud actual si ya existe
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DailyMetaScrape",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyWorkRequest
        )
    }"""

content = content.replace("""    override fun onCreate() {
        super.onCreate()
        
    }""", oncreate_work)

with open('app/src/main/java/com/example/WildRiftApplication.kt', 'w') as f:
    f.write(content)
