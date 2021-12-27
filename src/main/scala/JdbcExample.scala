import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

import java.io.PrintWriter
import java.sql.{Connection, DriverManager, ResultSet}
import java.util.logging.Logger
import javax.sql.DataSource
import scala.jdk.CollectionConverters._


case class User(host: String, user: String)

object JdbcExample extends App {
  val connection = makeConnection()
  val jdbc = makeJdbc(connection)
  val rowMapper = new RowMapper[User] {
    override def mapRow(rs: ResultSet, rowNum: Int): User = {
      User(
        host = rs.getString("host"),
        user = rs.getString("user")
      )
    }
  }

  val users = jdbc.query("SELECT host, user FROM user", Map[String, String]().asJava, rowMapper).asScala
  println(users)



  def makeJdbc(connection: Connection) = {
    val dataSource = new DataSource {
      override def getConnection: Connection = connection

      override def getConnection(username: String, password: String): Connection = ???

      override def getLogWriter: PrintWriter = ???

      override def setLogWriter(out: PrintWriter): Unit = ???

      override def setLoginTimeout(seconds: Int): Unit = ???

      override def getLoginTimeout: Int = ???

      override def getParentLogger: Logger = ???

      override def unwrap[T](iface: Class[T]): T = ???

      override def isWrapperFor(iface: Class[_]): Boolean = ???
    }
    new NamedParameterJdbcTemplate(dataSource)
  }

  def makeConnection() = {
    val url = "jdbc:mysql://localhost:3306/mysql?allowPublicKeyRetrieval=true&useSSL=false"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "root"
    Class.forName(driver)
    DriverManager.getConnection(url, username, password)
  }
}
