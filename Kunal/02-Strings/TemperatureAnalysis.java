import java.util.*;

public class TemperatureAnalysis {
    
    public static void main(String[] args) {
        // Sample input data
        String[] temperatureData = {
            "21 July 30",
            "23 July 29", 
            "22 July 40"
        };
        
        System.out.println("Temperature Data Analysis:");
        System.out.println("Data: " + Arrays.toString(temperatureData));
        System.out.println();
        
        System.out.println("1. Average Temperature: " + findAverageTemperature(temperatureData));
        System.out.println("2. Coldest Day: " + findColdestDay(temperatureData));
        System.out.println("3. Hottest Day: " + findHottestDay(temperatureData));
    }
    
    // Method 1: Find average temperature and return as string
    public static String findAverageTemperature(String[] data) {
        if (data == null || data.length == 0) {
            return "0.0";
        }
        
        double sum = 0;
        int count = 0;
        
        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    double temp = Double.parseDouble(parts[2]);
                    sum += temp;
                    count++;
                }
            } catch (NumberFormatException e) {
                // Skip invalid entries
                continue;
            }
        }
        
        if (count == 0) return "0.0";
        
        double average = sum / count;
        return String.format("%.1f", average);
    }
    
    // Method 2: Find coldest day and return as string
    public static String findColdestDay(String[] data) {
        if (data == null || data.length == 0) {
            return "No data available";
        }
        
        String coldestDay = "";
        double minTemp = Double.MAX_VALUE;
        
        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    double temp = Double.parseDouble(parts[2]);
                    if (temp < minTemp) {
                        minTemp = temp;
                        coldestDay = parts[0] + " " + parts[1]; // date and month
                    }
                }
            } catch (NumberFormatException e) {
                // Skip invalid entries
                continue;
            }
        }
        
        return coldestDay.isEmpty() ? "No valid data" : coldestDay;
    }
    
    // Method 3: Find hottest day and return as string
    public static String findHottestDay(String[] data) {
        if (data == null || data.length == 0) {
            return "No data available";
        }
        
        String hottestDay = "";
        double maxTemp = Double.MIN_VALUE;
        
        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    double temp = Double.parseDouble(parts[2]);
                    if (temp > maxTemp) {
                        maxTemp = temp;
                        hottestDay = parts[0] + " " + parts[1]; // date and month
                    }
                }
            } catch (NumberFormatException e) {
                // Skip invalid entries
                continue;
            }
        }
        
        return hottestDay.isEmpty() ? "No valid data" : hottestDay;
    }
    
    // Alternative version with better input validation and formatting
    public static class TemperatureRecord {
        private String date;
        private String month;
        private double temperature;
        
        public TemperatureRecord(String date, String month, double temperature) {
            this.date = date;
            this.month = month;
            this.temperature = temperature;
        }
        
        public String getDateString() {
            return date + " " + month;
        }
        
        public double getTemperature() {
            return temperature;
        }
        
        @Override
        public String toString() {
            return date + " " + month + " " + temperature;
        }
    }
    
    // Enhanced methods using TemperatureRecord class
    public static List<TemperatureRecord> parseData(String[] data) {
        List<TemperatureRecord> records = new ArrayList<>();
        
        for (String entry : data) {
            try {
                String[] parts = entry.trim().split("\\s+");
                if (parts.length >= 3) {
                    String date = parts[0];
                    String month = parts[1];
                    double temp = Double.parseDouble(parts[2]);
                    records.add(new TemperatureRecord(date, month, temp));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry skipped: " + entry);
            }
        }
        
        return records;
    }
    
    public static String findAverageTemperatureEnhanced(String[] data) {
        List<TemperatureRecord> records = parseData(data);
        
        if (records.isEmpty()) {
            return "0.0";
        }
        
        double sum = records.stream()
                           .mapToDouble(TemperatureRecord::getTemperature)
                           .sum();
        
        double average = sum / records.size();
        return String.format("%.1f", average);
    }
    
    public static String findColdestDayEnhanced(String[] data) {
        List<TemperatureRecord> records = parseData(data);
        
        return records.stream()
                     .min(Comparator.comparing(TemperatureRecord::getTemperature))
                     .map(TemperatureRecord::getDateString)
                     .orElse("No valid data");
    }
    
    public static String findHottestDayEnhanced(String[] data) {
        List<TemperatureRecord> records = parseData(data);
        
        return records.stream()
                     .max(Comparator.comparing(TemperatureRecord::getTemperature))
                     .map(TemperatureRecord::getDateString)
                     .orElse("No valid data");
    }
}