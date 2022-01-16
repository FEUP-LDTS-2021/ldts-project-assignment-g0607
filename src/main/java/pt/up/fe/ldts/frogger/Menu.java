package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Screen screen;
    private TextGraphics graphics;
    private int width= 60;
    private int height = 30;
    private List<String> options = new ArrayList<String>();
    private Game game;
    private int option = 1;

    public Menu(Game newGame) throws IOException {
        game = newGame;
        screen = game.getScreen();

        screen.setCursorPosition(null);
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        graphics = game.getGraphics();
        options.add("PLAY");
        options.add("INSTRUCTIONS");
        options.add("LEVELS");
        options.add("EXIT");

        this.draw();
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        //graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        int positionY = 10;

        graphics.putString(26, positionY, options.get(0));
        positionY = positionY+5;

        graphics.putString(22, positionY, options.get(1));
        positionY = positionY+5;

        graphics.putString(25, positionY, options.get(2));
        positionY = positionY+5;

        graphics.putString(26, positionY, options.get(3));

        screen.refresh();
        this.choosingOption();
    }

    public void choosingOption() throws IOException {
        graphics.putString(23, 10, "f");
        graphics.putString(32, 10, "f");
        screen.refresh();

        while(true) {
            KeyStroke key = screen.readInput();
            this.processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    public void cleanOptions() throws IOException {
        graphics.putString(23, 10, " ");
        graphics.putString(32, 10, " ");

        graphics.putString(19, 15, " ");
        graphics.putString(36, 15, " ");

        graphics.putString(23, 20, " ");
        graphics.putString(32, 20, " ");

        graphics.putString(24, 25, " ");
        graphics.putString(31, 25, " ");
        screen.refresh();
    }

    public void showOption(int option) throws IOException {
        if(option == 1){
            this.cleanOptions();
            graphics.putString(23, 10, "f");
            graphics.putString(32, 10, "f");
            screen.refresh();
        }
        else if (option == 2){
            this.cleanOptions();
            graphics.putString(19, 15, "f");
            graphics.putString(36, 15, "f");
            screen.refresh();
        }
        else if (option == 3){
            this.cleanOptions();
            graphics.putString(23, 20, "f");
            graphics.putString(32, 20, "f");
            screen.refresh();
        }
        else if (option == 4){
            this.cleanOptions();
            graphics.putString(24, 25, "f");
            graphics.putString(31, 25, "f");
            screen.refresh();
        }
    }

    public void processKey(KeyStroke key) throws IOException {
        switch(key.getKeyType()){
            case ArrowUp:
                if (option > 1){
                    option--;
                }
                this.showOption(option);
                break;
            case ArrowDown:
                if (option < 4){
                    option++;
                }
                this.showOption(option);
                break;
            case Enter:
                if (option == 1 ){
                    State newState = new GameState(game);
                    newState.onPlay(game);
                }
                else if (option == 2 ){
                    Instruction instruction = new Instruction();
                    instruction.show();
                }
                else if (option == 3 ){
                    Level level = new Level(game);
                    level.show();
                }
                else if (option == 4 ){
                    screen.close();
                }
                break;
            default:
                break;
        }
    }

}