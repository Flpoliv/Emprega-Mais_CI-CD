# 🚀 Emprega Mais – CI/CD com GitHub Actions e Azure

Este repositório contém o projeto **Emprega Mais**, uma aplicação **Java Spring Boot** containerizada com **Docker**, automatizada com **GitHub Actions** e implantada no **Azure Web App**.  
O objetivo é demonstrar um fluxo completo de **Integração Contínua (CI)** e **Entrega Contínua (CD)**.

---

## 🧩 Estrutura do Projeto

```
Emprega-Mais_CI-CD/
├── src/                       # Código-fonte da aplicação
│   ├── main/java/com/empregamais
│   └── test/java/com/empregamais
├── pom.xml                    # Gerenciador de dependências Maven
├── Dockerfile                 # Configuração da imagem Docker
├── docker-compose.yml         # Orquestração dos containers
├── .github/workflows/         # Pipelines CI/CD (GitHub Actions)
└── README.md                  # Este arquivo :)
```

---

## ⚙️ Tecnologias Utilizadas

| Tecnologia | Função |
|-------------|--------|
| **Java 21 / Spring Boot** | Backend principal |
| **PostgreSQL** | Banco de dados |
| **Maven** | Build e gerenciamento de dependências |
| **Docker / Docker Compose** | Containerização e orquestração |
| **GitHub Actions** | Pipeline de integração e deploy contínuo |
| **Azure Web App (Java SE)** | Ambiente de produção |

---

## 🐳 Containerização

O projeto possui um `Dockerfile` otimizado com duas etapas:

1. **Build:** compila e empacota o código usando Maven.  
2. **Runtime:** executa o `.jar` final em um container leve.

**Dockerfile simplificado:**
```Dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Para executar:**
```bash
docker compose up -d --build
```
Acesse: 👉 [http://localhost:8080](http://localhost:8080)

---

## 🧪 Testes Automatizados

O pipeline executa testes unitários via Maven:

```bash
./mvnw clean test
```

Os testes verificam se o contexto Spring Boot carrega corretamente e se os endpoints estão ativos.

---

## 🔁 Pipeline CI/CD (GitHub Actions)

O fluxo CI/CD é definido no arquivo `.github/workflows/azure-webapps.yml` e inclui:

1. **Checkout do código**
2. **Setup do JDK 21**
3. **Build e testes com Maven**
4. **Empacotamento (`.jar`)**
5. **Deploy automático no Azure Web App**

**Secrets configurados:**
| Nome | Descrição |
|------|------------|
| `AZURE_WEBAPP_PUBLISH_PROFILE` | Credenciais do Azure para deploy |
| `SPRING_DATASOURCE_URL` | URL do banco PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |

---

## ☁️ Deploy no Azure

O aplicativo é executado em um **Azure Web App (Java SE)** com Java 21.  
O pipeline GitHub Actions usa a ação:

```yaml
- name: Deploy to Azure Web App
  uses: azure/webapps-deploy@v3
  with:
    app-name: emprega-mais
    publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
    package: target/*.jar
```

Para verificar o deploy:
```
https://empregamais-webapp.azurewebsites.net
```

Logs em tempo real:
```bash
az webapp log tail --name emprega-mais --resource-group empregamais-rg
```

---

## 💻 Execução Local (sem Docker)

```bash
# Compila e roda a aplicação localmente
./mvnw spring-boot:run

# Ou, para gerar o JAR manualmente
./mvnw clean package -DskipTests
java -jar target/Emprega-Mais-0.0.1-SNAPSHOT.jar
```

---

## 🧠 Passo a Passo Resumido

| Ação | Comando |
|------|----------|
| Clonar o repositório | `git clone https://github.com/Flpoliv/Emprega-Mais_CI-CD.git` |
| Subir containers | `docker compose up -d --build` |
| Rodar testes | `./mvnw clean test` |
| Executar localmente | `./mvnw spring-boot:run` |
| Fazer build manual | `./mvnw clean package -DskipTests` |
| Parar containers | `docker compose down` |
| Enviar deploy (CI/CD) | `git push origin main` |

---

## 📸 Evidências de Sucesso

A execução bem-sucedida do pipeline e do deploy foi validada com prints do GitHub Actions e Azure Web App (inseridos na documentação técnica do projeto).

---

## 📘 Documentação Técnica

Um documento completo (`Documentacao_Tecnica_Emprega_Mais_CICD_v3.docx`) foi gerado, contendo:
- Diagrama do pipeline  
- Estrutura técnica  
- Prints de sucesso  
- Guia de execução e comandos  

---

## 👨‍💻 Autores

- **Felipe Oliveira**
- **Equipe de Desenvolvimento Emprega Mais**
- Supervisão: Projeto acadêmico voltado à integração DevOps (GitHub + Azure)
