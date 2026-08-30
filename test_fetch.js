const fs = require('fs');

async function main() {
    try {
        const url = "https://bestbuildwr.com/builds/143-aatrox-jungle-est-ndar";
        console.log("Fetching", url);
        const res = await fetch(url);
        const html = await res.text();
        console.log("Success fetch", html.substring(0, 100));
    } catch (e) {
        console.error(e);
    }
}

main();
