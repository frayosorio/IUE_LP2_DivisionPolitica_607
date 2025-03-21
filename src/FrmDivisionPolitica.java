import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import entidades.Pais;

public class FrmDivisionPolitica extends JFrame {

    private JTree arbol;
    DefaultMutableTreeNode nodoRaiz;
    JLabel lblMapa;

    public FrmDivisionPolitica() {
        setSize(600, 400);
        setTitle("División Política");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbDivisionPolitica = new JToolBar();

        JButton btnAgregarCuenta = new JButton();
        btnAgregarCuenta.setIcon(new ImageIcon(getClass().getResource("/iconos/Himno.png")));
        btnAgregarCuenta.setToolTipText("Reproducir Himno");
        btnAgregarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reproducirHimno();
            }
        });
        tbDivisionPolitica.add(btnAgregarCuenta);

        // Crear el nodo raíz del árbol
        nodoRaiz = new DefaultMutableTreeNode("Paises");

        // Crear el modelo del árbol
        arbol = new JTree(new DefaultTreeModel(nodoRaiz));
        JScrollPane spArbol = new JScrollPane(arbol);

        // Agregar listener para detectar selección de nodos
        arbol.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                mostrarMapa();
            }
        });

        // Crear
        lblMapa = new JLabel();
        JScrollPane spMapa = new JScrollPane(lblMapa);

        // Divisor entre el árbol y el mapa
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spArbol, spMapa);
        splitPane.setDividerLocation(250); // Tamaño inicial del árbol

        getContentPane().add(tbDivisionPolitica, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        cargarDatos();
    }

    private List<Pais> paises;

    private void cargarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();

        String nombreArchivo = System.getProperty("user.dir") + "/src/datos/DivisionPolitica.json";

        // Cargar datos desde el archivo JSON
        try {
            paises = objectMapper.readValue(new File(nombreArchivo),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Pais.class));

            if (paises != null) {
                for (Pais pais : paises) {
                    DefaultMutableTreeNode nodoPais=new DefaultMutableTreeNode(pais.getNombre());

                    nodoRaiz.add(nodoPais);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudieron cargar los datos: " + ex);
        }

    }

    private void mostrarMapa() {

    }

    private void reproducirHimno() {

    }

}
