# PostVision Mobile

Sistema de Correção de Postura com Análise de Agachamento via Visão Computacional

O **PostVision Mobile** é o aplicativo nativo Android que utiliza a câmera do dispositivo e algoritmos de **visão computacional** para analisar a postura do usuário durante o exercício de **agachamento** em tempo real, auxiliando na correção de movimentos incorretos e promovendo treinos mais seguros e eficientes.

---

## Arquitetura do Projeto

| Camada | Tecnologia | Hospedagem |
| --- | --- | --- |
| **Frontend (App)** | Android (Kotlin + Jetpack Compose) | Dispositivo Físico / Emulador |
| **API** | Node.js + Express | Render |
| **Banco de dados** | MongoDB | MongoDB Atlas |

O aplicativo chama as rotas da API configuradas na variável `BASE_URL` dentro do cliente Retrofit. A API é responsável pela lógica de negócio, autenticação e acesso ao MongoDB.

---

## Acesso

O painel de acompanhamento web está disponível em produção na Vercel:
**[postvision.vercel.app](https://postvision.vercel.app)**

---

## Como executar localmente

### 1. Clone o repositório

```bash
git clone https://github.com/postvision-pi/postvision-mobile.git
```

Abra a pasta do projeto utilizando o **Android Studio**.

### 2. Configure o IP da API

Para que o aplicativo se comunique com o seu servidor local (Node.js) durante o desenvolvimento, é necessário apontar para o IP da sua máquina na rede.

Vá até o arquivo `app/src/main/java/com/example/postvision/ui/network/RetrofitClient.kt` e altere a constante `BASE_URL`:

```kotlin
// Em desenvolvimento: endereço IPv4 da sua máquina na rede Wi-Fi local
private const val BASE_URL = "http://192.168.0.105:4000/"

// Em produção: URL do serviço da API no Render
// private const val BASE_URL = "https://sua-api-postvision.onrender.com/"
```

### 3. Sincronize as dependências

No Android Studio, clique em **"Sync Project with Gradle Files"** (ícone de elefante com uma seta na barra superior) para que o Gradle instale todas as bibliotecas necessárias.

### 4. Execute o projeto

Conecte o seu dispositivo Android físico (via cabo ou depuração Wi-Fi) ou inicie um Emulador. Clique no botão **Run (Shift + F10)** no Android Studio.

> Certifique-se de que a [postvision-api](https://github.com/postvision-pi/postvision-api) esteja disponível no endereço IP e porta configurados no `RetrofitClient`.

---

## Tecnologias Utilizadas

| Tecnologia | Descrição |
| --- | --- |
| **Kotlin** | Linguagem principal, oferecendo segurança de nulos e concisão. |
| **Jetpack Compose** | Toolkit moderno e declarativo para construção de interfaces nativas. |
| **Coroutines & Flow** | Gerenciamento de processos assíncronos e reatividade de dados. |
| **Node.js + Express** | Backend da API hospedado no Render. |
| **MongoDB Atlas** | Banco de dados NoSQL em nuvem. |

---

## Principais Bibliotecas

### MediaPipe (Google)

Usado para análise de postura e detecção de *landmarks* corporais. Ele mapeia os pontos articulares do usuário através da câmera em tempo real para avaliação dos agachamentos.

### CameraX

Biblioteca do Android Jetpack que facilita o acesso e controle da câmera, otimizada para capturar os frames contínuos exigidos pelo MediaPipe.

### Retrofit & Gson

Cliente HTTP *Type-Safe* usado para estruturar a comunicação com a API REST, aliado ao Gson para serialização e desserialização automática dos objetos JSON.

### auth0/JWTDecode.Android

Responsável por decodificar e validar localmente o Token JWT recebido no login, extraindo as informações de sessão de forma segura no dispositivo.

### ViewModel & UI State

Componentes de arquitetura para gerenciar os estados da interface de forma reativa e sobreviver a mudanças de configuração (como rotações de tela).

---

## Ambiente e verificações

Este repositório utiliza o Gradle como sistema de build. Por não possuir uma camada de testes automatizados complexa no frontend mobile, a validação de integridade depende da tipagem estática do Kotlin e das ferramentas de inspeção do Android Studio.

### Lint e Inspeção

O Android Studio possui o Android Lint nativo para garantir a qualidade estrutural e apontar vulnerabilidades ou códigos não otimizados.

Para rodar a verificação completa de Lint pelo terminal na raiz do projeto:

```bash
./gradlew lint
```

O relatório visual apontando avisos ou erros de estrutura será gerado em `app/build/reports/lint-results.html`.

### Segurança e Tráfego Local

Durante o desenvolvimento, o manifesto (`AndroidManifest.xml`) utiliza a flag `android:usesCleartextTraffic="true"` para permitir conexões HTTP com o servidor local (Node.js). Para consumir a API de produção no Render, essa flag não é necessária, pois a comunicação ocorre sob HTTPS. O JWT de autenticação trafega no cabeçalho `Bearer` do Retrofit, garantindo a proteção das rotas de relatórios e perfil.
