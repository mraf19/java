package programmer_zaman_now.spring.core.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CyclicB {
    private CyclicC cyclicC;
}
