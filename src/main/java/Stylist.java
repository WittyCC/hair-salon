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

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, expertise) VALUES (:name, :expertise)";
      con.createQuery(sql)
        .addParameter("name", this.name)
        .addParameter("email", this.expertise)
        .executeUpdate();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Stylist.class);
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
