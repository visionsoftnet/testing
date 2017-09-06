package facturacion;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Título: Facturación</p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: VisionSoft</p>
 * @author sin atribuir
 * @version 1.0
 */

public class Ventana_Abonos
    extends JFrame {
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel Cambio = new JLabel();
  JTextField Valor_Neto = new JTextField();
  JButton ACEPTAR = new JButton();
  JButton CANCELAR = new JButton();
  Control_de_Datos control = new Control_de_Datos();
  JLabel LabelValorNeto = new JLabel();
  JLabel LabelTotalCancelado = new JLabel();
  JTextField Cancelado = new JTextField();

  //Construir el marco
  public Ventana_Abonos() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      this.Valor_Neto.requestFocus();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Inicialización de componentes
  private void jbInit() throws Exception {
    ImageIcon image1 = null;
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(null);
    image1 = new ImageIcon(facturacion.Ventana_Principal.class.getResource(
        "Ojo.PNG"));
    this.setIconImage(image1.getImage());
    this.setResizable(false);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setSize(new Dimension(543, 295));
    this.setTitle("ABONOS");
    this.setLocation(100, 150);
    Cambio.setFont(new java.awt.Font("Dialog", 1, 14));
    Cambio.setText("ABONOS");
    Cambio.setBounds(new Rectangle(421, 8, 82, 15));
    Valor_Neto.setEnabled(true);
    Valor_Neto.setFont(new java.awt.Font("Dialog", 0, 22));
    Valor_Neto.setText("");
    Valor_Neto.setBounds(new Rectangle(240, 54, 271, 46));
    Valor_Neto.addKeyListener(new Ventana_Abonos_Valor_Neto_keyAdapter(this));
    Valor_Neto.addActionListener(new Ventana_Abonos_Valor_Neto_actionAdapter(this));
    ACEPTAR.setBounds(new Rectangle(138, 197, 89, 23));
    ACEPTAR.setText("ACEPTAR");
    ACEPTAR.addActionListener(new Ventana_Abonos_ACEPTAR_actionAdapter(this));
    CANCELAR.setText("CANCELAR");
    CANCELAR.addActionListener(new Ventana_Abonos_CANCELAR_actionAdapter(this));
    CANCELAR.setBounds(new Rectangle(348, 197, 89, 23));
    LabelValorNeto.setBounds(new Rectangle(87, 60, 148, 35));
    LabelValorNeto.setText("Valor Neto:");
    LabelValorNeto.setFont(new java.awt.Font("Dialog", 1, 27));
    LabelTotalCancelado.setFont(new java.awt.Font("Dialog", 1, 27));
    LabelTotalCancelado.setText("Total Cancelado:");
    LabelTotalCancelado.setBounds(new Rectangle(13, 120, 226, 35));
    Cancelado.setBounds(new Rectangle(239, 118, 271, 46));
    Cancelado.addKeyListener(new Ventana_Abonos_Cancelado_keyAdapter(this));
    Cancelado.addActionListener(new Ventana_Abonos_Cancelado_actionAdapter(this));
    Cancelado.setText("");
    Cancelado.setFont(new java.awt.Font("Dialog", 0, 22));
    Cancelado.setEnabled(true);
    contentPane.add(LabelValorNeto, null);
    contentPane.add(Cambio, null);
    contentPane.add(Valor_Neto, null);
    contentPane.add(LabelTotalCancelado, null);
    contentPane.add(Cancelado, null);
    contentPane.add(CANCELAR, null);
    contentPane.add(ACEPTAR, null);
    contentPane.add(Valor_Neto, null);
  }

  void CANCELAR_actionPerformed(ActionEvent e) {
    if (JOptionPane.showConfirmDialog(this, "Desea salir") == 0) {
      this.dispose();
    }

  }

  void Valor_Neto_keyReleased(KeyEvent e) {
    control.control(Valor_Neto, "numerico");
    Valor_Neto.setText(control.control_numerico_coma(Valor_Neto.getText()));
  }

  void Valor_Neto_keyPressed(KeyEvent e) {
    control.control(Valor_Neto, "numerico");
    Valor_Neto.setText(control.control_numerico_coma(Valor_Neto.getText()));

  }

  void Valor_Neto_actionPerformed(ActionEvent e) {
    this.Cancelado.requestFocus();
  }

  void ACEPTAR_actionPerformed(ActionEvent e) {
    ejecute();
  }

  /**
   * guardar
   */
  private boolean guardar() {
    if (this.Valor_Neto.getText().compareTo("") == 0 ||
        this.Cancelado.getText().compareTo("") == 0) {
      JOptionPane.showMessageDialog(this, "Ingrese un valor");
      return false;
    }

    Insertar inser = new Insertar();
    Conexion conexion = new Conexion();
    Connection con = conexion.conectar("mysql");
    fecha fecha = new fecha();
    if (!inser.Insertar("ABONADO",
                        "VALOR", control.Quitar_Coma(this.Valor_Neto.getText()),
                        "FECHA",
                        fecha.convertir_fecha_db(fecha.Buscar_fecha_sistema()),
                        con)) {
      JOptionPane.showMessageDialog(this,
                                    "Ha Ocurrido Un Error Grave En La Creacion De La Cuenta De Usuario Line 122");
      conexion.desconectar(con);
      return false;
    }
    return true;
  }

  void Cancelado_actionPerformed(ActionEvent e) {
    ejecute();
  }

  void Cancelado_keyPressed(KeyEvent e) {
    control.control(Cancelado, "numerico");
    Cancelado.setText(control.control_numerico_coma(Cancelado.getText()));
  }

  void Cancelado_keyReleased(KeyEvent e) {
    control.control(Cancelado, "numerico");
    Cancelado.setText(control.control_numerico_coma(Cancelado.getText()));
  }

  void ejecute() {
    if (this.Valor_Neto.getText().compareTo("") == 0 ||
        this.Cancelado.getText().compareTo("") == 0) {
      JOptionPane.showMessageDialog(this, "Llene todos los campos");
      return;
    }
    guardar();
    double valor_neto = Double.parseDouble(control.Quitar_Coma(this.Valor_Neto.
        getText()));
    double cancelado = Double.parseDouble(control.Quitar_Coma(this.Cancelado.
        getText()));
    if ( (cancelado - valor_neto) < 0) {
      JOptionPane.showMessageDialog(this, "El total cancelado no es valido");
      this.Cancelado.setText("");
      return;
    }
    this.dispose();
    Drive_Cajon cajon = new Drive_Cajon();
    cajon.abrir();
    Ventana_Cambio frame = new Ventana_Cambio();
    frame.setVisible(true);
    frame.CAMBIO.setText(control.Poner_Coma("" + (cancelado - valor_neto)));
    frame.ACEPTAR.requestFocus();
  }

}

