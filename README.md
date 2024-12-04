# Sistema de gerenciamento hospitalar

Este repositório contém o **IFSC Hospital**, um projeto desenvolvido para a disciplina de práticas em desenvolvimento de software, atendendo às exigências do professor. O objetivo principal é criar um sistema de gerenciamento hospitalar, utilizando tecnologias ensinadas no curso e práticas de programação.

## 📋 Descrição do Projeto

O sistema é destinado ao gerenciamento de informações hospitalares, como pacientes, médicos, medicamentos, leitos e muito mais. Ele será usado para cadastro, consulta e atualização dos dados relacionados a um hospital.

O projeto é dividido em camadas, seguindo o padrão arquitetural **MVC** (Model-View-Controller), garantindo separação de responsabilidades e facilitando a manutenção do código.

## 🛠️ Tecnologias Utilizadas

O projeto será desenvolvido utilizando:

- **Jetpack Compose Multiplatform (Desktop)**: Para a interface gráfica moderna e responsiva.
- **Java (para lógica de negócios e modelos)**: Oferecendo integração e reutilização do código do projeto antigo.
- **MySQL com JDBC**: Para gerenciamento do banco de dados, conectando o sistema ao armazenamento persistente.
- **Gradle**: Como ferramenta de build e gerenciamento de dependências.

## 🧑‍🏫 Exigências do Professor

1. O sistema deve ser um **aplicativo desktop**.
2. A principal lógica de negócio do projeto deve ser feito em Java seguindo conceitos de orientação a objetos
3. As funcionalidades devem seguir o padrão arquitetural **MVC**.
4. As interfaces gráficas podem ser desenvolvidas em **Swing** ou tecnologias mais modernas como **Jetpack Compose**.
5. O sistema deve se conectar a um banco de dados relacional (neste caso, **MySQL**).
6. Deve atender às necessidades de um hospital, incluindo gerenciamento de:
   - Pacientes
   - Médicos
   - Leitos
   - Medicamentos
   - Consultas
7. Deve possuir documentação no repositório (como este `README.md`).

## 🗂️ Estrutura do Projeto

O projeto é organizado nos seguintes pacotes principais:

- **model**: Contém as classes que representam os dados do sistema.
- **controller**: Gerencia a lógica do sistema e interage com o banco de dados.
- **view**: Contém a interface do usuário desenvolvida em Jetpack Compose.

## ⚙️ Configuração do Ambiente

### Pré-requisitos

Certifique-se de que os seguintes softwares estão instalados:

- [Java JDK 17+](https://jdk.java.net/)
- [Gradle](https://gradle.org/)
- [MySQL Server](https://dev.mysql.com/downloads/mysql/)
- IDE com suporte a Kotlin e Java (recomendado: IntelliJ IDEA)

### Configuração do Banco de Dados

1. Instale o MySQL Server e configure um banco de dados chamado `ifsc_hospital`.
2. Execute o script SQL em `/resources/sql/init.sql` para criar as tabelas necessárias.
3. Configure as credenciais de conexão no arquivo `application.properties` ou equivalente.

### Como Executar o Projeto

1. Clone este repositório:

   ```
   git clone https://github.com/seu-usuario/ifsc-hospital-compose.git
   
2. Navegue até o diretório do projeto:
   ```
   cd ifsc-hospital-compose
   
3. Execute o projeto com Gradle:
   ```
   ./gradlew run
   ```

## 🚀 Funcionalidades Planejadas
- Interface gráfica moderna com Jetpack Compose Multiplatform
- Cadastro de pacientes, médicos, e medicamentos
- Gerenciamento de leitos e internações
- Relatórios detalhados de consultas e tratamentos
- Gerenciamento de usuários com autenticação

