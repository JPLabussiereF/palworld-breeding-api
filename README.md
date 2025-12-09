# Palworld Breeding API 🥚

API REST desenvolvida em Java com Spring Boot para calcular combinações de acasalamento (Breeding) no jogo Palworld.

## 🚀 Tecnologias
- Java 21
- Spring Boot 3.x
- Lombok
- Maven

## 🎯 Objetivo
Permitir que usuários descubram quais pais geram um Pal específico através da rota:
`GET /api/breeding/combinations?target={NomeDoPal}`

## 🛠️ Como rodar
1. Clone o repositório
2. Execute `./mvnw spring-boot:run`