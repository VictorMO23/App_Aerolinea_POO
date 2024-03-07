import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class app_aerolinea {
    static LinkedList<cls_pasajero> pasajero = new LinkedList<cls_pasajero>();  //Creamos la lista de pasajeros
    static LinkedList<cls_compra_tiquetes> compra_tiquetes = new LinkedList<cls_compra_tiquetes>();//Creamos la lista de compra de tiquetes.
    public static float descuento_membresiaFlt;
    public static float sub_totalFlt;
    public static float ivaFlt = (float) 0.19;
    public static float descuento_forma_pagoFlt;
    public static float totalFlt;
    public static float descuentosFlt;
    public static String membresiaStr;
    public static String sexoStr;
    public static String tipo_claseStr;
    public static String forma_pagoStr;

    public static void main(String[] args) {
        fnt_menu(true);
    }

    private static void fnt_consultar_duplicida(String id){

        boolean swBln = false;
        int posicion = 0;

        for(int i=0; i<pasajero.size(); i++){
            if(pasajero.get(i).getIdStr().equals(id)){
                posicion = i;
                swBln = true;
            }
        }

        if (swBln == false){
            fnt_Registrarpasajeros(id);
        }else{
            JOptionPane.showMessageDialog(null, "Este ID ya se encuentra registrado, por favor intente con otro", "ADVERTENCIA", 2);
        }

    }

    private static void fnt_Registrarpasajeros(String id){
        String ID = id;
        String nombre = JOptionPane.showInputDialog(null, "Nombre ", "REGISTRO", 3);
        String contacto = JOptionPane.showInputDialog(null, "Contacto ", "REGISTRO", 3);
        String correo = JOptionPane.showInputDialog(null, "Correo", "REGISTRO", 3);
        String sexo = JOptionPane.showInputDialog(null, "Sexo\n\n1. Masculino \n2.Femenino \n3. Otro ", "REGISTRO", 3);
        String membresia = JOptionPane.showInputDialog(null, "Membresia: \n\n1. Bronce \n2. Plata \n3. Oro \n4. Platino", "REGISTRO", 3);
        if(id.equals("") || nombre.equals("") || contacto.equals("") || correo.equals("") || sexo.equals("") || membresia.equals("")){
            JOptionPane.showMessageDialog(null, "Debe de ingresar todos los datos correspondientes para poder registrar", "ERROR", 0);
        }else{
            pasajero.add(new cls_pasajero(ID, nombre, contacto, correo, sexo, membresia));
            JOptionPane.showMessageDialog(null, "Su registro ha sido éxitoso", "REGISTRAR", 1);
        }
    }

    private static void fnt_consultar(String id){
        boolean swBln = false;
        int posicion = 0;

        for(int i=0; i<pasajero.size(); i++){
            if(pasajero.get(i).getIdStr().equals(id)){
                posicion = i;
                swBln = true;
            }
        }

        if (swBln == false){
            JOptionPane.showMessageDialog(null, "Este ID del cliente no se encontra registrado", "ADVERTENCIA", 2);
        }else{
            String membresia = pasajero.get(posicion).getMembresiaStr();
            fnt_comprar_tiquete(id, membresia);
        }
    }

    private static void fnt_calculo_descuento_membresia(float op1){

        if (op1 == 1){ // Calculo el descuento según la membresia elegida por el pasajero
            descuento_membresiaFlt = (float) 0.10;
        }else if (op1 == 2){
            descuento_membresiaFlt = (float) 0.15;
        }else if(op1 == 3){
            descuento_membresiaFlt = (float) 0.25;
        }else if (op1 == 4){
            descuento_membresiaFlt = (float) 0.35;
        }

    }

    private static void fnt_calcular_valor_tipo_clase(int tipo_claseInt, int cantidad){

        float valor_tipo_claseFlt = 0;

        if (tipo_claseInt == 1) { // Calculo por el tipo de clase
            valor_tipo_claseFlt = (float) 450000;
        }else if(tipo_claseInt == 2){
            valor_tipo_claseFlt = (float) 600000;
        }else if(tipo_claseInt == 3){
            valor_tipo_claseFlt = (float) 900000;
        }

        sub_totalFlt = valor_tipo_claseFlt * cantidad; // Calculamos el subtotal.

    }

    private static void fnt_calcular_forma_pago_total(int f_p){
        if (f_p == 1){ // Calculo por la forma de pago
            descuento_forma_pagoFlt = (float) 0.05; // Se resta del total el 5%
            descuentosFlt = (descuento_forma_pagoFlt * sub_totalFlt) + (descuento_membresiaFlt* sub_totalFlt);
            totalFlt = ((sub_totalFlt + (sub_totalFlt * ivaFlt)) - (descuento_forma_pagoFlt* sub_totalFlt)) - (descuento_membresiaFlt * sub_totalFlt);
        }else if (f_p == 2){
            descuento_forma_pagoFlt = (float) 0.07;// Se suma al total el 7%
            descuentosFlt = ((- descuento_forma_pagoFlt* sub_totalFlt) + (descuento_membresiaFlt* sub_totalFlt));
            totalFlt = ((sub_totalFlt + (sub_totalFlt * ivaFlt)) + (descuento_forma_pagoFlt* sub_totalFlt)) - (descuento_membresiaFlt * sub_totalFlt);
        }else if(f_p  == 3){
            descuento_forma_pagoFlt = (float) 0.02;// Se resta al total el 2%
            descuentosFlt = (descuento_forma_pagoFlt * sub_totalFlt) + (descuento_membresiaFlt* sub_totalFlt);
            totalFlt = ((sub_totalFlt + (sub_totalFlt * ivaFlt)) - (descuento_forma_pagoFlt* sub_totalFlt)) - (descuento_membresiaFlt * sub_totalFlt);
        }else if (f_p == 4){
            descuento_forma_pagoFlt = (float) 0.10;// Se resta al total el 10%
            descuentosFlt = (descuento_forma_pagoFlt * sub_totalFlt) + (descuento_membresiaFlt* sub_totalFlt);
            totalFlt = ((sub_totalFlt + (sub_totalFlt * ivaFlt)) - (descuento_forma_pagoFlt* sub_totalFlt)) - (descuento_membresiaFlt * sub_totalFlt);
        }
    }

    private static void fnt_comprar_tiquete(String i, String membresia){
        
        Date fecha = new Date();
        String id = i;
        int t_clase = Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo de clase: \n\n1. Economica \n2. Turista \n3. VIP", "REGISTRO COMPRA", 3));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de tiquetes a comprar", "REGISTRO COMPRA", 3));
        int f_pago = Integer.parseInt(JOptionPane.showInputDialog(null, "Forma de pago: \n\n1. Efectivo \n2. T.C \n3. T.H \n4. Transferencia", "REGISTRO COMPRA", 3));
        compra_tiquetes.add(new cls_compra_tiquetes(fecha, id, cantidad, t_clase, f_pago));
        JOptionPane.showMessageDialog(null, "Compra registrada con éxito", "REGISTRAR COMPRA", 1);
    }

    private static void fnt_selectores(String op1, String op2, int op3, int op4){

        if (op1.equals("1")){ // Membresia
            membresiaStr = "Bronce";
        }else if (op1.equals("2")){
            membresiaStr = "Plata";
        }else if(op1.equals("3")){
            membresiaStr = "Oro";
        }else if (op1.equals("4")){
            membresiaStr = "Platino";
        }

        if (op2.equals("1")){ // Membresia
            sexoStr = "Masculino";
        }else if (op2.equals("2")){
            sexoStr = "Femenino";
        }else if(op2.equals("3")){
            sexoStr = "Otro";
        }

        if (op3 == 1){ // Membresia
            tipo_claseStr = "Economica";
        }else if (op3 == 2){
            tipo_claseStr = "Turista";
        }else if(op3 == 3){
            tipo_claseStr = "VIP";
        }

        if (op4 == 1){ // Membresia
            forma_pagoStr= "Efectivo";
        }else if (op4 == 2){
            forma_pagoStr = "Tarjeta de Credito";
        }else if(op4 == 3){
            forma_pagoStr = "T.H";
        }else if (op4 == 4){
            forma_pagoStr = "Transferencia";
        }
    }

    private static void fnt_reporte(String id){

        boolean swBln = false;
        int posicion = 0;

        for(int i=0; i<pasajero.size(); i++){
            if(pasajero.get(i).getIdStr().equals(id)){
                posicion = i;
                swBln = true;
            }
        }

        if (swBln == false){
            JOptionPane.showMessageDialog(null, "Este ID del cliente no se encontra registrado por tanto no tiene un reporte", "ADVERTENCIA", 2);
        }else{

            boolean swBln2 = false;
            int posicion1 = 0;

            for(int i=0; i<compra_tiquetes.size(); i++){
                if(compra_tiquetes.get(i).getId_clienteStr().equals(id)){
                    posicion1 = i;
                    swBln2 = true;
                }
            }
    
            if (swBln2 == false){
                JOptionPane.showMessageDialog(null, "El ID no ha realizado ninguna comprar para poder realizar el reporte", "ADVERTENCIA", 2);
            }else{

            float op1 = Float.parseFloat(pasajero.get(posicion).getMembresiaStr());
            fnt_calculo_descuento_membresia(op1);
            int t_c= compra_tiquetes.get(posicion).getTipo_claseInt();
            int c = compra_tiquetes.get(posicion).getCantidadInt();
            fnt_calcular_valor_tipo_clase(t_c, c);
            int op3 = compra_tiquetes.get(posicion).getForma_pagoInt();
            fnt_calcular_forma_pago_total(op3);

            String m = pasajero.get(posicion).getMembresiaStr();
            String s = pasajero.get(posicion).getSexoStr();
            int tipo_c = compra_tiquetes.get(posicion1).getTipo_claseInt();
            int forma_p = compra_tiquetes.get(posicion1).getForma_pagoInt();
            fnt_selectores(m, s, tipo_c, forma_p);

            JOptionPane.showMessageDialog(null, "ID: " + pasajero.get(posicion).getIdStr() +
            "\nNombre: " + pasajero.get(posicion).getNombreStr()+
            "\nContacto: " + pasajero.get(posicion).getContactoStr() +
            "\nCorreo: " + pasajero.get(posicion).getCorreroStr() +
            "\nSexo: " + sexoStr +
            "\nMembresia: " + membresiaStr +
            "\nFecha: " + new Date() +
            "\nTipo de clase: " + tipo_claseStr +
            "\nCantidad de tiques: " + compra_tiquetes.get(posicion1).getCantidadInt() +
            "\nForma de pago: " + forma_pagoStr +
            "\nSubtotal: " + sub_totalFlt +
            "\nMonto del IVA: " + (ivaFlt * sub_totalFlt) +
            "\nDescuento aplicado: "+ descuentosFlt +
            "\nTotal: " + totalFlt, "REPORTE", 1);
            
            }
        }
    }

    public static void fnt_menu(boolean m){

        while (m == true){
            String menu = JOptionPane.showInputDialog(null, "===== MENÚ PRINCIPAL =====\n\n" +
            "1.Registrar Pasajero\n"+
            "2. Comprar Tiquete\n"+
            "3. Reporte\n"+
            "4. Salir\n", "MENÚ PRINCIPAL", 1);

            if (menu.equals("1")){
                String id = JOptionPane.showInputDialog(null, "ID ", "ID" , 3);
                fnt_consultar_duplicida(id);
            }

            if (menu.equals("2")){
                String id = JOptionPane.showInputDialog(null, "ID del cliente",  "ID CLIENTE " , 3);
                fnt_consultar(id);
            }

            if(menu.equals("3")){
                String id = JOptionPane.showInputDialog(null, "ID del cliente",  "ID CLIENTE " , 3);
                fnt_reporte(id);
            }

            if (menu.equals("4")){
                JOptionPane.showMessageDialog(null, "Gracias por haber utilizado mi código", "Felices códigos" , 1);
                m = false;
            }
        }
    }

    
}
