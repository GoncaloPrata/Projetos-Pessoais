import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import Exceptions.*;
import Profile.*;

public class Main {

	private static final String AREA_OF_A_SQUARE = "AREA DE UM QUADRADO";
	private static final String AREA_OF_A_RECTANGLE = "AREA DE UM RECTANGULO";
	private static final String AREA_OF_A_TRIANGLE = "AREA DE UM TRIANGULO";
	private static final String FIRST_DEGREE_EQUATION_SOLVER = "EQUAÇÃO DE PRIMEIRO GRAU";
	private static final String SQUARE_ROOT = "RAIZ QUADRADA";
	private static final String DIVIDE = "DIVIDIR";
	private static final String MULTIPLY = "MULTIPLICAR";
	private static final String SUBTRACT = "SUBTRAIR";
	private static final String RESULT_IS = "O resultado corresponde a: ";
	private static final String ADD = "ADICIONAR";
	private static final String ACTIVATE_PROFILE = "ATIVAR PERFIL";
	private static final String DEACTIVATE_PROFILE = "DESATIVAR PERFIL";
	private static final String EXISTS_PROFILE_WITH_NAME = "PERFIL COM NOME";
	private static final String LIST_ALL_PROFILE = "LISTAR PERFIS";
	private static final String SHOW_ALL_CMDS = "AJUDA";
	private static final String PROMPT = "> ";
	private static final String INVALID_INPUT_CREATING_PROFILE_AGE = "Input invalido.";
	private static final String EXIT = "SAIR";
	private static final String EXIT_MESSAGE = "Adeus, até uma próxima";
	private static final String ADD_ACCOUNT = "CRIAR PERFIL";
	private static final String UNKNOWN_COMMAND = "Comando desconhecido, escreva 'ajuda' para saber quais os comandos disponíveis.";
	private static final String INSERT_NAME = "Qual é o se nome? ";
	private static final String INSERT_AGE = "Qual é a sua idade? ";
	private static final String NEW_PROFILE_ADDED = "Perfil criado com o sucesso!";
	private static final String HELP_CMD = "ajuda -> mostra o nome de todos os comandos "
			+ "\narea de um triangulo - devolve a área de um triangulo segundo uma dada altura e um dado comprimento"
			+ "\narea de um rectangulo - devolve a área de um rectangulo segundo uma dada altura e um dado comprimento"
			+ "\nnarea de um quadrado - devolve a área de um quadrado segundo um dado comprimento"
			+ "\nsair -> termina o programa; " + "\ncriar perfil -> adiciona um perfil"
			+ "\nperfil com nome -> procura se existe algum perfil com um nome inserido"
			+ "\nativar perfil -> ativa o perfil com um nome inserido"
			+ "\ndesativar perfil -> desativa o perfil que estava previamente ativo"
			+ "\nlistar perfis - lista todos os perfis criados"
			+ "\nadicionar - adiciona dois numeros na calculadora do perfil ativo"
			+ "\nsubtrair - subtrai dois numeros na calculadora do perfil ativo"
			+ "\ndividir - divide dois numeros na calculadora do perfil ativo"
			+ "\nraiz quadrada - faz a raiz quadrada de um numero"
			+ "\nequação de primeiro grau - resolve uma equação de primeiro grau"
			+ "\nmultiplicar - multiplica dois numeros na calculadora do perfil ativo";

	private static String getCommand(Scanner input) {
		String command = input.nextLine().toUpperCase();
		return command;
	}

