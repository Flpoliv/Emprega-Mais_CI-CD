# 🏙️ Projeto - Cidades ESGInteligentes

Este projeto apresenta uma aplicação **Java Spring Boot** com integração completa de **DevOps**, incluindo **containerização com Docker**, **integração e deploy contínuos (CI/CD) com GitHub Actions**, e **implantação automatizada no Azure Web App**.

O objetivo é simular um ambiente de produção real, aplicando práticas de **agilidade, automação e sustentabilidade tecnológica** no contexto de cidades inteligentes (ESG).

---

## ⚙️ Como executar localmente com Docker

Para executar a aplicação em ambiente local utilizando containers Docker:

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/Flpoliv/Emprega-Mais_CI-CD.git
   cd Emprega-Mais_CI-CD
   ```

2. **Construir e subir a aplicação com Docker Compose:**
   ```bash
   docker compose up -d --build
   ```

3. **Verificar se o container está rodando:**
   ```bash
   docker compose ps
   ```

4. **Acessar a aplicação:**
   ```
   http://localhost:8080
   ```

5. **Encerrar o container (quando necessário):**
   ```bash
   docker compose down
   ```

---

## 🔁 Pipeline CI/CD

O pipeline foi desenvolvido utilizando **GitHub Actions**, responsável por todo o fluxo automatizado de build, testes e deploy.  
O arquivo `.github/workflows/azure-webapps.yml` define as etapas principais:

1. **Checkout do repositório**  
   Recupera o código mais recente do GitHub.

2. **Setup do JDK 21**  
   Configura o ambiente de build para o Java Spring Boot.

3. **Build e Testes Automatizados (Maven)**  
   Executa `mvn clean test` para validar o código.

4. **Empacotamento**  
   Gera o artefato `.jar` executável.

5. **Deploy no Azure Web App (Java SE)**  
   Utiliza a ação oficial `azure/webapps-deploy@v3` com o secret `AZURE_WEBAPP_PUBLISH_PROFILE`.

**Secrets configurados:**
| Nome | Função |
|------|--------|
| `AZURE_WEBAPP_PUBLISH_PROFILE` | Credenciais seguras do Azure |
| `SPRING_DATASOURCE_URL` | String de conexão com o banco de dados |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |

O pipeline é acionado automaticamente com cada `git push` na branch principal (`main`).

---

## 🐳 Containerização

A aplicação é empacotada em um container Docker utilizando um **Dockerfile de duas etapas** (build e runtime), garantindo imagens leves e seguras.

**Dockerfile simplificado:**
```Dockerfile
# Etapa 1 - Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2 - Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Essa estratégia separa o build do Maven da execução, reduzindo o tamanho final da imagem e acelerando o deploy.

---

## 📸 Prints do funcionamento

Abaixo, evidências de execução e deploy bem-sucedidos:

- ✅ **GitHub Actions:** Build, Test e Deploy concluídos com sucesso.  
- ☁️ **Azure Web App:** Aplicação online e acessível via domínio público.  
- 🧪 **Testes Automatizados:** Todos os testes unitários executados sem falhas.

*(As imagens estão incluídas no documento técnico “Documentacao_Tecnica_Emprega_Mais_CICD_v3.docx”)*

---

## 🧰 Tecnologias utilizadas

| Tecnologia / Ferramenta | Função |
|--------------------------|--------|
| **Java 21 (Spring Boot)** | Framework principal da aplicação |
| **PostgreSQL** | Banco de dados relacional |
| **Maven** | Build e gerenciamento de dependências |
| **Docker / Docker Compose** | Containerização e orquestração |
| **GitHub Actions** | Pipeline de CI/CD automatizado |
| **Azure Web App (Java SE)** | Ambiente de deploy e hospedagem |
| **Azure CLI** | Monitoramento e logs do deploy |

---

## 📘 Autor e Documentação

**Autor:** Felipe Oliveira  
**Repositório:** [Emprega-Mais_CI-CD](https://github.com/Flpoliv/Emprega-Mais_CI-CD)  
**Documentação completa:** `Documentacao_Tecnica_Emprega_Mais_CICD_v3.docx` (inclui prints e detalhes técnicos)
