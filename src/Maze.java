import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze
{
    private final String file;
    private int yCoord = 0;
    private int xCoord = 0;

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
        int cols = fileData.getFirst().length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }

    public boolean canGoDown(String[][] maze)
    {
        if(yCoord!=maze.length-1)
        {
            return maze[yCoord + 1][xCoord].equals(".");
        }
        return false;
    }

    public boolean canGoUp(String[][] maze)
    {
        if(yCoord!=0)
        {
            return maze[yCoord-1][xCoord].equals(".");
        }
        return false;
    }

    public boolean canGoRight(String[][] maze)
    {
        if(xCoord!=maze[0].length-1)
        {
            return maze[yCoord][xCoord+1].equals(".");
        }
        return false;
    }

    public boolean canGoLeft(String[][] maze)
    {
        if(xCoord!=0)
        {
            return maze[yCoord][xCoord-1].equals(".");
        }
        return false;
    }

    public String[][] editMaze()
    {
        yCoord = 0;
        xCoord = 0;
        String[][] editedMaze = getMaze(file);
        editedMaze[0][0] = "x";
        while ((yCoord != editedMaze.length - 1) || (xCoord != editedMaze[0].length - 1))
        {
            if (!canGoDown(editedMaze) && (!canGoRight(editedMaze) && (!canGoLeft(editedMaze) && (!canGoUp(editedMaze)))))
            {
                editedMaze[yCoord][xCoord] = "#";
                for (int i = 0; i < editedMaze.length; i++)
                {
                    for (int j = 0; j < editedMaze[0].length; j++)
                    {
                        if (editedMaze[i][j].equals("x"))
                        {
                            editedMaze[i][j] = ".";
                        }
                    }
                }
                xCoord = 0;
                yCoord = 0;
            }
            else
            {
                if (canGoDown(editedMaze))
                {
                    yCoord++;
                    editedMaze[yCoord][xCoord] = "x";
                }
                else if (canGoRight(editedMaze)) {
                    xCoord++;
                    editedMaze[yCoord][xCoord] = "x";
                }
                else if (canGoUp(editedMaze))
                {
                    yCoord--;
                    editedMaze[yCoord][xCoord] = "x";
                }
                else if (canGoLeft(editedMaze))
                {
                    xCoord--;
                    editedMaze[yCoord][xCoord] = "x";
                }
            }
        }
        for (int i = 0; i < editedMaze.length; i++) {
            for (int j = 0; j < editedMaze[0].length; j++) {
                if (editedMaze[i][j].equals(".")) {
                    editedMaze[i][j] = "#";
                }
            }
        }
        for (int i = 0; i < editedMaze.length; i++) {
            for (int j = 0; j < editedMaze[0].length; j++) {
                if (editedMaze[i][j].equals("x")) {
                    editedMaze[i][j] = ".";
                }
            }
        }
        editedMaze[0][0] = ".";
        return editedMaze;
    }

    public ArrayList<String> solution(String[][] maze) {
        ArrayList<String> path = new ArrayList<>();
        path.add("[0,0]");
        yCoord = 0;
        xCoord = 0;
        maze[0][0] = "x";
        while ((yCoord != maze.length - 1) || (xCoord != maze[0].length - 1)) {
                if (canGoLeft(maze))
                {
                    xCoord--;
                    maze[yCoord][xCoord] = "x";
                    path.add("[" + yCoord + "," + xCoord + "]");
                }
                if (canGoDown(maze))
                {
                    yCoord++;
                    maze[yCoord][xCoord] = "x";
                    path.add("[" + yCoord + "," + xCoord + "]");
                }
                if (canGoUp(maze))
                {
                    yCoord--;
                    maze[yCoord][xCoord] = "x";
                    path.add("[" + yCoord + "," + xCoord + "]");
                }
                if (canGoRight(maze))
                {
                    xCoord++;
                    maze[yCoord][xCoord] = "x";
                    path.add("[" + yCoord + "," + xCoord + "]");
                }
            }
        return path;
        }
    }
