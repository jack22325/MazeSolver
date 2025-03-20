import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Maze m = new Maze("src/MazeTest");
        System.out.println(m.solution(m.editMaze()));
    }
}