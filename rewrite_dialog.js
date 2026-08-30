const fs = require('fs');

let content = fs.readFileSync('/tmp/old_avatar.kt', 'utf8');

// 1. Add imports
if (!content.includes('import androidx.compose.foundation.lazy.grid.GridItemSpan')) {
    content = content.replace(
        'import androidx.compose.foundation.lazy.grid.items',
        'import androidx.compose.foundation.lazy.grid.items\nimport androidx.compose.foundation.lazy.grid.item\nimport androidx.compose.foundation.lazy.grid.GridItemSpan'
    );
}

// 2. Modify filteredAvatars to return a Map
const oldFilteredAvatars = `    val filteredAvatars = remember(selectedFilter) {
        when (selectedFilter) {
            "Jonia" -> AvatarCatalog.avatars.filter { it.region.equals("Jonia", ignoreCase = true) }
            "Zaun / Piltóver" -> AvatarCatalog.avatars.filter {
                it.region.contains("Zaun", ignoreCase = true) || it.region.contains("Piltóver", ignoreCase = true)
            }
            "Demacia / Noxus" -> AvatarCatalog.avatars.filter {
                it.region.contains("Demacia", ignoreCase = true) || it.region.contains("Noxus", ignoreCase = true)
            }
            "Freljord / Shurima" -> AvatarCatalog.avatars.filter {
                it.region.contains("Freljord", ignoreCase = true) || it.region.contains("Shurima", ignoreCase = true)
            }
            "Runaterra / Varios" -> AvatarCatalog.avatars.filter {
                it.region.contains("Runaterra", ignoreCase = true) || it.region.contains("Islas", ignoreCase = true) || it.region.contains("Targon", ignoreCase = true) || it.region.contains("Aguas", ignoreCase = true) || it.region.contains("Vacío", ignoreCase = true) || it.region.contains("Oscuros", ignoreCase = true) || it.region.contains("Bandle", ignoreCase = true)
            }
            else -> AvatarCatalog.avatars
        }
    }`;

const newFilteredAvatars = `    val groupedAvatars = remember(selectedFilter) {
        val filtered = when (selectedFilter) {
            "Jonia" -> AvatarCatalog.avatars.filter { it.region.equals("Jonia", ignoreCase = true) }
            "Zaun / Piltóver" -> AvatarCatalog.avatars.filter {
                it.region.contains("Zaun", ignoreCase = true) || it.region.contains("Piltóver", ignoreCase = true)
            }
            "Demacia / Noxus" -> AvatarCatalog.avatars.filter {
                it.region.contains("Demacia", ignoreCase = true) || it.region.contains("Noxus", ignoreCase = true)
            }
            "Freljord / Shurima" -> AvatarCatalog.avatars.filter {
                it.region.contains("Freljord", ignoreCase = true) || it.region.contains("Shurima", ignoreCase = true)
            }
            "Runaterra / Varios" -> AvatarCatalog.avatars.filter {
                it.region.contains("Runaterra", ignoreCase = true) || it.region.contains("Islas", ignoreCase = true) || it.region.contains("Targon", ignoreCase = true) || it.region.contains("Aguas", ignoreCase = true) || it.region.contains("Vacío", ignoreCase = true) || it.region.contains("Oscuros", ignoreCase = true) || it.region.contains("Bandle", ignoreCase = true)
            }
            else -> AvatarCatalog.avatars
        }
        filtered.groupBy { it.region }.toSortedMap()
    }`;
content = content.replace(oldFilteredAvatars, newFilteredAvatars);

// 3. Update LazyVerticalGrid
const oldGridStart = `            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                items(filteredAvatars, key = { it.id }) { avatar ->`;

const newGridStart = `            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                groupedAvatars.forEach { (region, avatarsInRegion) ->
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = region.uppercase(),
                            color = HextechGoldLight,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 4.dp)
                        )
                    }
                    items(avatarsInRegion, key = { it.id }) { avatar ->`;
content = content.replace(oldGridStart, newGridStart);

// Also we need to close the `forEach` loop in the grid.
// Find the end of the `items` block. It ends with `} // end of card` then `} // end of items`.
// Then we need to add a closing brace for the `forEach`.
// Using regex to replace the end of the LazyVerticalGrid.
// It's safer to just split and insert.

