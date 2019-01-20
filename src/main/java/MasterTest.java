import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MasterTest {

    public static void main(String[] args) {
        start(TestClass.class);
        start(TestClass2.class);
    }

    public static void start(Class<?> className) {

        ArrayList<Method>[] ar = new ArrayList[12];
        for (int i = 0; i < 12; i++) {
            ar[i] = new ArrayList();
        }
        for (Method method : className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if ((ar[0]).size() > 0)
                    throw new RuntimeException();
                else
                    (ar[0]).add(method);
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                if ((ar[11]).size() > 0)
                    throw new RuntimeException();
                else
                    (ar[11]).add(method);
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                (ar[test.value()]).add(method);
            }
        }

        try {
                Object testCase = className.newInstance();
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < (ar[i]).size(); j++) {
                        (ar[i]).get(j).invoke(testCase);
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
