
package facturacion;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import com.borland.dbswing.*;





public class Ventana_Actualizar_Prestamo
    extends JFrame {
  static int openFrameCount = 0;
  static final int xOffset = 30, yOffset = 30;
  TableModel_Nueva myModel;
  JdbTable table;
  JPanel contentPane;
  String[][] data = new String[1][3];
  JScrollPane scrollPane;
  int[] tamaños;
  public static String[] Nombres = {
      " Codigo ", "Descripción", "Cantidad"};
  public static int tamaño_columnas[] = {
      65, 218, 70};
  public static String[] Modificable = {
      "false", "false", "false"};
  int counter = 0;
  boolean bandera = false;
  boolean bandera_mod = false;
  int cual_select = -1;
  String datos[][];
  fecha fecha = new fecha();
  public String asignacion[][] = new String[100][3];
  Control_de_Datos control = new Control_de_Datos();
  Consultar consulta = new Consultar();
  String numPrestamo;
  //Consultar consul = new Consultar();
  //Consultar consul = new Consultar();
  JTextField Codigo = new JTextField();
  JTextField Cantidad = new JTextField();
  JButton Adicionar = new JButton();
  JButton Limpiar = new JButton();
  JToggleButton Cancelar = new JToggleButton();
  JTextField Descripcion = new JTextField();
  ImageIcon imagen;
  ImageIcon imagenbuscar;
  JLabel NumeroRegistros = new JLabel();
  Border border1;
  JLabel jLabel1 = new JLabel();
  JLabel NroPrestamos = new JLabel();
  JTextField CodigoCliente = new JTextField();
  JLabel Cliente = new JLabel();
  Border border2;
  JLabel LabelCliente = new JLabel();
  JTextField Fecha = new JTextField();
  JLabel LabelCliente1 = new JLabel();

  public Ventana_Actualizar_Prestamo() {

    this.setLocation(2, 90);
    iniciar(data);
    // this.cargar_factura();//Metodo donde se cargan todos los elementos de una factura
    myModel = new TableModel_Nueva(Nombres, data, Modificable);
    table = new JdbTable(myModel);
    try {

      jbInit();
      this.tamaño_columnas(tamaño_columnas);
      String[][] datos = consulta.Consultar_modificado(
          "SELECT MAX(CODIGO)+1 FROM PRESTAMO", 1);
      if (datos != null & datos[0][0] != null) {
        this.NroPrestamos.setText(datos[0][0]);
      }
      this.CodigoCliente.requestFocus();
      Fecha.setText(fecha.Buscar_fecha_sistema());

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(156, 156, 158));
    border2 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(165, 163, 151));
    this.getContentPane().setLayout(null);
    this.setVisible(true);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setResizable(false);
    this.setSize(new Dimension(429, 435));
    this.setTitle("Actualizar Inventario Asignado a Empleado");
    //Set the window's location.
    table.setMinimumSize(new Dimension(15, 70));
    table.setPreferredScrollableViewportSize(new Dimension(300, 70));
    contentPane = (JPanel)this.getContentPane();
    //Create the scroll pane and add the table to it.
    scrollPane = new JScrollPane(table);
    //Add the scroll pane to this window.
    contentPane.setMaximumSize(new Dimension(30, 50));
    scrollPane.setMaximumSize(new Dimension(30, 50));
    scrollPane.setBounds(new Rectangle(18, 115, 396, 249));
    Codigo.setEnabled(true);
    Codigo.setFont(new java.awt.Font("Dialog", 0, 13));
    Codigo.setEditable(true);
    Codigo.setText("");
    Codigo.setBounds(new Rectangle(40, 88, 59, 24));
    Codigo.addKeyListener(new Ventana_Actualizar_Prestamo_Codigo_keyAdapter(this));
    Codigo.addActionListener(new Ventana_Actualizar_Prestamo_Codigo_actionAdapter(this));
    Cantidad.setEnabled(true);
    Cantidad.setFont(new java.awt.Font("Dialog", 0, 13));
    Cantidad.setText("");
    Cantidad.setBounds(new Rectangle(329, 88, 53, 24));
    Cantidad.addActionListener(new
                               Ventana_Actualizar_Prestamo_Cantidad_actionAdapter(this));
    Adicionar.setBounds(new Rectangle(144, 55, 51, 27));
    Adicionar.setToolTipText("Adiciona A La Tabla");
    Adicionar.setContentAreaFilled(true);
    Adicionar.setText("A");
    Adicionar.addActionListener(new
                                Ventana_Actualizar_Prestamo_Adicionar_actionAdapter(this));
    Limpiar.setBounds(new Rectangle(204, 55, 51, 27));
    Limpiar.setToolTipText("Borrar");
    Limpiar.setContentAreaFilled(true);
    Limpiar.setFocusPainted(true);
    Limpiar.setSelected(false);
    Limpiar.setText("C");
    Limpiar.addActionListener(new
                              Ventana_Actualizar_Prestamo_Limpiar_actionAdapter(this));

    imagen = new ImageIcon(facturacion.Ventana_Actualizar_Prestamo.class.
                           getResource("Ojo.PNG"));
    this.setIconImage(imagen.getImage());
    Cancelar.setText("Salir");
    Cancelar.setBounds(new Rectangle(231, 370, 122, 27));
    Cancelar.addActionListener(new
                               Ventana_Actualizar_Prestamo_Cancelar_actionAdapter(this));
    imagen = new ImageIcon(facturacion.Ventana_Actualizar_Prestamo.class.
                           getResource("delete.png"));
    imagen = new ImageIcon(facturacion.Ventana_Actualizar_Prestamo.class.
                           getResource("modifica.png"));
    imagenbuscar = new ImageIcon(facturacion.Ventana_Actualizar_Prestamo.class.
                                 getResource("buscar.png"));
    Descripcion.setEnabled(true);
    Descripcion.setFont(new java.awt.Font("Dialog", 0, 13));
    Descripcion.setEditable(false);
    Descripcion.setText("");
    Descripcion.setBounds(new Rectangle(102, 88, 224, 24));
    NumeroRegistros.setFont(new java.awt.Font("Dialog", 1, 11));
    NumeroRegistros.setBorder(border1);
    NumeroRegistros.setText("");
    NumeroRegistros.setBounds(new Rectangle(256, 54, 144, 28));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel1.setText("Nro Asignacion:");
    jLabel1.setBounds(new Rectangle(263, 58, 92, 19));
    NroPrestamos.setBounds(new Rectangle(366, 59, 30, 19));
    NroPrestamos.setFont(new java.awt.Font("Dialog", 1, 11));
    NroPrestamos.setText("1");
    imagen = new ImageIcon(Ventana_Actualizar_Prestamo.class.getResource(
        "delete.png"));
    CodigoCliente.setBounds(new Rectangle(7, 24, 72, 23));
    CodigoCliente.addKeyListener(new
                                 Ventana_Actualizar_Prestamo_CodigoCliente_keyAdapter(this));
    CodigoCliente.addActionListener(new
                                    Ventana_Actualizar_Prestamo_CodigoCliente_actionAdapter(this));
    CodigoCliente.setText("");
    CodigoCliente.setEditable(true);
    CodigoCliente.setFont(new java.awt.Font("Dialog", 0, 13));
    CodigoCliente.setEnabled(true);
    Cliente.setFont(new java.awt.Font("Dialog", 1, 12));
    Cliente.setBorder(border2);
    Cliente.setText("");
    Cliente.setBounds(new Rectangle(81, 24, 252, 26));
    LabelCliente.setFont(new java.awt.Font("Dialog", 1, 13));
    LabelCliente.setText("Empleado");
    LabelCliente.setBounds(new Rectangle(19, 6, 82, 17));
    Fecha.addActionListener(new Ventana_Actualizar_Prestamo_Fecha_actionAdapter(this));
    Fecha.setBounds(new Rectangle(335, 24, 80, 24));
    Fecha.addActionListener(new Ventana_Actualizar_Prestamo_Fecha_actionAdapter(this));
    Fecha.setText("");
    Fecha.setEditable(true);
    Fecha.setFont(new java.awt.Font("Dialog", 0, 13));
    Fecha.setEnabled(true);
    LabelCliente1.setBounds(new Rectangle(358, 6, 43, 17));
    LabelCliente1.setText("Fecha");
    LabelCliente1.setFont(new java.awt.Font("Dialog", 1, 13));
    contentPane.add(Cliente, null);
    contentPane.add(Limpiar, null);
    contentPane.add(Adicionar, null);
    contentPane.add(jLabel1, null);
    contentPane.add(Codigo, null);
    contentPane.add(Descripcion, null);
    contentPane.add(Cantidad, null);
    contentPane.add(Cancelar, null);
    contentPane.add(LabelCliente, null);
    contentPane.add(CodigoCliente, null);
    contentPane.add(Fecha, null);
    contentPane.add(LabelCliente1, null);
    contentPane.add(scrollPane, BorderLayout.CENTER);
    contentPane.add(NroPrestamos, null);
    contentPane.add(NumeroRegistros, null);
    scrollPane.getViewport().add(table, null);
  }

  /**
   *
   * @param tamaños int[] recibe un arreglo con los taamaños de la columnas
   */

  public void tamaño_columnas(int[] tamaños) {
    TableColumn column = null;

    for (int i = 0; i < tamaños.length; i++) {

      column = table.getColumnModel().getColumn(i);
      column.setPreferredWidth(tamaños[i]);
    }
  }

  /**
   *
   * @return Object obtiene los datos de la tabla
   */

  public Object Obtener_datos_finales() {
    return this.myModel.Datos_finales();
  }

  public void tamaño_columnas(int[] tamaños, JdbTable table) {
    TableColumn column = null;

    for (int i = 0; i < tamaños.length; i++) {

      column = table.getColumnModel().getColumn(i);
      column.setPreferredWidth(tamaños[i]);
    }
  }

  public Object[][] Eliminar_Fila(Object[][] Matrix, int Numero_fila) {
    Object[][] datos = new Object[Matrix.length - 1][3];
    for (int i = 0; i < Matrix.length; i++) {
      if (i < Numero_fila) {
        for (int j = 0; j < 3; j++) {
          datos[i][j] = Matrix[i][j];
        }
      }
      if (i > Numero_fila) {
        for (int j = 0; j < 3; j++) {
          datos[i - 1][j] = Matrix[i][j];
        }
      }
    }
    return datos;
  }

  public String[][] copiar_matrix(Object[][] date) {
    String[][] datos = new String[date.length + 1][3];
    for (int i = 0; i < date.length; i++) {
      for (int j = 0; j < 3; j++) {
        datos[i][j] = (String) date[i][j];
      }
    }
    return datos;
  }

  public void iniciar(String[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < 3; j++) {
        matrix[i][j] = "";
      }
    }
  }

  public void llevar_a_tabla(String codigo, String descripcion, String cantidad,
                             int cual) {
    if (cual < this.myModel.data.length) {
      this.myModel.data[cual][0] = codigo;
      this.myModel.data[cual][1] = descripcion;
      this.myModel.data[cual][2] = cantidad;
      scrollPane.getViewport().add(table, null);
      this.recalcular();
      this.Codigo.requestFocus();
      this.Descripcion.setText("");
      this.Cantidad.setText("");
      this.Codigo.setText("");
    }
  }

  public void limpiar() {
    this.Codigo.setText("");
    this.Descripcion.setText("");
    this.Cantidad.setText("");
    this.Codigo.setEnabled(true);
    this.Codigo.requestFocus();

  }

