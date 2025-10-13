# Emprega-Mais

Projeto voltado para empregabilidade, dentro do eixo **Social** do ESG.

## 🛠️ Tecnologias
- Java 17
- Spring Boot
- Oracle Database (via FIAP)
- Spring Security
- Flyway
- Docker

## 🚀 Como rodar

### Pré-requisitos
- Java 17
- Maven
- Docker (opcional)

### Passos

1. Compile o projeto:
```bash
./mvnw clean package
```

2. Rode o app:
```bash
java -jar target/emprega-mais-0.0.1-SNAPSHOT.jar
```

3. Acesse a aplicação:
- API: http://localhost:8080/vagas
- Banco Oracle: `oracle.fiap.com.br:1521/ORCL`, usuário `RM******`, senha `******`

### Usuários disponíveis
- `admin` / `admin123` (ROLE_ADMIN)
- `user` / `user123` (ROLE_USER)

## 📌 Endpoints RESTful

| Método | Endpoint       | Protegido | Descrição                      |
|--------|----------------|-----------|--------------------------------|
| GET    | /vagas         | ✅         | Lista todas as vagas           |
| POST   | /vagas         | ✅         | Cadastra nova vaga             |
| PUT    | /vagas/{id}    | ✅         | Atualiza vaga existente        |
| DELETE | /vagas/{id}    | ✅         | Deleta vaga                    |

## 📦 Migrações

Gerenciadas via Flyway, na pasta:
```
src/main/resources/db/migration/
```