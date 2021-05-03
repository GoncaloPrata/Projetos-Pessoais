package Calculadora;

import java.util.Iterator;

import Exceptions.*;

public interface CalculadoraInt {

	double add(double a, double b);

	double subtract(double a, double b);

	double multiply(double a, double b);

	double divide(double a, double b) throws InvalidNumberException;

	double squareRoot(double a) throws InvalidSquareRootException;

	double areaOfASquare(double a) throws InvalidNumberException;

	double areaOfARectangle(double comprimento, double largura) throws InvalidNumberException;

	double areaOfATriangle(double base, double altura) throws InvalidNumberException;

	double quadraticFormulaFirstResult(double a, double b, double c) throws InvalidNumberException, InvalidSquareRootException;

	double quadraticFormulaSecondResult(double a, double b, double c) throws InvalidNumberException, InvalidSquareRootException;
	
	double volumOfACube (double a) throws InvalidNumberException;
	
	double volumOfAPrism (double comprimento, double largura, double altura) throws InvalidNumberException;

	double volumOfATriangularPrism (double comprimento, double largura, double altura) throws InvalidNumberException;
	
	double aToThePowerB (double a, int b) throws InvalidNumberException;
	
	double firstDegreeEquationSolver (double firstX, double firstC, double secondX, double secondC) throws InvalidNumberException;
	
	Iterator <String> nameIterator() throws NoOperationsMadeException;
	
	Iterator<Double> resultIterator() throws NoOperationsMadeException;
	
	int sizeResultList();
	
	int sizeNameList();
	
}
