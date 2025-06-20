import nodes.TrieNode;
import exceptions.TreeEmptyException;
import exceptions.TreeNodeNotFoundException;
import java.util.*;

/**
 * Trie (Prefix Tree) Implementation - Efficient String Storage and Retrieval
 * 
 * A Trie is a tree-like data structure that stores strings in a way that allows
 * for extremely fast prefix-based searches. Each node represents a character,
 * and paths from root to nodes represent prefixes or complete words.
 * 
 * Key Features:
 * - Fast string insertion, search, and deletion: O(m) where m is string length
 * - Efficient prefix matching and autocomplete functionality
 * - Space-efficient storage of strings with common prefixes
 * - Support for word frequency tracking
 * - Comprehensive prefix-based operations
 * 
 * Applications:
 * - Autocomplete systems (search engines, IDEs)
 * - Spell checkers and word suggestion systems
 * - IP routing tables in networking
 * - Word games (Boggle, Scrabble word validation)
 * - Dictionary implementations
 * - DNA sequence analysis
 * 
 * Advantages over other data structures:
 * - Hash Tables: Better for prefix operations, no hash collisions
 * - Binary Search Trees: Better for string operations, prefix matching
 * - Arrays: Much more space-efficient for large dictionaries
 * 
 * Time Complexities:
 * - Insert: O(m) where m is the length of the string
 * - Search: O(m) where m is the length of the string
 * - Delete: O(m) where m is the length of the string
 * - Prefix search: O(p) where p is the length of the prefix
 * - Autocomplete: O(p + k) where p is prefix length, k is number of results
 * 
 * Space Complexity: O(ALPHABET_SIZE * N * M) in worst case
 * - ALPHABET_SIZE: typically 26 for lowercase English
 * - N: number of strings stored
 * - M: average length of strings
 * 
 * @author DSA Learning Project
 * @version 1.0
 * @since 2024
 */
public class Trie {
    
    // Instance variables
    private TrieNode root;
    private int wordCount;          // Total number of words stored
    private int nodeCount;          // Total number of nodes in trie
    private int maxDepth;           // Maximum depth (longest word length)
    private Set<String> allWords;   // Cache of all words for efficient operations
    
    /**
     * Default constructor that creates an empty Trie.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public Trie() {
        this.root = new TrieNode();
        this.wordCount = 0;
        this.nodeCount = 1; // Root node
        this.maxDepth = 0;
        this.allWords = new HashSet<>();
    }
    
    /**
     * Constructor that creates a Trie and inserts a list of words.
     * 
     * @param words list of words to insert initially
     * @throws IllegalArgumentException if words list is null
     * 
     * Time Complexity: O(N * M) where N is number of words, M is average length
     * Space Complexity: O(N * M) for storing the words
     */
    public Trie(List<String> words) {
        this();
        if (words == null) {
            throw new IllegalArgumentException("Words list cannot be null");
        }
        
        for (String word : words) {
            insert(word);
        }
    }
    
    // ==================== CORE TRIE OPERATIONS ====================
    
