import fs from 'fs';

const filePath = 'app/src/main/java/com/example/data/WildRiftItemsData.kt';
const data = fs.readFileSync(filePath, 'utf8');

const namesToFind = [
    "Lágrima de la diosa",
    "Chispa fulgurante",
    "Manto de anulación de magia",
    "Cristal de rubí",
    "Armadura de tela",
    "Escamas aluviales",
    "Sudario glacial",
    "Capa negatrón",
    "Armadura lunar alada",
    "Puño de Jaurim",
    "Chaleco de zarzas",
    "Chaleco de cadenas",
    "Malla del guardián",
    "Cinturón de gigante",
    "Gema avivadora",
    "Hábito del espectro",
    "Rescoldo de Bami",
    "Brazalete de la buscadora",
    "Brillo",
    "Catalizador de eones",
    "Barrera frondosa",
    "Fajín de mercurio"
];

for (const name of namesToFind) {
    const regex = new RegExp(`id = "([^"]+)",\\s*name = "${name}"`, "i");
    const match = data.match(regex);
    if (match) {
        console.log(`${name}: ${match[1]}`);
    } else {
        const fallbackRegex = new RegExp(`name = "[^"]*",\\s*nameEn = "${name}"`, "i");
        const fallbackMatch = data.match(fallbackRegex);
        if (fallbackMatch) {
            console.log(`${name} (EN match): found`);
        } else {
            console.log(`${name}: NOT FOUND`);
        }
    }
}
