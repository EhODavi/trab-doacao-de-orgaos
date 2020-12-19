programa
{	
	inclua biblioteca Arquivos --> arq
	inclua biblioteca Texto --> texto
	inclua biblioteca Tipos --> tipo
	
	funcao inicio()
	{
		inteiro quantidade, opcao = 0, linha, coluna
		cadeia Hospitais[10][8], Doadores[100][7], teclado
		cadeia caminhoHospitais = "/Hospitais.txt"
		cadeia caminhoDoadores = "/Doadores e Fila de Espera.txt"
		
		quantidade = carregarDoadores(caminhoDoadores)
		preencherHospitais(caminhoHospitais,Hospitais)
		preencherDoadores(caminhoDoadores,Doadores)
		
		escreva("Seja bem-vindo ao novo programa de doa��es de org�os da regi�o do Vale do A�o \n\n")
		enquanto (opcao != 3)
		{
			escreva("Escolha o que voc� deseja: \n")
			escreva("1) Doar org�os \n")
			escreva("2) Entrar na fila de espera para receber org�os \n")
			escreva("3) Sair \n\n")

			escreva("Escolha uma op��o: \n")
			leia(opcao)

			limpa()

			escolha (opcao)
			{
				caso 1:
					se(quantidade < 100)
					{
			 			adicionarDoadorEspera(Doadores, quantidade)	
			 			escreva("Cadastro feito com sucesso! \n")		 		
					}
					senao
					{
						escreva("\nA mem�ria do programa est� cheia\n")	
					}
					
					escreva("\nPressione qualquer tecla para continuar...\n")	
					leia(teclado)				
					limpa()
					pare
				caso 2:
					se(quantidade < 100)
					{
						Doadores[quantidade][6] = "Espera"
			 			adicionarDoadorEspera(Doadores, quantidade)	
			 			escreva("Cadastro feito com sucesso! \n")		 		
					}
					senao
					{
						escreva("\nA mem�ria do programa est� cheia\n")	
					}
					escreva("\nPressione qualquer tecla para continuar...\n")	
					leia(teclado)				
					limpa()
					pare
				caso 3:
					salvarArquivo(caminhoDoadores, Doadores, quantidade)
			 		escreva ("Sistema encerrado com sucesso! \n\n")
					pare
				caso contrario:
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
		limpa()
		escreva("Obrigado por utilizar o nosso programa!!!")
	}
	funcao inteiro carregarDoadores(cadeia caminhoDoadores) //Essa fun��o verifica se o arquivo esta cheio 
	{
		inteiro arquivoDoadores = arq.abrir_arquivo(caminhoDoadores, arq.MODO_LEITURA)
		inteiro quantidade = 0, coluna
		cadeia linha

		para (coluna = 0; coluna < 100; coluna++)
		{
			linha = arq.ler_linha(arquivoDoadores)
			se (texto.numero_caracteres(linha) > 0)
			{
				quantidade = quantidade + 1
			}
		}
		arq.fechar_arquivo(arquivoDoadores)
		retorne quantidade
	}
	funcao preencherHospitais(cadeia caminhoHospitais,cadeia &Hospitais[][]) //Preenche a matriz dos hospitais
	{
		inteiro arquivoHospitais = arq.abrir_arquivo(caminhoHospitais, arq.MODO_LEITURA)
		inteiro linha, coluna, posInicio, posFim
		cadeia conteudoLinha, extrairLinha

		para (linha = 0; linha < 10; linha++)
		{
			conteudoLinha = arq.ler_linha(arquivoHospitais)
			se (texto.numero_caracteres(conteudoLinha) > 0)
			{
				posInicio = 0
				posFim = 0
				para (coluna = 0; coluna < 8; coluna++)
				{
					posFim = texto.posicao_texto(";", conteudoLinha, posInicio)
					extrairLinha = texto.extrair_subtexto(conteudoLinha, posInicio, posFim)
					Hospitais[linha][coluna] = extrairLinha
					posInicio = posFim + 1
				}
			}
		}
		arq.fechar_arquivo(arquivoHospitais)
	}
	funcao preencherDoadores(cadeia caminhoDoadores,cadeia &Doadores[][]) //Preenche a matriz dos Doadores
	{
		inteiro arquivoDoadores = arq.abrir_arquivo(caminhoDoadores, arq.MODO_LEITURA)
		inteiro linha, coluna, posInicio, posFim
		cadeia conteudoLinha, extrairLinha

		para (linha = 0; linha < 100; linha++)
		{
			para (coluna = 0; coluna < 7; coluna++)
			{
				Doadores[linha][coluna] = "-"
			}
		}
		para (linha = 0; linha < 100; linha++)
		{
			conteudoLinha = arq.ler_linha(arquivoDoadores)
			se (texto.numero_caracteres(conteudoLinha) > 0)
			{
				posInicio = 0
				posFim = 0
				para (coluna = 0; coluna < 7; coluna++)
				{
					posFim = texto.posicao_texto(";", conteudoLinha, posInicio)
					extrairLinha = texto.extrair_subtexto(conteudoLinha, posInicio, posFim)
					Doadores[linha][coluna] = extrairLinha
					posInicio = posFim + 1
				}
			}
		}
		arq.fechar_arquivo(arquivoDoadores)
	}
	funcao adicionarDoadorEspera(cadeia &Doadores[][], inteiro &quantidade) // Adiciona Doadores e Recebedores
	{
		inteiro opcao = 0
		
		se (quantidade < 100)
		{
			escreva("Qual � o nome completo da pessoa? \n")
			leia(Doadores[quantidade][0])
			limpa()
			enquanto (opcao != 1 e opcao != 2)
			{
				escreva("Qual � o genero da pessoa? \n\n")
				escreva("1) Masculino \n")
				escreva("2) Feminino \n\n")

				escreva("Escolha uma op��o: \n")
				leia(opcao)
				
				escolha (opcao)
				{
					caso 1:
						Doadores[quantidade][1] = "Masculino"
						pare
					caso 2:
						Doadores[quantidade][1] = "Feminino"
						pare
					caso contrario:
						limpa()
						escreva ("Op��o Inv�lida!\n\n")
				}
			}
			limpa()
			Doadores[quantidade][2] = "0"
			enquanto (tipo.cadeia_para_real(Doadores[quantidade][2]) <= 0 ou 
					tipo.cadeia_para_real(Doadores[quantidade][2]) >= 118)
			{
				escreva("Qual a idade da pessoa? \n")
				leia(Doadores[quantidade][2])
				se (tipo.cadeia_para_real(Doadores[quantidade][2]) <= 0)
				{
					limpa()
					escreva("N�o � poss�vel ter uma idade negativa ou nula! \n")
				}
				senao
				se (tipo.cadeia_para_real(Doadores[quantidade][2]) >= 118)
				{
					limpa()
					escreva("N�o � poss�vel ter uma idade maior ou igual a 118 anos pois a pessoa mais velha atualmente possui 117 anos! \n")
				}
			}
			limpa()
			opcao = 0
			enquanto (opcao != 1 e opcao != 2 e opcao != 3 e opcao != 4)
			{
				escreva("Qual � o tipo sanguineo da pessoa? \n\n")
				escreva("1) Tipo A \n")
				escreva("2) Tipo B \n")
				escreva("3) Tipo AB \n")
				escreva("4) Tipo O \n\n")
				
				escreva("Escolha uma op��o: \n")
				leia(opcao)

				escolha (opcao)
				{
					caso 1:
						Doadores[quantidade][3] = "A"
						pare
					caso 2:
						Doadores[quantidade][3] = "B"
						pare
					caso 3:
						Doadores[quantidade][3] = "AB"
						pare
					caso 4:
						Doadores[quantidade][3] = "O"
						pare
					caso contrario:
						limpa()
						escreva ("Op��o Inv�lida!\n\n")
				}
			}
			limpa()
			opcao = 0
			se (Doadores[quantidade][6] != "Espera")
			{
				enquanto (opcao != 1 e opcao != 2)
				{
					escreva("Informe o status da pessoa cadastrada: \n\n")
					escreva("1) Doador Cad�ver \n")
					escreva("2) Doador Vivo \n\n")
					
					escreva("Escolha uma op��o: \n")
					leia(opcao)
	
					escolha (opcao)
					{
						caso 1:
							limpa()
							Doadores[quantidade][6] = "Doador Cad�ver"
							pare
						caso 2:
							limpa()
							Doadores[quantidade][6] = "Doador Vivo"
							DoadorVivo(Doadores, quantidade)
							limpa()
							pare
						caso contrario:
							limpa()
							escreva ("Op��o Inv�lida!\n\n")
					}
				}
			}
			se (Doadores[quantidade][6] == "Doador Cad�ver" ou Doadores[quantidade][6] == "Espera")
			{
				DoadorCadaverEspera(Doadores, quantidade)
				limpa()
			}
			
			opcao = 0
			se (Doadores[quantidade][6] == "Espera")
			{
				escreva("Agora voc� escolhera em qual hospital deseja receber: \n\n")
			}
			senao
			{
				escreva("Agora voc� escolhera em qual hospital deseja doar: \n\n")
			}
			enquanto (opcao != 1 e opcao != 2 e opcao != 3 e opcao != 4)
			{
				ListarOpcoesHospitais(opcao)
				escolha (opcao)
				{
				caso 1:
					ListarHospitais(Doadores, quantidade)
					limpa()
					pare
				caso 2:
					ListarPorCidades(Doadores, quantidade)
					limpa()
					pare
				caso 3:
					ListarQualidade(Doadores, quantidade)
					limpa()
					pare
				caso 4:
					ListarDistanciaTempo(Doadores, quantidade)
					limpa()
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
				}
			}
			se (Doadores[quantidade][6] == "Espera")
			{
				TempodeEspera(Doadores, quantidade)
			}
			quantidade = quantidade + 1
		}
	}
	funcao DoadorCadaverEspera(cadeia &Doadores[][], inteiro quantidade) // Seleciona os org�os que o doador pode doar ou a pessoa pode receber
	{
		inteiro opcao = 0
		
		enquanto (opcao != 1 e opcao != 2 e opcao != 3 e 
				opcao != 4 e opcao != 5 e opcao != 6 e 
				opcao != 7 e opcao != 8 e opcao != 9 e 
				opcao != 10)
		{
			se (Doadores[quantidade][6] == "Doador Cad�ver")
			{
				escreva("Informe o org�o que a pessoa cadastrada deseja doar (atualmente o programa aceita que cada pessoa doe apenas um org�o): \n\n")
			}
			senao
			{
				escreva("Informe o org�o que a pessoa cadastrada deseja receber (atualmente o programa aceita que cada pessoa receba apenas um org�o): \n\n")
			}
			escreva("1) Cora��o \t")
			escreva("2) P�ncreas \n")
			escreva("3) Rim \t\t")
			escreva("4) Intestino \n")
			escreva("5) C�rneas \t")
			escreva("6) Ossos \n")
			escreva("7) Veias \t")
			escreva("8) Tend�o \n")
			escreva("9) Pulm�o \t")
			escreva("10) F�gado \n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					Doadores[quantidade][4] = "Cora��o"
					pare
				caso 2:
					Doadores[quantidade][4] = "P�ncreas"
					pare
				caso 3:
					Doadores[quantidade][4] = "Rim"
					pare
				caso 4:
					Doadores[quantidade][4] = "Intestino"
					pare
				caso 5:
					Doadores[quantidade][4] = "C�rneas"
					pare
				caso 6:
					Doadores[quantidade][4] = "Ossos"
					pare
				caso 7:
					Doadores[quantidade][4] = "Veias"
					pare
				caso 8:
					Doadores[quantidade][4] = "Tend�o"
					pare
				caso 9:
					Doadores[quantidade][4] = "Pulm�o"
					pare
				caso 10:
					Doadores[quantidade][4] = "F�gado"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao DoadorVivo(cadeia &Doadores[][], inteiro quantidade) // Seleciona os org�os que o doador pode doar
	{
		inteiro opcao = 0
		
		enquanto (opcao != 1 e opcao != 2 e opcao != 3)
		{
			escreva("Informe o org�o que a pessoa cadastrada deseja doar (atualmente o programa aceita que cada pessoa doe apenas um org�o): \n\n")
			escreva("1) F�gado \n")
			escreva("2) Pulm�o\n")
			escreva("3) Rim \n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					Doadores[quantidade][4] = "F�gado"
					pare
				caso 2:
					Doadores[quantidade][4] = "Pulm�o"
					pare
				caso 3:
					Doadores[quantidade][4] = "Rim"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarOpcoesHospitais(inteiro &opcao) // Lista as op��es
	{
		escreva("Informe o modo para ordenar os hospitais: \n\n")
		escreva("1) Listar todos os hospitais disponiveis \n")
		escreva("2) Listar os hospitais por cidades \n")
		escreva("3) Listar os hospitais por qualidade \n")
		escreva("4) Listar os hospitais por distancia e tempo \n\n")
		
		escreva("Escolha uma op��o: \n")
		leia(opcao)
	}
	funcao ListarHospitais(cadeia &Doadores[][], inteiro quantidade) // Lista todos os hospitais
	{
		inteiro opcao = 0
		
		enquanto (opcao != 1 e opcao != 2 e opcao != 3 e 
				opcao != 4 e opcao != 5 e opcao != 6 e 
				opcao != 7 e opcao != 8 e opcao != 9 e 
				opcao != 10)
		{
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital S�o Camilo - Tim�teo          |Alta      |Rua Jos� Julio Lage          |Tim�teo        |Timirim        |(31) 3849-9500|1,9 km   |6 min |\n")
			escreva("2) |Hospital S�o Camilo - Cel. Fabriciano  |Alta      |Rua Argemiro Jos� Ribeiro    |Cel. Fabriciano|Santa Helena   |(31) 3865-1800|6,7 km   |14 min|\n")
			escreva("3) |Hospital S�o Lucas                     |Alta      |Rua Fernando Pinheiro d'�vila|Cel. Fabriciano|Santa Terezinha|(31) 3842-6219|8,2 km   |16 min|\n")
			escreva("4) |Hospital M�rcio Cunha I                |Muito Alta|Rua G�spar de Lemos          |Ipatinga       |Bom Retiro     |(31) 3829-9000|15,6 km  |24 min|\n")
			escreva("5) |Hospital UNIMED                        |Alta      |Rua Novo Hamburgo            |Ipatinga       |Veneza         |(31) 3841-9801|20,3 km  |29 min|\n")
			escreva("6) |Hospital M�rcio Cunha II               |Muito Alta|Avenida Kiyoshi Tsunawaki    |Ipatinga       |Das �guas      |(31) 3829-9000|19,0 km  |27 min|\n")
			escreva("7) |Hospital de Olhos Vale do A�o          |Alta      |Avenida Zita de Oliveira     |Ipatinga       |Novo Centro    |(31) 3801-1800|21,7 km  |33 min|\n")
			escreva("8) |Hospital Municipal de Ipatinga         |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,4 km  |37 min|\n")
			escreva("9) |Pronto Socorro Municipal               |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,7 km  |38 min|\n")
			escreva("10)|Pronto Atendimento - Unimed Vale do A�o|Alta      |Rua Ipanema                  |Cel. Fabriciano|Centro         |(31) 3841-9805|5,7 km   |11 min|\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("\nEscolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					Doadores[quantidade][5] = "Hospital S�o Camilo - Tim�teo"
					pare
				caso 2:
					Doadores[quantidade][5] = "Hospital S�o Camilo - Cel. Fabriciano"
					pare
				caso 3:
					Doadores[quantidade][5] = "Hospital S�o Lucas"
					pare
				caso 4:
					Doadores[quantidade][5] = "Hospital M�rcio Cunha I"
					pare
				caso 5:
					Doadores[quantidade][5] = "Hospital UNIMED"
					pare
				caso 6:
					Doadores[quantidade][5] = "Hospital M�rcio Cunha II"
					pare
				caso 7:
					Doadores[quantidade][5] = "Hospital de Olhos Vale do A�o"
					pare
				caso 8:
					Doadores[quantidade][5] = "Hospital Municipal de Ipatinga"
					pare
				caso 9:
					Doadores[quantidade][5] = "Pronto Socorro Municipal"
					pare
				caso 10:
					Doadores[quantidade][5] = "Pronto Atendimento - Unimed Vale do A�o"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarPorCidades(cadeia &Doadores[][], inteiro quantidade)// Lista os hospitais de acordo com a cidade que o usuario selecionou
	{
		inteiro opcao = 0
		limpa()
		
		enquanto (opcao != 1 e opcao != 2 e opcao != 3)
		{
			escreva("Informe a cidade: \n\n")
			escreva("1) Tim�teo \n")
			escreva("2) Cel. Fabriciano \n")
			escreva("3) Ipatinga \n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					limpa()
					ListarTimoteo(Doadores,quantidade)
					pare
				caso 2:
					limpa()
					ListarFabriciano(Doadores,quantidade)
					pare
				caso 3:
					limpa()
					ListarIpatinga(Doadores,quantidade)
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarTimoteo(cadeia &Doadores[][], inteiro quantidade) // Lista os hospitais de Tim�teo
	{
		inteiro opcao = 0
		limpa()
		
		enquanto (opcao != 1)
		{
			escreva("Informe a op��o: \n\n")
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital S�o Camilo - Tim�teo          |Alta      |Rua Jos� Julio Lage          |Tim�teo        |Timirim        |(31) 3849-9500|1,9 km   |6 min |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n\n")
			escreva("Escolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					limpa()
					Doadores[quantidade][5] = "Hospital S�o Camilo - Tim�teo"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarFabriciano(cadeia &Doadores[][], inteiro quantidade) // Lista os hospitais de Fabriciano
	{
		inteiro opcao = 0

		enquanto (opcao != 1 e opcao != 2 e opcao != 3)
		{
			escreva("Informe a op��o: \n\n")
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital S�o Camilo - Cel. Fabriciano  |Alta      |Rua Argemiro Jos� Ribeiro    |Cel. Fabriciano|Santa Helena   |(31) 3865-1800|6,7 km   |14 min|\n")
			escreva("2) |Hospital S�o Lucas                     |Alta      |Rua Fernando Pinheiro d'�vila|Cel. Fabriciano|Santa Terezinha|(31) 3842-6219|8,2 km   |16 min|\n")
			escreva("3) |Pronto Atendimento - Unimed Vale do A�o|Alta      |Rua Ipanema                  |Cel. Fabriciano|Centro         |(31) 3841-9805|5,7 km   |11 min|\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n\n")
			escreva("Escolha uma op��o: \n")
			leia(opcao)
			escolha (opcao)
			{
				caso 1:
					limpa()
					Doadores[quantidade][5] = "Hospital S�o Camilo - Cel. Fabriciano"
					pare
				caso 2:
					limpa()
					Doadores[quantidade][5] = "Hospital S�o Lucas"
					pare
				caso 3:
					limpa()
					Doadores[quantidade][5] = "Pronto Atendimento - Unimed Vale do A�o"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarIpatinga(cadeia &Doadores[][],inteiro quantidade) // Lista os hospitais de Ipatinga
	{
		inteiro opcao = 0

		enquanto (opcao != 1 e opcao != 2 e opcao != 3 e opcao != 4 e opcao != 5 e opcao != 6)
		{
			escreva("Informe a op��o: \n\n")
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital M�rcio Cunha I                |Muito Alta|Rua G�spar de Lemos          |Ipatinga       |Bom Retiro     |(31) 3829-9000|15,6 km  |24 min|\n")
			escreva("2) |Hospital UNIMED                        |Alta      |Rua Novo Hamburgo            |Ipatinga       |Veneza         |(31) 3841-9801|20,3 km  |29 min|\n")
			escreva("3) |Hospital M�rcio Cunha II               |Muito Alta|Avenida Kiyoshi Tsunawaki    |Ipatinga       |Das �guas      |(31) 3829-9000|19,0 km  |27 min|\n")
			escreva("4) |Hospital de Olhos Vale do A�o          |Alta      |Avenida Zita de Oliveira     |Ipatinga       |Novo Centro    |(31) 3801-1800|21,7 km  |33 min|\n")
			escreva("5) |Hospital Municipal de Ipatinga         |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,4 km  |37 min|\n")
			escreva("6) |Pronto Socorro Municipal               |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,7 km  |38 min|\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)
			
			escolha (opcao)
			{
				caso 1:
					limpa()
					Doadores[quantidade][5] = "Hospital M�rcio Cunha I"
					pare
				caso 2:
					limpa()
					Doadores[quantidade][5] = "Hospital UNIMED"
					pare
				caso 3:
					limpa()
					Doadores[quantidade][5] = "Hospital M�rcio Cunha II"
					pare
				caso 4:
					limpa()
					Doadores[quantidade][5] = "Hospital de Olhos Vale do A�o"
					pare
				caso 5:
					limpa()
					Doadores[quantidade][5] = "Hospital Municipal de Ipatinga"
					pare
				caso 6:
					limpa()
					Doadores[quantidade][5] = "Pronto Socorro Municipal"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarQualidade(cadeia &Doadores[][], inteiro quantidade) // Lista os hospitais de acordo com a qualidade
	{
		inteiro opcao = 0
		limpa()
		
		enquanto (opcao != 1 e opcao != 2)
		{
			escreva("Informe a qualidade: \n\n")
			escreva("1) Alta \n")
			escreva("2) Muito Alta \n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					limpa()
					ListarAlta(Doadores,quantidade)
					pare
				caso 2:
					limpa()
					ListarMuitoAlta(Doadores,quantidade)
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarAlta(cadeia &Doadores[][], inteiro quantidade) // lista os hospitais de qualidade alta
	{
		inteiro opcao = 0

		enquanto (opcao != 1 e opcao != 2 e opcao != 3 e opcao != 4 e 
				opcao != 5 e opcao != 6 e opcao != 7 e opcao != 8 )
		{
			escreva("Informe a op��o: \n\n")
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital S�o Camilo - Tim�teo          |Alta      |Rua Jos� Julio Lage          |Tim�teo        |Timirim        |(31) 3849-9500|1,9 km   |6 min |\n")
			escreva("2) |Hospital S�o Camilo - Cel. Fabriciano  |Alta      |Rua Argemiro Jos� Ribeiro    |Cel. Fabriciano|Santa Helena   |(31) 3865-1800|6,7 km   |14 min|\n")
			escreva("3) |Hospital S�o Lucas                     |Alta      |Rua Fernando Pinheiro d'�vila|Cel. Fabriciano|Santa Terezinha|(31) 3842-6219|8,2 km   |16 min|\n")
			escreva("4) |Hospital UNIMED                        |Alta      |Rua Novo Hamburgo            |Ipatinga       |Veneza         |(31) 3841-9801|20,3 km  |29 min|\n")
			escreva("5) |Hospital de Olhos Vale do A�o          |Alta      |Avenida Zita de Oliveira     |Ipatinga       |Novo Centro    |(31) 3801-1800|21,7 km  |33 min|\n")
			escreva("6) |Hospital Municipal de Ipatinga         |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,4 km  |37 min|\n")
			escreva("7) |Pronto Socorro Municipal               |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,7 km  |38 min|\n")
			escreva("8) |Pronto Atendimento - Unimed Vale do A�o|Alta      |Rua Ipanema                  |Cel. Fabriciano|Centro         |(31) 3841-9805|5,7 km   |11 min|\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)
			
			escolha (opcao)
			{
				caso 1:
					limpa()
					Doadores[quantidade][5] = "Hospital S�o Camilo - Tim�teo"
					pare
				caso 2:
					limpa()
					Doadores[quantidade][5] = "Hospital S�o Camilo - Cel. Fabriciano"
					pare
				caso 3:
					limpa()
					Doadores[quantidade][5] = "Hospital S�o Lucas"
					pare
				caso 4:
					limpa()
					Doadores[quantidade][5] = "Hospital UNIMED"
					pare
				caso 5:
					limpa()
					Doadores[quantidade][5] = "Hospital de Olhos Vale do A�o"
					pare
				caso 6:
					limpa()
					Doadores[quantidade][5] = "Hospital Municipal de Ipatinga"
					pare
				caso 7:
					limpa()
					Doadores[quantidade][5] = "Pronto Socorro Municipal"
					pare
				caso 8:
					limpa()
					Doadores[quantidade][5] = "Pronto Atendimento - Unimed Vale do A�o"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarMuitoAlta(cadeia &Doadores[][], inteiro quantidade) // Lista os hospitais de qualidade muito alta
	{
		inteiro opcao = 0

		enquanto (opcao != 1 e opcao != 2)
		{
			escreva("Informe a op��o: \n\n")
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital M�rcio Cunha I                |Muito Alta|Rua G�spar de Lemos          |Ipatinga       |Bom Retiro     |(31) 3829-9000|15,6 km  |24 min|\n")
			escreva("2) |Hospital M�rcio Cunha II               |Muito Alta|Avenida Kiyoshi Tsunawaki    |Ipatinga       |Das �guas      |(31) 3829-9000|19,0 km  |27 min|\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)
			
			escolha (opcao)
			{
				caso 1:
					limpa()
					Doadores[quantidade][5] = "Hospital M�rcio Cunha I"
					pare
				caso 2:
					limpa()
					Doadores[quantidade][5] = "Hospital M�rcio Cunha II"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao ListarDistanciaTempo(cadeia &Doadores[][], inteiro quantidade) // Lista as op��es de distancia e tempo
	{
		inteiro opcao = 0
		limpa()
		
		enquanto (opcao != 1 e opcao != 2)
		{
			escreva("Informe o modo de organizar: \n\n")
			escreva("1) Maior distancia e tempo para menor distancia e tempo \n")
			escreva("2) Menor distancia e tempo para maior distancia e tempo \n\n")
			
			escreva("Escolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					limpa()
					MaiorparaMenor(Doadores,quantidade)
					pare
				caso 2:
					limpa()
					MenorparaMaior(Doadores,quantidade)
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao MaiorparaMenor(cadeia &Doadores[][], inteiro quantidade) // Lista os hospitais da maior distancia para menor distancia
	{
		inteiro opcao = 0
		
		enquanto (opcao != 1 e opcao != 2 e opcao != 3 e 
				opcao != 4 e opcao != 5 e opcao != 6 e 
				opcao != 7 e opcao != 8 e opcao != 9 e 
				opcao != 10)
		{
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Pronto Socorro Municipal               |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,7 km  |38 min|\n")
			escreva("2) |Hospital Municipal de Ipatinga         |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,4 km  |37 min|\n")
			escreva("3) |Hospital de Olhos Vale do A�o          |Alta      |Avenida Zita de Oliveira     |Ipatinga       |Novo Centro    |(31) 3801-1800|21,7 km  |33 min|\n")
			escreva("4) |Hospital UNIMED                        |Alta      |Rua Novo Hamburgo            |Ipatinga       |Veneza         |(31) 3841-9801|20,3 km  |29 min|\n")
			escreva("5) |Hospital M�rcio Cunha II               |Muito Alta|Avenida Kiyoshi Tsunawaki    |Ipatinga       |Das �guas      |(31) 3829-9000|19,0 km  |27 min|\n")
			escreva("6) |Hospital M�rcio Cunha I                |Muito Alta|Rua G�spar de Lemos          |Ipatinga       |Bom Retiro     |(31) 3829-9000|15,6 km  |24 min|\n")
			escreva("7) |Hospital S�o Lucas                     |Alta      |Rua Fernando Pinheiro d'�vila|Cel. Fabriciano|Santa Terezinha|(31) 3842-6219|8,2 km   |16 min|\n")
			escreva("8) |Hospital S�o Camilo - Cel. Fabriciano  |Alta      |Rua Argemiro Jos� Ribeiro    |Cel. Fabriciano|Santa Helena   |(31) 3865-1800|6,7 km   |14 min|\n")
			escreva("9) |Pronto Atendimento - Unimed Vale do A�o|Alta      |Rua Ipanema                  |Cel. Fabriciano|Centro         |(31) 3841-9805|5,7 km   |11 min|\n")
			escreva("10)|Hospital S�o Camilo - Tim�teo          |Alta      |Rua Jos� Julio Lage          |Tim�teo        |Timirim        |(31) 3849-9500|1,9 km   |6 min |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			
			escreva("\nEscolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					Doadores[quantidade][5] = "Pronto Socorro Municipal"
					pare
				caso 2:
					Doadores[quantidade][5] = "Hospital Municipal de Ipatinga"
					pare
				caso 3:
					Doadores[quantidade][5] = "Hospital de Olhos Vale do A�o"
					pare
				caso 4:
					Doadores[quantidade][5] = "Hospital UNIMED"
					pare
				caso 5:
					Doadores[quantidade][5] = "Hospital M�rcio Cunha II"
					pare
				caso 6:
					Doadores[quantidade][5] = "Hospital M�rcio Cunha I"
					pare
				caso 7:
					Doadores[quantidade][5] = "Hospital S�o Lucas"
					pare
				caso 8:
					Doadores[quantidade][5] = "Hospital S�o Camilo - Cel. Fabriciano"
					pare
				caso 9:
					Doadores[quantidade][5] = "Pronto Atendimento - Unimed Vale do A�o"
					pare
				caso 10:
					Doadores[quantidade][5] = "Hospital S�o Camilo - Tim�teo"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao MenorparaMaior(cadeia &Doadores[][], inteiro quantidade) // Lista os hospitais da menor distancia para maior distancia
	
	{
		inteiro opcao = 0
		
		enquanto (opcao != 1 e opcao != 2 e opcao != 3 e 
				opcao != 4 e opcao != 5 e opcao != 6 e 
				opcao != 7 e opcao != 8 e opcao != 9 e 
				opcao != 10)
		{
			escreva("    ________________________________________________________________________________________________________________________________________________\n") 
			escreva("   |Nome do Hospital                       |Qualidade |Rua                          |Cidade         |Bairro         |Telefone      |Distancia|Tempo |\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			escreva("1) |Hospital S�o Camilo - Tim�teo          |Alta      |Rua Jos� Julio Lage          |Tim�teo        |Timirim        |(31) 3849-9500|1,9 km   |6 min |\n")
			escreva("2) |Pronto Atendimento - Unimed Vale do A�o|Alta      |Rua Ipanema                  |Cel. Fabriciano|Centro         |(31) 3841-9805|5,7 km   |11 min|\n")
			escreva("3) |Hospital S�o Camilo - Cel. Fabriciano  |Alta      |Rua Argemiro Jos� Ribeiro    |Cel. Fabriciano|Santa Helena   |(31) 3865-1800|6,7 km   |14 min|\n")
			escreva("4) |Hospital S�o Lucas                     |Alta      |Rua Fernando Pinheiro d'�vila|Cel. Fabriciano|Santa Terezinha|(31) 3842-6219|8,2 km   |16 min|\n")
			escreva("5) |Hospital M�rcio Cunha I                |Muito Alta|Rua G�spar de Lemos          |Ipatinga       |Bom Retiro     |(31) 3829-9000|15,6 km  |24 min|\n")
			escreva("6) |Hospital M�rcio Cunha II               |Muito Alta|Avenida Kiyoshi Tsunawaki    |Ipatinga       |Das �guas      |(31) 3829-9000|19,0 km  |27 min|\n")
			escreva("7) |Hospital UNIMED                        |Alta      |Rua Novo Hamburgo            |Ipatinga       |Veneza         |(31) 3841-9801|20,3 km  |29 min|\n")
			escreva("8) |Hospital de Olhos Vale do A�o          |Alta      |Avenida Zita de Oliveira     |Ipatinga       |Novo Centro    |(31) 3801-1800|21,7 km  |33 min|\n")
			escreva("9) |Hospital Municipal de Ipatinga         |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,4 km  |37 min|\n")
			escreva("10)|Pronto Socorro Municipal               |Alta      |Avenida Felipe dos Santos    |Ipatinga       |Cidade Nobre   |(31) 3828-5600|22,7 km  |38 min|\n")
			escreva("   |_______________________________________|__________|_____________________________|_______________|_______________|______________|_________|______|\n")
			
			escreva("\nEscolha uma op��o: \n")
			leia(opcao)

			escolha (opcao)
			{
				caso 1:
					Doadores[quantidade][5] = "Hospital S�o Camilo - Tim�teo"
					pare
				caso 2:
					Doadores[quantidade][5] = "Pronto Atendimento - Unimed Vale do A�o"
					pare
				caso 3:
					Doadores[quantidade][5] = "Hospital S�o Camilo - Cel. Fabriciano"
					pare
				caso 4:
					Doadores[quantidade][5] = "Hospital S�o Lucas"
					pare
				caso 5:
					Doadores[quantidade][5] = "Hospital M�rcio Cunha I"
					pare
				caso 6:
					Doadores[quantidade][5] = "Hospital M�rcio Cunha II"
					pare
				caso 7:
					Doadores[quantidade][5] = "Hospital UNIMED"
					pare
				caso 8:
					Doadores[quantidade][5] = "Hospital de Olhos Vale do A�o"
					pare
				caso 9:
					Doadores[quantidade][5] = "Hospital Municipal de Ipatinga"
					pare
				caso 10:
					Doadores[quantidade][5] = "Pronto Socorro Municipal"
					pare
				caso contrario:
					limpa()
					escreva ("Op��o Inv�lida!\n\n")
			}
		}
	}
	funcao TempodeEspera(cadeia Doadores[][], inteiro quantidade) // Informa o tempo de espera para o paciente
	{
		inteiro linha, PessoasEspera = 0, PessoasDoadoras = 0, tempo

		para (linha = 0; linha < quantidade - 1; linha++)
		{
			se (Doadores[quantidade][3] == "A") // Recebem das pessoas: A e O
			{
				se((Doadores[linha][3] == "A" ou Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Espera"))
				{
					PessoasEspera = PessoasEspera + 1
				}
				se((Doadores[linha][3] == "A" ou Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Doador Cad�ver" ou  Doadores[linha][6] == "Doador Vivo"))
				{
					PessoasDoadoras = PessoasDoadoras + 1
				}
			}
			senao
			{
				se (Doadores[quantidade][3] == "B") // Recebem das pessoas: B e O
				{
					se((Doadores[linha][3] == "B" ou Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Espera"))
					{
						PessoasEspera = PessoasEspera + 1
					}
					se((Doadores[linha][3] == "B" ou Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Doador Cad�ver" ou  Doadores[linha][6] == "Doador Vivo"))
					{
						PessoasDoadoras = PessoasDoadoras + 1
					}
				}
				senao
				{
					se (Doadores[quantidade][3] == "AB") // Recebem das pessoas: A, B, AB e O
					{
						se((Doadores[linha][3] == "A" ou Doadores[linha][3] == "B" ou Doadores[linha][3] == "AB" ou Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Espera"))
						{
							PessoasEspera = PessoasEspera + 1
						}
						se((Doadores[linha][3] == "A" ou Doadores[linha][3] == "B" ou Doadores[linha][3] == "AB" ou Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Doador Cad�ver" ou  Doadores[linha][6] == "Doador Vivo"))
						{
							PessoasDoadoras = PessoasDoadoras + 1
						}
					}
					senao
					{
						se (Doadores[quantidade][3] == "O") // Recebem das pessoas: O
						{
							se((Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Espera"))
							{
								PessoasEspera = PessoasEspera + 1
							}
							se((Doadores[linha][3] == "O") e (Doadores[linha][4] == Doadores[quantidade][4]) e (Doadores[linha][6] == "Doador Cad�ver" ou  Doadores[linha][6] == "Doador Vivo"))
							{
								PessoasDoadoras = PessoasDoadoras + 1
							}
						}
					}
				}
			}
		}
		
		tempo = PessoasEspera - PessoasDoadoras
		escreva("Doadores disponiveis: ",PessoasDoadoras,"\n")
		escreva("Fila de Espera: ",PessoasEspera,"\n\n")
		se (tempo > 0)
		{
			escreva("O tempo de espera m�dio para a pessoa receber o org�o �: ",tempo," meses. \n\n")
		}
		senao
		{
			se (tempo == 0)
			{
				escreva("O tempo de espera m�dio para a pessoa receber o org�o � indeterminado, sentimos muito por isso.\n\n")
			}
			senao
			{
				escreva("Entre em contato conosco pois brevemente a pessoa estara recebendo o novo org�o!!! \n\n")
			}
		}
	}
	funcao salvarArquivo(cadeia caminhoDoadores, cadeia Doadores[][], inteiro quantidade) // Salva o conteudo
	{
		inteiro arquivo = arq.abrir_arquivo(caminhoDoadores, arq.MODO_ESCRITA)
		inteiro linha, coluna
		cadeia linhaArquivo
		
		para(linha = 0; linha < quantidade; linha++)
		{	
			linhaArquivo = ""
			para(coluna = 0; coluna < 7; coluna++)
			{
				linhaArquivo = linhaArquivo + Doadores[linha][coluna] + ";"
			}
			arq.escrever_linha(linhaArquivo, arquivo)
		}	
		arq.fechar_arquivo(arquivo)
	}
}
/* $$$ Portugol Studio $$$ 
 * 
 * Esta se��o do arquivo guarda informa��es do Portugol Studio.
 * Voc� pode apag�-la se estiver utilizando outro editor.
 * 
 * @POSICAO-CURSOR = 7581; 
 * @DOBRAMENTO-CODIGO = [6, 73, 90, 114, 406, 417, 486, 481, 516, 544, 580, 633, 663, 727, 760, 790, 855, 1001];
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = {Hospitais, 10, 9, 9};
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */