#!/bin/bash
sed -i 's/if (allies\[idx\]?.id != scannedAlly.id) {/if (allies\[idx\] == null) {/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/if (enemies\[idx\]?.id != scannedEnemy.id || state.enemyConfidences\[role\] != result.enemyConfidencesByRole\[role\]) {/if (enemies\[idx\] == null) {/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/} else if (result.isSuccessful) {/\/\/ } else if (result.isSuccessful) {/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/if (allies\[idx\] != null) {/\/\/ if (allies\[idx\] != null) {/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/allies\[idx\] = null/\/\/ allies\[idx\] = null/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/if (enemies\[idx\] != null) {/\/\/ if (enemies\[idx\] != null) {/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/enemies\[idx\] = null/\/\/ enemies\[idx\] = null/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/state.enemyConfidences.remove(role)/\/\/ state.enemyConfidences.remove(role)/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
