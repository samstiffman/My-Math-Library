# My-Math-Library
A Java Library I made containing various mathematical concepts as well as symbolic mathematics
By symbolic mathematics I mean root(8) = 2root(2) this is similar to many calculators.

Currently I have implemented:
Matrices, Fractions

The Fractions, and Integer/Double wrapper classes extend my MyNumber class which lets them interact with eachother
The Fraction class allows fraction only operations as well as Operations with other MyNumber subclasses or Java.Lang.Number classes

The Matrix class includes:
  * Matrix **Addition**, **Subtraction**, and **Multiplication**
  * Scalar **Multiplication**, and **Division**
  * Determinant calculations for any size matrix
  * Random Matrices of any size
  * Identity Matrix constructor of any size
  * Empty Matrix constructor any size
  * Column and Row Vector constructor
  * A custom exception that is thrown when matrix operations are attempted with the wrong dimmensions
  * Transpose Matrix
  
The Exact Math and Fraction Java files are about visual mathematics
  * Fractions are displayed as a/b rather than their double version
  * When used with addition and Multiplication and Division Fraction is preserved (Doesn't become a double)
  * Square roots of fractions work and fractions are factored so there is never a root in the denominator
  * Doubles can be turned into rational numbers/fractions
  * Square roots of doubles convert them to rational numbers
