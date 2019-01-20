public class TestClass2 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Метод @BeforeSuite");
    }

    @BeforeSuite
    public void beforeSuite2(){
        System.out.println("Метод @BeforeSuite2");
    }

    @Test(value = 1)
    public void test1(){
        System.out.println("Метод @test приоритет 1");
    }

    @Test(value = 3)
    public void test2(){
        System.out.println("Метод @test приоритет 3");
    }

    @Test(value = 2)
    public void test3(){
        System.out.println("Метод @test приоритет 2");
    }

    @Test(value = 2)
    public void test4(){
        System.out.println("Метод @test приоритет 2");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Метод @AfterSuite");
    }


}
