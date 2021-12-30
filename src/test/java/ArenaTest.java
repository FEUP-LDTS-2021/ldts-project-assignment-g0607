
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ArenaTest {
    private Screen screen;
    private Arena arena;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        arena = new Arena(screen);
    }

    @Test
    void drawTextTest() {
        arena.drawText(new Position(1, 1), "Hello Frog Friends!", "#336699");

        //TODO: ver se funciona
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#336699"));
        //Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 102, 153));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "Hello Frog Friends!");
    }

    @Test
    void drawFrogTest() {
        arena.drawFrog(new Position(1, 1));

        //TODO: ver se funciona
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#33cc33"));
        //Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 204, 51));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "F");
    }

    //TODO: completar testes draw para os outros elementos
}
