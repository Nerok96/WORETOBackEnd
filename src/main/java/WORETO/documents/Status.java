package WORETO.documents;

public enum  Status {
    DRAFT, READY, TRANSFERRED, BILLED;
    public String roleName(){return "ROLE_"+this.toString();}
}
