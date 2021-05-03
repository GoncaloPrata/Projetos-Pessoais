package Profile;

import java.util.Iterator;

import Calculadora.*;

public class ProfileClass implements ProfileInterface {

	private int age;
	private String name;
	private boolean isProfileOn;
	private Calculadora opList;

	public ProfileClass(String name, int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.isProfileOn = false;
		this.opList = new Calculadora();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	@Override
	public void setProfileOn() {
		// TODO Auto-generated method stub
		isProfileOn = true;
	}

	@Override
	public void setProfileOff() {
		// TODO Auto-generated method stub
		isProfileOn = false;
	}

	@Override
	public boolean isProfileOn() {
		// TODO Auto-generated method stub
		return isProfileOn;
	}

	@Override
	public void addCalculadora() {
		// TODO Auto-generated method stub
		Calculadora op = new Calculadora();
		this.opList = op;
	}

	@Override
	public double addCalc(double a, double b) {
		// TODO Auto-generated method stub
		return opList.add(a, b);
	}

	@Override
	public double subCalc(double a, double b) {
		// TODO Auto-generated method stub
		return opList.subtract(a, b);
	}

	@Override
	public double multiplyCalc(double a, double b) {
		// TODO Auto-generated method stub
		return opList.multiply(a, b);
	}

	@Override
	public double divideCalc(double a, double b) {
		// TODO Auto-generated method stub
		return opList.divide(a, b);
	}

	@Override
	public double squareRootCalc(double a) {
		// TODO Auto-generated method stub
		return opList.squareRoot(a);
	}

	@Override
	public double areaOfASquareCalc(double a) {
		// TODO Auto-generated method stub
		return opList.areaOfASquare(a);
	}

	@Override
	public double areaOfARectangleCalc(double comprimento, double largura) {
		// TODO Auto-generated method stub
		return opList.areaOfARectangle(comprimento, largura);
	}

	@Override
	public double areaOfATriangleCalc(double base, double altura) {
		// TODO Auto-generated method stub
		return opList.areaOfATriangle(base, altura);
	}

	@Override
	public double quadraticFormulaFirstResultCalc(double a, double b, double c) {
		// TODO Auto-generated method stub
		return opList.quadraticFormulaFirstResult(a, b, c);
	}

	@Override
	public double quadraticFormulaSecondResultCalc(double a, double b, double c) {
		// TODO Auto-generated method stub
		return opList.quadraticFormulaSecondResult(a, b, c);
	}

	@Override
	public double volumOfACubeCalc(double a) {
		// TODO Auto-generated method stub
		return opList.volumOfACube(a);
	}

	@Override
	public double volumOfAPrismCalc(double comprimento, double largura, double altura) {
		// TODO Auto-generated method stub
		return opList.volumOfAPrism(comprimento, largura, altura);
	}

	@Override
	public double volumOfATriangularPrismCalc(double comprimento, double largura, double altura) {
		// TODO Auto-generated method stub
		return opList.volumOfATriangularPrism(comprimento, largura, altura);
	}

	@Override
	public double aToThePowerBCalc(double a, int b) {
		// TODO Auto-generated method stub
		return opList.aToThePowerB(a, b);
	}

	@Override
	public double firstDegreeEquationSolverCalc(double firstX, double firstC, double secondX, double secondC) {
		// TODO Auto-generated method stub
		return opList.firstDegreeEquationSolver(firstX, firstC, secondX, secondC);
	}

	@Override
	public Iterator<String> nameIteratorCalc() {
		// TODO Auto-generated method stub
		return opList.nameIterator();
	}

	@Override
	public Iterator<Double> resultIteratorCalc() {
		// TODO Auto-generated method stub
		return opList.resultIterator();
	}

	@Override
	public CalculadoraInt getCalculadora() {
		// TODO Auto-generated method stub
		return opList;
	}

}
