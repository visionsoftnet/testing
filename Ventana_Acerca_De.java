package facturacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * <p>Título: Facturación</p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: VisionSoft</p>
 * @author sin atribuir
 * @version 1.0
 */

public class Ventana_Acerca_De
    extends JFrame {
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel MARCO_DISTINTIVO = new JLabel();
  Border border1;
  Border border2;
  Border border3;
  Border border4;
  JLabel MAIL = new JLabel();
  JLabel CEL1 = new JLabel();
  JLabel N_E = new JLabel();
  JLabel TITULO1 = new JLabel();
  JLabel VISIONSOFT = new JLabel();
  JLabel VERSION = new JLabel();
  JLabel MARCO1 = new JLabel();
  JLabel MARCO2 = new JLabel();
  Border border5;
  JLabel jLabel1 = new JLabel();
  Border border6;
  JLabel Universidad1 = new JLabel();
  JLabel MAIL1 = new JLabel();
  JLabel labelImagen2 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();

  //Construir el marco
  public Ventana_Acerca_De() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Inicialización de componentes
  private void jbInit() throws Exception {
    ImageIcon imagen = null;
    ImageIcon imagen2 = null;
    contentPane = (JPanel)this.getContentPane();
    border1 = BorderFactory.createEmptyBorder();
    border2 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(158, 161, 169));
    border3 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white,
                                              Color.white,
                                              new Color(110, 112, 118),
                                              new Color(158, 161, 169));
    imagen = new ImageIcon(facturacion.Ventana_Principal.class.getResource(
        "Ojo.PNG"));

    border4 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(158, 161, 169));
    border5 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(158, 161, 169));
    border6 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(158, 161, 169));
    this.setIconImage(imagen.getImage());
    MARCO_DISTINTIVO.setBorder(border1);
    imagen = new ImageIcon(facturacion.Ventana_Principal.class.getResource(
        "Edwin.PNG"));
      imagen2 = new ImageIcon(facturacion.Ventana_Principal.class.getResource("Alejandro.png"));
    MARCO_DISTINTIVO.setIcon(imagen2);
    MARCO_DISTINTIVO.setText("");
    MARCO_DISTINTIVO.setBounds(new Rectangle(24, 22, 83, 86));
    contentPane.setLayout(null);
    this.setSize(new Dimension(524, 380));
    this.setTitle("ACERCA DE");
    imagen2 = new ImageIcon(facturacion.Ventana_Principal.class.getResource(
        "Edwin.PNG"));
    labelImagen2.setIcon(imagen2);
    MAIL.setFont(new java.awt.Font("Dialog", 1, 11));
    MAIL.setText("email: visionsoft.net@hotmail.com");
    MAIL.setBounds(new Rectangle(237, 258, 194, 15));
    CEL1.setBounds(new Rectangle(248, 203, 237, 15));
    CEL1.setText("Cel: 311 7367895-321 31 709 58");
    CEL1.setFont(new java.awt.Font("Dialog", 1, 11));
    N_E.setBounds(new Rectangle(204, 143, 254, 24));
    N_E.setText("LA CASA DEL SOFTWARE");
    N_E.setFont(new java.awt.Font("Dialog", 1, 18));
    TITULO1.setBounds(new Rectangle(255, 177, 149, 15));
    TITULO1.setText("Ingenieros de Soluciones");
    TITULO1.setFont(new java.awt.Font("Dialog", 1, 11));
    VISIONSOFT.setBackground(new Color(145, 233, 215));
    VISIONSOFT.setFont(new java.awt.Font("Dialog", 1, 24));
    VISIONSOFT.setForeground(new Color(114, 186, 164));
    VISIONSOFT.setText("VISIONSOFT");
    VISIONSOFT.setBounds(new Rectangle(336, 36, 146, 15));
    VERSION.setFont(new java.awt.Font("Dialog", 1, 13));
    VERSION.setForeground(new Color(114, 186, 164));
    VERSION.setText("Version 2.0");
    VERSION.setBounds(new Rectangle(372, 60, 74, 15));
    MARCO2.setBorder(border5);
    MARCO2.setText("");
    MARCO2.setBounds(new Rectangle(149, 16, 359, 322));
    MARCO1.setBorder(border6);
    MARCO1.setText("");
    MARCO1.setBounds(new Rectangle(17, 16, 134, 322));
    Universidad1.setBounds(new Rectangle(260, 231, 137, 15));
    Universidad1.setText("Universidad de Antioquia");
    Universidad1.setFont(new java.awt.Font("Dialog", 1, 11));
    MAIL1.setBounds(new Rectangle(222, 281, 222, 15));
    MAIL1.setText("http://sites.google.com/sites/visionsites");
    MAIL1.setFont(new java.awt.Font("Dialog", 1, 11));
    labelImagen2.setBounds(new Rectangle(22, 145, 86, 86));
    jLabel2.setText("Edwin Patiño");
    jLabel2.setBounds(new Rectangle(30, 239, 89, 20));
    jLabel3.setText("Alejandro Alvarez");
    jLabel3.setBounds(new Rectangle(26, 114, 107, 15));
    contentPane.add(VERSION, null);
    contentPane.add(VISIONSOFT, null);
    contentPane.add(MARCO_DISTINTIVO, null);
    contentPane.add(N_E, null);
    contentPane.add(TITULO1, null);
    contentPane.add(labelImagen2, null);
    contentPane.add(MARCO2, null);
    contentPane.add(CEL1, null);
    contentPane.add(Universidad1, null);
    contentPane.add(MAIL, null);
    contentPane.add(MAIL1, null);
    contentPane.add(jLabel3, null);
    contentPane.add(MARCO1, null);
    contentPane.add(jLabel2, null);
    setLocation(160, 140);
  }

  //Modificado para poder salir cuando se cierra la ventana
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      this.dispose();
    }
  }
}
