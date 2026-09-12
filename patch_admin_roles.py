import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

# Make the Header check role
old_header = """                AdminDashboardHeader(
                    onClose = onDismiss,
                    onOpenFeedbackAndSupport = { showReportsPanel = true },
                    onOpenBroadcast = { showBroadcastDialog = true },
                    onOpenNotice = { showNoticeConfigDialog = true },
                    onOpenCpmAnalytics = { showCpmAnalyticsDialog = true }
                )"""

new_header = """                AdminDashboardHeader(
                    onClose = onDismiss,
                    onOpenFeedbackAndSupport = { showReportsPanel = true },
                    onOpenBroadcast = { showBroadcastDialog = true },
                    onOpenNotice = { showNoticeConfigDialog = true },
                    onOpenCpmAnalytics = { showCpmAnalyticsDialog = true },
                    isFullAdmin = userRole == "admin" || com.example.util.AuthManager.isCurrentUserAdmin()
                )"""
content = content.replace(old_header, new_header)

old_panel = """                // Panel principal de gestión
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    EnhancedUserManagementPanel("""

new_panel = """                // Panel principal de gestión
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    if (userRole == "admin" || com.example.util.AuthManager.isCurrentUserAdmin()) {
                        EnhancedUserManagementPanel("""

content = content.replace(old_panel, new_panel)

old_panel_end = """                        isMinimized = isMonitoringMinimized,
                        onToggleMinimize = { isMonitoringMinimized = !isMonitoringMinimized }
                    )
                }"""

new_panel_end = """                        isMinimized = isMonitoringMinimized,
                        onToggleMinimize = { isMonitoringMinimized = !isMonitoringMinimized }
                    )
                    } else {
                        // Moderador View
                        Column(
                            modifier = Modifier.fillMaxSize().padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.SupportAgent, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(64.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Panel de Moderación", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Abre 'OCR y Soporte' en la parte superior para moderar los aportes de la comunidad.", color = TextSecondary, fontSize = 14.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                        }
                    }
                }"""

content = content.replace(old_panel_end, new_panel_end)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)
