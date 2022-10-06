package Election;


public class ElectionModel {
    private Integer Eid;
    private Integer Oid;
    private String ename;
    private String  sdate;
    private String edate;

    public ElectionModel(Integer eid,Integer oid,String ename,String sdate,String edate ) {
        this.Eid = eid;
        this.Oid=oid;
        this.ename=ename;
        this.edate=edate;
        this.sdate=sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getOid() {
        return Oid;
    }

    public void setOid(int oid) {
        Oid = oid;
    }

    public Integer getEid() {
        return Eid;
    }

    public void setEid(int eid) {
        Eid = eid;
    }

    @Override
    public String toString() {
        return "ElectionModel{" +
                "Eid=" + Eid +
                ", Oid=" + Oid +
                ", ename='" + ename + '\'' +
                ", sdate='" + sdate + '\'' +
                ", edate='" + edate + '\'' +
                '}';
    }
}
