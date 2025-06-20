package nodes;

import java.util.*;

/**
 * TrieNode class representing a single node in a Trie (prefix tree).
 * Specialized for efficient string storage and retrieval operations.
 * 
 * A Trie is a tree-like data structure that stores strings in a way that allows
 * for fast prefix-based searches. Each node represents a character position,
 * and paths from root to nodes represent prefixes or complete words.
 * 
 * Key Features:
 * - Character-based branching (typically 26 for lowercase letters)
 * - End-of-word marking for complete string identification
 * - Efficient prefix matching and autocomplete functionality
 * - Space-efficient storage of strings with common prefixes
 * 
 * Applications:
 * - Autocomplete systems
 * - Spell checkers
 * - IP routing tables
 * - Word games and dictionaries
 * - Search engines
 * 
 * Time Complexities:
 * - Insert: O(m) where m is the length of the string
 * - Search: O(m) where m is the length of the string
 * - Delete: O(m) where m is the length of the string
 * - Prefix search: O(p) where p is the length of the prefix
 * 
 * Space Complexity: O(ALPHABET_SIZE * N * M) where N is number of strings and M is average length
 * 
 * @author DSA Learning Project
 * @version 1.0
 */
public class TrieNode {
    
    /** Standard alphabet size for lowercase English letters */
    public static final int ALPHABET_SIZE = 26;
    
    /** Array of child nodes, indexed by character (a=0, b=1, ..., z=25) */
    public TrieNode[] children;
    
    /** Flag indicating if this node represents the end of a valid word */
    public boolean isEndOfWord;
    
    /** The character this node represents (for debugging and visualization) */
    public char character;
    
    /** Reference to parent node (useful for some operations) */
    public TrieNode parent;
    
    /** Count of words that pass through this node (for frequency tracking) */
    public int wordCount;
    
    /** Count of words that end at this node */
    public int endWordCount;
    
    /** Optional: store the actual word if this is an end node (for easy retrieval) */
    public String word;
    
    /**
     * Default constructor for root node.
     * Creates a TrieNode with no character association (used for root).
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(ALPHABET_SIZE)
     */
    public TrieNode() {
        this.children = new TrieNode[ALPHABET_SIZE];
        this.isEndOfWord = false;
        this.character = '\0'; // Null character for root
        this.parent = null;
        this.wordCount = 0;
        this.endWordCount = 0;
        this.word = null;
    }
    
    /**
     * Constructor for character-specific node.
     * Creates a TrieNode representing a specific character.
     * 
     * @param character the character this node represents
     * @param parent reference to the parent node
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(ALPHABET_SIZE)
     */
    public TrieNode(char character, TrieNode parent) {
        this.children = new TrieNode[ALPHABET_SIZE];
        this.isEndOfWord = false;
        this.character = character;
        this.parent = parent;
        this.wordCount = 0;
        this.endWordCount = 0;
        this.word = null;
    }
    
    /**
     * Constructor with end-of-word specification.
     * Creates a TrieNode with specified end-of-word status.
     * 
     * @param character the character this node represents
     * @param parent reference to the parent node
     * @param isEndOfWord whether this node represents end of a word
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(ALPHABET_SIZE)
     */
    public TrieNode(char character, TrieNode parent, boolean isEndOfWord) {
        this.children = new TrieNode[ALPHABET_SIZE];
        this.isEndOfWord = isEndOfWord;
        this.character = character;
        this.parent = parent;
        this.wordCount = 0;
        this.endWordCount = isEndOfWord ? 1 : 0;
        this.word = null;
    }
    
    // ==================== CHARACTER AND INDEX OPERATIONS ====================
    
