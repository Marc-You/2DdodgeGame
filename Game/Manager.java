import javax.swing.JFrame;


public class Manager {

    public static void manager(String[] args)
    {
        JFrame fenster = new JFrame();
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setResizable(false);
        fenster.setTitle("2D Dodge Game");

        Spielfenster spielfenster = new Spielfenster();
        fenster.add(spielfenster);

        fenster.pack(); //Setzt Größe auf Festlegung der preferred Funktion

        fenster.setLocationRelativeTo(null); //zentriert das Fenster auf dem Bildschirm
        fenster.setVisible(true);

    }

}