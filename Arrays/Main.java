package Arrays.ArrayTrain;

/**
 * @ Description:
 * @ Date: Created in 13:41 11/07/2018
 * @ Author: Anthony_Duan
 */
public class Main {

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(20);
        for (int i = 0; i < 20; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);


        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        arr.add(1, 300);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);


    }
}
