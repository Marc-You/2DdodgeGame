import javax.swing.*;
import java.awt.*;

public class Spielfenster extends JPanel
{
    //Bildschirmeinstellungen
    final int originalTileSize = 16; // 16x16 pixel Originalgröße von Stücken/Objekten
    final int scale =3; // setzt gezeigte Größe auf 48 pixel

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // Tiles pro Reihe
    final int maxScreenRow = 16; //Tiles pro Spalte
    final int screenWith = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow; // 768 pixel

    public Spielfenster() //Konstruktor
    {
        this.setPreferredSize(new Dimension(screenWith, screenHeight)); //Fenstergröße implementiert
        this.setBackground(Color.black); //Hintergrundfarbe des Spielfensters
        this.setDoubleBuffered(true); //Buffering für flüssigeres Rendern vom Spiel

    }

}
