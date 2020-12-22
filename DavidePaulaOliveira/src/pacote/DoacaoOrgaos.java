package pacote;

import java.io.BufferedReader; // Biblioteca de arquivo
import java.io.FileReader; // Biblioteca de arquivo
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

// Davi de Paula Oliveira
public class DoacaoOrgaos {
	public static void main(String[] args) throws IOException {
		int quantidade; // Quantidade de doadores que possui no arquivo
		int opcao = 0;
		Hospitais[] vetorHospitais = new Hospitais[10];
		Doadores[] vetorDoadores = new Doadores[100];
		String teclado; // Vari�vel para fazer a leitura de qualquer tecla do
						// teclado
		Scanner leia = new Scanner(System.in);

		quantidade = carregarDoadores();
		vetorHospitais = preencherHospitais(vetorHospitais);
		vetorDoadores = preencherDoadores(vetorDoadores);

		System.out
				.print("Seja bem-vindo ao novo programa de doa��es de org�os da regi�o do Vale do A�o \n\n");
		while (opcao != 3) {
			System.out.print("Escolha o que voc� deseja: \n");
			System.out.print("1) Doar org�os \n");
			System.out.print("2) Entrar na fila de espera para receber org�os \n");
			System.out.print("3) Sair \n\n");

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				if (quantidade < 100) {
					vetorDoadores[quantidade] = new Doadores();
					vetorDoadores[quantidade].statusD = "Doador";
					vetorDoadores = adicionarDoadorEspera(vetorDoadores, quantidade, vetorHospitais);
					quantidade = quantidade + 1;
					System.out.print("Cadastro feito com sucesso! \n");
				} else {
					System.out.print("\nA mem�ria do programa est� cheia\n");
				}

				System.out.print("\nPressione qualquer tecla para continuar...\n");
				teclado = leia.next();
				break;

			case 2:
				if (quantidade < 100) {
					vetorDoadores[quantidade] = new Doadores();
					vetorDoadores[quantidade].statusD = "Espera";
					vetorDoadores = adicionarDoadorEspera(vetorDoadores, quantidade, vetorHospitais);
					quantidade = quantidade + 1;
					System.out.print("Cadastro feito com sucesso! \n");
				} else {
					System.out.print("\nA mem�ria do programa est� cheia\n");
				}
				System.out.print("\nPressione qualquer tecla e aperte ENTER para continuar...\n");
				teclado = leia.next();
				break;

			case 3:
				vetorDoadores = salvarArquivo(vetorDoadores, quantidade);
				System.out.print("Sistema encerrado com sucesso! \n\n");
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		System.out.print("Obrigado por utilizar o nosso programa!!!");
		leia.close();
	}

	public static int carregarDoadores() // Essa fun��o verifica se o
											// arquivo
											// esta cheio (quantidade < 100)
	{
		int quantidade = 0;

		try {
			FileReader caminhoDoadores = new FileReader("Arquivos/Doadores e Fila de Espera.txt");
			BufferedReader lerArquivo = new BufferedReader(caminhoDoadores);
			String linha = lerArquivo.readLine();
			while (linha != null) {
				quantidade = quantidade + 1;
				linha = lerArquivo.readLine();
			}
			caminhoDoadores.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return quantidade;
	}

	public static Hospitais[] preencherHospitais(Hospitais[] vetorHospitais) // Preenche
																				// o
																				// registro
																				// dos
																				// Hospitais
	{
		int posicaoInicio;
		int posicaoFim;
		String parteLinha;
		String[] vetorAuxiliar = new String[8];
		int contador;
		int coluna = 0;

		try {
			FileReader caminhoHospitais = new FileReader("Arquivos/Hospitais.txt");
			BufferedReader lerArquivo = new BufferedReader(caminhoHospitais);
			String linha = lerArquivo.readLine();
			while (linha != null) {
				posicaoInicio = 0;
				posicaoFim = 0;
				contador = 0;

				while (posicaoFim != -1) {
					posicaoFim = linha.indexOf(";", posicaoInicio);
					if (posicaoFim != -1) {
						parteLinha = linha.substring(posicaoInicio, posicaoFim);
						vetorAuxiliar[contador] = parteLinha;
						contador = contador + 1;
						posicaoInicio = posicaoFim + 1;
					}
				}
				vetorHospitais[coluna] = new Hospitais();

				vetorHospitais[coluna].nomeH = vetorAuxiliar[0];
				vetorHospitais[coluna].qualidadeH = vetorAuxiliar[1];
				vetorHospitais[coluna].ruaH = vetorAuxiliar[2];
				vetorHospitais[coluna].cidadeH = vetorAuxiliar[3];
				vetorHospitais[coluna].bairroH = vetorAuxiliar[4];
				vetorHospitais[coluna].telefoneH = vetorAuxiliar[5];
				vetorHospitais[coluna].distanciaH = vetorAuxiliar[6];
				vetorHospitais[coluna].tempoH = vetorAuxiliar[7];
				linha = lerArquivo.readLine();
				coluna = coluna + 1;
			}
			caminhoHospitais.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return vetorHospitais;
	}

	public static Doadores[] preencherDoadores(Doadores[] vetorDoadores) // Preenche
																			// o
																			// registro
																			// dos
																			// Doadores
	{
		int posicaoInicio;
		int posicaoFim;
		String parteLinha;
		int coluna = 0;
		int contador;
		String[] vetorAuxiliar = new String[7];

		try {
			FileReader caminhoDoadores = new FileReader("Arquivos/Doadores e Fila de Espera.txt");
			BufferedReader lerArquivo = new BufferedReader(caminhoDoadores);
			String linha = lerArquivo.readLine();
			while (linha != null) {
				posicaoInicio = 0;
				posicaoFim = 0;
				contador = 0;

				while (posicaoFim != -1) {
					posicaoFim = linha.indexOf(";", posicaoInicio);
					if (posicaoFim != -1) {
						parteLinha = linha.substring(posicaoInicio, posicaoFim);
						vetorAuxiliar[contador] = parteLinha;
						contador = contador + 1;
						posicaoInicio = posicaoFim + 1;
					}
				}
				vetorDoadores[coluna] = new Doadores();

				vetorDoadores[coluna].nomeD = vetorAuxiliar[0];
				vetorDoadores[coluna].sexoD = vetorAuxiliar[1];
				vetorDoadores[coluna].idadeD = vetorAuxiliar[2];
				vetorDoadores[coluna].tipoSanguineoD = vetorAuxiliar[3];
				vetorDoadores[coluna].orgaoD = vetorAuxiliar[4];
				vetorDoadores[coluna].hospitalD = vetorAuxiliar[5];
				vetorDoadores[coluna].statusD = vetorAuxiliar[6];
				linha = lerArquivo.readLine();
				coluna = coluna + 1;
			}
			caminhoDoadores.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return vetorDoadores;
	}

	public static Doadores[] adicionarDoadorEspera(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Adiciona
																															// Doadores
																															// e
																															// Recebedores
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);

		if (quantidade < 100) {
			System.out.print("Qual � o nome completo da pessoa? \n");
			vetorDoadores[quantidade].nomeD = leia.nextLine();

			while (opcao != 1 && opcao != 2) {
				System.out.print("Qual � o genero da pessoa? \n\n");
				System.out.print("1) Masculino \n");
				System.out.print("2) Feminino \n\n");

				System.out.print("Escolha uma op��o: \n");
				opcao = leia.nextInt();

				switch (opcao) {
				case 1:
					vetorDoadores[quantidade].sexoD = "Masculino";
					break;

				case 2:
					vetorDoadores[quantidade].sexoD = "Feminino";
					break;

				default:
					System.out.print("Op��o Inv�lida!\n\n");
				}
			}
			System.out.print("Qual a idade da pessoa? \n");
			vetorDoadores[quantidade].idadeD = leia.next();
			opcao = 0;

			while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4) {
				System.out.print("Qual � o tipo sanguineo da pessoa? \n\n");
				System.out.print("1) Tipo A \n");
				System.out.print("2) Tipo B \n");
				System.out.print("3) Tipo AB \n");
				System.out.print("4) Tipo O \n\n");

				System.out.print("Escolha uma op��o: \n");
				opcao = leia.nextInt();

				switch (opcao) {
				case 1:
					vetorDoadores[quantidade].tipoSanguineoD = "A";
					break;

				case 2:
					vetorDoadores[quantidade].tipoSanguineoD = "B";
					break;

				case 3:
					vetorDoadores[quantidade].tipoSanguineoD = "AB";
					break;

				case 4:
					vetorDoadores[quantidade].tipoSanguineoD = "O";
					break;

				default:
					System.out.print("Op��o Inv�lida!\n\n");
				}
			}
			opcao = 0;
			if (vetorDoadores[quantidade].statusD.equals("Espera") == false) {
				while (opcao != 1 && opcao != 2) {
					System.out.print("Informe o status da pessoa cadastrada: \n\n");
					System.out.print("1) Doador Cad�ver \n");
					System.out.print("2) Doador Vivo \n\n");

					System.out.print("Escolha uma op��o: \n");
					opcao = leia.nextInt();

					switch (opcao) {
					case 1:
						vetorDoadores[quantidade].statusD = "Doador Cad�ver";
						break;

					case 2:
						vetorDoadores[quantidade].statusD = "Doador Vivo";
						vetorDoadores = DoadorVivo(vetorDoadores, quantidade);
						break;
					default:
						System.out.print("Op��o Inv�lida!\n\n");
					}
				}
			}
			if (vetorDoadores[quantidade].statusD.equals("Doador Cad�ver") == true
					|| vetorDoadores[quantidade].statusD.equals("Espera") == true) {
				vetorDoadores = DoadorCadaverEspera(vetorDoadores, quantidade);
			}
			if (vetorDoadores[quantidade].statusD.equals("Espera") == true) {
				System.out.print("Agora voc� escolhera em qual hospital deseja receber: \n\n");
			} else {
				System.out.print("Agora voc� escolhera em qual hospital deseja doar: \n\n");
			}
			opcao = 0;

			while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4) {
				System.out.print("Informe o modo para ordenar os hospitais: \n\n");
				System.out.print("1) Listar todos os hospitais disponiveis \n");
				System.out.print("2) Listar os hospitais por cidades \n");
				System.out.print("3) Listar os hospitais por qualidade \n");
				System.out.print("4) Listar os hospitais por distancia e tempo \n\n");

				System.out.print("Escolha uma op��o: \n");
				opcao = leia.nextInt();

				switch (opcao) {
				case 1:
					vetorDoadores = ListarHospitais(vetorDoadores, quantidade, vetorHospitais);
					break;

				case 2:
					vetorDoadores = ListarPorCidades(vetorDoadores, quantidade, vetorHospitais);
					break;

				case 3:
					vetorDoadores = ListarQualidade(vetorDoadores, quantidade, vetorHospitais);
					break;

				case 4:
					vetorDoadores = ListarDistanciaTempo(vetorDoadores, quantidade, vetorHospitais);
					break;

				default:
					System.out.print("Op��o Inv�lida!\n\n");
				}
			}
			if (vetorDoadores[quantidade].statusD.equals("Espera") == true) {
				vetorDoadores = TempodeEspera(vetorDoadores, quantidade);
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] DoadorVivo(Doadores[] vetorDoadores, int quantidade) // Seleciona
																					// os
																					// org�os
																					// que
																					// o
																					// doador
																					// pode
																					// doar
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);

		while (opcao != 1 && opcao != 2 && opcao != 3) {
			System.out.print(
					"Informe o org�o que a pessoa cadastrada deseja doar (atualmente o programa aceita que cada pessoa doe apenas um org�o): \n\n");
			System.out.print("1) F�gado \n");
			System.out.print("2) Pulm�o\n");
			System.out.print("3) Rim \n\n");

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].orgaoD = "F�gado";
				break;

			case 2:
				vetorDoadores[quantidade].orgaoD = "Pulm�o";
				break;

			case 3:
				vetorDoadores[quantidade].orgaoD = "Rim";
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] DoadorCadaverEspera(Doadores[] vetorDoadores, int quantidade) // Seleciona
																							// os
																							// org�os
																							// que
																							// o
																							// doador
																							// pode
																							// doar
																							// ou
																							// a
																							// pessoa
																							// pode
																							// receber
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);