    /**
     * Inserts a word into the Trie.
     * Creates nodes as necessary along the path for each character.
     * 
     * @param word the word to insert (must be non-null, lowercase letters only)
     * @throws IllegalArgumentException if word is null or contains invalid characters
     * 
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(m) in worst case (if word shares no common prefix)
     * 
     * @example
     * Trie trie = new Trie();
     * trie.insert("hello");
     * trie.insert("help");
     * // Creates shared path for "hel" with branches for "lo" and "p"
     */
    public void insert(String word) {
        validateWord(word);
        
        if (allWords.contains(word)) {
            System.out.println("⚠️ Word '" + word + "' already exists in Trie");
            return;
        }
        
        TrieNode current = root;
        int depth = 0;
        
        // Traverse/create path for each character
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch)) {
                current.setChild(ch, new TrieNode(ch, current));
                nodeCount++;
            }
            current = current.getChild(ch);
            current.incrementWordCount();
            depth++;
        }
        
        // Mark end of word
        current.markAsEndOfWord(word);
        wordCount++;
        maxDepth = Math.max(maxDepth, depth);
        allWords.add(word);
        
        System.out.println("✅ Inserted '" + word + "' into Trie. Total words: " + wordCount);
    }
    
    /**
     * Searches for a complete word in the Trie.
     * 
     * @param word the word to search for
     * @return true if the word exists as a complete word, false otherwise
     * @throws IllegalArgumentException if word is null or invalid
     * 
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(1)
     */
    public boolean search(String word) {
        validateWord(word);
        
        TrieNode node = findNode(word);
        boolean found = (node != null && node.isEndOfWord);
        
        System.out.println(found ? 
            "✅ Found word '" + word + "' in Trie" : 
            "❌ Word '" + word + "' not found in Trie");
        
        return found;
    }
    
    /**
     * Checks if any word in the Trie starts with the given prefix.
     * 
     * @param prefix the prefix to search for
     * @return true if at least one word starts with the prefix, false otherwise
     * @throws IllegalArgumentException if prefix is null or invalid
     * 
     * Time Complexity: O(p) where p is the length of the prefix
     * Space Complexity: O(1)
     */
    public boolean startsWith(String prefix) {
        validateWord(prefix);
        
        TrieNode node = findNode(prefix);
        boolean hasPrefix = (node != null);
        
        System.out.println(hasPrefix ? 
            "✅ Found prefix '" + prefix + "' in Trie" : 
            "❌ No words start with '" + prefix + "'");
        
        return hasPrefix;
    }
    
    /**
     * Deletes a word from the Trie.
     * Removes nodes that are no longer needed after deletion.
     * 
     * @param word the word to delete
     * @return true if word was deleted, false if word wasn't found
     * @throws IllegalArgumentException if word is null or invalid
     * 
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(1)
     */
    public boolean delete(String word) {
        validateWord(word);
        
        if (!search(word)) {
            System.out.println("❌ Cannot delete '" + word + "' - not found in Trie");
            return false;
        }
        
        TrieNode current = findNode(word);
        if (current == null || !current.isEndOfWord) {
            return false;
        }
        
        // Unmark as end of word
        current.unmarkAsEndOfWord();
        wordCount--;
        allWords.remove(word);
        
        // Clean up unnecessary nodes
        cleanupAfterDeletion(current, word);
        
        System.out.println("✅ Deleted '" + word + "' from Trie. Total words: " + wordCount);
        return true;
    }
    
    /**
     * Helper method to clean up nodes after word deletion.
     * Removes nodes that are no longer part of any word path.
     */
    private void cleanupAfterDeletion(TrieNode node, String word) {
        TrieNode current = node;
        int charIndex = word.length() - 1;
        
        // Traverse backwards and remove unnecessary nodes
        while (current != null && current != root && !current.isEndOfWord && !current.hasChildren()) {
            TrieNode parent = current.parent;
            if (parent != null) {
                parent.removeChild(word.charAt(charIndex));
                nodeCount--;
            }
            current = parent;
            charIndex--;
        }
    }
    
    // ==================== PREFIX AND AUTOCOMPLETE OPERATIONS ====================
    
    /**
     * Gets all words in the Trie that start with the given prefix.
     * 
     * @param prefix the prefix to search for
     * @return list of all words starting with the prefix
     * @throws IllegalArgumentException if prefix is null or invalid
     * 
     * Time Complexity: O(p + k) where p is prefix length, k is number of results
     * Space Complexity: O(k) for storing results
     */
    public List<String> getWordsWithPrefix(String prefix) {
        validateWord(prefix);
        
        List<String> results = new ArrayList<>();
        TrieNode prefixNode = findNode(prefix);
        
        if (prefixNode == null) {
            System.out.println("📋 No words found with prefix '" + prefix + "'");
            return results;
        }
        
        // If prefix itself is a word, include it
        if (prefixNode.isEndOfWord) {
            results.add(prefix);
        }
        
        // Find all words in the subtree
        collectWordsFromNode(prefixNode, prefix, results);
        
        System.out.println("📋 Found " + results.size() + " words with prefix '" + prefix + "': " + results);
        return results;
    }
    
    /**
     * Gets autocomplete suggestions for a given prefix.
     * Limited to a maximum number of suggestions.
     * 
     * @param prefix the prefix to autocomplete
     * @param maxSuggestions maximum number of suggestions to return
     * @return list of autocomplete suggestions
     * @throws IllegalArgumentException if prefix is invalid or maxSuggestions < 0
     * 
     * Time Complexity: O(p + min(k, maxSuggestions)) where p is prefix length
     * Space Complexity: O(min(k, maxSuggestions)) for storing results
     */
    public List<String> autocomplete(String prefix, int maxSuggestions) {
        validateWord(prefix);
        if (maxSuggestions < 0) {
            throw new IllegalArgumentException("Max suggestions cannot be negative");
        }
        
        List<String> allMatches = getWordsWithPrefix(prefix);
        List<String> suggestions = new ArrayList<>();
        
        for (int i = 0; i < Math.min(allMatches.size(), maxSuggestions); i++) {
            suggestions.add(allMatches.get(i));
        }
        
        System.out.println("🔍 Autocomplete for '" + prefix + "': " + suggestions);
        return suggestions;
    }
    
    /**
     * Recursive helper method to collect all words from a given node.
     */
    private void collectWordsFromNode(TrieNode node, String currentPrefix, List<String> results) {
        if (node == null) return;
        
        // Check all children
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (node.hasChild(ch)) {
                TrieNode child = node.getChild(ch);
                String newPrefix = currentPrefix + ch;
                
                if (child.isEndOfWord) {
                    results.add(newPrefix);
                }
                
                collectWordsFromNode(child, newPrefix, results);
            }
        }
    }
    
    /**
     * Gets the longest common prefix of all words in the Trie.
     * 
     * @return the longest common prefix, or empty string if no common prefix
     * @throws TreeEmptyException if Trie is empty
     * 
     * Time Complexity: O(L) where L is the length of the longest common prefix
     * Space Complexity: O(L) for building the prefix string
     */
    public String getLongestCommonPrefix() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot find common prefix in empty Trie");
        }
        
        StringBuilder prefix = new StringBuilder();
        TrieNode current = root;
        
        // Traverse down while there's exactly one child and not end of word
        while (current.getChildCount() == 1 && !current.isEndOfWord) {
            char nextChar = current.getChildCharacters().get(0);
            prefix.append(nextChar);
            current = current.getChild(nextChar);
        }
        
        String result = prefix.toString();
        System.out.println("📏 Longest common prefix: '" + result + "'");
        return result;
    }
    
    // ==================== UTILITY AND ANALYSIS METHODS ====================
    
    /**
     * Gets all words stored in the Trie.
     * 
     * @return list of all words in the Trie
     * @throws TreeEmptyException if Trie is empty
     * 
     * Time Complexity: O(N) where N is the number of words
     * Space Complexity: O(N) for the result list
     */
    public List<String> getAllWords() {
        if (isEmpty()) {
            throw new TreeEmptyException("Cannot get words from empty Trie");
        }
        
        List<String> words = new ArrayList<>(allWords);
        Collections.sort(words); // Return in alphabetical order
        
        System.out.println("📋 All words in Trie (" + words.size() + "): " + words);
        return words;
    }
    
    /**
     * Counts the number of words with the given prefix.
     * 
     * @param prefix the prefix to count words for
     * @return number of words starting with the prefix
     * @throws IllegalArgumentException if prefix is invalid
     * 
     * Time Complexity: O(p + k) where p is prefix length, k is number of matches
     * Space Complexity: O(1)
     */
    public int countWordsWithPrefix(String prefix) {
        validateWord(prefix);
        return getWordsWithPrefix(prefix).size();
    }
    
    /**
     * Gets the word frequency (how many times a word was inserted).
     * Currently returns 1 if word exists, 0 if not (can be extended for frequency tracking).
     * 
     * @param word the word to check frequency for
     * @return frequency of the word (1 if exists, 0 if not)
     * @throws IllegalArgumentException if word is invalid
     * 
     * Time Complexity: O(m) where m is the length of the word
     * Space Complexity: O(1)
     */
    public int getWordFrequency(String word) {
        validateWord(word);
        return search(word) ? 1 : 0;
    }
    
    /**
     * Finds the node corresponding to a given string (word or prefix).
     * 
     * @param str the string to find the node for
     * @return the node representing the end of the string, or null if not found
     */
    private TrieNode findNode(String str) {
        TrieNode current = root;
        
        for (char ch : str.toCharArray()) {
            if (!current.hasChild(ch)) {
                return null;
            }
            current = current.getChild(ch);
        }
        
        return current;
    }
    
    /**
     * Validates that a word contains only lowercase letters.
     * 
     * @param word the word to validate
     * @throws IllegalArgumentException if word is null or contains invalid characters
     */
    private void validateWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        
        if (word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }
        
        for (char ch : word.toCharArray()) {
            if (!TrieNode.isValidCharacter(ch)) {
                throw new IllegalArgumentException("Word must contain only lowercase letters (a-z), found: " + ch);
            }
        }
    }
    
    // ==================== TRIE STATISTICS AND INFO ====================
    
    /**
     * Returns the number of words stored in the Trie.
     * 
     * @return the number of words
     */
    public int getWordCount() {
        return wordCount;
    }
    
    /**
     * Returns the total number of nodes in the Trie.
     * 
     * @return the number of nodes
     */
    public int getNodeCount() {
        return nodeCount;
    }
    
    /**
     * Returns the maximum depth of the Trie (length of longest word).
     * 
     * @return the maximum depth
     */
    public int getMaxDepth() {
        return maxDepth;
    }
    
    /**
     * Checks if the Trie is empty.
     * 
     * @return true if Trie contains no words, false otherwise
     */
    public boolean isEmpty() {
        return wordCount == 0;
    }
    
    /**
     * Calculates the memory efficiency of the Trie.
     * Returns the ratio of actual nodes to theoretical maximum nodes.
     * 
     * @return memory efficiency ratio (0.0 to 1.0)
     */
    public double getMemoryEfficiency() {
        if (wordCount == 0) return 1.0;
        
        int totalCharacters = allWords.stream().mapToInt(String::length).sum();
        return (double) nodeCount / (totalCharacters + 1); // +1 for root
    }
    
    /**
     * Clears the entire Trie, removing all words.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        this.root = new TrieNode();
        this.wordCount = 0;
        this.nodeCount = 1;
        this.maxDepth = 0;
        this.allWords.clear();
        
        System.out.println("🗑️ Trie cleared. All words removed.");
    }
    
    // ==================== DISPLAY AND VISUALIZATION ====================
    
    /**
     * Prints the structure of the Trie in a tree-like format.
     * Shows all nodes and their relationships.
     */
    public void printTrieStructure() {
        if (isEmpty()) {
            System.out.println("🌳 Trie is empty");
            return;
        }
        
        System.out.println("🌳 Trie Structure:");
        System.out.println("ROOT");
        printTrieRecursive(root, "", true);
        
        System.out.println("\n📊 Trie Statistics:");
        System.out.println("   Words: " + wordCount);
        System.out.println("   Nodes: " + nodeCount);
        System.out.println("   Max Depth: " + maxDepth);
        System.out.println("   Memory Efficiency: " + String.format("%.2f%%", getMemoryEfficiency() * 100));
    }
    
    /**
     * Recursive helper method for printing Trie structure.
     */
    private void printTrieRecursive(TrieNode node, String prefix, boolean isLast) {
        if (node == null) return;
        
        List<Character> children = node.getChildCharacters();
        for (int i = 0; i < children.size(); i++) {
            char ch = children.get(i);
            TrieNode child = node.getChild(ch);
            boolean isLastChild = (i == children.size() - 1);
            
            System.out.print(prefix + (isLastChild ? "└── " : "├── "));
            System.out.print(ch);
            if (child.isEndOfWord) {
                System.out.print(" *");
            }
            System.out.println(" (count: " + child.wordCount + ")");
            
            String newPrefix = prefix + (isLastChild ? "    " : "│   ");
            printTrieRecursive(child, newPrefix, isLastChild);
        }
    }
    
    /**
     * Returns a string representation of the Trie.
     */
    @Override
    public String toString() {
        return "Trie{words=" + wordCount + ", nodes=" + nodeCount + 
               ", maxDepth=" + maxDepth + ", efficiency=" + 
               String.format("%.2f%%", getMemoryEfficiency() * 100) + "}";
    }
    
    // ==================== INTERACTIVE MAIN METHOD ====================
    
    /**
     * Interactive main method for testing and demonstrating Trie operations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Trie trie = new Trie();
        
        System.out.println("🌳 Trie (Prefix Tree) Interactive Demo");
        System.out.println("======================================");
        System.out.println("Efficient string storage and prefix-based operations!");
        
        while (true) {
            System.out.println("\n📋 Choose an operation:");
            System.out.println("1.  Insert Word");
            System.out.println("2.  Search Word");
            System.out.println("3.  Delete Word");
            System.out.println("4.  Check Prefix");
            System.out.println("5.  Get Words with Prefix");
            System.out.println("6.  Autocomplete");
            System.out.println("7.  Get All Words");
            System.out.println("8.  Count Words with Prefix");
            System.out.println("9.  Longest Common Prefix");
            System.out.println("10. Print Trie Structure");
            System.out.println("11. Get Word Count");
            System.out.println("12. Get Node Count");
            System.out.println("13. Get Memory Efficiency");
            System.out.println("14. Clear Trie");
            System.out.println("15. Demo with Sample Words");
            System.out.println("16. Exit");
            System.out.print("👉 Enter your choice (1-16): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter word to insert: ");
                        String insertWord = scanner.nextLine().toLowerCase().trim();
                        trie.insert(insertWord);
                        break;
                        
                    case 2:
                        System.out.print("Enter word to search: ");
                        String searchWord = scanner.nextLine().toLowerCase().trim();
                        trie.search(searchWord);
                        break;
                        
                    case 3:
                        System.out.print("Enter word to delete: ");
                        String deleteWord = scanner.nextLine().toLowerCase().trim();
                        trie.delete(deleteWord);
                        break;
                        
                    case 4:
                        System.out.print("Enter prefix to check: ");
                        String prefix = scanner.nextLine().toLowerCase().trim();
                        trie.startsWith(prefix);
                        break;
                        
                    case 5:
                        System.out.print("Enter prefix: ");
                        String wordsPrefix = scanner.nextLine().toLowerCase().trim();
                        trie.getWordsWithPrefix(wordsPrefix);
                        break;
                        
                    case 6:
                        System.out.print("Enter prefix for autocomplete: ");
                        String autoPrefix = scanner.nextLine().toLowerCase().trim();
                        System.out.print("Enter max suggestions (default 5): ");
                        String maxStr = scanner.nextLine().trim();
                        int maxSuggestions = maxStr.isEmpty() ? 5 : Integer.parseInt(maxStr);
                        trie.autocomplete(autoPrefix, maxSuggestions);
                        break;
                        
                    case 7:
                        trie.getAllWords();
                        break;
                        
                    case 8:
                        System.out.print("Enter prefix to count: ");
                        String countPrefix = scanner.nextLine().toLowerCase().trim();
                        int count = trie.countWordsWithPrefix(countPrefix);
                        System.out.println("📊 Words with prefix '" + countPrefix + "': " + count);
                        break;
                        
                    case 9:
                        System.out.println("📏 Longest common prefix: '" + trie.getLongestCommonPrefix() + "'");
                        break;
                        
                    case 10:
                        trie.printTrieStructure();
                        break;
                        
                    case 11:
                        System.out.println("📊 Total words: " + trie.getWordCount());
                        break;
                        
                    case 12:
                        System.out.println("📊 Total nodes: " + trie.getNodeCount());
                        break;
                        
                    case 13:
                        System.out.println("📊 Memory efficiency: " + 
                                         String.format("%.2f%%", trie.getMemoryEfficiency() * 100));
                        break;
                        
                    case 14:
                        trie.clear();
                        break;
                        
                    case 15:
                        System.out.println("🎬 Loading sample words...");
                        String[] sampleWords = {"cat", "car", "card", "care", "careful", "cats", "dog", "dodge", "door", "doors"};
                        for (String word : sampleWords) {
                            trie.insert(word);
                        }
                        System.out.println("✅ Sample words loaded. Try prefix searches like 'ca', 'car', 'do'!");
                        break;
                        
                    case 16:
                        System.out.println("👋 Thank you for using Trie Demo!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("❌ Invalid choice. Please enter 1-16.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
} 