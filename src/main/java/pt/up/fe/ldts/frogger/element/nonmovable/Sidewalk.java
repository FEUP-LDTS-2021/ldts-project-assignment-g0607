package pt.up.fe.ldts.frogger.element.nonmovable;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Sidewalk extends NonMovableElement {
    public Sidewalk(int min, int max) {
        super(min,max);
    }

    public void draw(TextGraphics graphics){
        for (int y = position.getYMin(); y <= position.getYMax(); y++) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#b175ff"));
            for (int x = 0; x <= 59; x++)
                graphics.putString(new TerminalPosition(x, y), String.valueOf(' '));
        }
    }
}
