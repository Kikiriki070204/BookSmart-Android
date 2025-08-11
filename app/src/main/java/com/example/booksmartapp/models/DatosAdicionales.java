package com.example.booksmartapp.models;

public class DatosAdicionales {
    private String curp;
    private String rfc;
    private String genero;
    private int email_verified;
    private String codigo_tarjeta;

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(int email_verified) {
        this.email_verified = email_verified;
    }

    public String getCodigo_tarjeta() {
        return codigo_tarjeta;
    }

    public void setCodigo_tarjeta(String codigo_tarjeta) {
        this.codigo_tarjeta = codigo_tarjeta;
    }
}
