package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.util.tr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQScreen(
    onNavigateBack: () -> Unit
) {
    var faqSearchQuery by remember { mutableStateOf("") }
    val faqs = remember {
        listOf(
            Pair(
                "¿Cómo funciona el overlay flotante durante la partida?",
                "Básicamente, al activar el asistente te aparece una burbuja en la pantalla que puedes mover donde te sea más cómoda. Cuando entras a selección de campeones, la abres y te tira al toque las recomendaciones según tu línea y el draft enemigo. Lo armé para que no tengas que salirte del juego ni marearte buscando guías."
            ),
            Pair(
                "¿Para qué sirve exactamente cada botón y sección de la app?",
                "Te cuento rápido cómo está armado: el botón de selección de campeones te abre el asistente de draft interactivo para armar tus composiciones y counters en tiempo real. La sección de Tier List te muestra el meta actual ordenado por tiers (S+, S, A) según winrate y desempeño en las rankeds. Las estadísticas recopilan datos actualizados por rol y enfrentamientos directos. El panel de usuario te deja gestionar tu cuenta, verificar tu estado premium y revisar tu historial de partidas guardadas con Room."
            ),
            Pair(
                "¿De dónde salen las estadísticas, los campeones y las builds?",
                "Todo el contenido viene directo del meta competitivo oficial de Wild Rift y servidores de alto elo (Challenger/Grandmaster). Analizamos constantemente las builds de los mejores jugadores del mundo, las runas óptimas, los hechizos de invocador que más se usan por línea y las guías de objetos core o situacionales (como cortacuras o fajines) para que siempre tengas la información más precisa."
            ),
            Pair(
                "¿Por qué cambian las recomendaciones de campeones si elijo 1er Pick?",
                "Buena pregunta. No es lo mismo pickear de los primeros que ir de counter en la última ronda. Si vas de primer pick, la app te sugiere campeones seguros que encajen bien en cualquier composición y no tengan counters fáciles. Si eres el último, te busca el counter perfecto contra el rival que ya sacó la cara."
            ),
            Pair(
                "¿El asistente consume mucha batería o me genera lag jugando?",
                "Cero lag. Está optimizado con aceleración por hardware y un motor súper ligero. Gasta menos del 2% de batería por hora, así que puedes jugar tranquilo tus ranked sin miedo a bajones de FPS ni tirones en las teamfights."
            ),
            Pair(
                "¿Cómo se guardan mis partidas y drafts en el historial?",
                "Si tienes la versión premium, con darle al botón de guardar al terminar la selección, la partida se almacena de una en tu base de datos local con Room. Así puedes revisar luego tus composiciones, ver con qué campeones tienes más winrate y repasar qué falló o funcionó."
            ),
            Pair(
                "¿Por qué usa la nomenclatura H1, H2, H3 en lugar de Q, W, E?",
                "Porque en Wild Rift las habilidades se llaman Habilidad 1, Habilidad 2, Habilidad 3 y Definitiva (H4). Como la app es 100% exclusiva para móviles y no un port de PC, usamos los términos oficiales para que no te confundas al momento de jugar rápido."
            )
        )
    }

    val filteredFaqs = remember(faqSearchQuery) {
        if (faqSearchQuery.isBlank()) faqs
        else faqs.filter { 
            it.first.contains(faqSearchQuery, ignoreCase = true) || 
            it.second.contains(faqSearchQuery, ignoreCase = true) 
        }
    }

    Scaffold(
        containerColor = HextechDarkBg,
        topBar = {
            TopAppBar(
                title = { Text(tr("Preguntas Frecuentes"), color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = tr("Volver"), tint = HextechGold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = HextechSurface)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = faqSearchQuery,
                onValueChange = { faqSearchQuery = it },
                placeholder = { Text(tr("Buscar pregunta o duda..."), color = TextMuted, fontSize = 13.sp) },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                textStyle = TextStyle(fontSize = 13.sp),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp)) },
                trailingIcon = {
                    if (faqSearchQuery.isNotEmpty()) {
                        IconButton(onClick = { faqSearchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Limpiar", tint = TextMuted, modifier = Modifier.size(18.dp))
                        }
                    }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HextechCyan,
                    unfocusedBorderColor = HextechCardBorder,
                    focusedContainerColor = HextechSurface,
                    unfocusedContainerColor = HextechSurface
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredFaqs.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tr("No se encontraron preguntas que coincidan con tu búsqueda."),
                        color = TextMuted,
                        fontSize = 13.sp
                    )
                }
            } else {
                filteredFaqs.forEach { (question, answer) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = HextechSurface),
                        border = BorderStroke(1.dp, HextechCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Info, contentDescription = null, tint = HextechGold, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = question,
                                    color = HextechGoldLight,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = answer,
                                color = TextSecondary,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
