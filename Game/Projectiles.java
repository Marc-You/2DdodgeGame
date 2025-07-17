import java.util.Random;

public class Projectiles
{
    int projectileSpeedX;
    int projectileSpeedY;
    int projectileX;
    int projectileY;
    int projectileStartPosition;
    boolean projectileStartOnLeft;
    boolean projectileStartOnTop;
    int relevantProjectileStartPosition;
    int timer;


    public Projectiles()
    {
        Random projectileGenerator = new Random();
        projectileStartPosition = projectileGenerator.nextInt(4);
        //0 = oben, 1 = unten, 2 links, 3 rechts

        if (projectileStartPosition <= 1)
        {
            projectileX = projectileGenerator.nextInt(1024);
            relevantProjectileStartPosition = projectileX;

            if (relevantProjectileStartPosition <= 512)
            {
                projectileStartOnLeft = true;
            }

            projectileSpeedX = projectileGenerator.nextInt(4);


            if (projectileSpeedX >= 2)
            {
                projectileSpeedY = projectileGenerator.nextInt(3);
            }
            else if (projectileSpeedX ==0 )
            {
                projectileSpeedY = 5;
            }
            else
            {
                projectileSpeedY = projectileGenerator.nextInt(2, 5);
            }

        }

        if (projectileStartPosition >= 2)
        {
            projectileY = projectileGenerator.nextInt(768);
            relevantProjectileStartPosition = projectileY;

            if (relevantProjectileStartPosition <= 384)
            {
                projectileStartOnTop = true;
            }

            projectileSpeedY = projectileGenerator.nextInt(4);

            if (projectileSpeedY >= 2)
            {
                projectileSpeedX = projectileGenerator.nextInt(3);
            }
            else if (projectileSpeedY ==0 )
            {
                projectileSpeedX = 5;
            }
            else
            {
                projectileSpeedX = projectileGenerator.nextInt(2, 5);
            }

        }
        if (projectileStartPosition == 0)
        {
           projectileY = 0;
        }
        else if ( projectileStartPosition == 1)
        {
            projectileY = 768;
        }
        else if (projectileStartPosition == 2)
        {
            projectileX = 0;
        }
        else
        {
            projectileX = 1024;
        }
    }
    void Fly()
    {
        if (projectileStartPosition == 0)
        {
            if (projectileStartOnLeft)
            {
                projectileX += projectileSpeedX;
            }
            else
            {
                projectileX -= projectileSpeedX;
            }
            projectileY += projectileSpeedY;
        }
        if (projectileStartPosition == 1)
        {
            if (projectileStartOnLeft)
            {
                projectileX += projectileSpeedX;
            }
            else
            {
                projectileX -= projectileSpeedX;
            }
            projectileY -= projectileSpeedY;
        }
        if (projectileStartPosition == 2)
        {
            if (projectileStartOnTop)
            {
                projectileY += projectileSpeedY;
            }
            else
            {
                projectileY -= projectileSpeedY;
            }
            projectileX += projectileSpeedX;
        }
        if (projectileStartPosition == 3)
        {
            if (projectileStartOnTop)
            {
                projectileY += projectileSpeedY;
            }
            else
            {
                projectileY -= projectileSpeedY;
            }
            projectileX -= projectileSpeedX;
        }
        timer += 1;
    }
}
