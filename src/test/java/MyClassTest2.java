import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class MyClassTest {

    private MyClass myClass;

    @Parameterized.Parameters
    public static List<Object[][]> data(){
        return (List<Object[][]>) Arrays.asList(new Object[][][]{
                {{2,4,5,6,7}, {5,6,7}},
                {{3,2,5,8,5,6,7}, {}},
                {{6,5,8,7,3,4,12,1,2,4,65,7,8}, {65, 7, 8}},
                {{5,6,7,8,3,2,1}, {}}
        });
    }

    private int[] a;
    private int[] res;

    public MyClassTest(int[] a, int[] res) {
        this.a = a;
        this.res = res;
    }

    @Before
    public void prepare(){
        this.myClass = new MyClass();
    }

    @Test
    public void test(){
        Assert.assertEquals(res, myClass.myArray(a));
    }

    @Test
    public void test2(){
        Assert.assertEquals(res, myClass.myArray2(a));
    }


}
