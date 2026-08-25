with open('app/src/main/java/com/example/ui/components/DownloadProgressWidget.kt', 'r') as f:
    lines = f.readlines()
with open('app/src/main/java/com/example/ui/components/DownloadProgressWidget.kt', 'w') as f:
    for line in lines:
        if line.startswith('CWD: .'): continue
        f.write(line)
