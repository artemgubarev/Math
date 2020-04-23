import java.io.*;

/**
 *
 * На вход программы подается количество кубов - первая строка,
 * и их измерения - остальные строки, т.е к прмеру первый куб будет с длиной
 * 1, шириной 1, высотой 1.
 *
 * Пример входных данных:
 * 4
 * 1 1 1
 * 2 1 2
 * 3 3 3
 * 3 3 4
 *
 * Задача проверяет какое максимальное количество кубов можно вложить друг в
 * друга. На выход выдает число int - количество вложений кубиков. Максимальная цепочка
 * будет равна максимальному числу вложений без прерываний.
 *
 * Пример выходных данных:
 * 2
 */

public class Cube {

    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new FileReader("D:\\java\\dev\\forTraining\\src\\input.txt"));
             BufferedWriter output = new BufferedWriter(new FileWriter("D:\\java\\dev\\forTraining\\src\\output.txt"))) {
            int n = Integer.parseInt(input.readLine());

            //два массива для хранения параметров кубов и счетчик
            int[] firstArr = new int[3];
            int[] secondArr = new int[3];
            int counter = 1;

            //Читаем первый массив значений
            String[] s1 = input.readLine().split(" ");

            //перевод в инт
            for(int i = 0; i < 3; i++){
                firstArr[i] = Integer.parseInt(s1[i]);
            }

            for(int i = 0; i < n-1; i ++){
                //Считываем второй массив
                String[] s2 = input.readLine().split(" ");

                //перевод в инт
                for(int j = 0; j < 3; j++) {
                    secondArr[j] = Integer.parseInt(s2[j]);
                }

                System.out.println();
                System.out.println("First: " + arrayAsString(firstArr));
                System.out.println("Second: " + arrayAsString(secondArr));

                //Отправляем на проверку, можно ли вложить первый во второй
                counter = cubesIsValid(firstArr, secondArr, counter);

                //Перезаписываем второй куб вместо первого
                for(int j = 0; j < 3; j++) {
                    firstArr[j] = secondArr[j];
                }

            }

            //Запись счетчика в файл
            System.out.print(counter + " ");
            output.write(String.valueOf(counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция проверки
     * @param firstArr
     * @param secondArr
     * @param counter
     * @return
     */
    public static int cubesIsValid(int[] firstArr, int[] secondArr, int counter){

        if(firstArr[0] < secondArr[0] && firstArr[1] < secondArr[1] && firstArr[2] < secondArr[2]) {
            counter++;
        }

        return counter;
    }


    public static String arrayAsString(int[] array){
        StringBuilder str1 = new StringBuilder();
        for(int l = 0; l < array.length; l++) {
            str1.append(array[l]);
            str1.append(" ");
        }
        return str1.toString();
    }


}
