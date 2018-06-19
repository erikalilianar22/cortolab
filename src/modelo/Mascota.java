/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Mascota {
    private int id;
    private String nombre;
    private String Ninscripcion;
    private String propietario;
    private int edad;
    private String raza;
    private boolean existencia;
    private int stock;
    
    public Mascota(){
    }
    
    public Mascota(int id,int stock, String raza,String Ninscripcion, String nombre, String propietario, int edad, boolean exitencia){
        this.id=id;
        this.nombre=nombre;
        this.propietario=propietario;
        this.existencia=exitencia;
        this.edad=edad;
        this.raza=raza;
        this.Ninscripcion=Ninscripcion;
        this.stock=stock;
    }

    public Mascota(String nombre,String Ninscripcion, String propietario, int edad, String raza, boolean existencia) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.edad = edad;
        this.raza = raza;
        this.existencia = existencia;
        this.Ninscripcion=Ninscripcion;
        
    }
    

    public Mascota(String raza, boolean existencia, int stock){
        this.raza=raza;
        this.existencia=existencia;
        
    }

    public Mascota(String string, int aInt, boolean aBoolean, int aInt0) {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNinscripcion() {
        return Ninscripcion;
    }

    public void setNinscripcion(String Ninscripcion) {
        this.Ninscripcion = Ninscripcion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }


    
    
}