class Ventana_Abonos_ACEPTAR_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Abonos adaptee;

  Ventana_Abonos_ACEPTAR_actionAdapter(Ventana_Abonos adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.ACEPTAR_actionPerformed(e);
  }
}

class Ventana_Abonos_CANCELAR_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Abonos adaptee;

  Ventana_Abonos_CANCELAR_actionAdapter(Ventana_Abonos adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.CANCELAR_actionPerformed(e);
  }
}

class Ventana_Abonos_Valor_Neto_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Abonos adaptee;

  Ventana_Abonos_Valor_Neto_actionAdapter(Ventana_Abonos adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Valor_Neto_actionPerformed(e);
  }
}

class Ventana_Abonos_Valor_Neto_keyAdapter
    extends java.awt.event.KeyAdapter {
  Ventana_Abonos adaptee;

  Ventana_Abonos_Valor_Neto_keyAdapter(Ventana_Abonos adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.Valor_Neto_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.Valor_Neto_keyReleased(e);
  }
}

class Ventana_Abonos_Cancelado_actionAdapter
    implements java.awt.event.ActionListener {
  Ventana_Abonos adaptee;

  Ventana_Abonos_Cancelado_actionAdapter(Ventana_Abonos adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.Cancelado_actionPerformed(e);
  }
}

class Ventana_Abonos_Cancelado_keyAdapter
    extends java.awt.event.KeyAdapter {
  Ventana_Abonos adaptee;

  Ventana_Abonos_Cancelado_keyAdapter(Ventana_Abonos adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.Cancelado_keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    adaptee.Cancelado_keyReleased(e);
  }
}
