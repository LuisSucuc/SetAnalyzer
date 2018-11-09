
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
import static setanalyzer.Token.*;



public class Interface extends javax.swing.JFrame {
    
    //String que guarda la ubicación del archivo
    String ubicacionArchivo;
    //Lista que guarda todos los errores en el archivo
    List<String> listaErrores = new ArrayList<String>();
    //Listat que tiene todas las líneas leídas
    List<Linea> listaLineas;
    
    public Interface() {
        UpdateLexer generarLexer = new UpdateLexer();
        generarLexer.generarLexer("/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/src/setanalyzer/LexGenerator.lexer");
        initComponents();
        //Boton para validar inicialmente desactivado
        btnAnalizarArchivo.setEnabled(true);
        this.setLocationRelativeTo(null);
        //Actualizar lexer
        
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalizarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(429, 429, 429))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPath, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
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
        for(String error: listaErrores) {
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
        listaLineas = new ArrayList<Linea>();
        // Se crea el objeto que generará el reporte
        PrintWriter archivoReporte = new PrintWriter("Salida.txt", "UTF-8");
        
        //Se crea el objeto que manipulará el archivo selecionado
        //ubicacionArchivo = "/home/luis/Dropbox/UMG/Automatas/Projects/Set Analyzer/Entrada.txt";
        Reader leerArchivo = new BufferedReader(new FileReader(ubicacionArchivo));
        //Se crea la instancia del analizador léxico (JFlex) y se le envía el archivo a analizar
        Lexer lexer        = new Lexer(leerArchivo);
        
        //Objeto que contendrá la información 
        Linea lineaActual = new Linea();
        while (true){
            
            
            //Objeto de la clase token, que retornará el token que encontró para su posterior evaluación
            Token token = lexer.yylex();

            //Si se legó el final del archivo
            if (token == null){
                String documentoFinal = utilidades.utils.finalText(listaLineas);
                //Se muestra el resultado en el label
                txtResultado.setText(documentoFinal);
                //Se guarda en el archivo
                archivoReporte.println(documentoFinal);
                //Se cierra el archivo
                archivoReporte.close();
                return;
            }
            
            //Se evalúa el token encontrado
            switch (token){
                
                //Si se encuentra una nueva línea
                case NUEVA_LINEA:
                        lineaActual.generarResultado();
                        listaLineas.add(lineaActual);
                        lineaActual = new Linea();
                    
                    break;

                case ERROR:
                    lineaActual.sumarTextoOriginal(lexer.yytext());
                    lineaActual.errorTextoResultado(lexer.yytext(), lexer.line_count, lexer.column_count);
                    

                    break;

                case SPACES:
                    lineaActual.sumarEspacioTextoOriginal();
                    break;

                 
                //Para todos los lexemas reconocidos
                default:
                    lineaActual.sumarTextoOriginal(lexer.yytext());
                    if (token == CONJUNTO_UNIVERSO || token == DEFINICION || token == CONJUNTO || token == OPERACION_CONJUNTO || token == OPERACION) {
                        if (token == CONJUNTO_UNIVERSO) {
                            System.out.println(utilidades.utils.getElements(lexer.yytext()));
                        }
                        lineaActual.sumarTextoResultado(token.name());
                        lineaActual.setReconocimientos(true);
                    }                    
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
            Logica logica = new Logica();
            String resultado =  logica.generarLectura(ubicacionArchivo);
            //Se muestra el resultado en el label
            txtResultado.setText(resultado);
            //Analizar();
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