//////////////////////////////////aceptar//////////////////////////////////
  /**
   * aceptar
   */
  private void aceptar() {
    Conexion conexion = new Conexion();
    Connection con = conexion.conectar("mysql");
    String[][] datos = this.consulta.Consultar_modificado(
        "SELECT * FROM PRESTAMO WHERE CODCLIENTE = '" +
        this.CodigoCliente.getText() + "'", 3);
      Eliminar elimina = new Eliminar();
    Actualizar actualiza = new Actualizar();
    if (datos != null) { // el cliente no tiene prestamos
      for (int i = 0; i < this.myModel.data.length; i++) {
          elimina.eliminacion("UPDATE PRESTAMOXPRODUCTO SET CANTIDAD = (CANTIDAD-'"+this.myModel.data[i][2]+"') WHERE CODIGOPRESTAMO ='"+ this.NroPrestamos.getText()+"'  AND CODIGOPRODUCTO = '"+ this.myModel.data[i][0]+"'",con);
      }
      this.dispose();
      JOptionPane.showMessageDialog(null,
                                    "Se guardó correctamente la actualización de la asignación de inventario # " +
                                    this.NroPrestamos.getText());

      JOptionPane.showMessageDialog(null,
                                    "Recuerde reportar el consumo");

      this.dispose();
      return;

    }else{
      JOptionPane.showMessageDialog(null,
                                    "Se empleado no tiene asignación " +
                                    this.NroPrestamos.getText());

    }


  }

  void Adicionar_actionPerformed(ActionEvent e) {
    if (this.Codigo.getText().compareTo("") == 0 ||
        this.Descripcion.getText().compareTo("") == 0 ||
        this.Cantidad.getText().compareTo("") == 0) {
      JOptionPane.showMessageDialog(null, "Llene todos los campos");
      return;
    }
    if (!this.bandera_mod) {
      this.llevar_a_tabla();
    }
    else {
      this.llevar_a_tabla(this.Codigo.getText(), this.Descripcion.getText(),
                          this.Cantidad.getText(), this.cual_select);
      this.Codigo.setEnabled(true);
      this.bandera_mod = false;
    }
    this.Adicionar.setEnabled(true);
    this.recalcular();
  }

  void Actualizar_actionPerformed(ActionEvent e) {
    aceptar();

  }

  /**
   * redefinirMatriz
   */
  private void redefinirMatriz() {
    String[][] nuevaMatrix = new String[counter][3];
    for(int i = 0; i < this.counter; i++){
      nuevaMatrix[i][0] = ""+this.asignacion[i][0];
      nuevaMatrix[i][1] = ""+this.asignacion[i][1];
      nuevaMatrix[i][2] = ""+this.asignacion[i][2];
    }
    this.asignacion = nuevaMatrix;
  }

  /**
   * imprimirAsignado
   */
  public void imprimirAsignado() {
 /*   Imprimir_Asignacion imprimir = new Imprimir_Asignacion(this);
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(imprimir);
    if (job.printDialog()) {
      try {
        job.print();
      }
      catch (PrinterException ex) {
        JOptionPane.showMessageDialog(null,
                                      "Error al imprimir la asignación ");

      }
    }*/


  }

  void Cancelar_actionPerformed(ActionEvent e) {
    if (JOptionPane.showConfirmDialog(null,
                                      "¿Desea salir?") ==
        0) {
      this.dispose();
    }
    return;

  }

  void Modificar_actionPerformed(ActionEvent e) {
    this.bandera_mod = true;
    this.cual_select = table.getSelectedRow();
    if (this.myModel.data[0][0] == null ||
        this.myModel.data[0][0].toString().compareTo("") == 0) {
      JOptionPane.showMessageDialog(null, "No Hay Datos Que Modificar");
      this.Codigo.requestFocus();
      return;
    }
    if (this.myModel.data.length > cual_select) {
      cargar_datos(cual_select);
      this.Codigo.setEnabled(false);
    }
    else {
      JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Fila");
    }
  }

  /**
   *
   * @param nombre_proveedor String
   * @param organizar String
   * @return String[][]
   */
  public void Consultar_Producto(String codigo) {
    if (codigo.compareTo("") == 0) {
      JOptionPane.showMessageDialog(null, "Ingrese un código");
      return;
    }
    String consulta = "SELECT DESCRIPCION FROM PRODUCTO WHERE CODIGO = '" +
        codigo + "'";
    String datos[][] = this.consulta.Consultar_modificado(consulta, 1);
    //SELECT CODIGO FROM PRESTAMOS

    String consulta2[][] =  this.consulta.Consultar_modificado("SELECT CODIGO FROM PRESTAMO WHERE CODCLIENTE = '"+this.CodigoCliente.getText()+"'",1);
    numPrestamo = consulta2[0][0];
    String consulta3[][] =  this.consulta.Consultar_modificado("SELECT CODIGOPRODUCTO, CANTIDAD FROM PRESTAMOXPRODUCTO WHERE CODIGOPRESTAMO = '"+consulta2[0][0]+"' AND CODIGOPRODUCTO = '"+this.Codigo.getText()+"'",2);

    if (datos != null && datos[0][0] != null && consulta3 != null && consulta3[0][0] != null) {
      this.Descripcion.setText(datos[0][0]);
      this.myModel.data[0][0] = this.Codigo.getText();
      this.myModel.data[0][1] =  datos[0][0];
      this.myModel.data[0][2] = consulta3[0][1];
      scrollPane.getViewport().add(table, null);
      this.Cantidad.requestFocus();
      return;
    }
    else {
      JOptionPane.showMessageDialog(null, "El producto  no existe asignado para el empleado");
      this.Codigo.requestFocus();
      return;
    }
  }

  void CodigoCliente_actionPerformed(ActionEvent e) {
    Busquedas busqueda = new Busquedas();
    this.Cliente.setText(busqueda.buscar_empleado(CodigoCliente));
    String[][] datos = this.consulta.Consultar_modificado(
        "SELECT CODIGO FROM PRESTAMO WHERE CODCLIENTE = '" +
        this.CodigoCliente.getText() + "'", 1);
    if (datos != null && datos[0][0] != null) {
      this.NroPrestamos.setText(datos[0][0]);
    }
   //buscar_prestamos();
    this.Codigo.requestFocus();
  }

  /**
   * buscar_prestamos
   */
  private void buscar_prestamos() {
    String[][] datos = this.consulta.Consultar_modificado(
        "SELECT CODIGO FROM PRESTAMO WHERE CODCLIENTE = '" +
        this.CodigoCliente.getText() + "'", 1);
    if (datos != null && datos[0][0] != null) {
      this.NroPrestamos.setText(datos[0][0]);
      datos = this.consulta.Consultar_modificado("SELECT CODIGOPRODUCTO, DESCRIPCION, CANTIDAD FROM PRESTAMOXPRODUCTO WHERE CODIGOPRESTAMO = '" +
                                                 datos[0][0] + "'", 3);
      myModel = new TableModel_Nueva(Nombres, datos, Modificable);
      table = new JdbTable(myModel);
      scrollPane.getViewport().add(table, null); //se carga lo que en este momento tiene el cliente
      this.tamaño_columnas(this.tamaño_columnas);
      bandera = true;
    }
    else {
      datos = this.consulta.Consultar_modificado(
          "SELECT MAX(CODIGO)+1 FROM PRESTAMO", 1);
      if (datos != null && datos[0][0] != null) {
        this.NroPrestamos.setText(datos[0][0]);
      }
      else {
        this.NroPrestamos.setText("1");
      }
      this.myModel.data = new Object[1][8];
      scrollPane.getViewport().add(table, null);
      this.tamaño_columnas(this.tamaño_columnas);
      bandera = false;
    }
  }

  /**
   *
   */
  public void llevar_a_tabla() {
    if (!bandera) {
      this.myModel.data[0][0] = this.Codigo.getText();
      this.myModel.data[0][1] = this.Descripcion.getText();
      this.myModel.data[0][2] = this.Cantidad.getText();
      this.asignacion[0][0] = this.Codigo.getText();
      this.asignacion[0][1] = this.Descripcion.getText();
      this.asignacion[0][2] = this.Cantidad.getText();
      counter++;
      this.bandera = true;
    }
    else {
      String[][] datos = copiar_matrix(this.myModel.data);
      datos[datos.length - 1][0] = this.Codigo.getText();
      datos[datos.length - 1][1] = this.Descripcion.getText();
      datos[datos.length - 1][2] = this.Cantidad.getText();
      this.myModel.data = datos;
      this.asignacion[counter][0] = this.Codigo.getText();
      this.asignacion[counter][1] = this.Descripcion.getText();
      this.asignacion[counter][2] = this.Cantidad.getText();
      counter++;

    }
    this.recalcular();
    this.Codigo.requestFocus();
    this.Descripcion.setText("");
    this.Cantidad.setText("");
    this.Codigo.setText("");
    this.setEnabled(true);
    scrollPane.getViewport().add(this.table, null);
    this.tamaño_columnas(tamaño_columnas);

  }

  public void recalcular() {
    if (this.myModel.data[0][0] == null ||
        this.myModel.data[0][0].toString().compareTo("") == 0) {
    }
  }

  /**
   * cargar_datos
   *
   * @param numero_fila int
   */
  public void cargar_datos(int numero_fila) {
    if (this.myModel.data[numero_fila][0] == null) {
      JOptionPane.showMessageDialog(null, "No Hay Datos Que Modificar");

      return;
    }
    else {
      this.Codigo.setText(this.myModel.data[numero_fila][0].toString());
      this.Descripcion.setText(this.myModel.data[numero_fila][1].toString());
      this.Cantidad.setText(this.myModel.data[numero_fila][2].toString());
      this.Cantidad.requestFocus();
    }

  }

  void Borrar_actionPerformed(ActionEvent e) {
    this.limpiar();
    cual_select = table.getSelectedRow();
    if (this.myModel.data[0][0] == null ||
        this.myModel.data[0][0].toString().compareTo("") == 0) {
      JOptionPane.showMessageDialog(null, "No Hay Datos Que Borrar");
      return;
    }
    if (this.myModel.data.length > cual_select) {
      this.myModel.data = Eliminar_Fila(this.myModel.data, table.getSelectedRow());
      //this.bandera = false;
    }
    else {
      JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Fila");
      return;
    }
    if (this.myModel.data.length == 0) {
      this.myModel.data = new Object[1][8];
      this.bandera = false;
//        this.iniciar((String[][])this.myModel.data);
    }
    this.recalcular();
    scrollPane.getViewport().add(table, null);

  }

  void Cantidad_keyPressed(KeyEvent e) {
    control.control(Cantidad, "numerico");
  }

  void Cantidad_keyReleased(KeyEvent e) {
    control.control(Cantidad, "numerico");
  }

  void Codigo_actionPerformed(ActionEvent e) {
    this.Consultar_Producto(this.Codigo.getText());
  }

  void Codigo_keyPressed(KeyEvent e) {
    control.control(Codigo, "alfanumerico");
  }

  void Codigo_keyReleased(KeyEvent e) {
    control.control(Codigo, "alfanumerico");
  }

  void Cantidad_actionPerformed(ActionEvent e) {
    if (this.Codigo.getText().compareTo("") == 0 ||
        this.Descripcion.getText().compareTo("") == 0 ||
        this.Cantidad.getText().compareTo("") == 0) {
      JOptionPane.showMessageDialog(null, "Llene todos los campos");
      return;
    }
//validar cantidad del producto


    try{
        Eliminar elimina = new Eliminar();
           elimina.eliminacion("UPDATE PRESTAMOXPRODUCTO SET CANTIDAD = '"+this.Cantidad.getText()+"' WHERE CODIGOPRESTAMO ='"+ this.NroPrestamos.getText()+"'  AND CODIGOPRODUCTO = '"+ this.Codigo.getText()+"'");


    }catch(Exception ee){

    }

      this.llevar_a_tabla(this.Codigo.getText(), this.Descripcion.getText(),
                          this.Cantidad.getText(), 0);
      this.Codigo.setEnabled(true);
      this.bandera_mod = false;

    this.Adicionar.setEnabled(true);

  }

  void Limpiar_actionPerformed(ActionEvent e) {
    this.limpiar();
    this.bandera_mod = false;

  }

