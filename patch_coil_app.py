import re

with open('app/src/main/java/com/example/WildRiftApp.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Add User-Agent header interceptor to coil
interceptor_code = """
            .components {
                add(coil.intercept.Interceptor { chain ->
                    val request = chain.request.newBuilder()
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                        .header("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                        .build()
                    chain.proceed(request)
                })
            }
"""

content = content.replace(".allowHardware(true)", ".allowHardware(true)\n" + interceptor_code)

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w', encoding='utf-8') as f:
    f.write(content)

