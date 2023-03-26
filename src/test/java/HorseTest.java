
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HorseTest {
    @Test
    public void testExceptionConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    public void testMessageConstructorWithNullName() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "  \t  "})
    public void testExceptionConstructorWithBlankName(String blank) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(blank, 1.0, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "  \t  "})
    public void testMessageConstructorWithBlankName(String blank) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(blank, 1.0, 1.0));
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @Test
    public void testExceptionConstructorWithNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1.0, 1.0));
    }

    @Test
    public void testMessageConstructorWithNegativeSpeed() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    public void testExceptionConstructorWithNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1.0, -1.0));
    }

    @Test
    public void testMessageConstructorWithNegativeDistance() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    public void testMessageGetName() {
        String horse = "horse";
        assertEquals(horse, new Horse(horse, 1.0, 1.0).getName());
    }

    @Test
    public void testMessageGetSpeed() {
        double speed = 100;
        assertEquals(speed, new Horse("horse", speed, 1.0).getSpeed());
    }

    @Test
    public void testMessageGetDistance() {
        double distance = 100;
        assertEquals(distance, new Horse("horse", 1.0, distance).getDistance());
    }

    @Test
    public void testMove() {
        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)) {
            Horse horse = new Horse("Bucephalus", 10, 100);
            horse.move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void testGetRandomDouble() {
        double speed = 10.0;
        double distance = 100.0;
        double randomDouble = 0.5;

        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)) {
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            Horse horse = new Horse("Bucephalus", speed, distance);
            horse.move();

            double expectedDistance = distance + speed * randomDouble;
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance);
        }

    }

}
