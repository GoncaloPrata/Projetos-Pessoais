package Profile;

import java.util.Iterator;

import Calculadora.CalculadoraInt;
public interface ProfileInterface {

	/**
	 * 
	 * @return o nome do perfil
	 */
	String getName();

	/**
	 * 
	 * @return a idade da pessoa a quem pertence o perfil
	 */
	int getAge();

	/**
	 * Ativa o perfil
	 */
	void setProfileOn();

	/**
	 * Desativa o perfil
	 */
	void setProfileOff();

	/**
	 * 
	 * @return se o perfil esta ligado ou nao
	 */
	boolean isProfileOn();

	/**
	 * Cria e adiciona um objeto "Calculadora" ao perfil;
	 */
	void addCalculadora();

	double addCalc(double a, double b);

	double subCalc(double a, double b);

	double multiplyCalc(double a, double b);

	double divideCalc(double a, double b);

	double squareRootCalc(double a);

	double areaOfASquareCalc(double a);

	double areaOfARectangleCalc(double comprimento, double largura);

	double areaOfATriangleCalc(double base, double altura);

	double quadraticFormulaFirstResultCalc(double a, double b, double c);

	double quadraticFormulaSecondResultCalc(double a, double b, double c);

	double volumOfACubeCalc(double a);

	double volumOfAPrismCalc(double comprimento, double largura, double altura);

	double volumOfATriangularPrismCalc(double comprimento, double largura, double altura);

	double aToThePowerBCalc(double a, int b);

	double firstDegreeEquationSolverCalc(double firstX, double firstC, double secondX, double secondC);

	Iterator<String> nameIteratorCalc();

	Iterator<Double> resultIteratorCalc();

	CalculadoraInt getCalculadora();

}
