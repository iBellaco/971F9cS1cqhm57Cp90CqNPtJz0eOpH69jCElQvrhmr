#!/bin/bash
sed -i 's|iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/champion/Mel.png", //P|iconUrl = "https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Mel_Passive.png",|g' app/src/main/java/com/example/data/champions/MidLaneChampions.kt
