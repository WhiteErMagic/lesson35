import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] arr = myArray(new int[]{1, 4, 5, 6, 3, 2, 1, 4, 7, 8, 9, 6, 4, 9, 8, 7});
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int[] myArray(int[] arg){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < arg.length; i++) {
            arr.add((Integer) arg[i]);
        }
        int found = arr.lastIndexOf(4) + 1;
        if(found == -1)
            throw new RuntimeException();

        int[] intArr = new int[arr.size() - found];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = arr.get(i + found);
        }
        return intArr;
    }
}