		while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao != 6 && opcao != 7
				&& opcao != 8 && opcao != 9 && opcao != 10) {
			if (vetorDoadores[quantidade].statusD.equals("Doador Cad�ver") == true) {
				System.out.print(
						"Informe o org�o que a pessoa cadastrada deseja doar (atualmente o programa aceita que cada pessoa doe apenas um org�o): \n\n");
			} else {
				System.out.print(
						"Informe o org�o que a pessoa cadastrada deseja receber (atualmente o programa aceita que cada pessoa receba apenas um org�o): \n\n");
			}
			System.out.print("1) Cora��o \t");
			System.out.print("2) P�ncreas \n");
			System.out.print("3) Rim \t\t");
			System.out.print("4) Intestino \n");
			System.out.print("5) C�rneas \t");
			System.out.print("6) Ossos \n");
			System.out.print("7) Veias \t");
			System.out.print("8) Tend�o \n");
			System.out.print("9) Pulm�o \t");
			System.out.print("10) F�gado \n\n");

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].orgaoD = "Cora��o";
				break;

			case 2:
				vetorDoadores[quantidade].orgaoD = "P�ncreas";
				break;

			case 3:
				vetorDoadores[quantidade].orgaoD = "Rim";
				break;

			case 4:
				vetorDoadores[quantidade].orgaoD = "Intestino";
				break;

			case 5:
				vetorDoadores[quantidade].orgaoD = "C�rneas";
				break;

			case 6:
				vetorDoadores[quantidade].orgaoD = "Ossos";
				break;

			case 7:
				vetorDoadores[quantidade].orgaoD = "Veias";
				break;

			case 8:
				vetorDoadores[quantidade].orgaoD = "Tend�o";
				break;

			case 9:
				vetorDoadores[quantidade].orgaoD = "Pulm�o";
				break;

			case 10:
				vetorDoadores[quantidade].orgaoD = "F�gado";
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarHospitais(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// todos
																													// os
																													// hospitais
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);

		System.out.print("Hospitais Disponiveis: \n\n");
		while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao != 6 && opcao != 7
				&& opcao != 8 && opcao != 9 && opcao != 10) {
			for (int linha = 0; linha < 10; linha++) {
				System.out.print(linha + 1 + ") " + vetorHospitais[linha].nomeH + ":\n");
				System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
				System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
				System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
				System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
				System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
				System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
				System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
			}
			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[0].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[1].nomeH;
				break;

			case 3:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[2].nomeH;
				break;

			case 4:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[3].nomeH;
				break;

			case 5:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[4].nomeH;
				break;

			case 6:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[5].nomeH;
				break;

			case 7:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[6].nomeH;
				break;

			case 8:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[7].nomeH;
				break;

			case 9:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[8].nomeH;
				break;

			case 10:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[9].nomeH;
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarPorCidades(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais)// Lista
																													// os
																													// hospitais
																													// de
																													// acordo
																													// com
																													// a
																													// cidade
																													// que
																													// o
																													// usuario
																													// selecionou
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);
		while (opcao != 1 && opcao != 2 && opcao != 3) {
			System.out.print("Informe a cidade: \n\n");
			System.out.print("1) Tim�teo \n");
			System.out.print("2) Cel. Fabriciano \n");
			System.out.print("3) Ipatinga \n\n");

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores = ListarTimoteo(vetorDoadores, quantidade, vetorHospitais);
				break;

			case 2:
				vetorDoadores = ListarFabriciano(vetorDoadores, quantidade, vetorHospitais);
				break;

			case 3:
				vetorDoadores = ListarIpatinga(vetorDoadores, quantidade, vetorHospitais);
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarTimoteo(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// de
																													// Tim�teo
	{
		int opcao = 0;
		int linha;
		int contador;
		Scanner leia = new Scanner(System.in);

		System.out.print("Hospitais Disponiveis: \n\n");
		while (opcao != 1) {
			contador = 1;
			for (linha = 0; linha < 10; linha++) {
				if (vetorHospitais[linha].cidadeH.equals("Tim�teo") == true) {
					System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
					System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
					System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
					System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
					System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
					System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
					System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
					System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
					contador = contador + 1;
				}
			}
			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[0].nomeH;
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarFabriciano(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// de
																													// Fabriciano
	{
		int opcao = 0;
		int linha;
		int contador;
		Scanner leia = new Scanner(System.in);

		System.out.print("Hospitais Disponiveis: \n\n");
		while (opcao != 1 && opcao != 2 && opcao != 3) {
			contador = 1;
			for (linha = 0; linha < 10; linha++) {
				if (vetorHospitais[linha].cidadeH.equals("Cel. Fabriciano") == true) {
					System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
					System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
					System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
					System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
					System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
					System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
					System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
					System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
					contador = contador + 1;
				}
			}
			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();
			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[1].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[2].nomeH;
				break;

			case 3:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[9].nomeH;
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarIpatinga(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// de
																													// Ipatinga
	{
		int opcao = 0;
		int linha;
		int contador;
		Scanner leia = new Scanner(System.in);

		System.out.print("Hospitais Disponiveis: \n\n");
		while (opcao != 1 & opcao != 2 & opcao != 3 & opcao != 4 & opcao != 5 & opcao != 6) {
			contador = 1;
			for (linha = 0; linha < 10; linha++) {
				if (vetorHospitais[linha].cidadeH.equals("Ipatinga") == true) {
					System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
					System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
					System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
					System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
					System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
					System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
					System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
					System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
					contador = contador + 1;
				}
			}

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[3].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[4].nomeH;
				break;

			case 3:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[5].nomeH;
				break;

			case 4:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[6].nomeH;
				break;

			case 5:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[7].nomeH;
				break;

			case 6:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[8].nomeH;
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarQualidade(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// de
																													// acordo
																													// com
																													// a
																													// qualidade
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);

		while (opcao != 1 && opcao != 2) {
			System.out.print("Informe a qualidade: \n\n");
			System.out.print("1) Alta \n");
			System.out.print("2) Muito Alta \n\n");

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores = ListarAlta(vetorDoadores, quantidade, vetorHospitais);
				break;

			case 2:
				vetorDoadores = ListarMuitoAlta(vetorDoadores, quantidade, vetorHospitais);
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarAlta(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // lista
																												// os
																												// hospitais
																												// de
																												// qualidade
																												// alta
	{
		int opcao = 0;
		int linha;
		int contador;
		Scanner leia = new Scanner(System.in);

		System.out.print("Hospitais Disponiveis: \n\n");
		while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao != 6 && opcao != 7
				&& opcao != 8) {
			contador = 1;
			for (linha = 0; linha < 10; linha++) {
				if (vetorHospitais[linha].qualidadeH.equals("Alta") == true) {
					System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
					System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
					System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
					System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
					System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
					System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
					System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
					System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
					contador = contador + 1;
				}
			}

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[0].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[1].nomeH;
				break;

			case 3:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[2].nomeH;
				break;

			case 4:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[4].nomeH;
				break;

			case 5:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[6].nomeH;
				break;

			case 6:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[7].nomeH;
				break;

			case 7:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[8].nomeH;
				break;

			case 8:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[9].nomeH;
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarMuitoAlta(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// de
																													// qualidade
																													// muito
																													// alta
	{
		int opcao = 0;
		int linha;
		int contador;
		Scanner leia = new Scanner(System.in);

		System.out.print("Hospitais Disponiveis: \n\n");
		while (opcao != 1 & opcao != 2) {
			contador = 1;
			for (linha = 0; linha < 10; linha++) {
				if (vetorHospitais[linha].qualidadeH.equals("Muito Alta") == true) {
					System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
					System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
					System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
					System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
					System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
					System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
					System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
					System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
					contador = contador + 1;
				}
			}
			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[3].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[5].nomeH;
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] ListarDistanciaTempo(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																														// as
																														// op��es
																														// de
																														// distancia
																														// e
																														// tempo
	{
		int opcao = 0;
		Scanner leia = new Scanner(System.in);

		while (opcao != 1 && opcao != 2) {
			System.out.print("Informe o modo de organizar: \n\n");
			System.out.print("1) Maior distancia e tempo para menor distancia e tempo \n");
			System.out.print("2) Menor distancia e tempo para maior distancia e tempo \n\n");

			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores = MaiorparaMenor(vetorDoadores, quantidade, vetorHospitais);
				break;

			case 2:
				vetorDoadores = MenorparaMaior(vetorDoadores, quantidade, vetorHospitais);
				break;

			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] MaiorparaMenor(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// da
																													// maior
																													// distancia
																													// para
																													// menor
																													// distancia
	{
		int opcao = 0;
		int linha;
		int posicaoA;
		int posicaoB;
		int contador;
		int coluna;
		double[] organizador = new double[10];
		double auxiliar;
		Scanner leia = new Scanner(System.in);

		for (linha = 0; linha < 10; linha++) {
			organizador[linha] = Double.parseDouble(vetorHospitais[linha].distanciaH);
		}
		for (posicaoA = 0; posicaoA < 10; posicaoA++) // Ordena��o de
														// maneira
														// crescente
		{
			for (posicaoB = (posicaoA + 1); posicaoB < 10; posicaoB++) {
				if (organizador[posicaoA] > organizador[posicaoB]) {
					auxiliar = organizador[posicaoB];
					organizador[posicaoB] = organizador[posicaoA];
					organizador[posicaoA] = auxiliar;
				}
			}
		}
		System.out.print("Hospitais Disponiveis: \n\n");

		while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao != 6 && opcao != 7
				&& opcao != 8 && opcao != 9 && opcao != 10) {
			contador = 1;
			for (coluna = 9; coluna >= 0; coluna--) {
				for (linha = 0; linha < 10; linha++) {
					if (Double.parseDouble(vetorHospitais[linha].distanciaH) == organizador[coluna]) {
						System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
						System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
						System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
						System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
						System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
						System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
						System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
						System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
						contador = contador + 1;
					}
				}
			}
			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[8].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[7].nomeH;
				break;

			case 3:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[6].nomeH;
				break;

			case 4:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[4].nomeH;
				break;
			case 5:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[5].nomeH;
				break;
			case 6:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[3].nomeH;
				break;
			case 7:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[2].nomeH;
				break;
			case 8:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[1].nomeH;
				break;
			case 9:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[9].nomeH;
				break;
			case 10:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[0].nomeH;
				break;
			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] MenorparaMaior(Doadores[] vetorDoadores, int quantidade, Hospitais[] vetorHospitais) // Lista
																													// os
																													// hospitais
																													// da
																													// menor
																													// distancia
																													// para
																													// maior
																													// distancia
	{
		int opcao = 0;
		int linha;
		int posicaoA;
		int posicaoB;
		int contador;
		int coluna;
		double[] organizador = new double[10];
		double auxiliar;
		Scanner leia = new Scanner(System.in);

		for (linha = 0; linha < 10; linha++) {
			organizador[linha] = Double.parseDouble(vetorHospitais[linha].distanciaH);
		}
		for (posicaoA = 0; posicaoA < 10; posicaoA++) // Ordena��o de
														// maneira
														// crescente
		{
			for (posicaoB = (posicaoA + 1); posicaoB < 10; posicaoB++) {
				if (organizador[posicaoA] > organizador[posicaoB]) {
					auxiliar = organizador[posicaoB];
					organizador[posicaoB] = organizador[posicaoA];
					organizador[posicaoA] = auxiliar;
				}
			}
		}
		System.out.print("Hospitais Disponiveis: \n\n");

		while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao != 6 && opcao != 7
				&& opcao != 8 && opcao != 9 && opcao != 10) {
			contador = 1;
			for (coluna = 0; coluna < 10; coluna++) {
				for (linha = 0; linha < 10; linha++) {
					if (Double.parseDouble(vetorHospitais[linha].distanciaH) == organizador[coluna]) {
						System.out.print(contador + ") " + vetorHospitais[linha].nomeH + ":\n");
						System.out.print("Qualidade: " + vetorHospitais[linha].qualidadeH + "\n");
						System.out.print("Rua: " + vetorHospitais[linha].ruaH + "\n");
						System.out.print("Cidade: " + vetorHospitais[linha].cidadeH + "\n");
						System.out.print("Bairro: " + vetorHospitais[linha].bairroH + "\n");
						System.out.print("Telefone: " + vetorHospitais[linha].telefoneH + "\n");
						System.out.print("Distancia: " + vetorHospitais[linha].distanciaH + " km\n");
						System.out.print("Tempo: " + vetorHospitais[linha].tempoH + " minutos\n\n");
						contador = contador + 1;
					}
				}
			}
			System.out.print("Escolha uma op��o: \n");
			opcao = leia.nextInt();

			switch (opcao) {
			case 1:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[0].nomeH;
				break;

			case 2:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[9].nomeH;
				break;

			case 3:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[1].nomeH;
				break;

			case 4:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[2].nomeH;
				break;

			case 5:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[3].nomeH;
				break;

			case 6:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[5].nomeH;
				break;
			case 7:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[4].nomeH;
				break;
			case 8:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[6].nomeH;
				break;
			case 9:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[7].nomeH;
				break;
			case 10:
				vetorDoadores[quantidade].hospitalD = vetorHospitais[8].nomeH;
				break;
			default:
				System.out.print("Op��o Inv�lida!\n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] TempodeEspera(Doadores[] vetorDoadores, int quantidade) // Informa
																						// o
																						// tempo
																						// de
																						// espera
																						// para
																						// o
																						// paciente
	{
		int linha;
		int PessoasEspera = 0;
		int PessoasDoadoras = 0;
		int tempo;

		for (linha = 0; linha < quantidade; linha++) {
			if (vetorDoadores[quantidade].tipoSanguineoD.equals("A") == true) // Recebem
																				// das
																				// pessoas:
																				// A
																				// e
																				// O
			{
				if ((vetorDoadores[linha].tipoSanguineoD.equals("A") == true
						|| vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
						&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
						&& (vetorDoadores[linha].statusD.equals("Espera") == true)) {
					PessoasEspera = PessoasEspera + 1;
				}
				if ((vetorDoadores[linha].tipoSanguineoD.equals("A") == true
						|| vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
						&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
						&& (vetorDoadores[linha].statusD.equals("Doador Cad�ver") == true
								|| vetorDoadores[linha].statusD.equals("Doador Vivo") == true)) {
					PessoasDoadoras = PessoasDoadoras + 1;
				}
			} else {
				if (vetorDoadores[quantidade].tipoSanguineoD.equals("B") == true) // Recebem
																					// das
																					// pessoas:
																					// B
																					// e
																					// O
				{
					if ((vetorDoadores[linha].tipoSanguineoD.equals("B") == true
							|| vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
							&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
							&& (vetorDoadores[linha].statusD.equals("Espera") == true)) {
						PessoasEspera = PessoasEspera + 1;
					}
					if ((vetorDoadores[linha].tipoSanguineoD.equals("B") == true
							|| vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
							&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
							&& (vetorDoadores[linha].statusD.equals("Doador Cad�ver") == true
									|| vetorDoadores[linha].statusD.equals("Doador Vivo") == true)) {
						PessoasDoadoras = PessoasDoadoras + 1;
					}
				} else {
					if (vetorDoadores[quantidade].tipoSanguineoD.equals("AB") == true) // Recebem
																						// das
																						// pessoas:
																						// A,
																						// B,
																						// AB
																						// e
																						// O
					{
						if ((vetorDoadores[linha].tipoSanguineoD.equals("A") == true
								|| vetorDoadores[linha].tipoSanguineoD.equals("B") == true
								|| vetorDoadores[linha].tipoSanguineoD.equals("AB") == true
								|| vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
								&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
								&& (vetorDoadores[linha].statusD.equals("Espera") == true)) {
							PessoasEspera = PessoasEspera + 1;
						}
						if ((vetorDoadores[linha].tipoSanguineoD.equals("A") == true
								|| vetorDoadores[linha].tipoSanguineoD.equals("B") == true
								|| vetorDoadores[linha].tipoSanguineoD.equals("AB") == true
								|| vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
								&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
								&& (vetorDoadores[linha].statusD.equals("Doador Cad�ver") == true
										|| vetorDoadores[linha].statusD.equals("Doador Vivo") == true)) {
							PessoasDoadoras = PessoasDoadoras + 1;
						}
					} else {
						if (vetorDoadores[quantidade].tipoSanguineoD.equals("O") == true) // Recebem
																							// das
																							// pessoas:
																							// O
						{
							if ((vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
									&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
									&& (vetorDoadores[linha].statusD.equals("Espera") == true)) {
								PessoasEspera = PessoasEspera + 1;
							}
							if ((vetorDoadores[linha].tipoSanguineoD.equals("O") == true)
									&& (vetorDoadores[linha].orgaoD.equals(vetorDoadores[quantidade].orgaoD) == true)
									&& (vetorDoadores[linha].statusD.equals("Doador Cad�ver") == true
											|| vetorDoadores[linha].statusD.equals("Doador Vivo") == true)) {
								PessoasDoadoras = PessoasDoadoras + 1;
							}
						}
					}
				}
			}
		}

		tempo = PessoasEspera - PessoasDoadoras;
		System.out.print("Doadores disponiveis: " + PessoasDoadoras + "\n");
		System.out.print("Fila de Espera: " + PessoasEspera + "\n\n");
		if (tempo > 0) {
			System.out
					.print("O tempo de espera m�dio para a pessoa receber o org�o �: " + tempo + " meses. \n\n");
		} else {
			if (tempo == 0) {
				System.out.print(
						"O tempo de espera m�dio para a pessoa receber o org�o � indeterminado, sentimos muito por isso.\n\n");
			} else {
				System.out.print(
						"Entre em contato conosco pois brevemente a pessoa estara recebendo o novo org�o!!! \n\n");
			}
		}
		return vetorDoadores;
	}

	public static Doadores[] salvarArquivo(Doadores[] vetorDoadores, int quantidade) throws IOException // Salva
																										// o
																										// conteudo
	{
		int linha;

		FileWriter caminhoDoadores = new FileWriter("Arquivos/Doadores e Fila de Espera.txt");
		PrintWriter gravarArquivo = new PrintWriter(caminhoDoadores);
		for (linha = 0; linha < quantidade; linha++) {
			gravarArquivo.printf(vetorDoadores[linha].nomeD + ";");
			gravarArquivo.printf(vetorDoadores[linha].sexoD + ";");
			gravarArquivo.printf(vetorDoadores[linha].idadeD + ";");
			gravarArquivo.printf(vetorDoadores[linha].tipoSanguineoD + ";");
			gravarArquivo.printf(vetorDoadores[linha].orgaoD + ";");
			gravarArquivo.printf(vetorDoadores[linha].hospitalD + ";");
			gravarArquivo.printf(vetorDoadores[linha].statusD + ";%n");
		}
		caminhoDoadores.close();
		return vetorDoadores;
	}
}