// Let's replace the clickable block
const oldClickable = `                            .clickable {
                                if (isEquipped) {
                                    Toast.makeText(context, "Este avatar ya está equipado.", Toast.LENGTH_SHORT).show()
                                } else if (canEquip) {
                                    isUpdating = true
                                    SubscriptionManager.changeAvatar(
                                        avatarId = avatar.id,
                                        onSuccess = {
                                            isUpdating = false
                                            Toast.makeText(context, "¡Avatar actualizado con éxito!", Toast.LENGTH_SHORT).show()
                                        },
                                        onError = { err ->
                                            isUpdating = false
                                            Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                                        }
                                    )
                                } else {
                                    showPremiumRequiredDialog = avatar
                                }
                            }`;

const newClickable = `                            .clickable(enabled = canEquip || isEquipped) {
                                if (isEquipped) {
                                    Toast.makeText(context, "Este avatar ya está equipado.", Toast.LENGTH_SHORT).show()
                                } else {
                                    isUpdating = true
                                    SubscriptionManager.changeAvatar(
                                        avatarId = avatar.id,
                                        onSuccess = {
                                            isUpdating = false
                                            Toast.makeText(context, "¡Avatar actualizado con éxito!", Toast.LENGTH_SHORT).show()
                                        },
                                        onError = { err ->
                                            isUpdating = false
                                            Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                                        }
                                    )
                                }
                            }`;
content = content.replace(oldClickable, newClickable);

// Also close the `forEach` inside the grid.
const oldGridEnd = `                                }
                            }
                        }
                    }
                }
            }
        }
    }`;

const newGridEnd = `                                }
                            }
                        }
                    }
                }
            }
        }
    }`;
// Wait, the safest way to inject the closing brace for `forEach` is after the `items(...) { ... }`.
// Let's find `} // End of items` and add another `}`.
// Instead of trying to parse the nested brackets, let's just do a manual replacement in node:

const parts = content.split('items(avatarsInRegion, key = { it.id }) { avatar ->');
if (parts.length === 2) {
    let secondPart = parts[1];
    // Find the end of `items` block
    // We know that `LazyVerticalGrid` block ends eventually.
    // Let's look for `                        }
    //                    }
    //                }
    //            }`
    secondPart = secondPart.replace(
        `                    }
                }
            }
        }
    }

    // Modal when user tries`,
        `                    }
                }
                } // End of forEach
            }
        }
    }

    // Modal when user tries`
    );
    content = parts[0] + 'items(avatarsInRegion, key = { it.id }) { avatar ->' + secondPart;
}

// Modify lock logic
const oldLock = `                                } else if (!canEquip) {
                                    Box(
                                        modifier = Modifier
                                            .size(18.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF0F172A).copy(alpha = 0.9f))
                                            .border(1.dp, HextechGold.copy(alpha = 0.6f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Lock,
                                            contentDescription = "Bloqueado",
                                            tint = HextechGold,
                                            modifier = Modifier.size(11.dp)
                                        )
                                    }
                                }`;
const newLock = `                                } else if (!canEquip) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF0F172A).copy(alpha = 0.95f))
                                            .border(1.5.dp, HextechGold, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Lock,
                                            contentDescription = "Bloqueado",
                                            tint = HextechGold,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }`;
content = content.replace(oldLock, newLock);

// Also modify the overall card styling to gray out disabled avatars
const oldCardColors = `                        colors = CardDefaults.cardColors(
                            containerColor = if (isEquipped) HextechGold.copy(alpha = 0.12f) else HextechSurface
                        )`;
const newCardColors = `                        colors = CardDefaults.cardColors(
                            containerColor = if (isEquipped) HextechGold.copy(alpha = 0.12f) else if (!canEquip) HextechSurface.copy(alpha = 0.5f) else HextechSurface
                        )`;
content = content.replace(oldCardColors, newCardColors);

const oldImageAlpha = `contentDescription = avatar.name,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop`;
const newImageAlpha = `contentDescription = avatar.name,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                    alpha = if (canEquip) 1f else 0.4f`;
content = content.replace(oldImageAlpha, newImageAlpha);

fs.writeFileSync('/tmp/new_avatar.kt', content);
console.log("Rewrote avatar dialog");
