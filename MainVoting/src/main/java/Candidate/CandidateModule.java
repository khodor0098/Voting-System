package Candidate;

public class CandidateModule {

    private Integer id;
    private String Cname;
    private String CEmail;
    private String Cpassword;
    private Integer Cage;
    private Integer eid;
    private Integer oid;
    private Integer numberOfVotes;

    public CandidateModule(Integer id, String Cname, String CEmail, Integer Cage, Integer eid,Integer numberOfVotes) {
        this.id = id;
        this.Cname = Cname;
        this.CEmail = CEmail;
        this.Cage = Cage;
        this.eid = eid;
        this.numberOfVotes=numberOfVotes;
    }
    public void setCEmail(String CEmail) {
        this.CEmail = CEmail;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCage(Integer cage) {
        Cage = cage;
    }

    public void setCpassword(String cpassword) {
        Cpassword = cpassword;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }


    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getCpassword() {
        return Cpassword;
    }

    public Integer getOid() {
        return oid;
    }



    public Integer getCage() {
        return Cage;
    }

    public Integer getId() {
        return id;
    }

    public String getCEmail() {
        return CEmail;
    }

    public Integer getEid() {
        return eid;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public String getCname() {
        return Cname;
    }

    @Override
    public String toString() {
        return "CandidateModule{" +
                "id=" + id +
                ", Cname='" + Cname + '\'' +
                ", CEmail='" + CEmail + '\'' +
                ", Cpassword='" + Cpassword + '\'' +
                ", Cage=" + Cage +
                ", eid=" + eid +
                ", oid=" + oid +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }
}
