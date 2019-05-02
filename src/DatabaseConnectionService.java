
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;
	private String databaseName;
	private String serverName;

	public DatabaseConnectionService(String serverName, String databaseName) {
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String user, String pass) {
		try {
			this.connection = DriverManager.getConnection(SampleURL.replace("${dbServer}", serverName)
					.replace("${dbName}", databaseName).replace("${user}", user).replace("${pass}", pass));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// what exception?
		}
	}
	
	public String getDatabaseName() {
		return this.databaseName;
	}
}
