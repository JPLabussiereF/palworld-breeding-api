# 🥚 Palworld Breeding API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.8-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Project-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

API REST desenvolvida para calcular combinações de acasalamento (*Breeding*) no jogo **Palworld**. 
O projeto utiliza a lógica matemática real do jogo ("Breeding Power") para determinar quais pares de pais podem gerar um Pal específico, buscando a aproximação mais próxima do valor alvo.

## 🚀 Funcionalidades

- **Cálculo de Breeding:** Algoritmo que varre combinações possíveis para encontrar pais compatíveis.
- **Dados Externalizados:** Base de dados de Pals carregada via arquivo JSON (`src/main/resources/pals.json`), facilitando atualizações sem recompilação.
- **Busca por Aproximação:** Implementação da lógica de `Floor((PaiA + PaiB) / 2)` para encontrar o Pal com poder mais próximo.
- **Tratamento de Erros:** Respostas HTTP adequadas (404 Not Found) para buscas inválidas.
- **Arquitetura Limpa:** Separação clara entre Models, Services (Lógica) e Controllers (API).

## 🛠️ Tech Stack

* **Java 21**: Linguagem moderna e performática.
* **Spring Boot 3.5.8**: Framework para criação de microsserviços.
* **Lombok**: Redução de boilerplate code.
* **Jackson**: Processamento e leitura de arquivos JSON.
* **Maven**: Gerenciamento de dependências.

## ⚙️ A Lógica de Breeding

No Palworld, cada Pal possui um valor oculto chamado "Breeding Power". Quanto **menor** o número, mais raro/forte é o Pal.
A mecânica implementada neste projeto segue a fórmula:

1.  Soma-se o poder dos dois pais.
2.  Divide-se por 2 e arredonda-se para baixo (Floor).
3.  O jogo (e esta API) busca na lista de todos os Pals qual tem o poder **mais próximo** desse resultado.

## 📦 Como Rodar o Projeto

### Pré-requisitos
* Java 17 ou 21 instalado.
* Git instalado.

### Passos
1. Clone o repositório:
   ```bash
   git clone [https://github.com/JPLabussiereF/palworld-breeding-api.git](https://github.com/SEU-USUARIO/palworld-breeding-api.git)
    ```

2.  Entre na pasta:
    ```bash
    cd palworld-breeding-api
    ```
3.  Execute a aplicação (Linux/Mac):
    ```bash
    ./mvnw spring-boot:run
    ```
    Ou no Windows:
    ```bash
    mvnw.cmd spring-boot:run
    ```

A API estará disponível em: `http://localhost:8080`

## 🔌 Documentação da API

### `GET /api/breeding/combinations`

Retorna uma lista de pares de pais que podem gerar o Pal alvo.

**Parâmetros:**

* `target` (Query Param): O nome do Pal que você deseja obter (ex: `Anubis`, `Grizzbolt`).

**Exemplo de Requisição:**

```http
GET http://localhost:8080/api/breeding/combinations?target=Anubis
```

**Exemplo de Resposta (JSON):**

```json
{
  "targetPal": "Anubis",
  "targetPower": 570,
  "combinationsFound": 2,
  "combinations": [
    {
      "parentA": "Penking",
      "parentB": "Bushi"
    },
    {
      "parentA": "Incineram",
      "parentB": "Bushi"
    }
  ]
}
```

## 📂 Estrutura do Projeto

```text
src/main/java/com/palworld/breedingapi
├── controller
│   └── BreedingController.java  # Endpoint REST
├── model
│   ├── Pal.java                 # Modelo de Dados
│   ├── BreedingCombination.java # DTO do Par de Pais
│   └── BreedingResponse.java    # DTO de Resposta JSON
└── service
    └── BreedingService.java     # Algoritmo e Leitura do JSON
```

-----

Desenvolvido com 💜 durante estudos de Spring Boot.