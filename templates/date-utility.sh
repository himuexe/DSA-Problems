#!/bin/bash

# DSA Date Utility Script
# Generates current dates and revision dates for problem documentation

echo "=== DSA Date Utility ==="
echo "Generating dynamic dates for problem documentation..."
echo

# Get current date
CURRENT_DATE=$(date +"%Y-%m-%d")
echo "Current Date: $CURRENT_DATE"

# Get revision date (7 days from now)
REVISION_DATE=$(date -d "+7 days" +"%Y-%m-%d")
echo "Revision Date (7 days): $REVISION_DATE"

# Get date range for streak calculation
START_DATE="2024-12-19"
DAYS_SINCE_START=$(( ( $(date -d "$CURRENT_DATE" +%s) - $(date -d "$START_DATE" +%s) ) / 86400 ))
echo "Days Since Start: $DAYS_SINCE_START days"

echo
echo "=== Template Replacements ==="
echo "Replace [CURRENT_DATE] with: $CURRENT_DATE"
echo "Replace [CURRENT_DATE + 7] with: $REVISION_DATE"
echo "Replace [DAYS_SINCE_START] with: $DAYS_SINCE_START"

echo
echo "=== Quick Copy Commands ==="
echo "Current Date: $CURRENT_DATE"
echo "Revision Date: $REVISION_DATE"

# Function to update template files with current dates
update_template() {
    local template_file="$1"
    local output_file="$2"
    
    if [ -f "$template_file" ]; then
        sed -e "s/\[CURRENT_DATE\]/$CURRENT_DATE/g" \
            -e "s/\[CURRENT_DATE + 7\]/$REVISION_DATE/g" \
            -e "s/\[DAYS_SINCE_START\]/$DAYS_SINCE_START/g" \
            "$template_file" > "$output_file"
        echo "Updated: $output_file with current dates"
    else
        echo "Template file not found: $template_file"
    fi
}

# Usage example
if [ "$1" = "update" ] && [ -n "$2" ] && [ -n "$3" ]; then
    update_template "$2" "$3"
    echo "Template updated successfully!"
fi

echo
echo "Usage: ./date-utility.sh update <template_file> <output_file>"
echo "Example: ./date-utility.sh update problem-template.md new-problem.md" 