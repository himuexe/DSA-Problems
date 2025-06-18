public class FloorCeiling {
    public static int ceiling(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left < arr.length ? left : -1;
    }

    public static int floor(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right >= 0 ? right : -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int target = 6;

        int ceil = ceiling(arr, target);
        int floor = floor(arr, target);

        System.out.println("Ceiling of " + target + ": " + (ceil != -1 ? arr[ceil] : "Not found"));
        System.out.println("Floor of " + target + ": " + (floor != -1 ? arr[floor] : "Not found"));
    }
}