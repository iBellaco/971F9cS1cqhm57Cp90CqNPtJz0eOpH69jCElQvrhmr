import re

with open('app/src/main/java/com/example/WildRiftApp.kt', 'r', encoding='utf-8') as f:
    content = f.read()

import_okhttp = "import okhttp3.OkHttpClient\n"
if import_okhttp not in content:
    content = content.replace("import java.util.concurrent.TimeUnit", "import java.util.concurrent.TimeUnit\n" + import_okhttp)

okhttp_code = """
            .okHttpClient {
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                            .header("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }
"""

content = content.replace(".allowHardware(true)", ".allowHardware(true)\n" + okhttp_code)

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w', encoding='utf-8') as f:
    f.write(content)