	private static void switchCase(Scanner input, ProfilesManager topoObject, String command) {
		while (!command.equals(EXIT)) {
			switch (command) {
			case AREA_OF_A_SQUARE:
				areaOfASquareCalculadora(input, topoObject);
				break;
			case AREA_OF_A_TRIANGLE:
				areaOfATriangleCalculadora(input, topoObject);
				break;
			case AREA_OF_A_RECTANGLE:
				areaOfARectangleCalculadora(input, topoObject);
				break;
			case FIRST_DEGREE_EQUATION_SOLVER:
				solveFirstDegreeEquationCalculadora(input, topoObject);
				break;
			case SQUARE_ROOT:
				squareRootCalculadora(input, topoObject);
				break;
			case DIVIDE:
				divideCalculadora(input, topoObject);
				break;
			case MULTIPLY:
				multiplyCalculadora(input, topoObject);
				break;
			case SUBTRACT:
				subtractCalculadora(input, topoObject);
				break;
			case ADD:
				addCalculadora(input, topoObject);
				break;
			case LIST_ALL_PROFILE:
				listAllProfiles(topoObject);
				break;
			case ADD_ACCOUNT:
				cmdAddAccount(input, topoObject);
				break;
			case SHOW_ALL_CMDS:
				System.out.println(HELP_CMD);
				break;
			case EXISTS_PROFILE_WITH_NAME:
				cmdExistsProfileWithName(input, topoObject);
				break;
			case ACTIVATE_PROFILE:
				cmdSetProfileOn(input, topoObject);
				break;
			case DEACTIVATE_PROFILE:
				cmdSetOnProfileOff(topoObject);
				break;
			default:
				System.out.println(UNKNOWN_COMMAND);
				break;
			}
			System.out.print(PROMPT);
			command = getCommand(input);
		}
		System.out.println(EXIT_MESSAGE);
		input.close();
	}

	private static void cmdExistsProfileWithName(Scanner input, ProfilesManager topoObject) {
		System.out.print("Nome a procurar: ");
		String name = input.nextLine();
		if (topoObject.existsProfileWithName(name) == true)
			System.out.println("\nJá existe um perfil com o nome inserido.");
		else
			System.out
					.println("\nAinda não existe nenhum perfil com o nome inserido. Pode criar um perfil com o mesmo.");
	}

	private static void cmdAddAccount(Scanner input, ProfilesManager topoObject) {
		try {
			String name;
			int age;
			System.out.print(INSERT_NAME);
			name = input.nextLine().trim();
			System.out.print(INSERT_AGE);
			age = input.nextInt();
			topoObject.addProfile(name, age);
			System.out.println(NEW_PROFILE_ADDED);
		} catch (ProfileAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} catch (InvalidAgeException e) {
			System.out.println(e.getMessage());
		} finally {
			input.nextLine();
		}
	}

	private static void listAllProfiles(ProfilesManager topoObject) {
		try {
			Iterator<String> iterator = topoObject.profileIterator();
			int i = 1;
			while (iterator.hasNext() == true) {
				System.out.println(i + "º perfil:");
				String profileName = iterator.next();
				ProfileInterface profile = topoObject.getProfile(profileName);
				int age = profile.getAge();
				String stateOfProfile = "";
				if (profile.isProfileOn() == true)
					stateOfProfile = "ativo";
				else
					stateOfProfile = "inativo";
				System.out.println("	Nome: " + profileName);
				System.out.println("	Idade: " + age);
				System.out.println("	Estado de ativação: " + stateOfProfile);
				i++;
				if (topoObject.getProfile(profileName).getCalculadora().sizeNameList() == 0) {
					NoOperationsMadeException e = new NoOperationsMadeException();
					System.out.println("	" + e.getMessage());
				} else {
					System.out.println("	Todas as contas feitas neste perfil pela ordem em que foram feitas:");
					Iterator<String> iteratorOfOperationNames = topoObject.getProfile(profileName).nameIteratorCalc();
					Iterator<Double> iteratorOfOperationResults = topoObject.getProfile(profileName)
							.resultIteratorCalc();
					while (iteratorOfOperationNames.hasNext())
						System.out.println(
								"		-" + iteratorOfOperationNames.next() + " " + iteratorOfOperationResults.next());
				}
			}
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void cmdSetProfileOn(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Nome do perfil a ativar: ");
			String name = input.nextLine();
			topoObject.activateProfile(name);
			System.out.println("Perfil ativado.");
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (ProfileDoesNotExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void cmdSetOnProfileOff(ProfilesManager topoObject) {
		try {
			topoObject.deactivateOnProfiles();
			System.out.println("O perfil que estava ativo foi agora desativado.");
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Primeiro valor: ");
			double a = input.nextDouble();
			System.out.print("Segundo valor: ");
			double b = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().add(a, b));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}

	private static void subtractCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Primeiro valor: ");
			double a = input.nextDouble();
			System.out.print("Segundo valor: ");
			double b = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().subtract(a, b));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}

	private static void multiplyCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Primeiro valor: ");
			double a = input.nextDouble();
			System.out.print("Segundo valor: ");
			double b = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().multiply(a, b));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}

