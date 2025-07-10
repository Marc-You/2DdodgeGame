import java.util.Random;

public class Projectiles
{
    int projectileSpeedX;
    int projectileSpeedY;
    int projectileX;
    int projectileY;

    public Projectiles()
    {
        Random projectileSpeedGenerator = new Random();
        projectileSpeedX = projectileSpeedGenerator.nextInt(1, 4);
        projectileSpeedY = projectileSpeedGenerator.nextInt(1, 4);
    }
}
