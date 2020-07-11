package WORETO.documents;

public enum  Role {
    ADMIN, PARTNER, TIMERECORDER, BILLING_SYSTEM, ACTIVEDIRECTORY_SYTEM, AUTHENTICATED;

    public String roleName(){return "ROLE_"+this.toString();}
}
