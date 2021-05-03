package Calculadora;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.*;

public class Calculadora implements CalculadoraInt {

	private double result;
	private ArrayList<String> operationNameList;
	private ArrayList<Double> operationResultList;
	private ArrayList<String> operationNameListByRecency;
	private ArrayList<Double> operationResultListByRecency;

	public Calculadora() {
		// TODO Auto-generated constructor stub
		this.result = 0;
		this.operationNameList = new ArrayList<String>();
		this.operationResultList = new ArrayList<Double>();
		this.operationNameListByRecency = new ArrayList<String>();
		this.operationResultListByRecency = new ArrayList<Double>();
	}

	@Override
	public double add(double a, double b) {
		result = a + b;
		operationNameList.add("Adição entre " + a + " e " + b + ":");
		operationResultList.add(result);
		operationNameListByRecency.add(0, "Adição entre " + a + " e " + b + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double subtract(double a, double b) {
		// TODO Auto-generated method stub
		result = a - b;
		operationResultList.add(result);
		operationNameList.add("Subtração entre " + a + " e " + b + ":");
		operationNameListByRecency.add(0, "Subtração entre " + a + " e " + b + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double multiply(double a, double b) {
		// TODO Auto-generated method stub
		result = a * b;
		operationNameList.add("Multiplicação entre " + a + " e " + b + ":");
		operationResultList.add(result);
		operationNameListByRecency.add(0, "Multiplicação entre " + a + " e " + b + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double divide(double a, double b) throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (b == 0)
			throw new InvalidNumberException();
		result = a / b;
		operationResultList.add(result);
		operationNameList.add("Divisão entre " + a + " e " + b + ":");
		operationNameListByRecency.add(0, "Divisão entre " + a + " e " + b + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double squareRoot(double a) throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (a < 0)
			throw new InvalidNumberException();
		result = Math.sqrt(a);
		operationResultList.add(result);
		operationNameList.add("Raiz quadrada de " + a + ":");
		operationNameListByRecency.add(0, "Raiz quadrada de " + a + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double areaOfASquare(double a) throws InvalidSquareRootException {
		// TODO Auto-generated method stub
		if (a <= 0)
			throw new InvalidSquareRootException();
		result = a * a;
		operationResultList.add(result);
		operationNameList.add("Area de um quadrado cujo lado tem o valor " + a + ":");
		operationNameListByRecency.add(0, "Area de um quadrado cujo lado tem o valor " + a + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double areaOfARectangle(double comprimento, double largura) throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (comprimento <= 0 || largura <= 0)
			throw new InvalidNumberException();
		result = comprimento * largura;
		operationResultList.add(result);
		operationNameList.add("Area de um rectangulo cujo comprimento tem o valor " + comprimento
				+ " e cuja largura tem o valor " + largura + ":");
		operationNameListByRecency.add(0, "Area de um rectangulo cujo comprimento tem o valor " + comprimento
				+ " e cuja largura tem o valor " + largura + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double areaOfATriangle(double base, double altura) throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (base <= 0 || altura <= 0)
			throw new InvalidNumberException();
		result = (base * altura) / 2;
		operationResultList.add(result);
		operationNameList.add(
				"Area de um triangulo cuja base tem o valor " + base + " e cuja altura tem o valor " + altura + ":");
		operationNameListByRecency.add(0,
				"Area de um triangulo cuja base tem o valor " + base + " e cuja altura tem o valor " + altura + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double quadraticFormulaFirstResult(double a, double b, double c)
			throws InvalidNumberException, InvalidSquareRootException {
		// TODO Auto-generated method stub
		/*
		 * Uma funcao quadratica e do estilo ax^2 + bx + c = 0 e a sua resolucao fica
		 * assim x = ( -b +/- sqrt ( b^2 - (4*a*c) ) ) / 2*a ou seja: a nao pode ser 0 e
		 * b^2 - 4ac nao pode ser negativo
		 */
		if (checkIfQuadraticFormulaPositive(a, b, c) == false)
			throw new InvalidSquareRootException();
		if (a == 0)
			throw new InvalidNumberException();
		double denominator = 2 * a;
		double insideSquareRoot = (b * b) - (4 * a * c);
		double resultSquareRoot = Math.sqrt(insideSquareRoot);
		double numerator = -b + resultSquareRoot;
		double result = numerator / denominator;
		operationResultList.add(result);
		operationNameList.add("Primeiro valor da raiz quadrada ( -b + raiz((b^2) - 4ac) ) da forma " + a + "x^2 + " + b
				+ "x + " + c + ":");
		operationNameListByRecency.add(0, "Primeiro valor da raiz quadrada ( -b + raiz((b^2) - 4ac) ) da forma " + a
				+ "x^2 + " + b + "x + " + c + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double quadraticFormulaSecondResult(double a, double b, double c)
			throws InvalidNumberException, InvalidSquareRootException {
		// TODO Auto-generated method stub
		/*
		 * Uma funcao quadratica e do estilo ax^2 + bx + c = 0 e a sua resolucao fica
		 * assim x = ( -b +/- sqrt ( b^2 - (4*a*c) ) ) / 2*a ou seja: a nao pode ser 0 e
		 * b^2 - 4ac nao pode ser negativo
		 */
		if (checkIfQuadraticFormulaPositive(a, b, c) == false)
			throw new InvalidSquareRootException();
		if (a == 0)
			throw new InvalidNumberException();
		double denominator = 2 * a;
		double insideSquareRoot = (b * b) - (4 * a * c);
		double resultSquareRoot = Math.sqrt(insideSquareRoot);
		double numerator = -b - resultSquareRoot;
		double result = numerator / denominator;
		operationResultList.add(result);
		operationNameList.add("Segundo valor da raiz quadrada ( -b - raiz((b^2) - 4ac) ) da forma " + a + "x^2 + " + b
				+ "x + " + c + ":");
		operationNameListByRecency.add(0, "Segundo valor da raiz quadrada ( -b - raiz((b^2) - 4ac) ) da forma " + a
				+ "x^2 + " + b + "x + " + c + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	private boolean checkIfQuadraticFormulaPositive(double a, double b, double c) {
		// TODO Auto-generated method stub
		result = (b * b) - (4 * a * c);
		return result >= 0;
	}

	@Override
	public double volumOfACube(double a) throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (a == 0)
			throw new InvalidNumberException();
		result = a * a * a;
		operationResultList.add(result);
		operationNameList.add("Volume de um cubo cuja aresta tem o valor " + a + ":");
		operationNameListByRecency.add(0, "Volume de um cubo cuja aresta tem o valor " + a + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double volumOfAPrism(double comprimento, double largura, double altura) throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (comprimento <= 0 || largura <= 0 || altura <= 0)
			throw new InvalidNumberException();
		result = (comprimento * largura) * altura;
		operationResultList.add(result);
		operationNameList.add("Volume de um prisma cujo comprimento tem o valor " + comprimento
				+ ", a largura tem o valor " + largura + " e a altura tem o valor " + altura + ":");
		operationNameListByRecency.add(0, "Volume de um prisma cujo comprimento tem o valor " + comprimento
				+ ", a largura tem o valor " + largura + " e a altura tem o valor " + altura + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double volumOfATriangularPrism(double comprimento, double largura, double altura)
			throws InvalidNumberException {
		// TODO Auto-generated method stub
		if (comprimento <= 0 || largura <= 0 || altura <= 0)
			throw new InvalidNumberException();
		result = ((comprimento * largura) * altura) / 2;
		operationResultList.add(result);
		operationNameList.add("Volume de um prisma triangular cujo comprimento tem o valor " + comprimento
				+ ", a largura tem o valor " + largura + " e a altura tem o valor " + altura + ":");
		operationNameListByRecency.add(0, "Volume de um prisma triangular cujo comprimento tem o valor " + comprimento
				+ ", a largura tem o valor " + largura + " e a altura tem o valor " + altura + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double aToThePowerB(double a, int b) throws InvalidNumberException {
		// TODO Auto-generated method stub
		result = Math.pow(a, b);
		operationResultList.add(result);
		operationNameList.add(a + " elevado a " + b + ":");
		operationNameListByRecency.add(0, a + " elevado a " + b + ":");
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public double firstDegreeEquationSolver(double firstX, double firstC, double secondX, double secondC)
			throws InvalidNumberException {
		// TODO Auto-generated method stub
		/*
		 * fX + fC = sX + sC (=) fX - sX = sC - fC (=) fX - sX passa a ser aX aX = C (=)
		 * X = C/a
		 */
		double finalX = firstX - secondX;
		if (finalX == 0)
			throw new InvalidNumberException();
		double finalC = secondC - firstC;
		result = finalC / finalX;
		operationNameList.add("X=" + result);
		operationNameListByRecency.add(0, "X=" + result);
		operationResultList.add(result);
		operationResultListByRecency.add(0, result);
		return result;
	}

	@Override
	public Iterator<String> nameIterator() throws NoOperationsMadeException {
		// TODO Auto-generated method stub
		if (operationNameList.size() == 0)
			throw new NoOperationsMadeException();
		return operationNameList.iterator();
	}

	@Override
	public Iterator<Double> resultIterator() throws NoOperationsMadeException {
		// TODO Auto-generated method stub
		if (operationResultList.size() == 0)
			throw new NoOperationsMadeException();
		return operationResultList.iterator();
	}

	@Override
	public int sizeResultList() {
		// TODO Auto-generated method stub
		return operationResultList.size();
	}

	@Override
	public int sizeNameList() {
		// TODO Auto-generated method stub
		return operationNameList.size();
	}

}
