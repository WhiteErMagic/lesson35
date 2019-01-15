import java.sql.*;
import java.util.ArrayList;

public class MyClass {
    private Connection connection;

    public int[] myArray(int[] arg) throws RuntimeException{
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < arg.length; i++) {
            arr.add((Integer) arg[i]);
        }

        int found = arr.lastIndexOf(4);
        if(found == -1)
            throw new RuntimeException();
        else
            found++;

        int[] intArr = new int[arr.size() - found];
        for (int i = found; i < intArr.length; i++) {
            intArr[i] = arr.get(i);
        }
        return intArr;
    }

    public boolean myArray2(int[] arg){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < arg.length; i++) {
            arr.add((Integer) arg[i]);
        }
        int found = arr.lastIndexOf(4);
        if(found == -1)
            found = arr.lastIndexOf(1);

        if(found == -1)
            return false;
        else
            return true;
    }

    public void connectToBase(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:base.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStr(String name, double ball) throws SQLException{
        connectToBase();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO students (name, ball) " +
                    "VALUES (?, ?)");
        ps.setString(1, name);
        ps.setDouble(2, ball);
        try {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

    }

    public void updateStr(int id, String name, double ball) throws SQLException {
        connectToBase();
        PreparedStatement ps = connection.prepareStatement("UPDATE students SET name = ?, ball = ? WHERE id = ?");
        ps.setString(1, name);
        ps.setDouble(2, ball);
        ps.setInt(3, id);

        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public ResultSet selectStr()  throws SQLException {
        connectToBase();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM students");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return resultSet;
    }

    public Object[] getTestStr(String arg){

        ResultSet resultSet = null;
        connectToBase();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT name, ball FROM students WHERE name = ?");
            ps.setString(1, arg);
            resultSet = ps.executeQuery();
            if(resultSet.next())
                return new Object[]{resultSet.getString(1), resultSet.getDouble(2)};
            else
                return new Object[0];

        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0];
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
