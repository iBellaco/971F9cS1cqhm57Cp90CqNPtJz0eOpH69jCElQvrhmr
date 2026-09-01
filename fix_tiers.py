import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

old_tierA = """                // Tier A
                if (tierA.isNotEmpty()) {
                    item {
                        TierSectionCard(
                            tierName = "TIER A (Opciones Sólidas y Balanceadas)",
                            tierColor = TierAColor,
                            champions = tierA,
                            onSelectChampion = onSelectChampion
                        )
                    }
                }"""

new_tiers_ui = """                // Tier A
                if (tierA.isNotEmpty()) {
                    item {
                        TierSectionCard(
                            tierName = "TIER A (Opciones Sólidas y Balanceadas)",
                            tierColor = TierAColor,
                            champions = tierA,
                            onSelectChampion = onSelectChampion
                        )
                    }
                }
                
                // Tier B
                if (tierB.isNotEmpty()) {
                    item {
                        TierSectionCard(
                            tierName = "TIER B (Opciones Viables)",
                            tierColor = com.example.ui.theme.TierBColor,
                            champions = tierB,
                            onSelectChampion = onSelectChampion
                        )
                    }
                }

                // Tier C
                if (tierC.isNotEmpty()) {
                    item {
                        TierSectionCard(
                            tierName = "TIER C (Situacionales)",
                            tierColor = com.example.ui.theme.TierCColor,
                            champions = tierC,
                            onSelectChampion = onSelectChampion
                        )
                    }
                }

                // Tier D
                if (tierD.isNotEmpty()) {
                    item {
                        TierSectionCard(
                            tierName = "TIER D / OTROS",
                            tierColor = com.example.ui.theme.TierDColor,
                            champions = tierD,
                            onSelectChampion = onSelectChampion
                        )
                    }
                }"""

content = content.replace(old_tierA, new_tiers_ui)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
