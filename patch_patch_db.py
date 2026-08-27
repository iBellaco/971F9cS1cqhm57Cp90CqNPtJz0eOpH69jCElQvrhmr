import re

file_path = "app/src/main/java/com/example/data/supabase/SupabaseClientManager.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Add fetchCurrentPatchVersion
new_fetch = """    suspend fun fetchCurrentPatchVersion(): Result<String> = withContext(Dispatchers.IO) {
        try {
            val patches = client.postgrest.from("wr_patches").select().decodeList<com.example.data.supabase.model.WrPatchDto>()
            if (patches.isNotEmpty()) {
                val latestPatch = patches.first().version
                com.example.data.WildRiftRepository.CURRENT_PATCH_VERSION = latestPatch
                Result.success(latestPatch)
            } else {
                Result.failure(Exception("No se encontraron parches en la base de datos."))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo la versión del parche de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }
    
    suspend fun testConnection"""

content = content.replace("    suspend fun testConnection", new_fetch)

# Update SQL Schema
new_schema = """-- =========================================================
-- ESQUEMA OFICIAL SUPABASE PARA WILD RIFT APP (REPORTES Y PARCHE)
-- =========================================================

-- 1. TABLA DE CONTROL DE PARCHES
CREATE TABLE IF NOT EXISTS public.wr_patches (
    id TEXT PRIMARY KEY DEFAULT 'current',
    version TEXT NOT NULL,
    notes TEXT DEFAULT '',
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. TABLA DE REPORTES / SUGERENCIAS
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

-- 3. HABILITAR SEGURIDAD (RLS) Y PERMITIR LECTURA/ESCRITURA PÚBLICA (ANON)
ALTER TABLE public.wr_patches ENABLE ROW LEVEL SECURITY;
CREATE POLICY "Allow public read wr_patches" ON public.wr_patches FOR SELECT USING (true);
CREATE POLICY "Allow public all wr_patches" ON public.wr_patches FOR ALL USING (true) WITH CHECK (true);

ALTER TABLE public.feedbacks ENABLE ROW LEVEL SECURITY;
CREATE POLICY "Allow public read feedbacks" ON public.feedbacks FOR SELECT USING (true);
CREATE POLICY "Allow public insert feedbacks" ON public.feedbacks FOR INSERT WITH CHECK (true);
CREATE POLICY "Allow public update feedbacks" ON public.feedbacks FOR UPDATE USING (true);
        \"\"\".trimIndent()"""

content = re.sub(r'-- =========================================================\s*-- ESQUEMA OFICIAL SUPABASE PARA WILD RIFT APP \(SOLO REPORTES\).*?\"\"\"\.trimIndent\(\)', new_schema, content, flags=re.DOTALL)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)
