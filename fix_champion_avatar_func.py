with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r') as f:
    text = f.read()

target = """            Box(
                modifier = Modifier
                    .size(size * 0.35f)
                    .clip(CircleShape)
                    .background(HextechDarkBg)
                    .border(1.dp, HextechGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = champion.tier,
                    color = tierColor,
                    fontSize = (size.value * 0.16).sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
    }}"""

replacement = """            Box(
                modifier = Modifier
                    .size(size * 0.35f)
                    .clip(CircleShape)
                    .background(HextechDarkBg)
                    .border(1.dp, HextechGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = champion.tier,
                    color = tierColor,
                    fontSize = (size.value * 0.16).sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w') as f:
    f.write(text)
print("Fixed ChampionAvatar function brackets")
