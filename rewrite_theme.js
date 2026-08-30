const fs = require('fs');

const fullCode = fs.readFileSync('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'utf8');

// The new file should have the same imports, but the body will be streamlined.
let newImports = `package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*
import com.example.util.tr`;

// Replace file from "package" to "import com.example.util.tr"
let code = fullCode.substring(fullCode.indexOf('@OptIn(ExperimentalMaterial3Api::class)'));

// Find "Catálogo Completo" section
let cutStart = code.indexOf('Row(\n            modifier = Modifier\n                .fillMaxWidth()\n                .padding(horizontal = 2.dp, vertical = 2.dp),');
let cutEnd = code.indexOf('@Composable\nprivate fun NavBarCustomizationTab');
if (cutEnd === -1) cutEnd = code.length;

let firstPart = code.substring(0, cutStart);
let afterThemeTab = code.substring(code.indexOf('private fun RegionVisualPreviewGridCard('));

let finalCode = newImports + "\n\n" + firstPart + "    }\n}\n\n" + afterThemeTab;

fs.writeFileSync('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt_temp', finalCode);
console.log("Wrote to temp");
