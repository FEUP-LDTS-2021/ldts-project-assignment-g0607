package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int level;
    private int width;
    private int height;
    private Frog frog;
    private List<Car> cars = new ArrayList<>();
    private List<TreeTrunk> treeTrunks = new ArrayList<>();
    private List<Turtle> turtles = new ArrayList<>();
    private Water water;
    private Grass grass;
    private Sidewalk firstSidewalk;
    private Sidewalk secondSidewalk;

    public Arena (int level, int width, int height) {
        this.level = level;
        this.width = width;
        this.height = height;

        //non-movable elements have fixed positions in the arena
        this.water = new Water(4, 13);
        this.grass = new Grass(0, 3);
        this.firstSidewalk = new Sidewalk(27, 29);
        this.secondSidewalk = new Sidewalk(14, 16);
        createFrog();
        createCars();
        createTreeTrunks();
        createTurtles();
    }

    public void createFrog() {
        this.frog = (Frog) new MovableElementsFactory(level, "Frog").create().get(0);
    }

    public void createCars() {
        for (int row = secondSidewalk.getPosition().getYMax()+1; row < firstSidewalk.getPosition().getYMin(); row++) {
            List<MovableElement> m = new MovableElementsFactory(level, row, "Car").create();
            if (cars.isEmpty())
                cars = new ArrayList<Car>((List) m);
            else
                cars.addAll(new ArrayList<Car>((List) m));
        }
    }

    public void createTreeTrunks() {
        for (int row = water.getPosition().getYMin()+1; row <= water.getPosition().getYMax(); row++) {
            List<MovableElement> m = new MovableElementsFactory(level, row, "TreeTrunk").create();
            if (treeTrunks.isEmpty())
                treeTrunks = new ArrayList<TreeTrunk>((List) m);
            else
                this.treeTrunks.addAll(new ArrayList<TreeTrunk>((List) m));
            row++;
        }
    }

    public void createTurtles() {
        for (int row = water.getPosition().getYMin(); row <= water.getPosition().getYMax(); row++) {
            List<MovableElement> m = new MovableElementsFactory(level, row, "Turtle").create();
            if (turtles.isEmpty())
                turtles = new ArrayList<Turtle>((List) m);
            else
                this.turtles.addAll(new ArrayList<Turtle>((List) m));
            row++;
        }
    }

    public Frog getFrog(){
        return frog;
    }

    public List<Car> getCars(){
        return cars;
    }

    public List<TreeTrunk> getTreeTrunks(){
        return treeTrunks;
    }

    public List<Turtle> getTurtles(){
        return turtles;
    }

    public Water getWater(){
        return water;
    }

    public Grass getGrass(){
        return grass;
    }

    public Sidewalk getFirstSidewalk() {
        return firstSidewalk;
    }

    public Sidewalk getSecondSidewalk() {
        return secondSidewalk;
    }

    //for testing purposes only
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    //for testing purposes only
    public void setTreeTrunks(List<TreeTrunk> treeTrunks) {
        this.treeTrunks = treeTrunks;
    }

    //for testing purposes only
    public void setTurtles(List<Turtle> turtles) {
        this.turtles = turtles;
    }

    public void setFrog(Frog newFrog){
        frog = newFrog;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        water.draw(graphics);
        firstSidewalk.draw(graphics);
        secondSidewalk.draw(graphics);
        grass.draw(graphics);
        for (Car car: cars)
            car.draw(graphics);
        for(TreeTrunk treeTrunk: treeTrunks)
            treeTrunk.draw(graphics);
        for(Turtle turtle: turtles)
            turtle.draw(graphics);
        frog.draw(graphics);
    }

    public void moveFrog(Position position) {
        if (canFrogMove(position))
            frog.setPosition(position);
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyCarCollision(Position frogNewPosition) {
        for(Car car: cars) {
            if (car.getPosition().equals(frogNewPosition)) {
                //State = lose
                System.out.println("GAME OVER");
                return true;
            }
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyTurtleCollision(Position frogNewPosition) {
        for(Turtle turtle : turtles) {
            if (turtle.getPosition().equals(frogNewPosition))
                return true;
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyTreeTrunkCollision(Position frogNewPosition) {
        for (TreeTrunk treeTrunk : treeTrunks) {
            if (treeTrunk.getPosition().equals(frogNewPosition))
                return true;
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyWaterCollision(Position frogNewPosition) {
        if (frogNewPosition.getY() >= water.getPosition().getYMin() && frogNewPosition.getY() <= water.getPosition().getYMax()
                && !verifyTreeTrunkCollision(frogNewPosition) && !verifyTurtleCollision(frogNewPosition)) {
            //State = lose
            System.out.println("GAME OVER");
            return true;
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyGrassCollision(Position frogNewPosition) {
        if (frogNewPosition.getY() >= grass.getPosition().getYMin() && frogNewPosition.getY() <= grass.getPosition().getYMax()) {
            //State = win
            System.out.println("YOU WON");
            return true;
        }
        return false;
    }

    public boolean canFrogMove(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= width || y < 0 || y >= height)
            return false;

        if (this.verifyCarCollision(position))
            return false;
        if (this.verifyWaterCollision(position))
            return false;
        if (this.verifyGrassCollision(position))
            return true;
        if (this.verifyTreeTrunkCollision(position))
            return true;
        if (this.verifyTurtleCollision(position))
            return true;
        //TODO: ldts.frogger.Water restriction and ldts.frogger.Grass restriction
        return true;
    }

    public void moveMovableElements() {
        for (Car car: cars) {
            if (car.getMovementDirection() == "left")
                car.move(new MoveLeft());
            else //car.getMovementDirection() == "right"
                car.move(new MoveRight());
        }
        for (TreeTrunk treeTrunk: treeTrunks) {
            if (treeTrunk.getMovementDirection() == "left")
                treeTrunk.move(new MoveLeft());
            else //treeTrunk.getMovementDirection() == "right"
                treeTrunk.move(new MoveRight());
        }
        for (Turtle turtle: turtles) {
            if (turtle.getMovementDirection() == "left")
                turtle.move(new MoveLeft());
            else //turtle.getMovementDirection() == "right"
                turtle.move(new MoveRight());
        }
    }
}
