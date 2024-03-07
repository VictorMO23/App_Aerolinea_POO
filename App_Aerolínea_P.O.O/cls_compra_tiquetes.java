import java.util.Date;

public class cls_compra_tiquetes {
    
    private Date fecha_actual;
    private String id_clienteStr;
    private int cantidadInt;
    private int tipo_claseInt;
    private int forma_pagoInt;

    

    public cls_compra_tiquetes(Date fecha, String id_clienteStr, int cantidadInt, int tipo_claseInt, int forma_pagoInt) {
        
        this.fecha_actual = fecha;
        this.id_clienteStr = id_clienteStr;
        this.cantidadInt = cantidadInt;
        this.tipo_claseInt = tipo_claseInt;
        this.forma_pagoInt = forma_pagoInt;

    }

    // Métodos Getters

    public Date getFechaActual() {return fecha_actual;}

    public String getId_clienteStr() {return id_clienteStr;}

    public int getCantidadInt() {return cantidadInt;}

    public int getTipo_claseInt() {return tipo_claseInt;}

    public int getForma_pagoInt() {return forma_pagoInt;}

}
