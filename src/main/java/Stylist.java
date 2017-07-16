import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Stylist {
  private String name;
  private String expertise;
  private int id;

  public Stylist(String name, String expertise) {
    this.name = name;
    this.expertise = expertise;
  }

  public String getName() {
    return name;
  }

  public String getExpertise() {
    return expertise;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, expertise) VALUES (:name, :expertise)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("email", this.expertise)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
    }
  }

  public static void update(int id, String name, String expertise) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name, expertise = :expertise WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("expertise", expertise)
        .executeUpdate();
    }
  }

  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE stylistid = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getExpertise().equals(newStylist.getExpertise());
    }
  }
}
