package myMath;

public class MyMathTester {

	public static void main(String[] args) throws BadDimmesionsException {
		
		
		Matrix a = Matrix.randomMatrix(3, 3, 1, 3);
		System.out.println("\n\n"); 
		a = Matrix.randomMatrix(10, 10, 0, 9);
		System.out.println(a);
		System.out.println(a.determinant());

	}
}
