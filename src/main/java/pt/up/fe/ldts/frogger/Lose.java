package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lose {

    private Screen screen;
    private TextGraphics graphics;
    private int width= 60;
    private int height = 30;
    private Game game;
    private String gameOver = "GAME OVER";

    public Lose(Game newGame) throws IOException {
        game = newGame;
        screen = game.getScreen();

        screen.setCursorPosition(null);
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        graphics = game.getGraphics();

        this.draw();
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        //graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.putString(26, 15, gameOver);

        screen.refresh();
        this.returnToMenu();
    }

    public void returnToMenu() throws IOException {

        while(true) {
            KeyStroke key = screen.readInput();
            this.processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    public void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Enter) {
            State newState = new MenuState(game);
            newState.onMenu(game);
        }
    }

}