with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

# Fix the broken imports in the middle
content = content.replace('''@Composable

import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold

@Composable
fun DashboardScreen(''', '''@Composable
fun DashboardScreen(''')

# Fix the missing Icons import
content = content.replace("import com.example.util.LocalLanguage", "import com.example.util.LocalLanguage\nimport androidx.compose.material.icons.Icons")

# Fix the when exhaustion (we removed AppScreen.META handling in when but it's still in the enum)
content = content.replace("AppScreen.INFO -> {", "AppScreen.META -> {}\n            AppScreen.INFO -> {")

# Clean up trailing braces
content = content.replace('''            }
            }
        }
    }
}''', '''            }
        }
    }
}''')

with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
    f.write(content)
