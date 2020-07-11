package WORETO.documents;

public enum  Status {
    DRAFT, SUBMITTED, TRANSFERRED, BILLED;
    public String roleName(){return "ROLE_"+this.toString();}
}
