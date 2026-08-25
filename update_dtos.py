import re

with open('app/src/main/java/com/example/data/supabase/model/WildRiftRemoteDtos.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# ItemDto
content = content.replace("val name: String = \"\",", "val name: String = \"\",\n    @SerialName(\"name_en\") val nameEn: String = \"\",\n    @SerialName(\"name_pt\") val namePt: String = \"\",")
content = content.replace("val stats: String = \"\",", "val stats: String = \"\",\n    @SerialName(\"stats_en\") val statsEn: String = \"\",\n    @SerialName(\"stats_pt\") val statsPt: String = \"\",")
content = content.replace("val passive: String = \"\",", "val passive: String = \"\",\n    @SerialName(\"passive_en\") val passiveEn: String = \"\",\n    @SerialName(\"passive_pt\") val passivePt: String = \"\",")

# For Champion Dto, "val name:" was already replaced because we matched globally. Let's do it carefully.
# We'll just write a whole new file for DTOs to be safe.
