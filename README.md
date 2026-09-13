# 📚 Biblioteca Java

Sistema de gerenciamento de biblioteca desenvolvido em Java, com persistência real em MySQL. Projeto construído do zero para praticar Programação Orientada a Objetos, Generics, Streams, JDBC e boas práticas de arquitetura em camadas.

## Status

✅ Funcional — CRUD completo de livros e usuários, empréstimos/devoluções com regras de negócio, relatórios via Streams, e menu interativo no console.

## Funcionalidades

- Cadastro de livros e usuários
- Empréstimo de livros, com validações (livro existe, está disponível, usuário existe)
- Devolução de livros, com manutenção de histórico
- Relatórios:
    - Livros disponíveis no momento
    - Histórico de empréstimos por usuário
    - Empréstimos ainda não devolvidos
    - Livro mais emprestado (contagem via Streams)
- Menu interativo via console

## Tecnologias

- **Java** (POO, Generics, Streams, Collections, Lambda expressions)
- **MySQL** (persistência via JDBC puro — `PreparedStatement`/`ResultSet`)
- **Maven** (gerenciamento de dependências)

## Arquitetura

O projeto segue uma separação em camadas:

```
model/        → Livro, Usuario, Emprestimo (entidades do domínio)
repository/   → acesso a dados via JDBC (LivroRepository, UsuarioRepository, EmprestimoRepository)
service/      → regras de negócio (EmprestimoService) e relatórios (RelatorioService)
Main.java     → menu interativo, ponto de entrada
```

Essa organização isola a lógica de negócio do acesso a dados — trocar a forma de persistência (por exemplo, para outro banco) exigiria mudanças apenas na camada `repository`.

## Como rodar localmente

### Pré-requisitos

- Java 23+ instalado
- MySQL instalado e rodando
- Maven (ou usar o wrapper da sua IDE)

### 1. Clone o repositório

```bash
git clone https://github.com/MatheusBaia1/biblioteca-java.git
cd biblioteca-java
```

### 2. Crie o banco de dados

No MySQL Workbench (ou cliente de sua preferência), rode:

```sql
CREATE DATABASE biblioteca_db;
USE biblioteca_db;

CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(50) NOT NULL UNIQUE,
    disponivel BOOLEAN NOT NULL
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT NOT NULL,
    usuario_id INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao_real DATE,
    FOREIGN KEY (livro_id) REFERENCES livros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
```

### 3. Configure suas credenciais

Copie o arquivo de exemplo e preencha com seus dados:

```bash
cp src/main/resources/database.properties.example src/main/resources/database.properties
```

Edite `database.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/biblioteca_db
db.usuario=SEU_USUARIO
db.senha=SUA_SENHA
```

> Esse arquivo é ignorado pelo Git — suas credenciais nunca vão para o repositório.

### 4. Rode o projeto

Abra o projeto na sua IDE (IntelliJ, Eclipse, VS Code com extensão Java, etc.) e execute a classe Main.java diretamente.

## Menu

```
=======MENU=======
1 - Cadastrar livro
2 - Cadastrar usuario
3 - Emprestar livro
4 - Devolver livro
5 - Listar livros disponíveis
6 - Ver histórico de um usuário
7 - Ver empréstimos não devolvidos
8 - Ver livro mais emprestado
0 - Sair
```

## Possíveis melhorias futuras

- Migrar a persistência para JPA/Hibernate
- Transformar em uma API REST com Spring Boot
- Adicionar campo de condição física do livro (rasgado, danificado, etc.)
- Restrição de acesso por perfil (ex: bibliotecário vs usuário comum), com autenticação
- Testes automatizados (JUnit)
- Paginação nos relatórios

## Autor

Matheus Baia — projeto desenvolvido como prática de Java avançado, aplicando POO, Generics, Streams e JDBC em um sistema com regras de negócio reais.