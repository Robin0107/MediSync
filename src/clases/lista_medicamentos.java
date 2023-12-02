
package clases;


public class lista_medicamentos {
    private String cantidad;
    private String descripcion;
    private String precio;
    private String itbis;
    private String sub_total;
    
    
    public lista_medicamentos(String cantidad, String descripcion, String precio, String itbis, String sub_total){
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.itbis = itbis;
        this.sub_total = sub_total;
    }
    
    public String getcantidad() {
        return cantidad;
    }
    
    public void setcantidad(String cantidad){
        this.cantidad = cantidad;
    }
        public String getdescripcion() {
        return descripcion;
    }
    
    public void setdescripcion(String descripcion){
        this.descripcion = descripcion;
    }
        public String getprecio() {
        return precio;
    }
    
    public void setprecio(String precio){
        this.precio = precio;
    }
        public String getitbis() {
        return itbis;
    }
    
    public void setitbis(String itbis){
        this.itbis = itbis;
    }
        public String getsub_total() {
        return sub_total;
    }
    
    public void setsub_total(String sub_total){
        this.sub_total = sub_total;
    }
    
}
