package facturacion;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Título: Facturación</p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: VisionSoft</p>
 * @author n - Edwin Patiño
 * @version 1.0
 */

public class Ventana {
  boolean packFrame = false;

  //Construir la aplicación
  public Ventana() {

    Splash splash = new Splash();
    try {
      Thread.sleep(3000);
    }
    catch (Exception ex) {
      System.out.println("Error");
    }
    splash.setVisible(false);

    Repara_Base_De_Datos reparar = new Repara_Base_De_Datos();
    reparar.reparar();
    Consultar consultar = new Consultar();
    if (consultar.Consultar("SELECT * FROM configuracion") == null) {
      Ventana_Configuracion_Programa config = new
          Ventana_Configuracion_Programa();
      config.setVisible(true);
      config.setResizable(false);
      config.usuario = "VISIONSOFT";
      config.ACEPTAR.setEnabled(true);
      config.EXAMINAR1.setEnabled(false);
      config.EXAMINAR2.setEnabled(false);
      config.EXAMINAR3.setEnabled(false);
      config.EXAMINAR4.setEnabled(false);
      config.EXAMINAR6.setEnabled(false);
      config.EXAMINAR7.setEnabled(false);
      config.CLAVE1.setEnabled(false);
      config.CLAVE2.setEnabled(false);
      config.CLAVE3.setEnabled(false);
    }
    else {

      Hola_Mundo hola = new Hola_Mundo();
      if (true) { //
        Guardar_Info_Mensual info = new Guardar_Info_Mensual();
        info.guardar_info_mensual();
        if (consultar.Consultar("SELECT * FROM usuario") != null) {
          Ventana_Usuarios frame = new Ventana_Usuarios();
          //Validar marcos que tienen tamaños preestablecidos
          //Empaquetar marcos que cuentan con información de tamaño preferente útil. Ej. de su diseño.
          if (packFrame) {
            frame.pack();
          }
          else {
            frame.validate();
          }

          //Centrar la ventana
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Dimension frameSize = frame.getSize();
          if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
          }
          if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
          }
          frame.setVisible(true);
        }
        else {
          Ventana_Principal frame = new Ventana_Principal();
          //Validar marcos que tienen tamaños preestablecidos
          //Empaquetar marcos que cuentan con información de tamaño preferente útil. Ej. de su diseño.
          if (packFrame) {
            frame.pack();
          }
          else {
            frame.validate();
          }

          //Centrar la ventana
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Dimension frameSize = frame.getSize();
          if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
          }
          if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
          }
          frame.setLocation(0, 0);
          frame.setVisible(true);
        }
      }
    }
  }

  //Método Main
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new Ventana();
  }
}
