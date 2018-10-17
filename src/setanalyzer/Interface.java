
package setanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class Interface extends javax.swing.JFrame {
    //String que guarda la ubicación del archivo
    String ubicacionArchivo;
    //Lista que guarda todos los errores en el archivo
    List<String> lista_errores = new ArrayList<String>();
    
    public Interface() {
        initComponents();
        //Boton para validar inicialmente desactivado
        btnAnalizarArchivo.setEnabled(true);
        this.setLocationRelativeTo(null);
        //Actualizar lexer
        UpdateLexer generarLexer = new UpdateLexer();
        generarLexer.generarLexer("/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/src/setanalyzer/Lexer.lex");
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGetArchivo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        btnAnalizarArchivo = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGetArchivo.setText("Seleccionar archivo");
        btnGetArchivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGetArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetArchivoActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblPath.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblPath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPath.setText("No seleccionado");

        btnAnalizarArchivo.setText("Analizar archivo");
        btnAnalizarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarArchivoActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnalizarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblPath, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(lblPath)
                .addGap(18, 18, 18)
                .addComponent(btnGetArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addGap(12, 12, 12)
                .addComponent(btnAnalizarArchivo)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Procedimiento que abre una ventana para seleccionar la ubicación del archivo
     * 
     */
    public void getUbicacion() {
        //Ventana para ubicación del archivo
        JFileChooser seleccionar_archivo = new JFileChooser();
        //Abre la ventana en al ubicación actual
        seleccionar_archivo.setCurrentDirectory(new java.io.File("."));
        //Se define el título de la ventana
        seleccionar_archivo.setDialogTitle("Buscar archivo");//seleccionar_archivo.setAcceptAllFileFilterUsed(true);
        
        //Si se seleccionó un archivo
        if (seleccionar_archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //Se obtiene la ubicación del archivo y se almacena en la variable correspondiente
            ubicacionArchivo = seleccionar_archivo.getSelectedFile().toString();
            //Se muestra al usuario en el label la ubicación y archivo seleccionado
            lblPath.setText(ubicacionArchivo);
            //Se activa el botón para Analizar
            btnAnalizarArchivo.setEnabled(true);
        } else { //Si no se selecciona el archivo
            //Se limpia el label
            limpiar();
        }
    }
      
    /**
     * Limpia el label que muestra la úbicación del archivo
     */
    public void limpiar(){
        //Cambia el texto en label de ubicacion archivo
        lblPath.setText("Archivo no seleccionado...");
        //Desactiva el botón analizar archivo
        btnAnalizarArchivo.setEnabled(false);
        //Se limpia la variable
        ubicacionArchivo = "";
        txtResultado.setText("");
    }
    
    /**
     * Comprueba si el error ya existe en la lista
     * @param stringError String que contiene el error a evaluar
     * @return Retorna un booleano que indica si existe o no el error en la lista
     */
    public boolean existeError(String stringError){
        //Se recorren todos los elementos en la lista
        for(String error: lista_errores) {
            //Se comprueba si conicide con el error enviado
            if(error.trim().contains(stringError))
               return true;
        }
        return false;
    }
    
    /**
     * Realiza el análisis léxico del archivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void Analizar() throws FileNotFoundException, IOException{
        
        // Se crea el objeto que generará el reporte
        PrintWriter archivoReporte = new PrintWriter("Salida.txt", "UTF-8");
        
        //Se crea el objeto que manipulará el archivo selecionado
        ubicacionArchivo = "/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/e1.txt";
        Reader leerArchivo = new BufferedReader(new FileReader(ubicacionArchivo));
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        Lexer lexer        = new Lexer(leerArchivo);
        
        //String que guardará el texto original
        String cadenaOriginal = "";
        //String que guardará los tokens reconocidos
        String cadenaTokens   = "";
        //String que concatenará el texto original y los tokens para guardarlo en el reporte y mostrarlo en el textarea
        String cadenaReporte  = "";
        //Variable que definirá si se encontraron errores
        Boolean errors = false;
        
        //Se crea un ciclo "infinito"
        while (true){
            
            //Objeteo de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();
            
            //System.out.println("TEXTO A ANALIZAR: " + lexer.yytext());
            //System.out.println("TOKEN RECIBIDO: " + token);

            //Si se leggó el final del archivo, ningún token coincidira
            if (token == null){
                //Se muestra el resultado en el label
                txtResultado.setText(cadenaReporte);
                //Se guarda en el archivo
                archivoReporte.println(cadenaReporte);
                //Se cierra el archivo
                archivoReporte.close();
                //Si exiten errores se muestra la lista de errores
                if (errors) {
                    JOptionPane.showMessageDialog(null, "Los siguientes elementos no se reconocieron" + lista_errores, 
                                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                //Se finaliza el procedimiento de análisis
                return;
            }

            //Se evalúa el token encontrado
            switch (token){
                
                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                    //Si las cadenas no están vacías (Esto se da cuando solo se encuentran errores)
                    if (!"".equals(cadenaOriginal) && !"".equals(cadenaTokens)) {
                        //Se guarda la cadenaOriginal leida y la cadena de tokens separada por una flecha
                        cadenaReporte = cadenaReporte + cadenaOriginal + " ---> " + cadenaTokens + "\n";
                        //cadenaReporte = cadenaReporte + cadenaOriginal + " ---> " + cadenaTokens + "_FIN_DE_LINEA_" +  "\n";
                        //Se limpia la cadenaOriginal y cadenaTokens
                        cadenaTokens = cadenaOriginal = "";
                    }
                    break;

                case ERROR:
                    //Si aún no existe el error 
                    if (!existeError(lexer.yytext())) {
                        //Se inserta en la lista el nuevo elemento
                        lista_errores.add(lexer.yytext());
                    }
                    //Se suma a la cadena original el texto-palabra que se está evaluando
                    cadenaOriginal = cadenaOriginal + " "+ lexer.yytext();
                    //Se suma a la cadena de tokens el token obtenido (ERROR)
                    cadenaTokens = cadenaTokens + " ******* "+ token + " ******* ";
                    //Indica que existen errores para posteriormente mostrar al ventana
                    errors = true;
                    break;

                case SPACES:
                    //Si exiten espacios se añade al texto original 
                    cadenaOriginal = cadenaOriginal + "_";
                    break;
                
                    
                //Para todos los lexemas reconocidos
                default:
                    //Se suma a la cadena original el texto-palabra que se está evaluando
                    cadenaOriginal = cadenaOriginal + lexer.yytext();
                    //Se suma a la cadena de tokens el token obtenido
                    cadenaTokens = cadenaTokens + " "+ token;
            }
        }
    }
    
    private void btnGetArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetArchivoActionPerformed
        getUbicacion();
    }//GEN-LAST:event_btnGetArchivoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //Cierra completamente el programa
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAnalizarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarArchivoActionPerformed
        try {
            Analizar();
        } catch (IOException ex) {
            
        }
      
    }//GEN-LAST:event_btnAnalizarArchivoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarArchivo;
    private javax.swing.JButton btnGetArchivo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPath;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
