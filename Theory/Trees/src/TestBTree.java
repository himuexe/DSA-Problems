public class TestBTree {
    public static void main(String[] args) {
        System.out.println("🧪 B-Tree Debug Test");
        
        BTree<Integer> btree = new BTree<>(3); // Min degree 3, max keys = 5
        
        try {
            System.out.println("Inserting 10...");
            btree.insert(10);
            
            System.out.println("Inserting 20...");
            btree.insert(20);
            
            System.out.println("Inserting 5...");
            btree.insert(5);
            
            System.out.println("Inserting 6...");
            btree.insert(6);
            
            System.out.println("Inserting 12...");
            btree.insert(12);
            
            System.out.println("Root should be full now with 5 keys");
            btree.printTree();
            
            System.out.println("Inserting 30... (should trigger root split)");
            btree.insert(30);
            
            System.out.println("After split:");
            btree.printTree();
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 