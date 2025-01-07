package programmer_zaman_now.spring.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FooBar {
    private Foo foo;
    private Bar bar;

//    public Bar getBar() {
//        return bar;
//    }
//
//    public Foo getFoo() {
//        return foo;
//    }
}
