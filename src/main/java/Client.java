import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
  private String name;
  private String contact;
  private int stylistid;
  private int id;

  public Client(String name, String contact, int stylistid) {
    this.name = name;
    this.contact = contact;
    this.stylistid = stylistid;
  }

  public String getName() {
    return name;
  }

  public String getContact() {
    return contact;
  }

  public int getStylistId() {
    return stylistid;
  }

  public int getId() {
    return id;
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, stylistid) VALUES (:name, :stylistid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylistid", this.stylistid)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public static void update(int id, String name, String contact, int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, contact = :contact, stylistid = :stylistid WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("contact", contact)
        .addParameter("stylistid", stylistid)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Client> assigned(int stylistid) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistid = :id ORDER by name";
      return con.createQuery(sql)
        .addParameter("id", stylistid)
        .executeAndFetch(Client.class);
    }
  }


  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }
}
