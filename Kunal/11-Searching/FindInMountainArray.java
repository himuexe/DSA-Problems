class FindInMountainArray {
    
    interface MountainArray {
        int get(int index);
        int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        // Find peak
        int peak = peak(mountainArr);
        
        // First try to find in ascending part
        int asc = binarySearch(mountainArr, target, 0, peak, true);
        if (asc != -1) {
            return asc;
        }
        
        // Then try to find in descending part
        return binarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, false);
    }

    private int peak(MountainArray arr) {
        int left = 0;
        int right = arr.length() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) > arr.get(mid + 1)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int binarySearch(MountainArray arr, int target, int left, int right, boolean ascending) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = arr.get(mid);
            
            if (midVal == target) {
                return mid;
            }
            
            if (ascending) {
                if (midVal < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (midVal < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
     public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 3, 1};
        MountainArray mountainArr = new MountainArray() {
            @Override
            public int get(int index) {
                return array[index];
            }
            
            @Override
            public int length() {
                return array.length;
            }
        };
        
        FindInMountainArray finder = new FindInMountainArray();
        int target = 3;
        int result = finder.findInMountainArray(target, mountainArr);
        System.out.println("Target found at index: " + result);
    }
}