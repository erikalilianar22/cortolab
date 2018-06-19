/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.MascotaDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Mascota;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    public JLabel lblNinscripcion,lblNombre,lblPropietario,lblEdad, lblRaza, lblStock, lblExistencia;
    
    public JTextField Ninscripcion, descripcion, stock;
    public JComboBox raza;
    
    ButtonGroup existencia= new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC =130, ALTOC= 30;
    
    DefaultTableModel tm;
    private JTextField Nombre;
    private JTextField Propietario;
    private JTextField Edad;
    private Iterable<Mascota> mascotas;
    
    public Consulta(){
        super ("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container= getContentPane();
        container.add(lblNinscripcion);
        container.add(lblNombre);
        container.add(lblPropietario);
        container.add(lblEdad);
        container.add(lblRaza);
        container.add(lblStock);
        container.add(lblExistencia);
        container.add(Ninscripcion);
        container.add(nombre);
        container.add(propietario);
        container.add(edad);
        container.add(raza);
        container.add(stock);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,600);
        eventos();
    }

    public final void agregarLabels() {
        lblNinscripcion= new JLabel("N°Inscripcion");
        
        lblNombre= new JLabel("Nombre");
        lblPropietario= new JLabel ("Propietario");
        lblRaza= new JLabel("Raza");
        lblExistencia= new JLabel("Estado");
        lblNinscripcion.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblPropietario.setBounds(10, 60, ANCHOC, ALTOC);
        lblRaza.setBounds(10, 60, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 140, ANCHOC, ALTOC);
    }

    public final void formulario() {
        Ninscripcion= new JTextField();
        Nombre=new JTextField();
        Propietario= new JTextField();
        Edad= new JTextField();
        raza= new JComboBox();
        stock= new JTextField();
        si= new JRadioButton("si", true);
        no= new JRadioButton("no");
        resultados= new JTable();
        buscar= new JButton("buscar");
        insertar= new JButton("insertar");
        eliminar= new JButton("eliminar");
        actualizar= new JButton("actualizar");
        limpiar= new JButton("limpiar");
        
        table= new JPanel();
        raza.addItem("Pitbull");
        raza.addItem("Pastor Aleman");
        raza.addItem("Gran danes");
        raza.addItem("Dalmata");
        raza.addItem("Rottweiler");
        existencia= new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        Ninscripcion.setBounds(140, 10, ANCHOC, ALTOC);
        Nombre.setBounds(140, 10, ANCHOC, ALTOC);
        Propietario.setBounds(140, 10, ANCHOC, ALTOC);
        raza.setBounds(140, 60, ANCHOC, ALTOC);
        stock.setBounds(140, 100, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140,50, ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados= new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
        
    }

    private void llenarTabla() {
        tm= new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("N°inscripcion");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en sucursal");
        

        MascotaDao fd= new MascotaDao();
        ArrayList<Mascota> filtros=fd.readAll();
        
        for (Mascota fi: mascotas){
            tm.addRow(new Object[]{fi.getNinscripcion(), fi.getRaza(), fi.getNombre(), fi.getPropietario(), fi.getEdad(), fi.getExistencia()});
        }
        resultados.setModel(tm);
        
    }

    private void eventos() {
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MascotaDao fd= new MascotaDao();
                Mascota f= new Mascota(Ninscripcion.getText(), raza.getSelectedItem().toString(),
                    Integer.parseInt(stock.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if (fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Mascota registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear la mascota");
                }
            }

            private void limpiarCampos() {
            }
            
        });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MascotaDao fd= new MascotaDao();
                Mascota f= new Mascota (Ninscripcion.getText(), Raza.getSelectedItem().toString(),
                            Integer.parseInt(stock.getText()), true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if (fd.update(f)){
                    JOptionPane.showMessageDialog(null, "mascota modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar la mascota");
                }
            }

            private void limpiarCampos() {
            }
            
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MascotaDao fd= new MascotaDao();
                if(fd.delete(Ninscripcion.getText())){
                    JOptionPane.showMessageDialog(null, "mascota eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar la mascota");
                }
            }            
        });
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MascotaDao fd =new MascotaDao();
                Mascota f=fd.read(Ninscripcion.getText());
                if(f== null){
                    JOptionPane.showMessageDialog(null,"la mascota buscada no se ha encontrado");
                }else{
                    Ninscripcion.setText(f.getNinscripcion());
                    raza.setSelectedItem(f.getRaza());
                    stock.setText(Integer.toString(f.getStock()));
                    
                    if(f.getExistencia()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                }
            }
            
        });
        
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
            
        });
    }
    public void limpiarCampos(){
        Ninscripcion.setText("");
        raza.setSelectedItem("Pitbull");
        stock.setText("");
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
            
        });
    }
    
}

