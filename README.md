# Problema de Roteamento de Véıculos Capacitados (do inglês The Capacitated Vehicle Routing Problem - CVRP).

# Como interpretar os arquivos:

<br><br>
   *   O número de vértices pode ser encontrado no campo DIMENSION.<br>
   *   O número de veículos pode ser encontrado no campo VEHICLES.<br>
   *   A capacidade de cada veículo pode ser encontrada no campo CAPACITY.<br>
   *   A demanda de cada cliente pode ser encontrada no campo DEMAND_SECTION.<br>
   *  A matriz de custos pode ser encontrada no campo EDGE_WEIGHT_SECTION.<br>
<br><br>
Soluções ótimas<br>
   *   P-n16-k8 = 450<br>
   *   P-n19-k2 = 212<br>
   *   P-n20-k2 = 216<br>
   *   P-n23-k8 = 529<br>
   *   P-n55-k7 = 510<br>
   *   P-n51-k10 = 696<br>
   *   P-n50-k10 = 741<br>
   *   P-n45-k5 = 568<br>


# Objetivo

Neste projeto vai conter:

• Implementação de ao menos uma heurística de construção
• Implementação dos movimentos de vizinhança (Mínimo 3)
• Implementação do algoritmo de busca local chamado VND (Variable Neighborhood Descent)

O motor é desenvolvido é baseado em Java.



# Introdução

<p align="center">
	<br>
	<img src="prints/vrp.png"/ >
      <br>
</p>

Nesse problema, uma frota de veículos é utilizada para visitar um conjunto de clientes realizando entregas de produtos. Cada veículo possui a mesma capacidade e cada cliente requer uma certa quantidade de produtos. O objetivo é criar um conjunto de rotas (uma para cada veículo) onde cada cliente é visitado exatamente uma vez, todas as demandas são atendidas sem exceder a capacidade dos veículos, e o custo de transporte é minimizado.

