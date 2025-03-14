import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze
{
    private final String file;
    private ArrayList<Integer> paths = new ArrayList<>();

    public Maze(String maze)
    {
        File f = new File(maze);
        file = f.toString();
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }

    public ArrayList<Integer> validStartingPaths()
    {
        String[][] maze = getMaze(file);
        for(int i = 0; i < maze.length; i++)
        {
            if(maze[0][i].equals("."))
            {
                paths.add(i);
            }
        }
        return paths;
    }

    public boolean canGoRight(int xCoord, int yCoord)
    {
        boolean right = false;
        if(getMaze(file)[xCoord+1][yCoord].equals("."))
        {
            right = true;
        }
        return right;
    }

    public boolean canGoLeft(int xCoord, int yCoord)
    {
        boolean left = false;
        if(getMaze(file)[xCoord-1][yCoord].equals("."))
        {
            left = true;
        }
        return left;
    }

    public boolean canGoDown(int xCoord, int yCoord)
    {
        boolean down = false;
        if(getMaze(file)[xCoord][yCoord-1].equals("."))
        {
            down = true;
        }
        return down;
    }

    public int pathsThatWork()
    {
        int yCoord = 0;
        for(int i = 0; i < validStartingPaths().size(); i++)
        {
            if(canGoDown())
        }
    }
}
