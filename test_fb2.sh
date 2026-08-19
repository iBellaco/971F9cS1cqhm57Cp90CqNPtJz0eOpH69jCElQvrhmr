#!/bin/bash
logcat -d | grep -i firebase > fb_logs.txt
cat fb_logs.txt | tail -n 20
