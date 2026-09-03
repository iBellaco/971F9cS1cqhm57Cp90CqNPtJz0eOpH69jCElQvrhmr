package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.*

import com.example.util.tr

@Composable
fun PrivacyPolicyDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.88f)
                .border(1.5.dp, HextechGold.copy(alpha = 0.7f), RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = HextechSurface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = HextechGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = tr("Política de Privacidad"),
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = tr("Cerrar"), tint = TextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Content scrollable
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    PolicySection(
                        title = tr("1. Qué datos recopilamos y por qué"),
                        body = tr("Hola. Queremos ser totalmente transparentes sobre la información que usa la app. Wild Rift Coach está diseñada para ayudarte en tus partidas y análisis de draft. Si decides iniciar sesión con tu cuenta, guardamos únicamente tu correo electrónico y tu nombre de perfil para gestionar tu acceso, suscripción o estado en la nube. No vendemos, alquilamos ni compartimos tus datos personales con terceros.")
                    )

                    PolicySection(
                        title = tr("2. Permisos especiales (Superposición y Captura)"),
                        body = tr("Para que el asistente flotante funcione mientras juegas, la app solicita permiso para mostrarse sobre otras aplicaciones y, opcionalmente, capturar la pantalla para analizar la selección de campeones mediante OCR. Estas capturas se procesan de forma local en tu propio dispositivo en tiempo real para detectar los campeones del draft y darte consejos al instante. No almacenamos ni enviamos capturas de pantalla de tus partidas a ningún servidor externo.")
                    )

                    PolicySection(
                        title = tr("3. Almacenamiento local"),
                        body = tr("Tus configuraciones personalizadas, favoritos y datos de uso habitual se guardan directamente en el almacenamiento interno de tu celular mediante bases de datos seguras. Puedes borrar tus datos en cualquier momento limpiando el almacenamiento de la app desde los ajustes de Android.")
                    )

                    PolicySection(
                        title = tr("4. Cero Publicidad"),
                        body = tr("Hemos eliminado por completo cualquier tipo de publicidad, banners comerciales y SDKs de anuncios. La aplicación es 100% libre de anuncios para garantizarte una experiencia limpia y sin distracciones.")
                    )

                    PolicySection(
                        title = tr("5. Seguridad y contacto"),
                        body = tr("Protegemos tu cuenta y datos mediante conexiones cifradas con servicios seguros en la nube. Si tienes cualquier duda sobre tu privacidad o quieres que eliminemos tus datos de nuestros registros, puedes ponerte en contacto con nosotros directamente desde el panel de soporte o comentarios de la aplicación.")
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = tr("Entendido y Aceptar"),
                        color = HextechDarkBg,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PolicySection(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = title,
            color = HextechGoldLight,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = body,
            color = TextSecondary,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}
