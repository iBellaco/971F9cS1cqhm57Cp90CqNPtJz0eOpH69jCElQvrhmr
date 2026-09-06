package com.example.data

import com.example.model.AvatarItem

object AvatarCatalog {
    val DEFAULT_AVATAR = AvatarItem(
        id = "default_poro",
        name = "Poro Guardián",
        title = "Espíritu de la Grieta",
        region = "Poro",
        rarity = "Clásico",
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/588.png",
        borderHex = "#C8AA6E",
        description = "El leal compañero de todo Invocador de Wild Rift.",
        isDefault = true
    )

    val avatars: List<AvatarItem> = listOf(
        DEFAULT_AVATAR,
        AvatarItem(
            id = "poro_wukong",
            name = "Poro Wukong",
            title = "El Rey Mono",
            region = "Poro",
            rarity = "Común",
            imageUrl = "https://i.postimg.cc/LXS8Tbv5/1788243501865.jpg",
            borderHex = "#A0A0A0",
            description = "Un poro listo para la batalla con su bastón mágico."
        ),
        AvatarItem(
            id = "poro_volibear",
            name = "Poro Volibear",
            title = "El Rugido del Trueno",
            region = "Poro",
            rarity = "Raro",
            imageUrl = "https://i.postimg.cc/LXV6030W/1788243804693.jpg",
            borderHex = "#00BFFF",
            description = "Un poro imbuido con el poder de la tormenta."
        ),
        AvatarItem(
            id = "poro_nashor",
            name = "Poro Nashor",
            title = "El Rey de la Grieta",
            region = "Poro",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/bJRNF9FF/1788243975279.jpg",
            borderHex = "#8A2BE2",
            description = "El poro más temible del río."
        ),
        AvatarItem(
            id = "poro_ahri",
            name = "Poro Ahri",
            title = "La Mujer Zorro",
            region = "Poro",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/gJKkBHBt/1788244165659.jpg",
            borderHex = "#8A2BE2",
            description = "Un poro con un encanto irresistible."
        ),
        AvatarItem(
            id = "poro_kaisa",
            name = "Poro Kai'Sa",
            title = "Hija del Vacío",
            region = "Poro",
            rarity = "Épico",
            imageUrl = "https://i.postimg.cc/NMxfPkPV/1788244288372.jpg",
            borderHex = "#8A2BE2",
            description = "Un poro que sobrevivió al vacío."
        ),
        AvatarItem(
            id = "poro_irelia",
            name = "Poro Irelia",
            title = "La Danza de las Cuchillas",
            region = "Poro",
            rarity = "Común",
            imageUrl = "https://i.postimg.cc/QtqxnJyG/1788244351208.jpg",
            borderHex = "#A0A0A0",
            description = "Un poro que danza en el campo de batalla."
        ),
        AvatarItem(
            id = "aatrox",
            name = "Aatrox",
            title = "la Espada de los Oscuros",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/dbb1d55dfccc9e9ddb8fa1a7915e3a544332958f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Aatrox"
        ),
        AvatarItem(
            id = "ahri",
            name = "Ahri",
            title = "La Mujer Zorro de nueve Colas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/3a7ecf6f50c441a1bfe93f2a835b070c6c0ac639-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ahri"
        ),
        AvatarItem(
            id = "akali",
            name = "Akali",
            title = "la Asesina Sigilosa",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/80443e3cfae7d2425d05d6e9fec5debf0c8a4f08-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Akali"
        ),
        AvatarItem(
            id = "akshan",
            name = "Akshan",
            title = "el Centinela Rebelde",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/aec46f6adf185797577a47809893ad6e6089a24e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Akshan"
        ),
        AvatarItem(
            id = "alistar",
            name = "Alistar",
            title = "El Minotauro",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/c886fb547e17fef8514bd27c97411d382fa02655-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Alistar"
        ),
        AvatarItem(
            id = "ambessa",
            name = "Ambessa",
            title = "la Matriarca de la Guerra",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/141dbd483d6e489961620962386a034417879430-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ambessa"
        ),
        AvatarItem(
            id = "amumu",
            name = "Amumu",
            title = "La Momia Triste",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/25044199d797ae84edbf5809966fd33b5a9df8d9-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Amumu"
        ),
        AvatarItem(
            id = "annie",
            name = "Annie",
            title = "La Hija de la Oscuridad",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/761e0840a97e1dbbf620579dce12b8ad3aef85fd-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Annie"
        ),
        AvatarItem(
            id = "ashe",
            name = "Ashe",
            title = "La Arquera de Hielo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/418813f2226d924771dd24aadaebe0624f39e5fb-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ashe"
        ),
        AvatarItem(
            id = "aurelion_sol",
            name = "Aurelion Sol",
            title = "El Forjador de Estrellas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/d1ccccc13d3ac4b911e9879c3dedc088c4729210-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Aurelion Sol"
        ),
        AvatarItem(
            id = "aurora",
            name = "Aurora",
            title = "la Bruja entre Mundos",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/d15f85326d86dc268de2ffc2f0a0f4ecd7ba37ba-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Aurora"
        ),
        AvatarItem(
            id = "bard",
            name = "Bardo",
            title = "El Guardián Errante",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/e72bcd4a5b671afa7c07c60d25757c5397b7ad44-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Bardo"
        ),
        AvatarItem(
            id = "blitzcrank",
            name = "Blitzcrank",
            title = "El Gran Gólem de Vapor",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/53aa63cbf9950ad1c45c73c372dc085f5e4167d7-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Blitzcrank"
        ),
        AvatarItem(
            id = "brand",
            name = "Brand",
            title = "La Venganza Ardiente",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/bb7ad76655cc1190dd0dd9a457486f7360e533a3-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Brand"
        ),
        AvatarItem(
            id = "braum",
            name = "Braum",
            title = "El Corazón de Freljord",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/2a483e1895f43d972a483694cf654d334d05e82f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Braum"
        ),
        AvatarItem(
            id = "caitlyn",
            name = "Caitlyn",
            title = "La Sheriff de Piltover",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/e4d985575acb029e19d8a4f02d0f2fe777b13154-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Caitlyn"
        ),
        AvatarItem(
            id = "camille",
            name = "Camille",
            title = "la Sombra de Acero",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/18946b90e4865c3581fa2f0fd54c8d664828f875-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Camille"
        ),
        AvatarItem(
            id = "cho_gath",
            name = "Cho'Gath",
            title = "El Terror del Vacío",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/d532b61341e7297740f08996d458d589d5ab4c91-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Cho'Gath"
        ),
        AvatarItem(
            id = "corki",
            name = "Corki",
            title = "El Bombardero Osado",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/faf41847d99731c5853a08f417bbb9fd87edc4ce-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Corki"
        ),
        AvatarItem(
            id = "darius",
            name = "Darius",
            title = "La Mano de Noxus",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/64d4b577a35cf68bbd13841bb603de28fe14473b-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Darius"
        ),
        AvatarItem(
            id = "diana",
            name = "Diana",
            title = "El Desdén de la Luna",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/925b9cb2ee9c84e26cc4aa55287ca03cb3175c28-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Diana"
        ),
        AvatarItem(
            id = "dr_mundo",
            name = "Dr. Mundo",
            title = "El Loco de Zaun",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/65dd94a5af9279fdd97563d5a0cf86f332fe2afb-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Dr. Mundo"
        ),
        AvatarItem(
            id = "draven",
            name = "Draven",
            title = "El Ejecutor Glorioso",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/0b1f50021fca8e0960f226067d3433d533e72a38-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Draven"
        ),
        AvatarItem(
            id = "ekko",
            name = "Ekko",
            title = "El Chico que Quebró el Tiempo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/d4c506aac1c10418d3f40334ebb0bf747fd150f1-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ekko"
        ),
        AvatarItem(
            id = "evelynn",
            name = "Evelynn",
            title = "El Abrazo Agónico",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/52a3a7705f18a74c1ae56f6224e9742705e5404e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Evelynn"
        ),
        AvatarItem(
            id = "ezreal",
            name = "Ezreal",
            title = "El Explorador Pródigo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/d5193795e3f4c81acb122de52c25243e2ab2f76e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ezreal"
        ),
        AvatarItem(
            id = "fiddlesticks",
            name = "Fiddlesticks",
            title = "el Terror Ancestral",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/106cd3a8f9da82ca3533b908d68bf3f303373c14-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Fiddlesticks"
        ),
        AvatarItem(
            id = "fiora",
            name = "Fiora",
            title = "La Estocada Excelsa",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/2dafcddd5b1dfdf4e127464abf3f84b690bac5ec-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Fiora"
        ),
        AvatarItem(
            id = "fizz",
            name = "Fizz",
            title = "El Gamberro de las Mareas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/9127f3d8365daf1b686d540feee14606304db886-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Fizz"
        ),
        AvatarItem(
            id = "galio",
            name = "Galio",
            title = "el Coloso",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/3ce641181c33067ab130026356bb66b9c72a7de7-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Galio"
        ),
        AvatarItem(
            id = "garen",
            name = "Garen",
            title = "El Poder de Demacia",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/e22e0b14ddfdf70087c47f795ebabfc180055bc7-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Garen"
        ),
        AvatarItem(
            id = "gnar",
            name = "Gnar",
            title = "El Eslabón Perdido",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/2b74cce5203bed8c192ff5c017b01a5231497430-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Gnar"
        ),
        AvatarItem(
            id = "gragas",
            name = "Gragas",
            title = "El Camorrista",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/91eb41547dbcb8e4765559984b8765d8b93cf9c5-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Gragas"
        ),
        AvatarItem(
            id = "graves",
            name = "Graves",
            title = "El Forajido",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/1a9925c47327dd6899c2a180335ca28abb52431e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Graves"
        ),
        AvatarItem(
            id = "gwen",
            name = "Gwen",
            title = "La Costurera Consagrada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/791a08cd7a00bd8875c9aee470289697d55792a6-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Gwen"
        ),
        AvatarItem(
            id = "hecarim",
            name = "Hecarim",
            title = "La Sombra de la Guerra",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/33f7d1c081f03ea787f203f15e0819ba0ef2db3a-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Hecarim"
        ),
        AvatarItem(
            id = "heimerdinger",
            name = "Heimerdinger",
            title = "El Inventor Venerado",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/05d7e6408668133c58b09564f97f40b9af70ec8c-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Heimerdinger"
        ),
        AvatarItem(
            id = "irelia",
            name = "Irelia",
            title = "la Danza de las Cuchillas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/c80c9488be046cd2a0bde8e4791aaf0bc6d2996e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Irelia"
        ),
        AvatarItem(
            id = "janna",
            name = "Janna",
            title = "La Furia de la Tormenta",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/81c7653646f1a6a039675c0765cf541fdf7d5d48-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Janna"
        ),
        AvatarItem(
            id = "jarvan_iv",
            name = "Jarvan IV",
            title = "El Ejemplo de Demacia",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/486df69cef429e122f31d04852a607e622de1184-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Jarvan IV"
        ),
        AvatarItem(
            id = "jax",
            name = "Jax",
            title = "El Maestro de Armas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/fd521c42d56ebd2d3a043e01040fca2fb91cc5b0-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Jax"
        ),
        AvatarItem(
            id = "jayce",
            name = "Jayce",
            title = "El Defensor del Mañana",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/12b847ec19834e8653a7e297618b2ed41047708c-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Jayce"
        ),
        AvatarItem(
            id = "jhin",
            name = "Jhin",
            title = "El Virtuoso",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/c51637d437c6309d4d0f1e7ecfa4052bf33095f4-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Jhin"
        ),
        AvatarItem(
            id = "jinx",
            name = "Jinx",
            title = "La Bala Perdida",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/2ba338ed3f34e991bb7c678170c27fc42c25b2a0-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Jinx"
        ),
        AvatarItem(
            id = "k_sante",
            name = "K'Sante",
            title = "el Orgullo de Nazumah",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/3a7df03714afd695c4558b8aba7986ec797c6efd-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "K'Sante"
        ),
        AvatarItem(
            id = "kai_sa",
            name = "Kai'Sa",
            title = "La Hija del Vacío",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/df1ed639ce9c00d8231f73d22da704227204cf4d-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kai'Sa"
        ),
        AvatarItem(
            id = "kalista",
            name = "Kalista",
            title = "El Espíritu de la Venganza",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/3b9790d6e8580e611e6531f431d3dc321abf0ccc-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kalista"
        ),
        AvatarItem(
            id = "karma",
            name = "Karma",
            title = "La Iluminada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/e3926a6c4a4f6d5bb224005ef6cbc3a29d5fd952-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Karma"
        ),
        AvatarItem(
            id = "kassadin",
            name = "Kassadin",
            title = "El Caminante del Vacío",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/92644f2ada40c4b6a699d621bab44d7f0cd157af-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kassadin"
        ),
        AvatarItem(
            id = "katarina",
            name = "Katarina",
            title = "La Cuchilla Siniestra",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/ef0534c53287ade5eb971270788d0928a704cdc5-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Katarina"
        ),
        AvatarItem(
            id = "kayle",
            name = "Kayle",
            title = "la Justa",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/62d46f0ab099e7f96521a905db1283fa626dc99a-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kayle"
        ),
        AvatarItem(
            id = "kayn",
            name = "Kayn",
            title = "la Guadaña de las Sombras",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/711546f158fc71185f8a6dc701c0dbe3aa9d866e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kayn"
        ),
        AvatarItem(
            id = "kennen",
            name = "Kennen",
            title = "El Corazón de la Tempestad",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/a60e3051d8b0a87287a149fefd565794c3604441-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kennen"
        ),
        AvatarItem(
            id = "kha_zix",
            name = "Kha'Zix",
            title = "El Saqueador del Vacío",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/23399fc627a7f13e21d57cc7e618c859e1203980-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kha'Zix"
        ),
        AvatarItem(
            id = "kindred",
            name = "Kindred",
            title = "Los Cazadores Eternos",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/61da72c43e45e119868f3616293c0ea4c950e44d-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kindred"
        ),
        AvatarItem(
            id = "kog_maw",
            name = "Kog'Maw",
            title = "La Boca del Abismo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/4b94917c3fd10e913256106dcb536aae8f3922e7-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Kog'Maw"
        ),
        AvatarItem(
            id = "lee_sin",
            name = "Lee Sin",
            title = "El Monje Ciego",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/c9f11b9fbdeec59ed58ead8fb232f41bb17d9ac3-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Lee Sin"
        ),
        AvatarItem(
            id = "leona",
            name = "Leona",
            title = "El Amanecer Radiante",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/8a1915cfa8e5454697c0a5914f15cc71830fbf4f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Leona"
        ),
        AvatarItem(
            id = "lillia",
            name = "Lillia",
            title = "el Tímido Florecer",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/2c2411d61886497c29b3874d81ddd1a844a35d79-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Lillia"
        ),
        AvatarItem(
            id = "lissandra",
            name = "Lissandra",
            title = "La Bruja de Hielo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/345aaf7c4860326ca2df1d76e1d66337cb4de472-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Lissandra"
        ),
        AvatarItem(
            id = "lucian",
            name = "Lucian",
            title = "El Destello Purificador",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/ffac1a7d5718cf25d9f57d2cdc099be1951d606a-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Lucian"
        ),
        AvatarItem(
            id = "lulu",
            name = "Lulu",
            title = "El Hada Hechicera",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/037e014fe9e10822a0cc156561bae2b31a3803a1-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Lulu"
        ),
        AvatarItem(
            id = "lux",
            name = "Lux",
            title = "La Dama Luminosa",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/e22157610b3f8a197cbc45d28d1a0d0ff7cb7378-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Lux"
        ),
        AvatarItem(
            id = "malphite",
            name = "Malphite",
            title = "El Fragmento del Monolito",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/4ccb2b985e326ea4d8793f8647797f97739fe22b-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Malphite"
        ),
        AvatarItem(
            id = "maokai",
            name = "Maokai",
            title = "El Treant Retorcido",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/1163376dc1c8bfe91f5b3658b0837e6487a34a0a-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Maokai"
        ),
        AvatarItem(
            id = "master_yi",
            name = "Maestro Yi",
            title = "El Espadachín Wuju",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/8e0e462641e47b86e9e10e8f27f08dbb87dffaf8-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Maestro Yi"
        ),
        AvatarItem(
            id = "mel",
            name = "Mel",
            title = "la Consejera Dorada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/759c5501dda9a39525061225e0024b3742a0f382-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Mel"
        ),
        AvatarItem(
            id = "milio",
            name = "Milio",
            title = "la Llama Serena",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/49db0ecb0ee7c82470b4c468062b056632c548bd-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Milio"
        ),
        AvatarItem(
            id = "miss_fortune",
            name = "Miss Fortune",
            title = "La Cazarrecompensas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/ec2feb5bc10ae2fb645d240bf291f62049380856-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Miss Fortune"
        ),
        AvatarItem(
            id = "mordekaiser",
            name = "Mordekaiser",
            title = "La Pesadilla de Hierro",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/8515e3f6f0955eeab611bb8434556a031a970644-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Mordekaiser"
        ),
        AvatarItem(
            id = "morgana",
            name = "Morgana",
            title = "la Caída",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/eccecc05caf593d9125e54cec2af8418eb891d07-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Morgana"
        ),
        AvatarItem(
            id = "nami",
            name = "Nami",
            title = "La Invocadora de Mareas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/356d557a4c38e27a001e6498ec6cb58a97814739-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nami"
        ),
        AvatarItem(
            id = "nasus",
            name = "Nasus",
            title = "El Guardián de las Arenas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/11c5035096b35403af6d6fc19a4c9e474c6ce658-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nasus"
        ),
        AvatarItem(
            id = "nautilus",
            name = "Nautilus",
            title = "El Titán Abisal",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/b8ba22659d0bcd44b659c63eed609852ce334693-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nautilus"
        ),
        AvatarItem(
            id = "nidalee",
            name = "Nidalee",
            title = "La Cazadora Bestial",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/55aff8863fe623be889c1754518fa21bf5a2ec33-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nidalee"
        ),
        AvatarItem(
            id = "nilah",
            name = "Nilah",
            title = "la Alegría Desatada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/ab18652f86c0d9840b79d9e1eeabdf3e780b9db2-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nilah"
        ),
        AvatarItem(
            id = "nocturne",
            name = "Nocturne",
            title = "La Pesadilla Eterna",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/401c6039075be773174cc07a447fe37ede3f6edb-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nocturne"
        ),
        AvatarItem(
            id = "norra",
            name = "Norra",
            title = "la Maestra de los Portales",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/6a1ba2f0d1178b81cf869741e94e9453ffd3e69f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Norra"
        ),
        AvatarItem(
            id = "nunu_willump",
            name = "Nunu y Willump",
            title = "Un Niño y su Yeti",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/b62121686dbb3602f85a5184f2793e643a764fbb-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Nunu y Willump"
        ),
        AvatarItem(
            id = "olaf",
            name = "Olaf",
            title = "El Berserker",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/c7218ca3fa28cd4b2d8cbafb7f712c44b2c342a3-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Olaf"
        ),
        AvatarItem(
            id = "orianna",
            name = "Orianna",
            title = "La Dama Mecánica",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/1f897c1d72ffd15041c1a014bf4ed244bede5dbe-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Orianna"
        ),
        AvatarItem(
            id = "ornn",
            name = "Ornn",
            title = "Las Llamas de la Forja",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/be99d24fc00bc88a562e1fe0c1a0cfc2eb82a57d-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ornn"
        ),
        AvatarItem(
            id = "pantheon",
            name = "Pantheon",
            title = "la Lanza Inquebrantable",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/269fda31aef7eb1c73466c4690cc08f9e4c04e37-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Pantheon"
        ),
        AvatarItem(
            id = "poppy",
            name = "Poppy",
            title = "La Guardiana del Martillo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/f7d42547737f5b3df01743ae5e99f6f37193a45b-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Poppy"
        ),
        AvatarItem(
            id = "pyke",
            name = "Pyke",
            title = "el Destripador de los Puertos",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/c3f5603716698795f749be1658cbd8296491f7a8-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Pyke"
        ),
        AvatarItem(
            id = "rakan",
            name = "Rakan",
            title = "El Encantador",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/e2505693c2fa65dfa3ac5865f448666683759b5e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Rakan"
        ),
        AvatarItem(
            id = "rammus",
            name = "Rammus",
            title = "El Armadurillo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/2ac50d528c0505db1a35ab5663bb1783c977c72d-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Rammus"
        ),
        AvatarItem(
            id = "rell",
            name = "Rell",
            title = "la Dama de Hierro",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/0b00075ef09190e496e780436963b3dcb2d35b68-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Rell"
        ),
        AvatarItem(
            id = "renekton",
            name = "Renekton",
            title = "El Carnicero de las Arenas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/d71da849a00e19983454e03e157ed91b55afb8e2-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Renekton"
        ),
        AvatarItem(
            id = "rengar",
            name = "Rengar",
            title = "El Acechador Orgulloso",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/ab6887e69775c18bac138dbad45bc427ca111f6e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Rengar"
        ),
        AvatarItem(
            id = "riven",
            name = "Riven",
            title = "La Exiliada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/155ccc38a8bae3bbe20e50878b7c8c038d468b80-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Riven"
        ),
        AvatarItem(
            id = "rumble",
            name = "Rumble",
            title = "La Amenaza Mecánica",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/82ce17611af10fd53bd213ee25a95938b587a421-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Rumble"
        ),
        AvatarItem(
            id = "ryze",
            name = "Ryze",
            title = "El Hechicero Rúnico",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/db6eed107ed96045dd1b7f83087493806b93c349-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ryze"
        ),
        AvatarItem(
            id = "samira",
            name = "Samira",
            title = "la Rosa del Desierto",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/b9ce1da2229255f5dc81bee1b5cecee3ed39e035-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Samira"
        ),
        AvatarItem(
            id = "senna",
            name = "Senna",
            title = "la Redentora",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/61ec8874d20f8d60a2d2a0021af82bad225d10d1-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Senna"
        ),
        AvatarItem(
            id = "seraphine",
            name = "Seraphine",
            title = "la Cantante Soñadora",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/115574cabea3c98bffd4fc47378c8cd4edbb3340-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Seraphine"
        ),
        AvatarItem(
            id = "sett",
            name = "Sett",
            title = "el Jefe",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/1a8806fa26c29d3cf363480f91e7af339a59e0f2-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Sett"
        ),
        AvatarItem(
            id = "shen",
            name = "Shen",
            title = "El Ojo del Crepúsculo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/71e3d7ca7d26158874a567f8a2d9d8bb5c11b1db-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Shen"
        ),
        AvatarItem(
            id = "shyvana",
            name = "Shyvana",
            title = "La Medio Dragón",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/2da9174190af1472ce49266702e17d113ce7697a-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Shyvana"
        ),
        AvatarItem(
            id = "singed",
            name = "Singed",
            title = "El Químico Loco",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/0acabb2aef28c1cdabcfa1cc97a3c55b946fa5aa-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Singed"
        ),
        AvatarItem(
            id = "sion",
            name = "Sion",
            title = "El Coloso no Muerto",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/4307bbb9f9bc476daf3fc9452a929f5200cc9174-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Sion"
        ),
        AvatarItem(
            id = "sivir",
            name = "Sivir",
            title = "La Señora de la Batalla",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/d427ef3a36baeabb16d24e702e0a66a7a89f4db8-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Sivir"
        ),
        AvatarItem(
            id = "skarner",
            name = "Skarner",
            title = "el Soberano Primigenio",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/7829853403818fecfe447864e5359624a379c26d-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Skarner"
        ),
        AvatarItem(
            id = "smolder",
            name = "Smolder",
            title = "el Heredero de las Llamas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/471870cc28273c1a64bc9ace981133492225081c-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Smolder"
        ),
        AvatarItem(
            id = "sona",
            name = "Sona",
            title = "La Virtuosa de las Cuerdas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/30d7e7b516c26e695678bf138cda3d27aceb8dbd-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Sona"
        ),
        AvatarItem(
            id = "soraka",
            name = "Soraka",
            title = "La Hija de las Estrellas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/1cae735dc336de0b27054053760eb5bb69f5296f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Soraka"
        ),
        AvatarItem(
            id = "swain",
            name = "Swain",
            title = "el Gran General de Noxus",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/343896be8966bbdfbaef4bb71fb9a956ea7e813a-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Swain"
        ),
        AvatarItem(
            id = "syndra",
            name = "Syndra",
            title = "La Soberana Oscura",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/afe2af12bc4093a1339e42253c60bb0abf54d5f3-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Syndra"
        ),
        AvatarItem(
            id = "taliyah",
            name = "Taliyah",
            title = "La Tejedora de Piedra",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/18126e948c466734781770a100bbaecba8526702-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Taliyah"
        ),
        AvatarItem(
            id = "talon",
            name = "Talon",
            title = "La Sombra de la Espada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/e35b396a540cd65db0b5f1a86d81662c74be4ddf-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Talon"
        ),
        AvatarItem(
            id = "teemo",
            name = "Teemo",
            title = "El Explorador Veloz",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/1a32e31a330157f441f3b007a28eea49036a7dd3-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Teemo"
        ),
        AvatarItem(
            id = "thresh",
            name = "Thresh",
            title = "El Carcelero Implacable",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/6d56721ba65a6740d8696205c2b0d4b60181330e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Thresh"
        ),
        AvatarItem(
            id = "tristana",
            name = "Tristana",
            title = "La Artillera Yordle",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/17b80232bc951c5feefdfb43eb03abab8f3420fd-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Tristana"
        ),
        AvatarItem(
            id = "tryndamere",
            name = "Tryndamere",
            title = "El Rey Bárbaro",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/24c0c376e074f21b43184372f0aae7fa04defd63-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Tryndamere"
        ),
        AvatarItem(
            id = "twisted_fate",
            name = "Twisted Fate",
            title = "El Maestro de las Cartas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/fc7e88a5c33bcc95df95189b80f32a644bb08208-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Twisted Fate"
        ),
        AvatarItem(
            id = "twitch",
            name = "Twitch",
            title = "La Rata Apestada",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/4657f1ab80489515a2534b9fbe87b93e1a755533-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Twitch"
        ),
        AvatarItem(
            id = "urgot",
            name = "Urgot",
            title = "la Pesadilla Mecánica",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/7b5459e9b96913e08af78bba1323387f02bf712e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Urgot"
        ),
        AvatarItem(
            id = "varus",
            name = "Varus",
            title = "La Flecha del Castigo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/306929e43330e836c567b237f622e8372f2b0fb8-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Varus"
        ),
        AvatarItem(
            id = "vayne",
            name = "Vayne",
            title = "La Cazadora Noctívaga",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/9353f647f42e40569934d15dc5b8d3fe77faaa48-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Vayne"
        ),
        AvatarItem(
            id = "veigar",
            name = "Veigar",
            title = "El Pequeño Maestro del Mal",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/a4070b89b69159d6aef2b2bbc517c7f87ac4e5b7-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Veigar"
        ),
        AvatarItem(
            id = "vel_koz",
            name = "Vel'Koz",
            title = "El Ojo del Vacío",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/7883ed4bc84dadddf1208b55e3c1327870f22e5e-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Vel'Koz"
        ),
        AvatarItem(
            id = "vex",
            name = "Vex",
            title = "la Lúgubre",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/dd071cb9c5d0c57224fe26cd7d303bbb5342e5bb-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Vex"
        ),
        AvatarItem(
            id = "vi",
            name = "Vi",
            title = "La Agente de Piltover",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/a78c07fec8238af98971b5de942e77cb1c600922-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Vi"
        ),
        AvatarItem(
            id = "viego",
            name = "Viego",
            title = "el Rey Arruinado",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/9d87fe0b943dabe1c3a5d0b42fa1b38961d5395b-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Viego"
        ),
        AvatarItem(
            id = "viktor",
            name = "Viktor",
            title = "El Heraldo de las Máquinas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data/a181fcb11c7f7c423b3156f0482398ea7ef24379-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Viktor"
        ),
        AvatarItem(
            id = "vladimir",
            name = "Vladimir",
            title = "El Segador Carmesí",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/5b947e2d2b2c618c669a6d87ac1248d4611c67f2-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Vladimir"
        ),
        AvatarItem(
            id = "volibear",
            name = "Volibear",
            title = "la Tormenta Incesante",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/717020ebeb667da0a0061c55015d175d0d5a88d5-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Volibear"
        ),
        AvatarItem(
            id = "warwick",
            name = "Warwick",
            title = "la Ira Descontrolada de Zaun",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/3877115ff2db8522cc9e9c87ed554c02dcd774c6-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Warwick"
        ),
        AvatarItem(
            id = "wukong",
            name = "Wukong",
            title = "El Rey Mono",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/10d97ab0a3d1852eca0803ebb8ad063fb923c76b-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Wukong"
        ),
        AvatarItem(
            id = "xayah",
            name = "Xayah",
            title = "La Rebelde",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/e215e85898f1f9a52924136d1c6dab0dcf782ed6-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Xayah"
        ),
        AvatarItem(
            id = "xin_zhao",
            name = "Xin Zhao",
            title = "El Senescal de Demacia",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/5335eb9ededf36af430339ae00d178508f163e3c-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Xin Zhao"
        ),
        AvatarItem(
            id = "yasuo",
            name = "Yasuo",
            title = "La Espada sin Honor",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/4bb74e4eced7ff33fff5853da95aa4ae8044e417-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Yasuo"
        ),
        AvatarItem(
            id = "yone",
            name = "Yone",
            title = "el Imperecedero",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/2acc4e225bed2896719dc80233f9dec80b490748-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Yone"
        ),
        AvatarItem(
            id = "yunara",
            name = "Yunara",
            title = "la Tejedora de Penumbra",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/50157a1e1a9597825b3bcfac15b87c0cbac94450-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Yunara"
        ),
        AvatarItem(
            id = "yuumi",
            name = "Yuumi",
            title = "la Gata Mágica",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/97c4e293f961cade702300981a4f2f80e8a844af-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Yuumi"
        ),
        AvatarItem(
            id = "zed",
            name = "Zed",
            title = "El Maestro de las Sombras",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news/5aec4f6c69c30415dfa03c58c3e7bcee36992649-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Zed"
        ),
        AvatarItem(
            id = "zeri",
            name = "Zeri",
            title = "La Chispa de Zaun",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/ded1acb06a88316f32c54be24083209440fa19c2-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Zeri"
        ),
        AvatarItem(
            id = "ziggs",
            name = "Ziggs",
            title = "El Experto en Hexplosivos",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/12dfd0ae9fa0de33d5c617dc69fab205e5a5733f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Ziggs"
        ),
        AvatarItem(
            id = "zilean",
            name = "Zilean",
            title = "El Guardián del Tiempo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/game_data_live/968798e27ba225183db6ca6edfaa92def0acd74e-285x328.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Zilean"
        ),
        AvatarItem(
            id = "zoe",
            name = "Zoe",
            title = "el Aspecto del Crepúsculo",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/2d29638a3813e14c520f8de5d7275caea925c934-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Zoe"
        ),
        AvatarItem(
            id = "zyra",
            name = "Zyra",
            title = "La Dama de Espinas",
            region = "Runaterra",
            rarity = "Clásico",
            imageUrl = "https://cmsassets.rgpub.io/sanity/images/dsfx7636/news_live/cce39159a887f0405d9ed13750b16f8bccdb417f-285x323.jpg?accountingTag=WR",
            borderHex = "#C8AA6E",
            description = "Zyra"
        )
    )

    fun getAvatarById(id: String): AvatarItem {
        return avatars.find { it.id == id } ?: DEFAULT_AVATAR
    }
}
