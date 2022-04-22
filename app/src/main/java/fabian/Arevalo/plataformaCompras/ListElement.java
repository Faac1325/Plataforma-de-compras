package fabian.Arevalo.plataformaCompras;

public class ListElement {
    public String planes;
    public String precios;
    public  String detalles;

    public ListElement(String planes, String precios, String detalles) {
        this.planes = planes;
        this.precios = precios;
        this.detalles = detalles;
    }

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    public String getPrecios() {
        return precios;
    }

    public void setPrecios(String precios) {
        this.precios = precios;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
