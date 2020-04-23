package algoCSC;

import java.util.Arrays;
import java.util.Scanner;

/**
 * В первой строке задано два целых числа 1≤n≤50000 1 b 1≤m≤50000 — количество отрезков и точек на прямой,
 * соответственно. Следующие n строк содержат по два целых числа ai и bi(ai≤bi) — координаты концов отрезков.
 * Последняя строка содержит m целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю.
 * Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
 * Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.
 *
 * Sample Input:
 * 2 3
 * 0 5
 * 7 10
 * 1 6 11
 * Sample Output:
 * 1 0 0
 */
public class PointsAndDistances {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];

        for(int i = 0; i < n; i++){
            start[i] = in.nextInt();
            end[i] = in.nextInt();
        }

        Arrays.sort(start);
        Arrays.sort(end);

        for(int i = 0; i < m; i++){
            int point = in.nextInt();
            System.out.print((start(start, point) - end(end, point)) + " ");
        }

    }

    public static int start(int[] start, int point){
        int x = 0;
        for(int i = 0; i < start.length; i++){
            if (start[i] <= point){
                x++;
            } else {
                break;
            }
        }
        return x;
    }

    public static int end(int[] end, int point){
        int y = 0;
        for(int i = 0; i < end.length; i++){
            if (end[i] < point){
                y++;
            } else {
                break;
            }
        }
        return y;
    }

}