    /**
     * Converts a character to array index.
     * Assumes lowercase English letters (a-z).
     * 
     * @param ch the character to convert
     * @return the index (0-25) for the character
     * @throws IllegalArgumentException if character is not lowercase letter
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int charToIndex(char ch) {
        if (ch < 'a' || ch > 'z') {
            throw new IllegalArgumentException("Character must be lowercase letter (a-z), got: " + ch);
        }
        return ch - 'a';
    }
    
    /**
     * Converts an array index to character.
     * 
     * @param index the index (0-25) to convert
     * @return the corresponding character (a-z)
     * @throws IllegalArgumentException if index is out of range
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static char indexToChar(int index) {
        if (index < 0 || index >= ALPHABET_SIZE) {
            throw new IllegalArgumentException("Index must be 0-25, got: " + index);
        }
        return (char) ('a' + index);
    }
    
    /**
     * Checks if a character is valid for this Trie (lowercase letter).
     * 
     * @param ch the character to validate
     * @return true if character is valid, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static boolean isValidCharacter(char ch) {
        return ch >= 'a' && ch <= 'z';
    }
    
    // ==================== CHILD NODE OPERATIONS ====================
    
    /**
     * Gets the child node for a specific character.
     * 
     * @param ch the character to look for
     * @return the child node for the character, or null if doesn't exist
     * @throws IllegalArgumentException if character is invalid
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public TrieNode getChild(char ch) {
        if (!isValidCharacter(ch)) {
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
        return children[charToIndex(ch)];
    }
    
    /**
     * Sets a child node for a specific character.
     * 
     * @param ch the character for the child
     * @param child the child node to set
     * @throws IllegalArgumentException if character is invalid
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void setChild(char ch, TrieNode child) {
        if (!isValidCharacter(ch)) {
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
        children[charToIndex(ch)] = child;
        if (child != null) {
            child.parent = this;
            child.character = ch;
        }
    }
    
    /**
     * Checks if this node has a child for the specified character.
     * 
     * @param ch the character to check
     * @return true if child exists, false otherwise
     * @throws IllegalArgumentException if character is invalid
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean hasChild(char ch) {
        if (!isValidCharacter(ch)) {
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
        return children[charToIndex(ch)] != null;
    }
    
    /**
     * Creates and returns a new child node for the specified character.
     * If child already exists, returns the existing child.
     * 
     * @param ch the character for the new child
     * @return the child node (new or existing)
     * @throws IllegalArgumentException if character is invalid
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public TrieNode createChild(char ch) {
        if (!isValidCharacter(ch)) {
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
        
        int index = charToIndex(ch);
        if (children[index] == null) {
            children[index] = new TrieNode(ch, this);
        }
        return children[index];
    }
    
    /**
     * Removes the child node for the specified character.
     * 
     * @param ch the character whose child to remove
     * @return the removed child node, or null if didn't exist
     * @throws IllegalArgumentException if character is invalid
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public TrieNode removeChild(char ch) {
        if (!isValidCharacter(ch)) {
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
        
        int index = charToIndex(ch);
        TrieNode removedChild = children[index];
        children[index] = null;
        
        if (removedChild != null) {
            removedChild.parent = null;
        }
        
        return removedChild;
    }
    
    // ==================== NODE STATE OPERATIONS ====================
    
    /**
     * Checks if this node has any children.
     * 
     * @return true if node has at least one child, false otherwise
     * 
     * Time Complexity: O(ALPHABET_SIZE)
     * Space Complexity: O(1)
     */
    public boolean hasChildren() {
        for (TrieNode child : children) {
            if (child != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Counts the number of children this node has.
     * 
     * @return the number of non-null children
     * 
     * Time Complexity: O(ALPHABET_SIZE)
     * Space Complexity: O(1)
     */
    public int getChildCount() {
        int count = 0;
        for (TrieNode child : children) {
            if (child != null) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gets a list of all characters that have children from this node.
     * 
     * @return list of characters with children
     * 
     * Time Complexity: O(ALPHABET_SIZE)
     * Space Complexity: O(k) where k is the number of children
     */
    public List<Character> getChildCharacters() {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (children[i] != null) {
                characters.add(indexToChar(i));
            }
        }
        return characters;
    }
    
    /**
     * Gets a list of all non-null child nodes.
     * 
     * @return list of child nodes
     * 
     * Time Complexity: O(ALPHABET_SIZE)
     * Space Complexity: O(k) where k is the number of children
     */
    public List<TrieNode> getChildNodes() {
        List<TrieNode> childNodes = new ArrayList<>();
        for (TrieNode child : children) {
            if (child != null) {
                childNodes.add(child);
            }
        }
        return childNodes;
    }
    
    /**
     * Checks if this node is a leaf (has no children).
     * 
     * @return true if node is a leaf, false otherwise
     * 
     * Time Complexity: O(ALPHABET_SIZE)
     * Space Complexity: O(1)
     */
    public boolean isLeaf() {
        return !hasChildren();
    }
    
    /**
     * Checks if this node is the root (has no parent).
     * 
     * @return true if node is root, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isRoot() {
        return parent == null;
    }
    
    // ==================== WORD OPERATIONS ====================
    
    /**
     * Marks this node as the end of a word.
     * Optionally stores the complete word for easy retrieval.
     * 
     * @param word the complete word ending at this node (optional)
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1) or O(m) if storing word
     */
    public void markAsEndOfWord(String word) {
        if (!this.isEndOfWord) {
            this.endWordCount++;
        }
        this.isEndOfWord = true;
        this.word = word;
    }
    
    /**
     * Marks this node as the end of a word without storing the word.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void markAsEndOfWord() {
        if (!this.isEndOfWord) {
            this.endWordCount++;
        }
        this.isEndOfWord = true;
    }
    
    /**
     * Unmarks this node as the end of a word.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void unmarkAsEndOfWord() {
        if (this.isEndOfWord) {
            this.endWordCount--;
        }
        this.isEndOfWord = false;
        this.word = null;
    }
    
    /**
     * Increments the word count (number of words passing through this node).
     * Used for frequency tracking.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void incrementWordCount() {
        this.wordCount++;
    }
    
    /**
     * Decrements the word count.
     * Used when removing words from the trie.
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void decrementWordCount() {
        if (this.wordCount > 0) {
            this.wordCount--;
        }
    }
    
    // ==================== PATH OPERATIONS ====================
    
    /**
     * Gets the path from root to this node as a string.
     * Reconstructs the prefix represented by this node.
     * 
     * @return the string path from root to this node
     * 
     * Time Complexity: O(d) where d is the depth of the node
     * Space Complexity: O(d) for the string construction
     */
    public String getPathFromRoot() {
        if (isRoot()) {
            return "";
        }
        
        StringBuilder path = new StringBuilder();
        TrieNode current = this;
        
        // Build path backwards
        while (current != null && !current.isRoot()) {
            path.append(current.character);
            current = current.parent;
        }
        
        // Reverse to get correct order
        return path.reverse().toString();
    }
    
    /**
     * Gets the depth of this node (distance from root).
     * 
     * @return the depth of this node
     * 
     * Time Complexity: O(d) where d is the depth
     * Space Complexity: O(1)
     */
    public int getDepth() {
        int depth = 0;
        TrieNode current = this.parent;
        while (current != null) {
            depth++;
            current = current.parent;
        }
        return depth;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Clears all children of this node.
     * Useful for removing subtrees.
     * 
     * Time Complexity: O(ALPHABET_SIZE)
     * Space Complexity: O(1)
     */
    public void clearChildren() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (children[i] != null) {
                children[i].parent = null;
                children[i] = null;
            }
        }
    }
    
    /**
     * Creates a deep copy of this node and its subtree.
     * 
     * @return a deep copy of this node
     * 
     * Time Complexity: O(n) where n is the number of nodes in subtree
     * Space Complexity: O(n) for the copied nodes
     */
    public TrieNode deepCopy() {
        TrieNode copy = new TrieNode(this.character, null, this.isEndOfWord);
        copy.wordCount = this.wordCount;
        copy.endWordCount = this.endWordCount;
        copy.word = this.word;
        
        // Recursively copy children
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (children[i] != null) {
                copy.children[i] = children[i].deepCopy();
                copy.children[i].parent = copy;
            }
        }
        
        return copy;
    }
    
    /**
     * Returns a string representation of this TrieNode.
     * Includes character, end-of-word status, and child count.
     * 
     * @return string representation of the node
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrieNode{");
        sb.append("char='").append(character == '\0' ? "ROOT" : character).append("'");
        sb.append(", isEndOfWord=").append(isEndOfWord);
        sb.append(", childCount=").append(getChildCount());
        sb.append(", wordCount=").append(wordCount);
        sb.append(", depth=").append(getDepth());
        if (word != null) {
            sb.append(", word='").append(word).append("'");
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a detailed string representation showing the path and children.
     * 
     * @return detailed string representation
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrieNode{");
        sb.append("path='").append(getPathFromRoot()).append("'");
        sb.append(", char='").append(character == '\0' ? "ROOT" : character).append("'");
        sb.append(", isEndOfWord=").append(isEndOfWord);
        sb.append(", wordCount=").append(wordCount);
        sb.append(", endWordCount=").append(endWordCount);
        sb.append(", depth=").append(getDepth());
        sb.append(", children=[").append(getChildCharacters()).append("]");
        if (word != null) {
            sb.append(", storedWord='").append(word).append("'");
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Returns a compact representation for tree visualization.
     * 
     * @return compact string representation
     */
    public String toCompactString() {
        String charStr = character == '\0' ? "ROOT" : String.valueOf(character);
        return charStr + (isEndOfWord ? "*" : "");
    }
} 