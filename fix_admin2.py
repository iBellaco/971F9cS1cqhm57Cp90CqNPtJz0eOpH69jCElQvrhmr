import sys

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "r") as f:
    lines = f.readlines()

# find line 1064
target_idx = -1
for i in range(1000, 1100):
    if "Row(" in lines[i] and "modifier = Modifier.fillMaxWidth()," in lines[i+1] and "horizontalArrangement = Arrangement.SpaceBetween," in lines[i+2]:
        target_idx = i
        break

if target_idx != -1:
    lines[target_idx] = "            Column(modifier = Modifier.fillMaxWidth()) {\n" + lines[target_idx]
    
    # find hardware section
    end_idx = -1
    for i in range(target_idx, 1500):
        if "// --- HARDWARE & DEVICES SECTION ---" in lines[i]:
            end_idx = i
            break
            
    if end_idx != -1:
        lines.insert(end_idx, "            } // close Row\n")
        
        # Now we need to close the Column we opened at the very end of the Box.
        # Find UID footer
        footer_idx = -1
        for i in range(end_idx, 1500):
            if "// UID footer" in lines[i]:
                footer_idx = i
                break
                
        if footer_idx != -1:
            # find the closing brace of the Box
            for i in range(footer_idx, 1500):
                if "// --- DYNAMIC RUNIC GOLDEN BORDER" in lines[i]:
                    # insert before the two closing braces?
                    # The original had:
                    #            }
                    #        }
                    #        // --- DYNAMIC RUNIC GOLDEN BORDER & CORNER ENGRAVINGS OVERLAY ---
                    # We need an extra `}` for the Column.
                    lines.insert(i-2, "            } // close Column\n")
                    break

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "w") as f:
    f.writelines(lines)
