import models.Game;
import models.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTests {

    Game game;

    @BeforeEach
    public void setup () {
        game = new Game(
                new Team("GRYFFINDOR", "Oliver", "Harry",
                        new String[] {"Angelina", "Ginny", "Katie"}),

                new Team("SLYTHERIN", "Vincent",  "Draco",
                        new String[] {"Bridget", "Harper", "Malcolm"})
        );
    }

    @Test
    public void hasNullTest () {
        String[] chasers = new String[] {null, "Ginny", "Katie"};
        Assertions.assertTrue(Team.hasNull(chasers));
    }

    @Test
    public void hasBlank () {
        String[] chasers = new String[] {" ", "Ginny", "Katie"};
        Assertions.assertTrue(Team.hasBlank(chasers));
    }

    @Test
    public void getPlaceholderTest () {
        assertEquals("chaser", game.getPlaceholder("<chaser> gets the next pass"));
    }

    @Test
    public void replacePlaceholderTest () {
        assertEquals("Katie gets the next pass", game.replacePlaceholder("<chaser> gets the next pass", "chaser", "Katie"));
    }

    @Test
    public void quaffleScoreTest () {
        Team team = game.getTeam("GRYFFINDOR");
        game.quaffleScore(team);
        game.quaffleScore(team);
        assertEquals(Game.getQuafflePoints() * 2, game.getScore(team));
    }

    @Test
    public void catchSnitchTest () {
        Team team = game.getTeam("SLYTHERIN");
        game.catchSnitch(team);
        assertEquals(Game.SNITCH_POINTS, game.getScore(team));
    }
}
