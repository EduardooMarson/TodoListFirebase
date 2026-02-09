# 📝 TodoListFirebase

![Android](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange.svg)
![Firebase](https://img.shields.io/badge/Backend-Firebase-yellow.svg)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue.svg)

## 1. Introdução
O **TodoListFirebase** é um aplicativo Android desenvolvido para fins educacionais com o objetivo de implementar um sistema de lista de tarefas (To-Do List). O aplicativo permite que o usuário gerencie suas atividades diárias com persistência em banco de dados.

O projeto explora conceitos fundamentais, como integração com serviços externos, organização arquitetural e construção de interfaces modernas seguindo as diretrizes do **Material Design**.

---

## 2. Objetivos

### 🎯 Objetivo Geral
Desenvolver um aplicativo funcional para gerenciamento de tarefas utilizando o ecossistema Firebase.

### 🔍 Objetivos Específicos
* Implementar autenticação de usuários via **Firebase Authentication**.
* Permitir operações de **CRUD** (Create, Read, Update, Delete).
* Armazenamento de dados (Room).
* Aplicar boas práticas de arquitetura de software (**MVVM**).

---

## 3. Tecnologias Utilizadas
* **Linguagem:** Kotlin
* **IDE:** Android Studio
* **Autenticação:** Firebase (Authentication)
* **Persistência de dados:** Room
* **UI Toolkit:** Android Material Design
* **Arquitetura:** MVVM (Model-View-ViewModel)
* **Build System:** Gradle

---

## 4. Estrutura do Projeto
O projeto segue a estrutura padrão recomendada para garantir a escalabilidade:


```text
app/
└── src/
    └── main/
        ├── java/
        │   └── com.eduardoomarson.todolistfirebase/
        │       ├── authentication/      # Estado e ViewModel de autenticação
        │       │   ├── AuthState.kt
        │       │   └── AuthViewModel.kt
        │       │
        │       ├── data/                # Camada de dados (Room / Firebase)
        │       │   ├── TodoDao.kt
        │       │   ├── TodoDatabase.kt
        │       │   ├── TodoEntity.kt
        │       │   ├── TodoRepository.kt
        │       │   └── TodoRepositoryImpl.kt
        │       │
        │       ├── domain/              # Regras de negócio
        │       │   └── Todo.kt
        │       │
        │       ├── navigation/          # Navegação da aplicação
        │       │   └── TodoNavHost.kt
        │       │
        │       ├── ui/
        │       │   ├── components/      # Componentes reutilizáveis
        │       │   │   └── TodoItem.kt
        │       │   │
        │       │   └── feature/         # Telas organizadas por funcionalidade
        │       │       ├── addedit/
        │       │       │   ├── AddEditEvent.kt
        │       │       │   ├── AddEditScreen.kt
        │       │       │   └── AddEditViewModel.kt
        │       │       │
        │       │       ├── list/
        │       │       │   ├── ListEvent.kt
        │       │       │   ├── ListScreen.kt
        │       │       │   └── ListViewModel.kt
        │       │       │
        │       │       ├── login/
        │       │       │   └── LoginScreen.kt
        │       │       │
        │       │       ├── signup/
        │       │       │   └── SignupScreen.kt
        │       │       │
        │       │       └── forgotpassword/
        │       │           └── ForgotPasswordScreen.kt
        │       │
        │       ├── theme/               # Tema do Jetpack Compose
        │       ├── UiEvent.kt            # Eventos globais de UI
        │       └── MainActivity.kt
        │
        └── AndroidManifest.xml
```

- **Data**: responsável pelo acesso aos dados (Room).
- **Domain**: contém as regras de negócio da aplicação.
- **UI**: implementada com Jetpack Compose, organizada por features.
- **ViewModels**: fazem a ponte entre UI e dados, seguindo o padrão MVVM.
- **Navigation**: centraliza o controle de rotas da aplicação.

## 5. Funcionalidades Implementadas
* **Autenticação:** Cadastro e login via e-mail e senha com Firebase Authentication.
* **Criação:** Adição de novas tarefas de forma simplificada.
* **Edição:** Atualização de tarefas existentes.
* **Checklist:** Marcação de tarefas como concluídas.
* **Exclusão:** Remoção definitiva de tarefas do banco de dados.
* **UX/UI:** Interface limpa, intuitiva e organizada.

---

## 6. Integração com Firebase
O Firebase é utilizado como o **backend-as-a-service (BaaS)** da aplicação, fornecendo:

* **Authentication:** Controle de acesso e segurança dos usuários

---

## 7. Arquitetura Utilizada
O projeto adota o padrão **MVVM (Model-View-ViewModel)**, garantindo uma separação clara de responsabilidades:

1.  **Model:** Representa a estrutura dos dados (tarefas e usuários).
2.  **View:** Camada de interface (XML/Activities) que exibe as informações ao usuário.
3.  **ViewModel:** Atua como ponte, lidando com a lógica de negócio e mantendo o estado da UI.

---

## 8. Execução do Projeto

### 8.1 Pré-requisitos
* Android Studio instalado e configurado.
* Android SDK atualizado.
* Uma conta ativa no [Firebase Console](https://console.firebase.google.com/).
* Dispositivo físico ou emulador configurado.

### 8.2 Passos para Execução
1.  **Clonar o repositório:**
    ```bash
    git clone [https://github.com/EduardooMarson/TodoListFirebase.git](https://github.com/EduardooMarson/TodoListFirebase.git)
    ```
2.  **Configurar no Firebase:** No console do Firebase, crie um novo projeto Android.
3.  **Configurar o App:** Baixe o arquivo `google-services.json` fornecido pelo console.
4.  **Adicionar credenciais:** Mova o arquivo para a pasta `app/` do seu projeto.
5.  **Build:** Aguarde a sincronização do Gradle e execute o app.

---

## 9. Observações
Este projeto foi desenvolvido para **fins educacionais**, com o foco principal em praticar conceitos de desenvolvimento Android nativo, arquitetura de software e a integração prática com serviços de infraestrutura em nuvem.
