import re

fpath = "app/src/main/java/com/example/util/ChampionRoleAdapter.kt"
with open(fpath, "r", encoding="utf-8") as f:
    content = f.read()

MAPPING = {
    # Replace runes names and trees
    '"Electrocutar (Dominación)", "Dominación: Impacto Repentino • Marca del Verdugo • Colección de Ojos • Pionero", "Electrocutar"': '"Electrocutar", "Impacto Repentino • Golpe Bajo • Colección de Globos Oculares • Cazador Incesante", "Electrocutar"',
    '"Réplica (Valor)", "Valor: Fuente de Vida • Acondicionamiento • Sobrecrecimiento • Pionero", "Réplica"': '"Reverberacción", "Fuente de Vida • Condicionamiento • Sobrecrecimiento • Inquebrantable", "Reverberacción"',
    '"Conquistador (Precisión)", "Precisión: Triunfo • Leyenda: Presteza • Cazador Titánico • Pionero", "Conquistador"': '"Conquistador", "Triunfo • Leyenda: Presteza • Último Esfuerzo • Cazador Incesante", "Conquistador"',
    
    '"Réplica (Valor)", "Valor: Fuente de Vida • Revestimiento de Huesos • Sobrecrecimiento • Dulces Frutos", "Réplica"': '"Reverberacción", "Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Goloso", "Reverberacción"',
    '"Invocar a Aery (Brujería)", "Brujería: Banda de Maná • Trascendencia • Tormenta Creciente • Dulces Frutos", "Invocar a Aery"': '"Aery", "Anillo de Flujo de Maná • Trascendencia • Se avecina tormenta • Goloso", "Aery"',
    '"Electrocutar (Dominación)", "Dominación: Impacto Repentino • Marca del Verdugo • Cazador Ingenioso • Dulces Frutos", "Electrocutar"': '"Electrocutar", "Impacto Repentino • Golpe Bajo • Cazador Ingenioso • Goloso", "Electrocutar"',
    
    '"Agarre del Perpetuo (Valor)", "Valor: Demolición • Revestimiento de Huesos • Sobrecrecimiento • Dulces Frutos", "Agarre del Perpetuo"': '"Garras del Inmortal", "Demolición • Coraza Ósea • Sobrecrecimiento • Goloso", "Garras del Inmortal"',
    '"Conquistador (Precisión)", "Precisión: Triunfo • Golpe de Gracia • Leyenda: Presteza • Revestimiento de Huesos", "Conquistador"': '"Conquistador", "Triunfo • Golpe de Gracia • Leyenda: Presteza • Coraza Ósea", "Conquistador"',
    '"Conquistador (Precisión)", "Precisión: Triunfo • Último Esfuerzo • Leyenda: Presteza • Revestimiento de Huesos", "Conquistador"': '"Conquistador", "Triunfo • Último Esfuerzo • Leyenda: Presteza • Coraza Ósea", "Conquistador"',
    
    '"Primer Golpe (Inspiración)", "Dominación: Impacto Repentino • Marca del Verdugo • Cazador Ingenioso • Banda de Maná", "Primer Golpe"': '"Primer Golpe", "Impacto Repentino • Golpe Bajo • Cazador Ingenioso • Anillo de Flujo de Maná", "Primer Golpe"',
    '"Electrocutar (Dominación)", "Dominación: Impacto Repentino • Marca del Verdugo • Colección de Ojos • Cazador Voraz", "Electrocutar"': '"Electrocutar", "Impacto Repentino • Golpe Bajo • Colección de Globos Oculares • Cazador Incesante", "Electrocutar"',
    
    '"Primer Golpe (Inspiración)", "Inspiración: Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Banda de Maná", "Primer Golpe"': '"Primer Golpe", "Calzado Mágico • Entrega de Galletas • Perspicacia Cósmica • Anillo de Flujo de Maná", "Primer Golpe"',
    '"Cadencia Letal (Precisión)", "Precisión: Triunfo • Leyenda: Linaje • Golpe de Gracia • Revestimiento de Huesos", "Cadencia Letal"': '"Compás Letal", "Triunfo • Leyenda: Linaje • Golpe de Gracia • Coraza Ósea", "Compás Letal"'
}

for old, new in MAPPING.items():
    content = content.replace(old, new)

with open(fpath, "w", encoding="utf-8") as f:
    f.write(content)

print("Mapped runes in adapter")
