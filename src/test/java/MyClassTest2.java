import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class MyClassTest2 {

    private MyClass myClass;

    @Parameterized.Parameters
    public static List<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{2,4,5,6,7}, true},
                {new int[]{3,2,5,8,5,6,7}, false},
                {new int[]{6,5,8,7,3,4,12,1,2,4,65,7,8}, true},
                {new int[]{5,6,7,8,3,2,1}, true}
        });
    }

    private int[] a;
    private boolean res;

    public MyClassTest2(int[] a, boolean res) {
        this.a = a;
        this.res = res;
    }

    @Before
    public void prepare(){
        this.myClass = new MyClass();
    }

    @Test
    public void test(){
        Assert.assertEquals(res, myClass.myArray2(a));
    }
}
