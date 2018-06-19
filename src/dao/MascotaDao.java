/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Mascota;

/**
 *
 * @author LN710Q
 */
public class MascotaDao implements metodos<Mascota> {
    private static final String SQL_INSERT = "INSERT INTO RAZAS(codmascota,nombre,raza,propietario , existencia) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE RAZAS SET nombre=?, raza=?, propietario=?, existencia=? WHERE codmascota=?";
    private static final String SQL_DELETE = "DELETE FROM RAZAS WHERE codmascota=?";
    private static final String SQL_READ = " SELECT *FROM RAZAS WHERE codmascota=?";
    private static final String SQL_READALL = "SELECT *FROM RAZAS";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Mascota g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNinscripcion());
            ps.setString(2, g.getRaza());
            ps.setBoolean(3, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MascotaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MascotaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Mascota c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getNinscripcion());
            ps = con.getCnx().clientPrepareStatement(SQL_UPDATE);
            ps.setString(1, c.getRaza());
            ps.setInt(2, c.getStock());
            ps.setBoolean(3, c.getExistencia());
            ps.setString(4, c.getNinscripcion());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MascotaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Mascota read(Object key) {
        Mascota f=null;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps= con.getCnx().clientPrepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                f= new Mascota(rs.getString(1),rs.getInt(2),rs.getBoolean(3),rs.getInt(4));   
            }
            rs.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(MascotaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Mascota> readAll() {
        ArrayList<Mascota> all =new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s= con.getCnx().clientPrepareStatement(SQL_READALL);
            rs=s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Mascota(rs.getString(1), rs.getInt(2),rs.getBoolean(3),rs.getInt(4)));
            }
            rs.close();
        }catch(SQLException ex){
            Logger.getLogger(MascotaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}   


