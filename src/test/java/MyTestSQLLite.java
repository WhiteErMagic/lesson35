import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTestSQLLite {

    private MyClass myClass;

    public MyTestSQLLite() {
        this.myClass = new MyClass();
    }

    @Test
    public void testInsert() throws SQLException{
        myClass.insertStr("Жуков", 5);
        ResultSet rs = myClass.selectStr();
        Assert.assertArrayEquals(new Object[]{"Жуков", 5.0}, myClass.getTestStr("Жуков"));
    }

    @Test
    public void testUpdate() throws SQLException{
        myClass.updateStr(3,"Кутузов", 5);
        ResultSet rs = myClass.selectStr();
        Assert.assertArrayEquals(new Object[]{"Кутузов", 5.0}, myClass.getTestStr("Кутузов"));
    }

    @Test
    public void testSelect() throws SQLException{
        Assert.assertArrayEquals(new Object[]{"Жуков", 5.0}, myClass.getTestStr("Жуков"));
    }
}
