import sys

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "r") as f:
    content = f.read()

target = """        // Inner card body with subtle ambient role gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            roleColor.copy(alpha = 0.08f),
                            LolCardBg,
                            Color(0xFF091428).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {"""

replacement = """        // Inner card body with subtle ambient role gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            roleColor.copy(alpha = 0.08f),
                            LolCardBg,
                            Color(0xFF091428).copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {"""

content = content.replace(target, replacement)

# Make sure we don't have dangling brackets
# Wait, I already have the Column started, but I need to ensure it closes properly before the Canvas.

