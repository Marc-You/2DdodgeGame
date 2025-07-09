import javax.swing.*;
import java.awt.*;

public class Spielfenster extends JPanel implements Runnable {
    //Bildschirmeinstellungen
    final int originalTileSize = 16; // 16x16 pixel Originalgröße von Stücken/Objekten
    final int scale = 2; // setzt gezeigte Größe auf 32 pixel

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 32; // Tiles pro Reihe
    final int maxScreenRow = 32; //Tiles pro Spalte
    final int screenWith = tileSize * maxScreenCol; // 1024 pixel
    final int screenHeight = tileSize * maxScreenRow; // 1024 pixel

    Key_Control keyC = new Key_Control();
    Thread gameThread;

    //Standard Spielerposition setzen
    int playerX = 380;
    int playerY = 380;
    int playerSpeed = 5;

    double FPS = 60;

    public Spielfenster() //Konstruktor
    {
        this.setPreferredSize(new Dimension(screenWith, screenHeight)); //Fenstergröße implementiert
        this.setBackground(Color.black); //Hintergrundfarbe des Spielfensters
        this.setDoubleBuffered(true); //Buffering für flüssigeres Rendern vom Spiel
        this.addKeyListener(keyC);
        this.setFocusable(true);
    }

    public void startGameTread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //Nanosekunden in einer Sekunde geteilt durch 60 für Intervall zwischen Frames
        double nextDrawTime = System.nanoTime() + drawInterval; //aktuelle Zeit + Zeit, in welcher der nächste Frame gemalt werden soll

        while (gameThread != null) {

            //1. UPDATE: Informationen aktualisieren zB Position des Charakters
            update();

            // 2 DRAW: den Frame zeichnen mit den aktualisierten Informationen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        if (keyC.upPressed) {
            playerY -= playerSpeed;
        }

        if (keyC.downPressed) {
            playerY += playerSpeed;
        }

        if (keyC.leftPressed) {
            playerX -= playerSpeed;
        }

        if (keyC.rightPressed) {
            playerX += playerSpeed;
        }
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //wandelt Klasse Graphics zu Graphics2D mit mehr Funktionen für 2D

        g2.setColor(Color.white); //Farbe zu weiß

        g2.fillRect(playerX, playerY, tileSize, tileSize); //Rechteck mit größe von einem Tile

        g2.dispose();

    }
}
