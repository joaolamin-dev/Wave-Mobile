# Wave Mobile

Cliente Android da plataforma de streaming musical Wave, que consome a mesma API REST usada pelo site.

## Funcionalidades

- Cadastro de conta (nome, e-mail e senha).
- Login com autenticação JWT.
- Catálogo de músicas com busca por título e tela de detalhe.
- Artistas com busca por nome e tela de detalhe.
- Perfil do usuário com suas playlists.
- Compartilhar uma música com outros apps (Intent `ACTION_SEND`).
- Abrir a versão web do Wave no navegador (Intent `ACTION_VIEW`).

## Tecnologias

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose com rotas tipadas
- ViewModel
- Corrotinas
- Retrofit + Gson

## Como executar

Requisitos:

- Android Studio.
- Emulador ou celular com Android 7.0 (API 24) ou superior.

Passos:

1. Abra a pasta do projeto no Android Studio.
2. Aguarde o Gradle Sync terminar.
3. Selecione o emulador ou o celular e clique em **Run 'app'**.

Opcional: para gerar o APK de debug pelo terminal, rode na raiz do projeto:

```
.\gradlew.bat assembleDebug
```

O APK fica em `app/build/outputs/apk/debug/`.

## Instruções de uso

1. **Login**: ao abrir o app aparece a tela de login. Quem ainda não tem conta toca em **Criar conta**.
2. **Cadastro**: preencha nome, e-mail e senha (mínimo de 6 caracteres) e toque em **Cadastrar**. Com a conta criada, o app volta ao login com a mensagem "Conta criada! Faça login.". O botão **Voltar ao login** volta sem cadastrar.
3. **Entrar**: no login, informe e-mail e senha e toque em **Entrar**.
4. **Início**: mostra "Olá, <nome>" e três opções: **Músicas**, **Artistas** e **Meu perfil**.
5. **Músicas**: lista o catálogo com título, artista, gênero e ano. Digite parte de um título e toque em **Buscar**; buscar com o campo vazio volta a listar tudo. Toque em uma música para abrir o detalhe.
6. **Detalhe da música**: mostra título, artista, gênero e ano. O botão **Compartilhar** envia o texto "Ouvindo <título> - <artista> no Wave" para o app escolhido.
7. **Artistas**: lista os artistas com tipo e nacionalidade, com busca por nome. Toque em um artista para ver o detalhe (nome, tipo e nacionalidade).
8. **Meu perfil**: mostra nome, e-mail e papel, e a seção "Minhas playlists" com as músicas de cada playlist. O botão **Abrir Wave na web** abre o site no navegador.
9. **Voltar**: as telas internas têm um botão **Voltar** no topo.
10. **Sair**: no Início, o botão **Sair** encerra a sessão e volta ao login. Se a sessão expirar, o app também volta ao login automaticamente.

## API

- URL base: https://wave-api-16zu.onrender.com/
- Versão web: https://wave-web-s3te.onrender.com

Avisos:

- A API está no plano gratuito do Render. Depois de um tempo sem uso, a primeira requisição pode levar cerca de 1 minuto. Se aparecer uma falha de conexão, aguarde e tente novamente.
- Contas novas começam sem playlists, porque a API não oferece criação de playlists.

## Estrutura do projeto

```
app/src/main/java/br/com/fatec/wave/mobile/
├── data/        modelos, interface Retrofit, cliente HTTP, sessão (token) e repositório
├── viewmodel/   ViewModels com o estado das telas e as chamadas à API
├── ui/          telas em Compose, componentes, rotas e navegação
│   └── theme/   cores, tipografia e tema do app
└── MainActivity.kt
```
