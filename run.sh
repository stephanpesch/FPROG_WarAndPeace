#!/bin/bash

java -jar build/libs/FPROG_WarAndPeace-1.0-SNAPSHOT.jar "${1} ${2} ${3}" > tmp.txt
# Files to compare
file1="in/output.txt"
file2="tmp.txt"

diff -y "$file1" "$file2"

# Calculate the number of different lines
num_diff_lines=$(diff -y --suppress-common-lines "$file1" "$file2" | wc -l)

# Get the total number of lines in the reference file
total_lines_ref=$(wc -l < "$file1")

# Calculate the percentage of different lines
percentage=$(((total_lines_ref - num_diff_lines) * 100 / total_lines_ref))

echo "Percentage of matching lines: $percentage%"
rm tmp.txt