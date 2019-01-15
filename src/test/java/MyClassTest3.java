import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class MyClassTest3 {

    private MyClass myClass;

    @Parameterized.Parameters
    public static List<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{2,4,5,6,7}, new int[]{5,6,7}},
                {new int[]{3,2,5,4,8,5,6,7}, new int[]{8,5,6,7}},
                {new int[]{6,5,8,7,3,4,12,1,2,4,65,7,8}, new int[]{65, 7, 8}},
                {new int[]{5,6,7,8,3,4,2,1}, new int[]{2,1}}
        });
    }

    private int[] a;
    private int[] res;

    public MyClassTest3(int[] a, int[] res) {
        this.a = a;
        this.res = res;
    }

    @Before
    public void prepare(){
        this.myClass = new MyClass();
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(res, myClass.myArray(a));
    }

}
