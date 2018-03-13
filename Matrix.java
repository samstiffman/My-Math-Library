package myMath;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

class Matrix{
	final double[][] matrix;
	final int rows;
	final int columns;
	final static DecimalFormat FORMAT = new DecimalFormat("0.#");
	
	/**
	 * Matrix constructor using 2d array
	 * @param 2d array of doubles that becomes the matrix
	 */
	public Matrix(double[][] matrix) {
		rows = matrix.length;
		columns = matrix[0].length;
		this.matrix = new double[rows][columns];
		for(int i=0; i<matrix.length;i++) 
			for(int q=0; q<matrix[0].length; q++) 
				this.matrix[i][q]=matrix[i][q];
	}
	/**
	 * Vector constructor
	 * @param vector
	 * @param selector use 'r' or 'R' if row vector otherwise it will be a column vector
	 */
	public Matrix(double[] vector, char selector){
		switch(selector) {
		case 'r': 
		case 'R':
			rows = 1;
			columns = vector.length;
			matrix = new double[1][vector.length];
			for(int i=0;i<columns;i++) 
				matrix[0][i] = vector[i];
			break;
		default:
			rows = vector.length;
			columns = 1;
			matrix = new double[vector.length][1];
			for(int i=0;i<rows;i++) 
				matrix[i][0] = vector[i];
		}
	}
	/**
	 * Default private constructor only for use in exception handling returns a blank matrix 0x0
	 * Used so that when an exception is thrown the program does not crash due to not having anything returned from methods
	 */
	private Matrix() {
		rows = 0;
		columns = 0;
		matrix = new double[0][0];
	}
	public String toString() {
		StringBuilder string = new StringBuilder();
		
		for(int i = 0; i < rows; i++) {
			string.append('[');
			for(int j = 0; j < columns; j++) {
				string.append(FORMAT.format(matrix[i][j]));
				if(j != columns-1)
					string.append(", ");
			}
			string.append(']');
			string.append('\n');
		}
		return string.toString();
	}	
	/**
	 * Constructor for the Empty Matrix
	 * @param rows
	 * @param columns
	 * @return Empty Matrix with dimensions Rows x Columns
	 */
	public static Matrix zeroMatrix(int rows, int columns) {
		double[][] theMatrix = new double[rows][columns];
		for(int i=0; i<rows; i++)
			for(int q=0; q<columns; q++)
				theMatrix[i][q] = 0;
		Matrix newMatrix = new Matrix(theMatrix);
		return newMatrix;
	}
	/**
	 * Makes the identity matrix of the correct dimensions
	 * @param dimensions
	 * @return Identity Matrix with dimensions Dimensions x Dimensions
	 */
	public static Matrix identityMatrix(int dimensions) {
		double[][] temp = new double[dimensions][dimensions];
		for(int i=0; i<dimensions; i++) 
			temp[i][i] = 1;
		return new Matrix(temp);
	}
	/**
	 * Makes a random matrix rows x columns 
	 * @param rows Amount of rows
	 * @param columns Amount of columns
	 * @param lowerBound Lower bound of matrix values
	 * @param upperBound Upper bound of matrix values
	 * @return random matrix rows x columns
	 */
	public static Matrix randomMatrix(int rows, int columns, int lowerBound, int upperBound) {
		Random rand = new Random();
		double[][] temp = new double[rows][columns];
		
		for(int i=0; i<rows; i++)
			for(int q=0; q<columns; q++)
				temp[i][q] = rand.nextInt(upperBound) + lowerBound;
		return new Matrix(temp);
	}
	/**
	 * Makes a random square matrix rows x columns 
	 * @param dimensions How many rows and columns
	 * @param lowerBound Lower bound of matrix values
	 * @param upperBound Upper bound of matrix values
	 * @return Random square matrix
	 */
	public static Matrix randomMatrix(int dimensions, int lowerBound, int upperBound) {
		Random rand = new Random();
		double[][] temp = new double[dimensions][dimensions];
		
		for(int i=0; i<dimensions; i++)
			for(int q=0; q<dimensions; q++)
				temp[i][q] = rand.nextInt(upperBound) + lowerBound;
		return new Matrix(temp);
	}
	/**
	 * Returns value of the matrix at certain coordinates
	 * @param rowCoord  Row the value is 
	 * @param columnCoord Column the value is 
	 * @return value in matrix at coords [rowCoord][columnCoord]
	 */
	public double getValue(int rowCoord, int columnCoord) {
		return matrix[rowCoord][columnCoord];
	}
	/**
	 * Returns the determinant of the matrix
	 * @return the determinant of the matrix
	 * @throws BadDimmesionsException Throws if matrix is not square
	 */
	public double determinant() throws BadDimmesionsException{
		try {
			ArrayList<Double> listOfMinors = new ArrayList<>();
			if(columns != rows) {
				String message = String.format("Check the dimensions of your Matrix "
						+ "%d x %d matrix must be square to calculate determinant", rows, columns);
				throw new BadDimmesionsException(message);
			}
			switch(rows) {
			case 1:
				return matrix[0][0]; 
			case 2:
				return (matrix[0][0]*matrix[1][1])-(matrix[0][1]*matrix[1][0]);
			default:
				int sign = 1;
				//Makes one matrix for each column
				for(int i=0; i<rows; i++) {					
					int buffer = 0;
					double[][] current = new double[rows-1][rows-1];
					for(int q = 0; q<rows-1; q++) {
						buffer = 0;
						for(int k=0; k<rows-1; k++) { 
							//Skips column if same column as the value we are using
							buffer += ((k == i)? 1: 0);
							current[q][k] = matrix[q+1][(k+buffer)%rows];
						}
					}
					listOfMinors.add(sign*(new Matrix(current)).determinant()*matrix[0][i]);
					sign = (sign == 1)? -1 : 1;
				}
			}
			double sum=0;
			//adds up determinants of every matrix in the list of subdeterminants
			for(Double element: listOfMinors) 
				sum += element;
			return sum;
		}catch(BadDimmesionsException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Multiplies a scalar to every value in a Matrix
	 * @param scalar
	 * @return New Matrix that was a product of the old Matrix and the scalar
	 */
	public Matrix scalarMultiplication(double scalar) {
		double[][] temp = matrix;
		for(int i=0; i<rows; i++)
			for(int q=0; q<columns; q++)
				temp[i][q] *= (temp[i][q]==0 || scalar==0)? 0:scalar;
		return new Matrix(temp);
	}
	/**
	 * Divides every value in a Matrix by the scalar
	 * @param scalar
	 * @return New Matrix that was a product of the old Matrix and 1/scalar
	 */
	public Matrix scalarDivision(int scalar) {
		double[][] temp = matrix;
		for(int i=0; i<rows; i++)
			for(int q=0; q<columns; q++)
				temp[i][q] /= scalar;
		return new Matrix(temp);
	}
	
	/**
	 * Computes the Transpose of a Matrix
	 * @return the Transpose of the Matrix
	 */
	public Matrix transposeMatrix() { 
		double[][] temp = new double[columns][rows];
		for(int i=0; i<columns; i++)
			for(int q=0; q<rows; q++)
				temp[i][q] = matrix[q][i];
		return new Matrix(temp);
	}
	/**
	 * Matrix multiplication
	 * @throws BadDimmesionsException throws if the matrices don't have the correct dimmensions for multiplication
	 * for an NxM matric and AxB matrix M has to equal B
	 */
	public Matrix multiply(Matrix a) throws BadDimmesionsException {
		try {
			if(columns != a.rows) {
				String message = String.format("Check the dimensions of your Matrices "
						+ "%d x %d and %d x %d ", rows, columns, a.rows, a.columns);
				throw new BadDimmesionsException(message);
			}
			double[][] temp = new double[columns][a.rows];
			double sum = 0;
			for(int i = 0; i<columns; i++) {
				for(int q = 0; q<a.rows; q++) {
					sum =0;
					for(int pointer=0; pointer<a.rows; pointer++) 
						sum += a.matrix[pointer][q] * matrix[i][pointer];
					temp[i][q] = sum;
				}
			}
			return new Matrix(temp);
		} catch (BadDimmesionsException e) {
			e.printStackTrace();
			return new Matrix();
		}
	}
	/**
	 * Matrix addition
	 * @throws BadDimmesionsException throws if the matrices don't have the same dimmension
	 */
	public Matrix add(Matrix a) throws BadDimmesionsException {
		double[][] temp;
		try {
			if((rows != a.rows) || (columns != a.columns)) {
				String message = String.format("Check the dimensions of your Matrices "
						+ "%d x %d and %d x %d ", rows, columns, a.rows, a.columns);
				throw new BadDimmesionsException(message);
			}
			temp = new double[rows][columns];
			for(int i=0; i<rows; i++)
				for(int q=0; q<columns; q++)
					temp[i][q] = a.matrix[i][q] + matrix[i][q];
			return new Matrix(temp);
		} catch (BadDimmesionsException e) {
			e.printStackTrace();
			return new Matrix();
		}
		
	}
	/**
	 * Matrix subtraction
	 * @throws BadDimmesionsException throws if the matrices don't have the same dimmension
	 */
	public Matrix subtract(Matrix a) throws BadDimmesionsException {
		try {
			if((rows != a.rows) || (columns != a.columns)) {
				String message = String.format("Check the dimensions of your Matrices "
						+ "%d x %d and %d x %d ", rows, columns, a.rows, a.columns);
				throw new BadDimmesionsException(message);
			}
			double[][] temp = new double[rows][columns];
				for(int i=0; i<rows; i++)
					for(int q=0; q<columns; q++)
						temp[i][q] = a.matrix[i][q] - matrix[i][q];
			return new Matrix(temp);
		} catch (BadDimmesionsException e) {
			e.printStackTrace();
			return new Matrix();
		}
	}
	/**
	 * Matrix check for equality
	 * @throws BadDimmesionsException throws if the matrices don't have the same dimmension
	 */
	public boolean equals(Matrix a) throws BadDimmesionsException {
		try {
			if((rows != a.rows) || (columns != a.columns)) {
				String message = String.format("Check the dimensions of your Matrices "
						+ "%d x %d and %d x %d ", rows, columns, a.rows, a.columns);
				throw new BadDimmesionsException(message);
			}
			for(int i=0; i<rows; i++)
				for(int q=0; q<columns; q++)
					if(a.matrix[i][q]!=matrix[i][q])
						return false;
			return true;
		} catch (BadDimmesionsException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
