const fs = require('fs');

// 1. Clean root build.gradle.kts from the previous block
let rootBuild = fs.readFileSync('build.gradle.kts', 'utf8');
rootBuild = rootBuild.replace(/val keystoreFile = file\([\s\S]*\}\n\}\n/g, '');
fs.writeFileSync('build.gradle.kts', rootBuild);

// 2. Decode the base64 to github.keystore
const base64 = fs.readFileSync('debug.keystore.base64', 'utf8').replace(/\s/g, '');
const buffer = Buffer.from(base64, 'base64');
fs.writeFileSync('github.keystore', buffer);
console.log('Decoded debug.keystore.base64 to github.keystore');

// 3. Update app/build.gradle.kts to use github.keystore if it exists
let appBuild = fs.readFileSync('app/build.gradle.kts', 'utf8');
appBuild = appBuild.replace(/storeFile = file\("\$\{rootDir\}\/debug\.keystore"\)/g, 'storeFile = if (file("${rootDir}/github.keystore").exists()) file("${rootDir}/github.keystore") else file("${rootDir}/debug.keystore")');
fs.writeFileSync('app/build.gradle.kts', appBuild);
