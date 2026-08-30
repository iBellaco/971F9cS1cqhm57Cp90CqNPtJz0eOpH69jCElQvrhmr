const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', 'utf8');

const newPoros = `
        AvatarItem(
            id = "poro_astronauta",
            name = "Poro Astronauta",
            title = "Explorador Espacial",
            region = "Variados",
            rarity = "Común",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/751.png",
            borderHex = "#A0A0A0",
            description = "Un pequeño poro listo para explorar las estrellas."
        ),
        AvatarItem(
            id = "poro_maquina_guerra",
            name = "Poro Máquina de Guerra",
            title = "Poro Mecanizado",
            region = "Variados",
            rarity = "Común",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/752.png",
            borderHex = "#A0A0A0",
            description = "Tecnología implacable en un paquete adorable."
        ),
        AvatarItem(
            id = "poro_matadragones",
            name = "Poro Matadragones",
            title = "Cazador Escamado",
            region = "Variados",
            rarity = "Común",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/772.png",
            borderHex = "#A0A0A0",
            description = "Equipado para cazar a las bestias más temibles."
        ),
        AvatarItem(
            id = "poro_caballero",
            name = "Poro Caballero",
            title = "Elegancia Poro",
            region = "Variados",
            rarity = "Común",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/773.png",
            borderHex = "#A0A0A0",
            description = "Un poro con gustos refinados."
        ),
        AvatarItem(
            id = "poro_proyecto",
            name = "Poro Proyecto",
            title = "Mejora Cibernética",
            region = "Variados",
            rarity = "Común",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/1301.png",
            borderHex = "#A0A0A0",
            description = "La perfección de la evolución cibernética."
        ),
        AvatarItem(
            id = "poro_recreativa",
            name = "Poro Recreativa",
            title = "Jugador 1",
            region = "Variados",
            rarity = "Común",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/1302.png",
            borderHex = "#A0A0A0",
            description = "Un poro de 8 bits listo para la acción."
        ),`;

// Insert new poros after the default poro (or anywhere in the list).
code = code.replace(/val avatars = listOf\(/, "val avatars = listOf(" + newPoros);

fs.writeFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', code);
