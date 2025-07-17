import java.awt.*;

public class Texte {
    Spielfenster sf;

    Font titelSchrift;
    Font untertitelSchrift;
    String text;
    String text1;
    String text2;
    String punktzahl;
    public Texte(Spielfenster sf)
    {
        this.sf = sf;

       titelSchrift = new Font("Arial", Font.BOLD, 80);
       untertitelSchrift = new Font("Arial", Font.PLAIN, 30);
    }

    public void startScreen(Graphics2D g2)
    {
        g2.setFont(titelSchrift);
        g2.setColor(Color.WHITE);
        text = "2D Dodge Game";
        int textLenght = textLenghtCalculator(text, 0, g2);
        int textPositionX = sf.screenWith/2 - textLenght/2;
        g2.drawString(text, textPositionX, 300);
        g2.setFont(untertitelSchrift);

        text = "drücke LEERTASTE zum Starten";
        textLenght = textLenghtCalculator(text, 0, g2);
        textPositionX = sf.screenWith/2 - textLenght/2;
        g2.drawString(text, textPositionX, 500);
    }

    public void deathScreen(Graphics2D g2)
    {
        g2.setFont(titelSchrift);
        g2.setColor(Color.LIGHT_GRAY);
        text = "Du wurdest getroffen";
        int textLenght = textLenghtCalculator(text, 0, g2);
        int textPositionX = sf.screenWith/2 - textLenght/2;
        g2.drawString(text, textPositionX, 300);
        g2.setFont(untertitelSchrift);

        text = "dein SCORE ist ";
        textLenght = textLenghtCalculator(text, sf.score, g2);
        textPositionX = sf.screenWith/2 - textLenght/2;
        g2.drawString(text + sf.score, textPositionX, 400);

        text = "drücke LEERTASTE zum erneut versuchen";
        textLenght = textLenghtCalculator(text, sf.score, g2);
        textPositionX = sf.screenWith/2 - textLenght/2;
        g2.drawString(text, textPositionX, 500);
    }

    public int textLenghtCalculator(String te, int in, Graphics2D g2)
    {
        return (int)g2.getFontMetrics().getStringBounds(text + in, g2).getWidth();
    }

}