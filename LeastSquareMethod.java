import java.util.Scanner;

/**
 * Код решает систему уравнений методом наименьших квадратов.
 * Входные данные: n, m - размерность системы
 * Двумерный массив - сама система
 * Одномерный массив - столбец свободных членов.
**/

class Main{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();//count of equations 
		int m = in.nextInt();//count of variables
		
		if(n<m){
			System.out.println("Error, count of equations(n) shouldn't be less than count of variables");
		}
		
		double[][] arr = new double[n][m];//linear system
		double[] b = new double[n];//column of free elemnts
	
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m; j++){
				arr[i][j] = in.nextDouble();	
			}
			b[i] = in.nextDouble();
		}
		
		 if(n==1 & m==1){
			 System.out.println(b[0]/arr[0][0]);
		 } else if( n!=1 & m == 1){
			 
			 double f = 0, e = 0;
			 for(int i = 0; i < arr.length; i++){
				 f += arr[i][0]*b[i];
				 e += b[i]*b[i];
			 }
			 System.out.println(f/e);
			
		 } else {
			double[][] arrT = transpose(arr);
			double[][] A = multiply(arrT, arr);
			double det = determinant(A);
			double[][] inverseA = inverse(A);
			double[][] lastMatrix = multiply(inverseA, arrT);
			double[] X = multiply(lastMatrix, b);
			for(int i = 0; i<n; i++){	
				System.out.printf("%.10f", X[i]);
				System.out.print(" ");
			}
		}
	}
	
	private static double determinant(double[][] matrix) {
		if (matrix.length != matrix[0].length)
			throw new IllegalStateException("invalid dimensions");
		
		if(matrix.length == 1)
			return matrix[0][0];

		if (matrix.length == 2)
			return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

		double det = 0;
		for (int i = 0; i < matrix[0].length; i++)
			det += Math.pow(-1, i) * matrix[0][i]
					* determinant(minor(matrix, 0, i));
		return det;
	}
	
	private static double[][] inverse(double[][] matrix) {
		double[][] inverse = new double[matrix.length][matrix.length];
	

		// minors and cofactors
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				inverse[i][j] = Math.pow(-1, i + j)
						* determinant(minor(matrix, i, j));

		// adjugate and determinant
		double det = 1.0 / determinant(matrix);
		for (int i = 0; i < inverse.length; i++) {
			for (int j = 0; j <= i; j++) {
				double temp = inverse[i][j];
				inverse[i][j] = inverse[j][i] * det;
				inverse[j][i] = temp * det;
			}
		}
		return inverse;
	}
	
	private static double[][] minor(double[][] matrix, int row, int column) {
		double[][] minor = new double[matrix.length - 1][matrix.length - 1];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; i != row && j < matrix[i].length; j++)
				if (j != column)
					minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
		return minor;
	}
	

	public static double[][] multiply(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.0;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
	
	public static double[] multiply(double[][] A, double[] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
       
        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[] C = new double[aRows];
        for (int i = 0; i < aRows; i++) {
                C[i] = 0.0;
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < aColumns; j++) { // bColumn
                C[i] += A[i][j] * B[j];
            }
        }

        return C;
    }
	
	
	public static double[][] transpose(double[][] arr){
		double[][] arrT = new double[arr[0].length][arr.length];
		for(int i=0; i<arrT.length; i++){
			for(int j=0; j<arrT[i].length; j++){
				arrT[i][j] = arr[j][i];
			}
		}
		return arrT;
	}

	
	public static void printArray(double[][] arr, int n, int m){
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printArray(double[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