//Modificado para poder salir cuando se cierra la ventana
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      this.Salir();
    }
  }

  /**
   * método utilizado para salir de la ventana presionando en la X
   */
  public void Salir() {
    int i = JOptionPane.showConfirmDialog(null,
                                          "¿Desea salir de ingreso de prestamos?");
    if (i == 0) {
      this.dispose();
    }
  }

  void CodigoCliente_keyPressed(KeyEvent e) {
    control.control(CodigoCliente, "numerico");
  }

  void CodigoCliente_keyReleased(KeyEvent e) {
    control.control(CodigoCliente, "numerico");
  }

  void Fecha_actionPerformed(ActionEvent e) {
    validar_fecha();
  }

  /**
   * validar_fecha
   */
  public void validar_fecha() {
    if (Fecha.getText().compareTo("") == 0) {
      JOptionPane.showMessageDialog(null, "Debe ingresar una fecha", "Error", 1);
      return;
    }
    Vector fech = this.fecha.validar_fecha(Fecha.getText());
    if (fech.get(1).toString().compareTo("false") == 0) {
      JOptionPane.showMessageDialog(null, "Ingrese correctamente la fecha",
                                    "Error", 1);
      return;
    }
    this.Codigo.requestFocus();
  }
}

class Ventana_Actualizar_Prestamo_Codigo_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Codigo_actionAdapter(Ventana_Actualizar_Prestamo
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Codigo_actionPerformed(e);
  }
}

