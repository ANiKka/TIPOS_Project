
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultSetConverter {
	
  public static ArrayList<HashMap<String, String>> convert( ResultSet rs ) throws SQLException
  {
    ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();
    ResultSetMetaData rsmd = rs.getMetaData();

    while(rs.next()) {
	  int numColumns = rsmd.getColumnCount();
	  HashMap<String, String> map = new HashMap<String, String>();

	  for( int i=1; i<numColumns+1; i++) {
	    String column_name = rsmd.getColumnName(i);
	    map.put(column_name, rs.getString(column_name));
	   /* switch( rsmd.getColumnType( i ) ) { 
	     case java.sql.Types.ARRAY:
	        map.put(column_name, rs.getArray(column_name));     break;
	      case java.sql.Types.BIGINT:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.BOOLEAN:
	        map.put(column_name, rs.getBoolean(column_name));   break;
	      case java.sql.Types.BLOB:
	        map.put(column_name, rs.getBlob(column_name));      break;
	      case java.sql.Types.DOUBLE:
	        map.put(column_name, rs.getDouble(column_name));    break;
	      case java.sql.Types.FLOAT:
	        map.put(column_name, rs.getFloat(column_name));     break;
	      case java.sql.Types.INTEGER:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.NVARCHAR:
	        map.put(column_name, rs.getNString(column_name));   break;
	      case java.sql.Types.VARCHAR:
	        map.put(column_name, rs.getString(column_name));    break;
	      case java.sql.Types.TINYINT:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.SMALLINT:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.DATE:
	        map.put(column_name, rs.getDate(column_name));      break;
	      case java.sql.Types.TIMESTAMP:
	        map.put(column_name, rs.getTimestamp(column_name)); break;
	      default:
	        map.put(column_name, rs.getObject(column_name));    break;
	    }*/
	  }

	  al.add(map);
	}

    return al;
  }
  
  public static HashMap<String, String> convertOne( ResultSet rs ) throws SQLException
  {
	  HashMap<String, String> map = new HashMap<String, String>();
    ResultSetMetaData rsmd = rs.getMetaData();
    
    while(rs.next()) {
	  int numColumns = rsmd.getColumnCount();	  
	  for( int i=1; i<numColumns+1; i++) {
	    String column_name = rsmd.getColumnName(i);
	    map.put(column_name, rs.getString(column_name));
	   /* switch( rsmd.getColumnType( i ) ) { 
	     case java.sql.Types.ARRAY:
	        map.put(column_name, rs.getArray(column_name));     break;
	      case java.sql.Types.BIGINT:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.BOOLEAN:
	        map.put(column_name, rs.getBoolean(column_name));   break;
	      case java.sql.Types.BLOB:
	        map.put(column_name, rs.getBlob(column_name));      break;
	      case java.sql.Types.DOUBLE:
	        map.put(column_name, rs.getDouble(column_name));    break;
	      case java.sql.Types.FLOAT:
	        map.put(column_name, rs.getFloat(column_name));     break;
	      case java.sql.Types.INTEGER:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.NVARCHAR:
	        map.put(column_name, rs.getNString(column_name));   break;
	      case java.sql.Types.VARCHAR:
	        map.put(column_name, rs.getString(column_name));    break;
	      case java.sql.Types.TINYINT:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.SMALLINT:
	        map.put(column_name, rs.getInt(column_name));       break;
	      case java.sql.Types.DATE:
	        map.put(column_name, rs.getDate(column_name));      break;
	      case java.sql.Types.TIMESTAMP:
	        map.put(column_name, rs.getTimestamp(column_name)); break;
	      default:
	        map.put(column_name, rs.getObject(column_name));    break;
	    }*/
	  }	  
	}

    return map;
  }
  
}