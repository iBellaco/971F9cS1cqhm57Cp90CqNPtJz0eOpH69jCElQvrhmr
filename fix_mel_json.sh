#!/bin/bash
sed -i 's|"iconUrl": "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/540.png"|"iconUrl": "REPLACE_ME"|g' app/src/main/assets/champions.json

# Now we need to sequentially replace REPLACE_ME
awk '/REPLACE_ME/{
    c++;
    if(c==1){sub("REPLACE_ME","https://ddragon.leagueoflegends.com/cdn/16.16.1/img/passive/Mel_Passive.png")}
    if(c==2){sub("REPLACE_ME","https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelQ.png")}
    if(c==3){sub("REPLACE_ME","https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelW.png")}
    if(c==4){sub("REPLACE_ME","https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelE.png")}
    if(c==5){sub("REPLACE_ME","https://ddragon.leagueoflegends.com/cdn/16.16.1/img/spell/MelR.png")}
}1' app/src/main/assets/champions.json > tmp.json && mv tmp.json app/src/main/assets/champions.json
