import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] arr = {97, 25, 42, 90, 71, 16, 82, 39, 36, 74};

        arr = thanosSort(arr);
        for(int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    public static int[] thanosSort(int[] arr) {
        // если в массиве меньше 2 элементов, то можно не сортировать
        if (arr.length < 2)
            return arr;

        //ищем среднее значение
        double avgValue = 0;
        for (int i = 0; i < arr.length; i++) {
            avgValue = avgValue + arr[i];
        }
        avgValue = avgValue / arr.length;

        // промежуточный массив
        int[] resArray = new int[arr.length];

        int lengthLeftArray = 0;

        //разгоняем по левой/правой стороне
        for (int i = 0, r = arr.length-1 ; i < arr.length; i++) {
            if (arr[i] <= avgValue)
                resArray[lengthLeftArray++] = arr[i];
            else
                resArray[r--] = arr[i];
        }

        //если все элементы попали в левую сторону, то все элементы массива одинаковые и можно не сортировать
        if (lengthLeftArray == arr.length)
            return arr;

        // формируем левый и правый массивы
        int[] leftArray = Arrays.copyOf(resArray, lengthLeftArray);
        int[] rightArray = new int[arr.length - lengthLeftArray];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = resArray[lengthLeftArray+i];

        // сортируем левые и правые массивы
        leftArray = thanosSort(leftArray);
        rightArray = thanosSort(rightArray);

        // формируем итоговый массив
        resArray = Arrays.copyOf(leftArray, arr.length);

        for (int j = 0, i = lengthLeftArray; j < rightArray.length; j++)
            resArray[i++] = rightArray[j];

        return resArray;
    }
}
