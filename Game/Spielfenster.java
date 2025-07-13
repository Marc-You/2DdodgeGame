import javax.swing.*;
import java.awt.*;
import java.util.Vector;

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
    int playerX = 495;
    int playerY = 495;
    int playerSpeed = 5;

    double FPS = 60;
    int gesamtFrames;

    boolean playerHit = false;
    int score;
    boolean scoreGegeben;
    Vector<Projectiles> projectiles = new Vector<Projectiles>();

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


            update();

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
        if (!playerHit) {
            if (keyC.upPressed) {
                playerY -= playerSpeed;
            }

            if (keyC.downPressed) {
                playerY += playerSpeed;
                if (playerY > screenHeight - tileSize)
                {
                    playerY = screenHeight - tileSize;
                }
            }

            if (keyC.leftPressed) {
                playerX -= playerSpeed;

                if (playerX < 0)
                {
                    playerX = 0;
                }
            }

            if (keyC.rightPressed) {
                playerX += playerSpeed;

                if (playerX > screenWith - tileSize)
                {
                    playerX = screenWith - tileSize;
                }
            }


            gesamtFrames += 1;

            if (gesamtFrames % 60 == 0) {
                projectiles.add(new Projectiles());
                while (projectiles.getFirst().timer > 1200) {
                    projectiles.removeFirst();
                }
                for (int i = 0; i < gesamtFrames / 1000; i++) {
                    projectiles.add(new Projectiles());
                }
                for (Projectiles projectile : projectiles) {
                    projectile.Fly();
                }
            } else if (!projectiles.isEmpty()) {
                for (Projectiles projectile : projectiles) {
                    projectile.Fly();
                    if (playerX - tileSize / 2 < projectile.projectileX) {
                        if (projectile.projectileX < playerX + tileSize) {
                            if (playerY - tileSize / 2 < projectile.projectileY) {
                                if (projectile.projectileY < playerY + tileSize) {
                                    playerHit = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        else if (keyC.spacePressed)
        {
            playerX = 495;
            playerY = 495;
            gesamtFrames = 0;
            playerHit = false;
            scoreGegeben = false;
        }
        else if (!scoreGegeben)
        {
            projectiles.removeAllElements();
            score = gesamtFrames;
            System.out.println("Dein Score ist " + score + " drücke LEERTASTE zum Neustart");
            scoreGegeben = true;
        }
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g; //wandelt Klasse Graphics zu Graphics2D mit mehr Funktionen für 2D
        Graphics2D g2 = (Graphics2D)g;

        g2D.setColor(Color.white); //Farbe zu weiß

        g2D.fillRect(playerX, playerY, tileSize, tileSize); //Rechteck mit größe von einem Tile

        for (Projectiles projectile : projectiles)
        {
            g2.setColor(Color.red);
            g2.fillRect(projectile.projectileX, projectile.projectileY, tileSize / 2, tileSize / 2);
        }

        g2.dispose();
        g2D.dispose();

    }
}
