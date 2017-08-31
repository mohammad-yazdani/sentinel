import com.sentinel.lib.JWT.Jwt;
import org.junit.Test;

public class JWTTest {

    @Test
    public void Simple() throws Exception {
        System.out.println(Jwt.getPublicKey().toString());
    }
}
