import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHippodrome {
    @Test
    public void testExceptionConstructorWithNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testMessageConstructorWithNullArguments() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    public void testExceptionConstructorWithEmptyArguments() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    public void testMessageConstructorWithEmptyArguments() {
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }
    @Test
    public void testGetHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, 5, 5));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();
        Assertions.assertEquals(horses, returnedHorses);
    }

    @Test
    public void testMove(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for(Horse horse: horses){
            Mockito.verify(horse,  Mockito.times(1)).move();
        }
    }

    @Test
    public void testGetWinner(){
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Brownie", 3, 0));
        horses.add(new Horse("Star", 4, 0));
        horses.add(new Horse("Lucky", 5, 0));
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Horse winner = hippodrome.getWinner();
        Assertions.assertEquals("Lucky", winner.getName());
    }
}
