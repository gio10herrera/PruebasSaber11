package com.example.gio10.pruebassaber11.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Estudiante implements Parcelable{
    private int identificacion;
    private String nombre;
    private String apellido;
    private String colegio;
    private String tipoColegio;
    private String departament;
    private String ciudad;
    private double puntaje;

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getTipoColegio() {
        return tipoColegio;
    }

    public void setTipoColegio(String tipoColegio) {
        this.tipoColegio = tipoColegio;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public Estudiante(int id, String nom, String ape, String coleg, String tColeg, String dep, String ciud, double punt) {
        identificacion = id;
        nombre = nom;
        apellido = ape;
        colegio = coleg;
        tipoColegio = tColeg;
        departament = dep;
        ciudad = ciud;
        puntaje = punt;
    }

    private Estudiante(Parcel in) {
        identificacion = in.readInt();
        nombre = in.readString();
        apellido = in.readString();
        colegio = in.readString();
        tipoColegio = in.readString();
        departament = in.readString();
        ciudad = in.readString();
        puntaje = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(identificacion);
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeString(colegio);
        parcel.writeString(tipoColegio);
        parcel.writeString(departament);
        parcel.writeString(ciudad);
        parcel.writeDouble(puntaje);
    }

    public static final Parcelable.Creator<Estudiante> CREATOR
            = new Parcelable.Creator<Estudiante>() {
        public Estudiante createFromParcel(Parcel in) {
            return new Estudiante(in);
        }

        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };
}
