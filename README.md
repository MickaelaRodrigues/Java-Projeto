# Projeto-Java
Linguagens Digitais e Multiplataformas

 Sistema de Gerenciamento de Músicas

Sobre o projeto

O projeto consiste em um sistema de gerenciamento de músicas desenvolvido em Java, com integração a um banco de dados MySQL. Seu objetivo é permitir o cadastro, a consulta e o gerenciamento de músicas, incluindo a organização de músicas favoritas.

O sistema utiliza os conceitos de Programação Orientada a Objetos (POO), como herança, encapsulamento e reutilização de código, para representar diferentes tipos de músicas.

A classe Musica é a classe principal, responsável pelas informações e operações comuns. As classes MusicaNacional e MusicaInternacional herdam suas características e adicionam atributos específicos de cada tipo.

Tecnologias utilizadas

Java: desenvolvimento da aplicação.

MySQL: armazenamento das músicas cadastradas.

JDBC / Spring JdbcTemplate: comunicação com o banco de dados.

Programação Orientada a Objetos: organização das classes e utilização de herança.

Funcionalidades

O sistema possui um menu interativo no terminal com as seguintes opções:

Cadastrar música: permite cadastrar uma música nacional ou internacional, informando seus dados e características específicas.

Listar todas as músicas: exibe as músicas cadastradas e permite adicionar ou remover músicas dos favoritos.

Listar músicas favoritas: apresenta somente as músicas marcadas como favoritas.

Remover música: exclui uma música do banco de dados pelo título informado.

Sair: encerra a aplicação.
