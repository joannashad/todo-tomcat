
package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String conn = "jdbc:mysql://localhost/todo?user=dbUser&password=secret";
    private String driver = "com.mysql.cj.jdbc.Driver";

    
    public void execSP( String query) throws Exception{
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {  // loop
               // ol.add(resultSet.getString("ID"));
            }
             
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    public void execWithParameters( String query, List<String> params) throws Exception{
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1
            int i = 1;
            for (String s : params) {
                 preparedStatement.setString(i++, s);
            }
            resultSet = preparedStatement.executeQuery();
            
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    public CachedRowSet execResultSet( String query, List<String> params) throws Exception{
        
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1
            int i = 1;
            for (String s : params) {
                 preparedStatement.setString(i++, s);
            }
            resultSet = preparedStatement.executeQuery();
            
            rowset.populate(resultSet);
            
    
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return rowset;
    }
   public CachedRowSet execResults( String query) throws Exception{
        
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
            
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            resultSet = preparedStatement.executeQuery();
            
            rowset.populate(resultSet);
            
    
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return rowset;
    }
     
        public int execID( String query, List<String> params) throws Exception{
            int returnID=0;
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           
            // Parameters start with 1
            int i = 1;
            for (String s : params) {
                 preparedStatement.setString(i++, s);
            }
            returnID=preparedStatement.executeUpdate();
            
            } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
         return returnID;

    }
        

        public void  exec( String query) throws Exception{
            
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(driver);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(conn);
            
    
                        // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement(query);
           preparedStatement.executeUpdate();
            
            } catch (Exception e) {
            	recordError(e.getMessage(),"exec( String "+query+")");
            throw e;
        } finally {
            close();
        }

    }
        
    public void GetList(String fieldName,ResultSet resultSet){
        try{
         while (resultSet.next()) {  // loop
                //ol.add(resultSet.getString(fieldName));
         }
        }
        catch(Exception e){}
        
    }
    private void recordError(String error, String method) throws Exception {
    	try {
    		String query = "INSERT INTO todo.log"
    				+ "(log_error, item)"
    				+ "VALUES ('unhandled exception','delete todo()')";
    		exec(query);
    	}
    	catch(Exception e) {
    		throw e;
    	}
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}

