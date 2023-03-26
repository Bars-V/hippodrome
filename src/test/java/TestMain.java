import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class TestMain {

    @Disabled
    @Test
    @Timeout(22_000)
    public void TestMainSpeed() throws Exception {
        String[] args = { "arg1"};
        Main.main(args);
    }
}
