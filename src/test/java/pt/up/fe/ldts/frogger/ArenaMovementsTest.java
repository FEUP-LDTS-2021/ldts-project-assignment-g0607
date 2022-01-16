package pt.up.fe.ldts.frogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class ArenaMovementsTest {

    @Test
    public void moveFrogTrueTest() {
        Arena newArena = new Arena(1, 60, 30);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car(2, 2, "left"));
        cars.add(new Car(4, 6, "right"));
        newArena.setCars(cars);

        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 24, "left"));
        treeTrunks.add(new TreeTrunk(24, 24, "right"));
        newArena.setTreeTrunks(treeTrunks);

        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 19, "left"));
        turtles.add(new Turtle(19, 24, "right"));
        newArena.setTurtles(turtles);

        //positions the frog will be moving to
        Position availablePositionUp = new Position (30, 28); //move up
        Position availablePositionLeft = new Position(29, 29);  //move left
        Position availablePositionRight = new Position(31, 29);  //move right

        newArena.moveFrog(availablePositionUp);
        Assertions.assertEquals(newArena.getFrog().getPosition().getX(), availablePositionUp.getX());
        Assertions.assertEquals(newArena.getFrog().getPosition().getY(), availablePositionUp.getY());

        newArena.moveFrog(availablePositionLeft);
        Assertions.assertEquals(newArena.getFrog().getPosition().getX(), availablePositionLeft.getX());
        Assertions.assertEquals(newArena.getFrog().getPosition().getY(), availablePositionLeft.getY());

        newArena.moveFrog(availablePositionRight);
        Assertions.assertEquals(newArena.getFrog().getPosition().getX(), availablePositionRight.getX());
        Assertions.assertEquals(newArena.getFrog().getPosition().getY(), availablePositionRight.getY());
    }

    @Test
    public void moveFrogFalseTest() {
        Arena newArena = new Arena(1, 60, 30);
        Position expectedPosition = new Position(30, 29);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car(30, 28, "left"));
        cars.add(new Car(29, 29, "right"));
        cars.add(new Car(31, 29, "left"));
        newArena.setCars(cars);

        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 24, "left"));
        treeTrunks.add(new TreeTrunk(24, 24, "right"));
        newArena.setTreeTrunks(treeTrunks);

        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 19, "left"));
        turtles.add(new Turtle(19, 24, "right"));
        newArena.setTurtles(turtles);

        //positions the frog will be moving to
        Position unavailablePositionUp = new Position (30, 28); //move up
        Position unavailablePositionLeft = new Position(29, 29);  //move left
        Position unavailablePositionRight = new Position(31, 29);  //move right

        newArena.moveFrog(unavailablePositionUp);
        Assertions.assertEquals(newArena.getFrog().getPosition().getX(), expectedPosition.getX());
        Assertions.assertEquals(newArena.getFrog().getPosition().getY(), expectedPosition.getY());

        newArena.moveFrog(unavailablePositionLeft);
        Assertions.assertEquals(newArena.getFrog().getPosition().getX(), expectedPosition.getX());
        Assertions.assertEquals(newArena.getFrog().getPosition().getY(), expectedPosition.getY());

        newArena.moveFrog(unavailablePositionRight);
        Assertions.assertEquals(newArena.getFrog().getPosition().getX(), expectedPosition.getX());
        Assertions.assertEquals(newArena.getFrog().getPosition().getY(), expectedPosition.getY());
    }

    @Test
    public void verifyCarCollision() {
        Arena newArena = new Arena(1, 60, 30);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(2, 2, "left"));
        cars.add(new Car(4, 6, "right"));
        cars.add(new Car(1, 2, "left"));
        newArena.setCars(cars);

        Assertions.assertEquals(false, newArena.verifyCarCollision(new Position(3, 3)));
        Assertions.assertEquals(false, newArena.verifyCarCollision(new Position(7, 4)));
        Assertions.assertEquals(false, newArena.verifyCarCollision(new Position(1, 1)));
        Assertions.assertEquals(true, newArena.verifyCarCollision(new Position(2, 2)));
        Assertions.assertEquals(true, newArena.verifyCarCollision(new Position(4, 6)));
        Assertions.assertEquals(true, newArena.verifyCarCollision(new Position(1, 2)));
    }

    @Test
    public void verifyTreeTrunkCollision() {
        Arena newArena = new Arena(1, 60, 30);
        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 24, "left"));
        treeTrunks.add(new TreeTrunk(24, 24, "left"));
        treeTrunks.add(new TreeTrunk(4, 24, "left"));
        treeTrunks.add(new TreeTrunk(15, 15, "right"));
        newArena.setTreeTrunks(treeTrunks);

        Assertions.assertEquals(false, newArena.verifyTreeTrunkCollision(new Position(1, 1)));
        Assertions.assertEquals(false, newArena.verifyTreeTrunkCollision(new Position(15, 14)));
        Assertions.assertEquals(false, newArena.verifyTreeTrunkCollision(new Position(14, 15)));
        Assertions.assertEquals(true, newArena.verifyTreeTrunkCollision(new Position(35, 24)));
        Assertions.assertEquals(true, newArena.verifyTreeTrunkCollision(new Position(24, 24)));
        Assertions.assertEquals(true, newArena.verifyTreeTrunkCollision(new Position(4, 24)));
        Assertions.assertEquals(true, newArena.verifyTreeTrunkCollision(new Position(15, 15)));
    }

    @Test
    public void verifyTurtleCollision() {
        Arena newArena = new Arena(1, 60, 30);
        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 19, "left"));
        turtles.add(new Turtle(19, 24, "right"));
        turtles.add(new Turtle(15, 25, "right"));
        newArena.setTurtles(turtles);

        Assertions.assertEquals(false, newArena.verifyTurtleCollision(new Position(1, 1)));
        Assertions.assertEquals(false, newArena.verifyTurtleCollision(new Position(15, 14)));
        Assertions.assertEquals(false, newArena.verifyTurtleCollision(new Position(14, 15)));
        Assertions.assertEquals(false, newArena.verifyTurtleCollision(new Position(36, 18)));
        Assertions.assertEquals(true, newArena.verifyTurtleCollision(new Position(36, 19)));
        Assertions.assertEquals(true, newArena.verifyTurtleCollision(new Position(19, 24)));
        Assertions.assertEquals(true, newArena.verifyTurtleCollision(new Position(15, 25)));
    }

    @Test
    public void verifyWaterCollisionTrueTest() {
        Arena newArena = new Arena(1, 60, 30);

        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 4, "left"));
        turtles.add(new Turtle(19, 5, "right"));
        turtles.add(new Turtle(15, 6, "right"));
        newArena.setTurtles(turtles);

        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 4, "left"));
        treeTrunks.add(new TreeTrunk(24, 5, "left"));
        treeTrunks.add(new TreeTrunk(4, 6, "left"));
        newArena.setTreeTrunks(treeTrunks);

        //water is always at yMin = 4 and yMax = 13
        //the frog never collides with either a tree trunk or a turtle, therefore it always collides with water
        //Positions with water
        Assertions.assertTrue(newArena.verifyWaterCollision(new Position(25, 4)));
        Assertions.assertTrue(newArena.verifyWaterCollision(new Position(15, 4)));
        Assertions.assertTrue(newArena.verifyWaterCollision(new Position(25, 5)));
        Assertions.assertTrue(newArena.verifyWaterCollision(new Position(15, 5)));
        Assertions.assertTrue(newArena.verifyWaterCollision(new Position(25, 6)));
        Assertions.assertTrue(newArena.verifyWaterCollision(new Position(40, 6)));
        //Positions without water
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(1, 20)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(40, 1)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(20, 15)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(15, 1)));
    }

    @Test
    public void verifyWaterCollisionFalseTest() {
        Arena newArena = new Arena(1, 60, 30);

        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 4, "left"));
        turtles.add(new Turtle(19, 5, "right"));
        turtles.add(new Turtle(15, 6, "right"));
        newArena.setTurtles(turtles);

        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 4, "left"));
        treeTrunks.add(new TreeTrunk(24, 5, "left"));
        treeTrunks.add(new TreeTrunk(4, 6, "left"));
        newArena.setTreeTrunks(treeTrunks);

        //water is always at yMin = 4 and yMax = 13
        //the frog always collides with either a tree trunk or a turtle, therefore it never collides with water
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(36, 19)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(19, 5)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(15, 6)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(35, 4)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(24, 5)));
        Assertions.assertFalse(newArena.verifyWaterCollision(new Position(4, 6)));
    }

    @Test
    public void verifyGrassCollision() {
        Arena newArena = new Arena(1, 60, 30);

        //grass is always at yMin = 0 and yMax = 3
        Assertions.assertEquals(false, newArena.verifyGrassCollision(new Position(25, 5)));
        Assertions.assertEquals(false, newArena.verifyGrassCollision(new Position(26, 10)));
        Assertions.assertEquals(false, newArena.verifyGrassCollision(new Position(27, 15)));
        Assertions.assertEquals(false, newArena.verifyGrassCollision(new Position(28, 20)));
        Assertions.assertEquals(false, newArena.verifyGrassCollision(new Position(29, 25)));
        Assertions.assertEquals(true, newArena.verifyGrassCollision(new Position(30, 1)));
        Assertions.assertEquals(true, newArena.verifyGrassCollision(new Position(1, 2)));
        Assertions.assertEquals(true, newArena.verifyGrassCollision(new Position(13, 3)));
    }

    @Test
    public void canFrogMoveTrueTest() {
        Arena newArena = new Arena(1, 60, 30);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car(3, 25, "left"));
        cars.add(new Car(10, 22, "right"));
        cars.add(new Car(12, 18, "left"));
        newArena.setCars(cars);

        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 4, "left"));
        turtles.add(new Turtle(19, 5, "right"));
        turtles.add(new Turtle(15, 6, "right"));
        newArena.setTurtles(turtles);

        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 4, "left"));
        treeTrunks.add(new TreeTrunk(24, 5, "left"));
        treeTrunks.add(new TreeTrunk(4, 6, "left"));
        treeTrunks.add(new TreeTrunk(15, 7, "right"));
        newArena.setTreeTrunks(treeTrunks);

        Assertions.assertEquals(true, newArena.canFrogMove( new Position(1, 22)));
        Assertions.assertEquals(true, newArena.canFrogMove( new Position(3, 23)));
        Assertions.assertEquals(true, newArena.canFrogMove( new Position(12, 19)));
        //Frog is on top of turtle
        Assertions.assertEquals(true, newArena.canFrogMove( new Position(36, 4)));
        Assertions.assertEquals(true, newArena.canFrogMove( new Position(19, 5)));
        //Frog is on top of a tree trunk
        Assertions.assertEquals(true, newArena.canFrogMove( new Position(35, 4)));
        Assertions.assertEquals(true, newArena.canFrogMove( new Position(15, 7)));
    }

    @Test
    public void canFrogMoveCollisionCarTest() {
        //the frog collides with a car therefore it can't move
        Arena newArena = new Arena(1, 60, 30);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car(3, 25, "left"));
        cars.add(new Car(10, 22, "right"));
        cars.add(new Car(12, 18, "left"));
        newArena.setCars(cars);

        Assertions.assertFalse(newArena.canFrogMove(new Position(3, 25)));
        Assertions.assertFalse(newArena.canFrogMove(new Position(10, 22)));
        Assertions.assertFalse(newArena.canFrogMove(new Position(12, 18)));
    }

    @Test
    public void canFrogMoveCollisionWaterTest() {
        Arena newArena = new Arena(1, 60, 30);

        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(36, 4, "left"));
        turtles.add(new Turtle(19, 5, "right"));
        turtles.add(new Turtle(15, 6, "right"));
        newArena.setTurtles(turtles);

        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(35, 4, "left"));
        treeTrunks.add(new TreeTrunk(24, 5, "left"));
        treeTrunks.add(new TreeTrunk(4, 6, "left"));
        treeTrunks.add(new TreeTrunk(15, 7, "right"));
        newArena.setTreeTrunks(treeTrunks);

        //the frog collides with water therefore it can't move
        Assertions.assertFalse(newArena.canFrogMove(new Position(1, 4)));
        Assertions.assertFalse(newArena.canFrogMove(new Position(3, 5)));
        Assertions.assertFalse(newArena.canFrogMove(new Position(12, 9)));
    }
}

