package Voter;

public class Votermodel {

    private Integer id;
    private String Vname;
    private String VEmail;
    private String Vpassword;
    private int Vage;
    private Integer eid;
    private int oid;
    public Votermodel(Integer id, String vname, String vEmail, int vage, Integer eid) {
        this.id = id;
        Vname = vname;
        VEmail = vEmail;
        Vage = vage;
        this.eid = eid;
    }
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getVname() {
        return Vname;
    }

    public void setVname(String vname) {
        Vname = vname;
    }
    public String getVEmail() {
        return VEmail;
    }

    public void setVEmail(String VEmail) {
        this.VEmail = VEmail;
    }

    public String getVpassword() {
        return Vpassword;
    }

    public void setVpassword(String vpassword) {
        Vpassword = vpassword;
    }

    public int getVage() {
        return Vage;
    }

    public void setVage(int vage) {
        Vage = vage;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "Votermodel{" +
                "id=" + id +
                ", Vname='" + Vname + '\'' +
                ", VEmail='" + VEmail + '\'' +
                ", Vpassword='" + Vpassword + '\'' +
                ", Vage=" + Vage +
                ", eid=" + eid +
                '}';
    }
}
