package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Exceptions.DadoInvalidoException;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class Musica {

    private Integer id;
    private String titulo;
    private String cantor;
    private String compositor;
    private Double duracao;
    private String descricao;
    private Boolean favorita;



    public Musica(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita) {


        if (titulo == null ||titulo.isBlank()){
            throw new DadoInvalidoException("Titulo inválido");
        }

        if (cantor == null ||cantor.isBlank()){
            throw new DadoInvalidoException("Cantor inválido");
        }
        if (compositor == null ||compositor.isBlank()){
            throw new DadoInvalidoException("Compositor inválido");
        }
        if (duracao == null ||duracao<=0 ){
            throw new DadoInvalidoException("Duração inválido");
        }
        if (favorita == null ){
            throw new DadoInvalidoException("Favorita inválido");
        }
        if (descricao == null|| descricao.isBlank()){
            throw new DadoInvalidoException("Duração inválido");
        }

        this.titulo = titulo;
        this.cantor = cantor;
        this.compositor = compositor;
        this.duracao = duracao;
        this.descricao = descricao;
        this.favorita = favorita;



    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public void cadastrarMusica(Musica musica){

        if (musica == null){
            throw new DadoInvalidoException("Musica inválida");
        }


        DBConnectionProvider db = new DBConnectionProvider();

        JdbcTemplate conexao = db.getConnection();

        String sql = """
            INSERT INTO musica
            (titulo, duracao, cantor, compositor, descricao, favorita)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        conexao.update(
                sql,
                musica.getTitulo(),
                musica.getDuracao(),
                musica.getCantor(),
                musica.getCompositor(),
                musica.getDescricao(),
                musica.getFavorita()
        );

        System.out.println("Musica Cadastrada com sucesso");


    }


    public List<Musica> listarMusicas() {

        DBConnectionProvider db = new DBConnectionProvider();
        JdbcTemplate conexao = db.getConnection();

        List<Musica> musicas = new ArrayList<>();

        String sql = """
            SELECT id, titulo, duracao, cantor, compositor, descricao, favorita,
                   estadoOrigem, genero, paisOrigem, idioma, possuiTraducao
            FROM musica
            """;

        conexao.query(sql, (musica, rowNum) -> {

            Integer id = musica.getInt("id");
            String titulo = musica.getString("titulo");
            String cantor = musica.getString("cantor");
            String compositor = musica.getString("compositor");
            Double duracao = musica.getDouble("duracao");
            String descricao = musica.getString("descricao");
            Boolean favorita = musica.getBoolean("favorita");

            String estadoOrigem = musica.getString("estadoOrigem");
            String genero = musica.getString("genero");

            String paisOrigem = musica.getString("paisOrigem");
            String idioma = musica.getString("idioma");
            Boolean possuiTraducao = musica.getBoolean("possuiTraducao");

            if (paisOrigem != null) {

                MusicaInternacional internacional = new MusicaInternacional(
                        titulo, cantor, compositor, duracao, descricao,
                        favorita, paisOrigem, idioma, possuiTraducao
                );
                internacional.setId(id);
                musicas.add(internacional);

            } else if (estadoOrigem != null) {

                MusicaNacional nacional = new MusicaNacional(
                        titulo, cantor, compositor, duracao, descricao,
                        favorita, estadoOrigem, genero
                );
                nacional.setId(id);
                musicas.add(nacional);

            } else {

                Musica comum = new Musica(
                        titulo, cantor, compositor, duracao, descricao, favorita
                );
                comum.setId(id);
                musicas.add(comum);
            }

            return null;
        });

        return musicas;
    }



    public void adicionarFavorita(String titulo) {

        DBConnectionProvider db = new DBConnectionProvider();
        JdbcTemplate conexao = db.getConnection();

        List<Musica> listaMusicas = listarMusicas();

        for (int i = 0; i < listaMusicas.size(); i++) {

            Musica musicaAtual = listaMusicas.get(i);

            if (musicaAtual.getTitulo().equalsIgnoreCase(titulo)) {

                String sql = """
                UPDATE musica
                SET favorita = TRUE
                WHERE id = ?
                """;

                conexao.update(sql, musicaAtual.getId());

                musicaAtual.setFavorita(true);

                System.out.println("Música atualizada com sucesso!");
                return;
            }
        }

        System.out.println("Música não encontrada.");
    }

    public void removerFavorita(String titulo) {

        DBConnectionProvider db = new DBConnectionProvider();
        JdbcTemplate conexao = db.getConnection();

        List<Musica> listaMusicas = listarMusicas();

        for (int i = 0; i < listaMusicas.size(); i++) {

            Musica musicaAtual = listaMusicas.get(i);

            if (musicaAtual.getTitulo().equalsIgnoreCase(titulo)) {

                String sql = """
                UPDATE musica
                SET favorita = FALSE
                WHERE id = ?
                """;

                conexao.update(sql, musicaAtual.getId());

                musicaAtual.setFavorita(false);

                System.out.println("Música removida dos favoritos!");
                return;
            }
        }

        System.out.println("Música não encontrada.");
    }


    public void removerMusica(String titulo) {

        DBConnectionProvider db = new DBConnectionProvider();
        JdbcTemplate conexao = db.getConnection();

        List<Musica> listaMusicas = listarMusicas();

        for (int i = 0; i < listaMusicas.size(); i++) {

            Musica musicaAtual = listaMusicas.get(i);

            if (musicaAtual.getTitulo().equalsIgnoreCase(titulo)) {

                String sql = """
                DELETE FROM musica
                WHERE id = ?
                """;

                conexao.update(sql, musicaAtual.getId());

                System.out.println("Música deletada com sucesso!");
                return;
            }
        }

        System.out.println("Música não encontrada.");
    }


    @Override
    public String toString() {
        return "Musica{" +
                "titulo='" + titulo + '\'' +
                ", cantor='" + cantor + '\'' +
                ", compositor='" + compositor + '\'' +
                ", duracao=" + duracao +
                ", descricao='" + descricao + '\'' +
                ", favorita=" + favorita +
                '}';
    }
}
