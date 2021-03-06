package pt.up.fe.ldts.frogger.element.nonmovable;

import pt.up.fe.ldts.frogger.PositionRange;
import pt.up.fe.ldts.frogger.element.Element;

public abstract class NonMovableElement implements Element {
    protected PositionRange position;

    public NonMovableElement(int min, int max){
        position = new PositionRange(min,max);
    }

    public PositionRange getPosition() {
        return position;
    }
}