class Ventana_Actualizar_Prestamo_Cancelar_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Cancelar_actionAdapter(Ventana_Actualizar_Prestamo
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Cancelar_actionPerformed(e);
  }
}

class Ventana_Actualizar_Prestamo_Codigo_keyAdapter
    extends java.awt.event.KeyAdapter {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Codigo_keyAdapter(Ventana_Actualizar_Prestamo adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.Codigo_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.Codigo_keyReleased(e);
  }
}

class Ventana_Actualizar_Prestamo_Cantidad_keyAdapter
    extends java.awt.event.KeyAdapter {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Cantidad_keyAdapter(Ventana_Actualizar_Prestamo
                                                adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.Cantidad_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.Cantidad_keyReleased(e);
  }
}

class Ventana_Actualizar_Prestamo_Cantidad_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Cantidad_actionAdapter(Ventana_Actualizar_Prestamo
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Cantidad_actionPerformed(e);
  }
}

class Ventana_Actualizar_Prestamo_Limpiar_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Limpiar_actionAdapter(Ventana_Actualizar_Prestamo
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Limpiar_actionPerformed(e);
  }
}

class Ventana_Actualizar_Prestamo_Adicionar_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Adicionar_actionAdapter(Ventana_Actualizar_Prestamo
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Adicionar_actionPerformed(e);
  }
}

class Ventana_Actualizar_Prestamo_CodigoCliente_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_CodigoCliente_actionAdapter(
      Ventana_Actualizar_Prestamo adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.CodigoCliente_actionPerformed(e);
  }
}

class Ventana_Actualizar_Prestamo_CodigoCliente_keyAdapter
    extends java.awt.event.KeyAdapter {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_CodigoCliente_keyAdapter(Ventana_Actualizar_Prestamo
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.CodigoCliente_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.CodigoCliente_keyReleased(e);
  }
}

class Ventana_Actualizar_Prestamo_Fecha_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Actualizar_Prestamo adaptee;

  Ventana_Actualizar_Prestamo_Fecha_actionAdapter(Ventana_Actualizar_Prestamo
                                                adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Fecha_actionPerformed(e);
  }
}
