package school.sptech;

import java.util.List;
import java.util.Scanner;

public class main {


    static void main() {

        Scanner scanner = new Scanner(System.in);
        Musica musica = new Musica("Inicial", "Inicial", "Inicial", 1.0,
                "Inicial", false);

        Integer opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar música");
            System.out.println("2 - Listar todas as músicas");
            System.out.println("3 - Listar músicas favoritas");
            System.out.println("4 - Remover música");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 1) {

                System.out.println("\n1 - Nacional");
                System.out.println("2 - Internacional");
                System.out.print("Tipo da música: ");

                Integer tipo = Integer.parseInt(scanner.nextLine());

                System.out.print("Título: ");
                String titulo = scanner.nextLine();

                System.out.print("Cantor: ");
                String cantor = scanner.nextLine();

                System.out.print("Compositor: ");
                String compositor = scanner.nextLine();

                System.out.print("Duração: ");
                Double duracao = Double.parseDouble(scanner.nextLine());

                System.out.print("Descrição: ");
                String descricao = scanner.nextLine();

                System.out.print("É favorita? (s/n): ");
                Boolean favorita = scanner.nextLine()
                        .equalsIgnoreCase("s");

                Musica novaMusica;

                if (tipo == 1) {

                    System.out.print("Estado de origem: ");
                    String estado = scanner.nextLine();

                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();

                    novaMusica = new MusicaNacional(
                            titulo, cantor, compositor, duracao,
                            descricao, favorita, estado, genero
                    );

                } else if (tipo == 2) {

                    System.out.print("País de origem: ");
                    String pais = scanner.nextLine();

                    System.out.print("Idioma: ");
                    String idioma = scanner.nextLine();

                    System.out.print("Possui tradução? (s/n): ");
                    Boolean traducao = scanner.nextLine()
                            .equalsIgnoreCase("s");

                    novaMusica = new MusicaInternacional(
                            titulo, cantor, compositor, duracao,
                            descricao, favorita, pais, idioma, traducao
                    );

                } else {

                    System.out.println("Tipo inválido.");
                    continue;
                }

                novaMusica.cadastrarMusica(novaMusica);

            } else if (opcao == 2) {

                List<Musica> listaMusica = musica.listarMusicas();

                if (listaMusica.isEmpty()) {
                    System.out.println("Nenhuma música cadastrada.");
                    continue;
                }

                System.out.println("\n===== TODAS AS MÚSICAS =====");

                for (Musica musicaListar : listaMusica) {
                    System.out.println(musicaListar.toString());
                }

                System.out.println("\n1 - Adicionar música aos favoritos");
                System.out.println("2 - Remover música dos favoritos");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");

                int escolha = Integer.parseInt(scanner.nextLine());

                if (escolha == 1) {

                    System.out.print("Digite o título da música: ");
                    String titulo = scanner.nextLine();

                    musica.adicionarFavorita(titulo);

                } else if (escolha == 2) {

                    System.out.print("Digite o título da música: ");
                    String titulo = scanner.nextLine();

                    musica.removerFavorita(titulo);

                } else if (escolha != 0) {

                    System.out.println("Opção inválida.");
                }

            } else if (opcao == 3) {

                List<Musica> listaMusica = musica.listarMusicas();

                System.out.println("\n===== MÚSICAS FAVORITAS =====");

                boolean encontrouFavorita = false;

                for (Musica musicaListar : listaMusica) {

                    if (musicaListar.getFavorita()) {
                        System.out.println(musicaListar.toString());
                        encontrouFavorita = true;
                    }
                }

                if (!encontrouFavorita) {
                    System.out.println("Nenhuma música favorita encontrada.");
                }

            } else if (opcao == 4) {

                System.out.print(
                        "Digite o título da música que deseja remover: "
                );

                String titulo = scanner.nextLine();

                musica.removerMusica(titulo);

            } else if (opcao == 0) {

                System.out.println("Encerrando o sistema...");

            } else {

                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}


//    // Cadastrando Variaveis no Banco de Dados
//
//    Musica musica = new Musica("Summertime Sadness", "LanaDelRei", "LanaDelRei",4.3, "Musica oficial",true);
//
//    musica.cadastrarMusica(musica);
//
//
//
//    MusicaInternacional musicaInternacional = new MusicaInternacional("Starboys", "The Wekend", "The Wekend",3.3, "Musica oficial",true, "EUA", "Ingles", false );
//
//    musicaInternacional.cadastrarMusica(musicaInternacional);
//
//
//
//    MusicaNacional musicaNacional = new MusicaNacional("Sorri Sou Rei", "Natiruts", "Natiruts", 2.5, "Musica Legal", false, "SP", "Raggae");
//
//    musicaNacional.cadastrarMusica(musicaNacional);
//
//        // Listando músicas em geral
//
//        List<Musica> listaMusica = musica.listarMusicas();
//
//        for (Musica musicalistar : listaMusica) {
//            System.out.println(musicalistar.toString());
//        }
//
//
//        // Listando músicas Internacionais
//
//        List<Musica> listaMusicaInternacional = musica.listarMusicas();
//
//        for (Musica musicalistar : listaMusica) {
//            if (musicalistar instanceof MusicaInternacional m) {
//
//                System.out.println(musicalistar.toString());
//            }
//
//
//        }
//
//
//    //Listando músicas nacionais
//
//        List<Musica> listaMusicaNacional = musica.listarMusicas();
//
//        for (Musica musicalistar : listaMusica) {
//            if (musicalistar instanceof MusicaNacional) {
//
//                System.out.println(musicalistar.toString());
//            }
//
//
//        }
//
//

