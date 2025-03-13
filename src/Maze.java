import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze
{
    private String file;

    public Maze(String file)
    {
        File f = new File("src/MazeTest");
        this.file = f.toString();
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
        ArrayList<Integer> paths = new ArrayList<>();
        for(int i = 0; i < maze.length; i++)
        {
            if(maze[0][i].equals("."))
            {
                paths.add(i);
            }
        }
        int path = paths.getFirst();
        for(int i = 0; i < paths.size(); i++)
        {
            path = paths.get(i);
            for(int j = 1; j < maze.length; i++)
            {
                if(!(maze[j][i].equals(".")))
                {
                    if(!(maze[j-1][i+1].equals(".")))
                    {
                        if(!(maze[j-1][i-1].equals(".")))
                        {
                            paths.remove(i);
                        }
                        else
                        {
                            paths.set(i, i);
                        }
                    }
                    else
                    {
                        paths.set(i, i);
                    }
                }
                else
                {
                    paths.set(i, i);
                }
            }
        }
        return paths;
    }
}
