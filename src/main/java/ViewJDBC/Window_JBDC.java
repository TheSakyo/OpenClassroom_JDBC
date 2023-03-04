package ViewJDBC;

import ControlJDBC.JDBC_Connect;
import ControlJDBC.JDBC_Controler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Window_JBDC extends JFrame {

    private static Statement state;

    private final JPanel panel = new JPanel();

    public Window_JBDC(ImageIcon iconplay) {

        this.setTitle("JBDC_Window");
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BorderLayout());

        //On y ajoute un JTable (on affiche également l'alignement au milieu des textes affichés aux colonnes du tableau).
        JTable table = new JTable();
        DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)table.getDefaultRenderer(String.class);
        stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        //On y ajoute un JScrollPane au tableau et on définit le scrollbar en mode vertical.
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        //On ajoute le tableau au panneau
        panel.add(tableScroll, BorderLayout.CENTER);

        JTextArea textArea = new JTextArea();
        JScrollPane AreaScroll = new JScrollPane(textArea);
        AreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel load = new JLabel(new ImageIcon(iconplay.getImage().getScaledInstance(32,32, Image.SCALE_DEFAULT)));
        load.setHorizontalAlignment(SwingConstants.LEFT);
        load.setBorder(BorderFactory.createEmptyBorder(3,5,3,3));
        load.setToolTipText("Appuyez pour éxécuter la requête SQL !");
        load.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent event) { initTable(textArea.getText()); }
        });
        this.getContentPane().add(load, BorderLayout.NORTH);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(textArea), panel);
        split.setDividerLocation(200);
        this.getContentPane().add(split, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void initTable(String query) {

        try {

            //Se Connecte à la base de donnée
            JDBC_Connect.getInstance();

            //On crée un statement
            long start = System.currentTimeMillis();
            JDBC_Controler.setState(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //On exécute la requête
            ResultSet res = JDBC_Controler.getState().executeQuery(query);

            //On récupère les meta afin de récupérer le nom des colonnes
            ResultSetMetaData meta = res.getMetaData();

            //On initialise un tableau d'Object pour les en-têtes du tableau
            Object[] column = new Object[meta.getColumnCount()];

            for(int i = 1 ; i <= meta.getColumnCount(); i++)  column[i-1] = meta.getColumnName(i);

            //Petite manipulation pour obtenir le nombre de lignes
            res.last();
            int rowCount = res.getRow();
            Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

            //On revient au départ
            res.beforeFirst();
            int j = 1;

            //On remplit le tableau d'Object[][]
            while(res.next()) { for(int i = 1 ; i <= meta.getColumnCount(); i++) data[j-1][i-1] = res.getObject(i); j++; }

            //On ferme le tout
            res.close();
            JDBC_Controler.getState().close();

            long totalTime = System.currentTimeMillis() - start;

            //On enlève le contenu de notre conteneur

            panel.removeAll();

            //On y ajoute un JTable (on affiche également l'alignement au milieu des textes affichés aux colonnes du tableau).
            JTable table = new JTable(data, column);
            DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)table.getDefaultRenderer(String.class);
            stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            //On y ajoute un JScrollPane au tableau et on définit le scrollbar en mode vertical
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

            //On ajoute les composants au panneau
            panel.add(tableScroll, BorderLayout.CENTER);
            panel.add(new JLabel("La requête à été exécuter en " + totalTime + " ms et a retourné " + rowCount + " ligne(s)"), BorderLayout.SOUTH);

            //On force la mise à jour de l'affichage
            panel.revalidate();

        } catch(SQLException | ClassNotFoundException e) {

            //Dans le cas d'une exception, on affiche un 'pop-up' et on efface le contenu

            panel.removeAll();

            JScrollPane tableScroll = new JScrollPane(new JTable());
            panel.add(tableScroll);

            panel.revalidate();

            JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
}
