import io.github.aks.utils.Decoder;
import net.querz.nbt.tag.CompoundTag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoderTest {
    @Test
    void arrayEncoderShouldReturnString(){
        String t = Decoder.intArrayToString(new int[]{31,-117,8,0,0,0,0,0,0,-1,-59,91,75,111,27,-41,21,30,89,-79,-34,-110,45,-7,33,-65,-30,-36,-40,117,44,71,-114,35,82,18,});
//        byte[] nbt = Decoder.decodeToNBT(t);
//        CompoundTag compoundTag = Decoder.parsedNBT(nbt);
        System.out.println(t);
        assertNotNull(t);
    }
}
