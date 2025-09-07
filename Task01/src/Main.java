//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Task 1 test
        int[] arr2 = {2,1,3,4,5};
        int[] arr1 = {1,2,3,4,5};

        System.out.println("Task 1 - Test: ");
        System.out.println(isSorted(arr1,5));
        System.out.println(isSorted(arr2,5));

        //Task 2 test
        System.out.println("Task 2 - Test: ");
        FizzBuzz();

        //Task 3 test
        int[] arrt_1 = {1,2,3,3,2,1};
        int[] arrt_2 = {1,2,3,2,1};
        int[] arrt_3 = {2,2};
        System.out.println("Task 3 - Test: ");
        System.out.println(isSameSum(arrt_1, arrt_1.length));
        System.out.println(isSameSum(arrt_2, arrt_2.length));
        System.out.println(isSameSum(arrt_3, arrt_3.length));

    }
    //Task 1 Method
    static boolean  isSorted(int[] arr, int length){
        for(int i = 0 ; i < length-1; i++){
            if(arr[i]>arr[i+1])return false;
        }
        return true;
    }
    // Task 2 Methond
    static void FizzBuzz(){
        for(int i = 1; i < 100;i++){
            if(i%3 == 0 && i%5 == 0){
                System.out.println("FizzBuzz");

            }
            else if(i%3 == 0){
                System.out.println("Fizz");
            }
            else if(i%5 == 0){
                System.out.println("Buzz");
            }
            else{
                System.out.println(i) ;
            }
        }
    }
    // Task 3 Methond
    static boolean isSameSum(int[] arr, int length){
        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0 ; i < length/2 ;i++){
            sum1+=arr[i];
        }
        for(int i = length/2; i < length;i++ ){
            sum2+=arr[i];
        }
        return  sum1 == sum2;

    }

}
