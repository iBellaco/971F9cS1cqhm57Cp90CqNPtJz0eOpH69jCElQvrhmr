import re

file_path = "app/src/main/java/com/example/data/supabase/SupabaseClientManager.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace testConnection
new_test_conn = """    suspend fun testConnection(): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Se asume que existe la tabla feedbacks
            val count = client.postgrest.from("feedbacks").select().data
            Result.success("Conexión exitosa. Se pudo conectar al panel de reportes.")
        } catch (e: Exception) {
            Log.e(TAG, "Error probando conexión a Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }"""
content = re.sub(r'suspend fun testConnection\(\).*?Result\.failure\(e\)\s*\}\s*\}', new_test_conn, content, flags=re.DOTALL)

# Replace SQL Schema
new_schema = """    fun getSupabaseSqlSchema(): String {
        return \"\"\"-- =========================================================
-- ESQUEMA OFICIAL SUPABASE PARA WILD RIFT APP (SOLO REPORTES)
-- =========================================================

-- 1. TABLA DE REPORTES / SUGERENCIAS
CREATE TABLE IF NOT EXISTS public.feedbacks (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type TEXT NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    app_version TEXT,
    device_info TEXT,
    status TEXT DEFAULT 'PENDING',
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. HABILITAR SEGURIDAD (RLS) Y PERMITIR LECTURA/ESCRITURA PÚBLICA (ANON)
ALTER TABLE public.feedbacks ENABLE ROW LEVEL SECURITY;
CREATE POLICY "Allow public read feedbacks" ON public.feedbacks FOR SELECT USING (true);
CREATE POLICY "Allow public insert feedbacks" ON public.feedbacks FOR INSERT WITH CHECK (true);
CREATE POLICY "Allow public update feedbacks" ON public.feedbacks FOR UPDATE USING (true);
        \"\"\".trimIndent()
    }"""
content = re.sub(r'fun getSupabaseSqlSchema\(\): String \{.*?\}\s*\}\s*$', new_schema + "\n}\n", content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
