while ps aux | grep -v grep | grep -q "python3 fix_all.py"; do
    sleep 2
done
echo "Finished!"
