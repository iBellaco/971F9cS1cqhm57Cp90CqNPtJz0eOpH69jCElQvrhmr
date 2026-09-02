import sys

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    content = f.read()

target = """            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Summoner Crest Avatar + User Details
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // LoL Summoner Profile Crest with User Avatar
                    Box(contentAlignment = Alignment.BottomEnd) {
                        UserAvatarView(
                            avatarId = user.avatarId,
                            size = 42.dp,
                            fallbackInitial = if (user.name.isNotBlank()) user.name else user.email
                        )
                        // Online Status Bead
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(if (isOnline) LolZaunGreen else Color.DarkGray)
                                .border(1.dp, Color(0xFF05101E), CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (user.name.isNotBlank()) user.name else user.email.substringBefore("@"),
                                color = LolGoldLight,
                                fontSize = 14.5.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.3.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = user.email,
                            color = TextSecondary,
                            fontSize = 11.5.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // Role Pill with Runic Golden Border
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(roleColor.copy(alpha = 0.15f))
                                    .border(0.8.dp, roleColor.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = user.role.uppercase(),
                                    color = roleColor,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.6.sp
                                )
                            }
                            // Subscription Duration Pill (Clickable to manage)
                            if (user.role.equals("premium", ignoreCase = true)) {
                                val dummyTime = currentTime
                                val durationText = SubscriptionManager.formatDuration(user.premiumUntil)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (isExpired) LolNoxusRed.copy(alpha = 0.18f) else LolBorderGold.copy(alpha = 0.15f))
                                        .border(0.8.dp, if (isExpired) LolNoxusRed else LolBorderGold.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                                        .clickable { showSubscriptionTimeDialog = true }
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = if (isExpired) Icons.Default.Warning else Icons.Default.HourglassBottom,
                                            contentDescription = null,
                                            tint = if (isExpired) LolNoxusRed else LolBorderGold,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = if (isExpired) "EXPIRADO" else durationText,
                                            color = if (isExpired) LolNoxusRed else LolGoldLight,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "UID: ${user.uid.take(8)}...",
                                color = TextMuted,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
                // Quick Subscription Timer Button + Dropdown Menu
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    IconButton(
                        onClick = { showSubscriptionTimeDialog = true },
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(LolBorderGold.copy(alpha = 0.15f))
                            .border(1.dp, LolBorderGold.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                    ) {
                        Icon(
                            Icons.Default.HourglassTop,
                            contentDescription = "Gestionar Tiempo",
                            tint = LolBorderGold,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Box {
                        IconButton(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(LolDeepNavy)
                                .border(1.dp, LolBorderGoldDark.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        ) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "Opciones",
                                tint = LolBorderGold,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        DropdownMenu("""

replacement = """            Column(modifier = Modifier.fillMaxWidth()) {
                // Top Row: Avatar + Details + Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Summoner Crest Avatar + User Details
                    Row(
                        modifier = Modifier.weight(1f).padding(end = 6.dp)
                    ) {
                        // LoL Summoner Profile Crest with User Avatar
                        Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.padding(top = 4.dp)) {
                            UserAvatarView(
                                avatarId = user.avatarId,
                                size = 42.dp,
                                fallbackInitial = if (user.name.isNotBlank()) user.name else user.email
                            )
                            // Online Status Bead
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(if (isOnline) LolZaunGreen else Color.DarkGray)
                                    .border(1.dp, Color(0xFF05101E), CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (user.name.isNotBlank()) user.name else user.email.substringBefore("@"),
                                color = LolGoldLight,
                                fontSize = 14.5.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.3.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(
                                text = user.email,
                                color = TextSecondary,
                                fontSize = 11.5.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                // Role Pill with Runic Golden Border
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(roleColor.copy(alpha = 0.15f))
                                        .border(0.8.dp, roleColor.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = user.role.uppercase(),
                                        color = roleColor,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        letterSpacing = 0.6.sp
                                    )
                                }
                                // Subscription Duration Pill (Clickable to manage)
                                if (user.role.equals("premium", ignoreCase = true)) {
                                    val dummyTime = currentTime
                                    val durationText = SubscriptionManager.formatDuration(user.premiumUntil)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(if (isExpired) LolNoxusRed.copy(alpha = 0.18f) else LolBorderGold.copy(alpha = 0.15f))
                                            .border(0.8.dp, if (isExpired) LolNoxusRed else LolBorderGold.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                                            .clickable { showSubscriptionTimeDialog = true }
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = if (isExpired) Icons.Default.Warning else Icons.Default.HourglassBottom,
                                                contentDescription = null,
                                                tint = if (isExpired) LolNoxusRed else LolBorderGold,
                                                modifier = Modifier.size(10.dp)
                                            )
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text(
                                                text = if (isExpired) "EXPIRADO" else durationText,
                                                color = if (isExpired) LolNoxusRed else LolGoldLight,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    // Quick Subscription Timer Button + Dropdown Menu
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        IconButton(
                            onClick = { showSubscriptionTimeDialog = true },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(LolBorderGold.copy(alpha = 0.15f))
                                .border(1.dp, LolBorderGold.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                        ) {
                            Icon(
                                Icons.Default.HourglassTop,
                                contentDescription = "Gestionar Tiempo",
                                tint = LolBorderGold,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Box {
                            IconButton(
                                onClick = { expanded = true },
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(LolDeepNavy)
                                    .border(1.dp, LolBorderGoldDark.copy(alpha = 0.6f), RoundedCornerShape(6.dp))
                            ) {
                                Icon(
                                    Icons.Default.MoreVert,
                                    contentDescription = "Opciones",
                                    tint = LolBorderGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            DropdownMenu("""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(content)

print("Patch applied to AdminDashboardDialog.kt top layout")
