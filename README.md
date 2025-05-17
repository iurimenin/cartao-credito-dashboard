# Dashboard de Gastos do Cartão de Crédito

Este projeto é um visualizador de dados de gastos do cartão de crédito Sicredi que cria um gráfico agrupado por descrição a partir de um arquivo CSV.

## Estrutura do Projeto

O projeto está dividido em duas partes:

### Backend (Kotlin/Ktor)
- Processamento do arquivo CSV
- API REST para receber e processar os dados
- Agrupamento e cálculos dos valores

### Frontend (JavaScript)
- Interface web para upload do arquivo
- Visualização gráfica dos dados
- Gráficos interativos com Chart.js

## Requisitos

### Backend
- JDK 11 ou superior
- Gradle (incluído no wrapper)

### Frontend
- Node.js
- NPM (Node Package Manager)

## Instalação e Execução

### Backend (Kotlin/Ktor)

1. Navegue até a pasta do backend:
```bash
cd backend
```

2. Execute o servidor usando Gradle:
```bash
# No Windows
gradlew.bat run

# No Linux/Mac
./gradlew run
```

O backend estará disponível em `http://localhost:8081`

### Frontend

1. Na pasta raiz do projeto, instale as dependências:
```bash
npm install
```

2. Inicie o servidor de desenvolvimento:
```bash
npm start
```

3. Acesse `http://localhost:8080` no navegador

## Como usar

1. Certifique-se que tanto o backend quanto o frontend estão rodando
2. Abra o navegador em `http://localhost:8080`
3. Clique em "Escolher arquivo" e selecione seu arquivo CSV do Sicredi
4. Clique em "Carregar Dados" para visualizar o gráfico

## Formato do CSV esperado

O arquivo deve ser um extrato do cartão de crédito Sicredi no formato CSV, onde:
- O arquivo começa com informações do cartão e resumo
- Os dados das transações começam a partir da linha 21
- As colunas são separadas por ponto e vírgula (;)
- O formato das colunas é: Data; Descrição; Parcela; Valor; Valor em Dólar; Adicional; Nome
- Os valores estão no formato "R$ X.XXX,XX"

## Funcionalidades

- Leitura automática do formato CSV do Sicredi
- Agrupamento de gastos por descrição
- Ordenação por valor (do maior para o menor)
- Formatação adequada dos valores em reais
- Gráfico interativo com tooltips detalhados

## Tecnologias utilizadas

### Backend
- Kotlin
- Ktor (Framework web)
- kotlin-csv (Processamento de CSV)
- kotlinx.serialization (Serialização JSON)

### Frontend
- Chart.js para visualização de dados
- HTTP-Server para servidor local 