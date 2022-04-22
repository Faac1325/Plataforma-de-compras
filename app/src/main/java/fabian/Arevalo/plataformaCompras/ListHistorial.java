package fabian.Arevalo.plataformaCompras;



public class ListHistorial  {
    public String texnombre;
    public String texcorreo;
    public String texplan;
    public String texprecio;
    public String texfecha;
    public String cedula;
    public String pin;



    public ListHistorial(String texnombre, String texcorreo, String texplan, String texprecio, String texfecha, String cedula, String pin) {
        this.texnombre = texnombre;
        this.texcorreo = texcorreo;
        this.texplan = texplan;
        this.texprecio = texprecio;
        this.texfecha = texfecha;
        this.cedula = cedula;
        this.pin= pin;
    }

    public String getTexnombre() {
        return texnombre;
    }

    public void setTexnombre(String texnombre) {
        this.texnombre = texnombre;
    }

    public String getTexcorreo() {
        return texcorreo;
    }

    public void setTexcorreo(String texcorreo) {
        this.texcorreo = texcorreo;
    }

    public String getTexplan() {
        return texplan;
    }

    public void setTexplan(String texplan) {
        this.texplan = texplan;
    }

    public String getTexprecio() {
        return texprecio;
    }

    public void setTexprecio(String texprecio) {
        this.texprecio = texprecio;
    }

    public String getTexfecha() {
        return texfecha;
    }

    public void setTexfecha(String texfecha) {
        this.texfecha = texfecha;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}