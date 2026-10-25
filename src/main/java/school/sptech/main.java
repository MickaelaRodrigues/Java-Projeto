package school.sptech;

public class main {

    static void main() {

    Musica musica = new Musica("Summertime Sadness", "LanaDelRei", "LanaDelRei",4.3, "Musica oficial",true);

    musica.cadastrarMusica(musica);

    MusicaInternacional musicaInternacional = new MusicaInternacional("Starboys", "The Wekend", "The Wekend",3.3, "Musica oficial",true, "EUA", "Ingles", false );

    musicaInternacional.cadastrarMusica(musicaInternacional);

    MusicaNacional musicaNacional = new MusicaNacional("Sorri Sou Rei", "Natiruts", "Natiruts",2.5, "Musica Legal",false, "SP","Raggae" );

    musicaNacional.cadastrarMusica(musicaNacional);


    }

}
