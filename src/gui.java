import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;


//gui itself is not a visible component.
public class gui extends JPanel
        implements ListSelectionListener

    {
        private JLabel id;
        private JLabel stazione;
        private JLabel nomeMacchina;
        private JLabel ip;
        private JLabel subnet;
        private JLabel gateway;
        private JLabel listaFermate;
        private JLabel url;
        private BoxLayout box_layout;
        private JList list;
        private JSplitPane splitPane;
        private xmlParser xmlp = new xmlParser();
        private String[] infobuses = new String[xmlp.returnInfobuses().size()];
        private ArrayList<infobus> infobusCollection = new ArrayList<>();
        public gui() {
        //Create the list of images and put it in a scroll pane.
            infobuses = xmlp.returnMachineNames(xmlp.returnInfobuses());
        DefaultListModel<String> infobusListModel = new DefaultListModel<>();
        for(int i=0;i<infobuses.length;i++){
            infobusListModel.addElement(infobuses[i]);
        }
        JList<String> list = new JList<>(infobusListModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);
        id = new JLabel();
        stazione= new JLabel();
        nomeMacchina = new JLabel();
        ip = new JLabel();
        subnet = new JLabel();
        gateway= new JLabel();
        listaFermate = new JLabel();
        url = new JLabel();
        id.setFont(id.getFont().deriveFont(Font.ITALIC));
        stazione.setFont(stazione.getFont().deriveFont(Font.ITALIC));
        nomeMacchina.setFont(nomeMacchina.getFont().deriveFont(Font.ITALIC));
        ip.setFont(ip.getFont().deriveFont(Font.ITALIC));
        subnet.setFont(subnet.getFont().deriveFont(Font.ITALIC));
        gateway.setFont(gateway.getFont().deriveFont(Font.ITALIC));
        listaFermate.setFont(listaFermate.getFont().deriveFont(Font.ITALIC));
        url.setFont(url.getFont().deriveFont(Font.ITALIC));
        id.setHorizontalAlignment(JLabel.CENTER);
        stazione.setHorizontalAlignment(JLabel.CENTER);
        nomeMacchina.setHorizontalAlignment(JLabel.CENTER);
        ip.setHorizontalAlignment(JLabel.CENTER);
        subnet.setHorizontalAlignment(JLabel.CENTER);
        gateway.setHorizontalAlignment(JLabel.CENTER);
        listaFermate.setHorizontalAlignment(JLabel.CENTER);
        url.setHorizontalAlignment(JLabel.CENTER);
        JPanel detailsPane = new JPanel();
        box_layout = new BoxLayout(detailsPane,BoxLayout.PAGE_AXIS);
        detailsPane.setLayout(box_layout);
        detailsPane.add(id);
        detailsPane.add(Box.createRigidArea(new Dimension(0,10)));
        detailsPane.add(stazione);
        detailsPane.add(nomeMacchina);
        detailsPane.add(ip);
        detailsPane.add(subnet);
        detailsPane.add(gateway);
        detailsPane.add(listaFermate);
        detailsPane.add(url);
        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                listScrollPane, detailsPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        detailsPane.setMinimumSize(minimumSize);

        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(400, 200));
        updateLabel(infobuses[list.getSelectedIndex()]);
    }

        //Listens to the list

    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        updateLabel(infobuses[list.getSelectedIndex()]);
    }

    //Renders the selected image
    protected void updateLabel(String name) {
        infobusCollection = xmlp.returnInfobuses();
        infobus findOut;
        String single_ip;
        String single_subnet;
        String single_gateway;
        String single_listaFermate;
        for(int i=0;i< infobusCollection.size();i++){
            if(infobusCollection.get(i).getNomeMacchina().equalsIgnoreCase(name)){
                findOut = infobusCollection.get(i);
                single_ip = String.join(",",findOut.getIndirizzoIp());
                single_subnet = String.join(",",findOut.getSubnetMask());
                single_gateway = String.join(",",findOut.getDefaultGateway());
                single_listaFermate = String.join(",",findOut.getListaFermate());
                id.setText(String.valueOf(findOut.getId()));
                stazione.setText(findOut.getStazione());
                nomeMacchina.setText(findOut.getNomeMacchina());
                ip.setText(single_ip);
                subnet.setText(single_subnet);
                gateway.setText(single_gateway);
                listaFermate.setText(single_listaFermate);
                url.setText(findOut.returnUrl());
            }
        }
       id.setVisible(true);
       stazione.setVisible(true);
       nomeMacchina.setVisible(true);
       ip.setVisible(true);
       subnet.setVisible(true);
       gateway.setVisible(true);
       listaFermate.setVisible(true);
       url.setVisible(true);
    }

        public JSplitPane getSplitPane() {
            return splitPane;
        }
    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("INFOBUS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui gui = new gui();
        frame.getContentPane().add(gui.getSplitPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
