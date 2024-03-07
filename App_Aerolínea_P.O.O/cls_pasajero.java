public class cls_pasajero {

    private String idStr;
    private String nombreStr;
    private String contactoStr;
    private String correroStr;
    private String sexoStr;
    private String membresiaStr;
    
    public cls_pasajero(String idStr, String nombreStr, String contactoStr, String correroStr, String sexoStr, String membresiaStr) {
        this.idStr = idStr;
        this.nombreStr = nombreStr;
        this.contactoStr = contactoStr;
        this.correroStr = correroStr;
        this.sexoStr = sexoStr;
        this.membresiaStr = membresiaStr;
    }

    // Métodos Getters

    public String getIdStr() {return idStr;}

    public String getNombreStr() {return nombreStr;}

    public String getContactoStr() {return contactoStr;}

    public String getCorreroStr() {return correroStr;}

    public String getSexoStr() {return sexoStr;}

    public String getMembresiaStr() {return membresiaStr;}

}