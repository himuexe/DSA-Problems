# Temperature Analysis System

**Date:** 2025-01-19  
**Category:** Kunal Strings  
**Source:** Kunal DSA Course  
**Difficulty:** Medium  
**Topic:** String Processing/Data Analysis

## Problem Statement

Design a system to analyze temperature data given in string format. The system should find the average temperature, identify the coldest day, and identify the hottest day from the given dataset.

**Input Format:** Array of strings like `["21 July 30", "23 July 29", "22 July 40"]`
**Output:** Average temperature, coldest day, and hottest day information.

## Intuition/Approach

**Multi-Method Data Analysis:**
The solution implements a comprehensive temperature analysis system with:

1. **String Parsing:** Extract date, month, and temperature from each entry
2. **Statistical Analysis:** Calculate average, find min/max temperatures
3. **Error Handling:** Skip invalid entries gracefully
4. **Enhanced Design:** Object-oriented approach with TemperatureRecord class

**Two Implementation Styles:**
- **Basic Version:** Simple methods with direct string processing
- **Enhanced Version:** Object-oriented with stream processing

## Algorithm Steps

### Basic Implementation:
1. **Parse Each Entry:** Split by spaces to get [date, month, temperature]
2. **Validate Data:** Use try-catch for number parsing
3. **Calculate Average:** Sum temperatures and divide by count
4. **Find Extremes:** Track min/max temperatures with corresponding dates
5. **Handle Edge Cases:** Empty data, invalid formats

### Enhanced Implementation:
1. **Create TemperatureRecord Objects:** Parse and validate data
2. **Use Streams:** Leverage Java 8+ features for cleaner code
3. **Functional Processing:** Use stream operations for calculations

## Key Observations

- String parsing requires careful handling of different formats
- Exception handling prevents crashes from invalid data
- Object-oriented design improves maintainability
- Stream processing provides cleaner, more readable code
- Proper formatting ensures consistent output

## Time & Space Complexity

- **Time Complexity:** O(n) - process each temperature entry once
- **Space Complexity:** O(n) - store temperature records (enhanced version)

## Edge Cases Considered

- [ ] Empty dataset
- [ ] Invalid number formats
- [ ] Missing date or temperature information
- [ ] Single temperature entry
- [ ] All temperatures are the same
- [ ] Negative temperatures
- [ ] Extra whitespace in input

## Solution Code

### Basic Implementation:
```java
public class TemperatureAnalysis {
    public static String findAverageTemperature(String[] data) {
        if (data == null || data.length == 0) return "0.0";
        
        double sum = 0; int count = 0;
        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    double temp = Double.parseDouble(parts[2]);
                    sum += temp; count++;
                }
            } catch (NumberFormatException e) { continue; }
        }
        
        if (count == 0) return "0.0";
        return String.format("%.1f", sum / count);
    }
    
    public static String findColdestDay(String[] data) {
        if (data == null || data.length == 0) return "No data available";
        
        String coldestDay = ""; double minTemp = Double.MAX_VALUE;
        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    double temp = Double.parseDouble(parts[2]);
                    if (temp < minTemp) {
                        minTemp = temp;
                        coldestDay = parts[0] + " " + parts[1];
                    }
                }
            } catch (NumberFormatException e) { continue; }
        }
        
        return coldestDay.isEmpty() ? "No valid data" : coldestDay;
    }
}
```

### Enhanced Object-Oriented Version:
```java
public static class TemperatureRecord {
    private String date, month;
    private double temperature;
    
    public TemperatureRecord(String date, String month, double temperature) {
        this.date = date; this.month = month; this.temperature = temperature;
    }
    
    public String getDateString() { return date + " " + month; }
    public double getTemperature() { return temperature; }
}

// Stream-based processing
public static String findHottestDayEnhanced(String[] data) {
    List<TemperatureRecord> records = parseData(data);
    
    return records.stream()
                 .max(Comparator.comparing(TemperatureRecord::getTemperature))
                 .map(TemperatureRecord::getDateString)
                 .orElse("No valid data");
}
```

## Alternative Approaches

1. **Database Approach:** Store in database for complex queries
2. **CSV Processing:** Use CSV libraries for structured data
3. **JSON Format:** Use structured JSON for temperature data
4. **Time Series Analysis:** Add date parsing for temporal analysis

## System Design Benefits

- **Extensibility:** Easy to add new analysis methods
- **Maintainability:** Clean separation of concerns
- **Robustness:** Comprehensive error handling
- **Performance:** Efficient single-pass algorithms

## Related Problems

- Weather data analysis
- Stock price analysis
- Log file processing
- CSV data parsing
- Statistical data processing

**Real-World Applications:** Weather systems, IoT sensor data, financial analysis, scientific data processing 