	private static void divideCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Primeiro valor: ");
			double a = input.nextDouble();
			System.out.print("Segundo valor: ");
			double b = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().divide(a, b));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}

	private static void squareRootCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor do qual quer saber a raiz quadrada: ");
			double a = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().squareRoot(a));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidSquareRootException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}

	private static void solveFirstDegreeEquationCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.println("A equação a resolver é da forma: 'ax + b = cx + d'.");
			System.out.print("Qual o valor de 'a'? ");
			double firstX = input.nextDouble();
			System.out.print("Qual o valor de 'b'?: ");
			double firstC = input.nextDouble();
			String finalFirstC = "";
			if (firstC < 0)
				finalFirstC = "(" + firstC + ")";
			System.out.print("Qual o valor de 'c'?: ");
			double secondX = input.nextDouble();
			System.out.print("Qual o valor de 'd'?: ");
			double secondC = input.nextDouble();
			String finalSecondC = "" + secondC;
			if (secondC < 0)
				finalSecondC = "(" + secondC + ")";
			System.out.println("A equação: ''" + firstX + "X + " + finalFirstC + " = " + secondX + "X + " + finalSecondC
					+ "'' tem como solução X = " + +topoObject.getCalculadoraOfProfile().firstDegreeEquationSolver(firstX,
							firstC, secondX, secondC));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} finally {
			input.nextLine();
		}
	}
	
	private static void areaOfASquareCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor do lado do quadrado do qual quer saber a área: ");
			double a = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().areaOfASquare(a));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}
	
	private static void areaOfARectangleCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor do comprimento do retangulo do qual quer saber a área: ");
			double comprimento = input.nextDouble();
			System.out.print("Valor da altura do retangulo do qual quer saber a área: ");
			double altura = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().areaOfARectangle(comprimento, altura));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}
	
	private static void areaOfATriangleCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor da base do triangulo do qual quer saber a área: ");
			double comprimento = input.nextDouble();
			System.out.print("Valor da altura do triangulo do qual quer saber a área: ");
			double altura = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().areaOfATriangle(comprimento, altura));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}
	
	private static void volumOfACubeCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor da aresta do cubo do qual quer saber a área: ");
			double a = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().volumOfACube(a));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}
	
	private static void volumOfAPrismCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor do comprimento do prisma do qual quer saber a área: ");
			double comprimento = input.nextDouble();
			System.out.print("Valor da largura do prisma do qual quer saber a área: ");
			double largura = input.nextDouble();
			System.out.print("Valor da altura do prisma do qual quer saber a área: ");
			double altura = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().volumOfAPrism(comprimento, largura, altura));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}
	
	private static void volumOfATriangularPrismCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor do comprimento do prisma do qual quer saber a área: ");
			double comprimento = input.nextDouble();
			System.out.print("Valor da largura do prisma do qual quer saber a área: ");
			double largura = input.nextDouble();
			System.out.print("Valor da altura do prisma do qual quer saber a área: ");
			double altura = input.nextDouble();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().volumOfATriangularPrism(comprimento, largura, altura));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}
	
	private static void aToThePowerBCalculadora(Scanner input, ProfilesManager topoObject) {
		try {
			System.out.print("Valor da base: ");
			double a = input.nextDouble();
			System.out.print("Valor do expoente: ");
			int b = input.nextInt();
			System.out.println(RESULT_IS + topoObject.getCalculadoraOfProfile().aToThePowerB(a, b));
		} catch (NoProfilesCreatedException e) {
			System.out.println(e.getMessage());
		} catch (NoProfileIsOnException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNumberException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(INVALID_INPUT_CREATING_PROFILE_AGE);
		} finally {
			input.nextLine();
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ProfilesManager topoObject = new ProfilesManager();
		System.out.print(PROMPT);
		String command = getCommand(input);
		switchCase(input, topoObject, command);
	}

}
