package entity;

public class Multiplicador {

	String opcion1;
    String opcion2;
    boolean ordenInverso;
    float multiplicador;
    float multiplicadorInverso;

    public Multiplicador() {
    }

    public Multiplicador(String opcion1, String opcion2, float multiplicador, float multiplicadorInverso) {
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.multiplicador = multiplicador;
        this.multiplicadorInverso = multiplicadorInverso;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public boolean isOrdenInverso() {
        return ordenInverso;
    }

    public void setOrdenInverso(boolean ordenInverso) {
        this.ordenInverso = ordenInverso;
    }

    public float getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador = multiplicador;
    }

    public float getMultiplicadorInverso() {
        return multiplicadorInverso;
    }

    public void setMultiplicadorInverso(float multiplicadorInverso) {
        this.multiplicadorInverso = multiplicadorInverso;
    }
	

}
