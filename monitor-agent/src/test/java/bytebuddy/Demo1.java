package bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

/**
 * Created by chenhao.ye on 12/02/2018.
 */
public class Demo1 {


    public static void main(String[] args) {
        AbstractImage image = new Image() {
            @Override
            public void draw() {
                System.out.println("aaa");
            }
        };

        image.draw();
    }

}
