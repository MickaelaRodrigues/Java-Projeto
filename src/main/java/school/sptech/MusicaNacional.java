package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Exceptions.DadoInvalidoException;

public class MusicaNacional extends Musica {

    private String estadoOrigem;
    private String genero;

    public MusicaNacional(String titulo, String cantor, String compositor, Double duracao, String descricao, Boolean favorita, String estadoOrigem, String genero) {
        super(titulo, cantor, compositor, duracao, descricao, favorita);
        this.estadoOrigem = estadoOrigem;
        this.genero = genero;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public void cadastrarMusica(Musica musica) {
        if (musica == null){
            throw new DadoInvalidoException("Musica inválida");
        }


        DBConnectionProvider db = new DBConnectionProvider();

        JdbcTemplate conexao = db.getConnection();

        String sql = """
            INSERT INTO musica
            (titulo, duracao, cantor, compositor, descricao, favorita, estadoOrigem, genero)
            VALUES (?, ?, ?, ?, ?, ?,?,?)
            """;

        conexao.update(
                sql,
                musica.getTitulo(),
                musica.getDuracao(),
                musica.getCantor(),
                musica.getCompositor(),
                musica.getDescricao(),
                musica.getFavorita(),
                getEstadoOrigem(),
                getGenero()
        );

        System.out.println("Musica Nacional cadastrada com sucesso");


    }

}
