package client;

import client.sucheEintrag.SucheEintragStub;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.rmi.RemoteException;
import javax.swing.*;
import org.apache.axis2.AxisFault;

/**
 * Name: MyPanel
 *
 * Beschreibung: In der Klasse werden auf das Panel die ensprechenden Buttons,
 * ActionListener gesetzt.
 *
 * @author Pfeiffer-Vogl, Koyuncu Harun
 * @version 1.0
 */
public class MyPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    // Attribute werden definiert
    private JButton artVer, artSuch;
    private JTextField az, server_t2, suche_t2, port_t2;
    private JTextArea erg;
    private MyFrame f;
    private JScrollPane sp;
    private JLabel l2, l3, server, port, suche;
    private ClientLogik client;
    private JTabbedPane tabbedPane;
    private JTextArea autoren;
    private JPanel p2, p3;
    private JTabbedPane tp2 = new JTabbedPane();

    /**
     * Konstruktor der Klasse MyPanel. In diesem werden alle Komponenten gesetzt
     * und die Buutons beim ActionHandler registriert.
     */
    public MyPanel(MyFrame f) {
        // set bounds of integrated tabbed view of search tab
        tp2.setBounds(5, 100, 475, 300);
        p2 = new JPanel();

        this.tabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        this.f = f;
        // Buttons und andere Komponente werden definiert
        client = new ClientLogik();
        az = new JTextField();
        artVer = new JButton("Publish Article");
        artSuch = new JButton("Search Article");

        JPanel about = new JPanel();
        // Autoren Tab wird konfiguriert
        this.autoren = new JTextArea("Legendary SOA Client was developed by Pfeiffer-Vogl Paul\nand "
                + "Koyuncu Harun. We are not responsible for anything. \n"
                + "Copyright(c) by Legendary GmbH. (Debug Infos->Console)!");
        this.autoren.setEditable(false);
        this.autoren.setBackground(getBackground());

        about.add(autoren);

        suche = new JLabel("Search for:");

        suche_t2 = new JTextField();
        server_t2 = new JTextField();
        port_t2 = new JTextField();
        erg = new JTextArea();

        sp = new JScrollPane(erg);

        l2 = new JLabel("Title:");
        l3 = new JLabel("Article:");

        JPanel p1 = new JPanel();
        p1.setLayout(null);

        p3 = new JPanel();
        p3.setLayout(null);
        // Textlabels werden definiert
        server = new JLabel("Serveradress (Glassfish):");
        port = new JLabel("Port (of the services):");
        p1.add(server);

        server.setBounds(10, 10, 200, 15);
        p1.add(server);
        server_t2.setBounds(10, 25, 450, 20);
        p1.add(server_t2);

        // Suche tab
        suche.setBounds(10, 10, 200, 10);
        p3.add(suche);
        suche_t2.setBounds(10, 25, 450, 20);
        p3.add(suche_t2);
        artSuch.setBounds(10, 60, 200, 30);
        p3.add(artSuch);

        port.setBounds(10, 60, 200, 15);
        p1.add(port);
        port_t2.setBounds(10, 75, 450, 20);
        p1.add(port_t2);

        p2.setLayout(null);
        // Genauigkeit plazieren
        this.l2.setBounds(5, 10, 200, 20);
        this.az.setBounds(5, 30, 300, 20);

        // InhaltAtrikel plazieren
        this.l3.setBounds(5, 60, 100, 20);
        this.sp.setBounds(5, 80, 300, 300);

        // Button plazieren
        this.artVer.setBounds(5, 390, 170, 30);


        p2.add(l2);
        p2.add(az);
        p2.add(l3);
        p2.add(sp);
        p2.add(artVer);

        tabbedPane.addTab("SOA Service settings", null, p1, "Set the SOA settings to connect with the service");
        tabbedPane.addTab("Create article", null, p2, "Create articlen");
        tabbedPane.addTab("Search article", null, p3, "Search article");
        tabbedPane.addTab("About", null, about, "Authors");
        add(tabbedPane);

        // Buttons werden beim actionHandler registriert
        az.addActionListener(new ActionHandler());
        artVer.addActionListener(new ActionHandler());
        artSuch.addActionListener(new ActionHandler());
        // set example values
        server_t2.setText("localhost");
        port_t2.setText("8146");
    }

    /**
     * Name: ActionHandler
     *
     * Beschreibung: In dieser Klasse werden die entsprechenden Operationen ausgefuehrt,
     * 				 wenn die Buttons gedrueckt werden
     *
     * @author Paul Pfeiffer-Vogl, Koyuncu
     * @version 1.0
     */
    class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // wenn der Start Button(Brechnen Button) gedrueckt wurde
            if( az.getText() != null &&  az.getText().length() > 50)
            {
                JOptionPane.showMessageDialog(null, "Title was to long (max. 50)");
            }
            else if(erg.getText().length() > 500 )
            {
                JOptionPane.showMessageDialog(null, "Message was to long (max. 500)");
            }
            else if(e.getSource() == artSuch && suche_t2.getText().length()>50)
            {
                JOptionPane.showMessageDialog(null, "Search string was to long (max. 50)");
            }
            else if (e.getSource() == artVer) {
                try {
                    if (!az.getText().equals("") && !erg.getText().equals("")) {
                        // cast the port from string
                        int portInt = Integer.parseInt(port_t2.getText());
                        // create the new article
                        boolean status = client.callErstelleEintrag(az.getText(), erg.getText(), server_t2.getText(), portInt);
                        // check if the article creation was successful
                        if (status) {
                            // show a message to the user
                            JOptionPane.showMessageDialog(null, "Article is now published.");
                            // set the input fields back
                            az.setText("");
                            erg.setText("");
                        } else {
                            // if no articles were found, display it to the user
                            JOptionPane.showMessageDialog(null, "No articles were published.");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please enter a title and its content!");
                    }
                } catch (AxisFault ex) {
                    JOptionPane.showMessageDialog(null, "Error occured. Maybe wrong IP/Port or service not available? ("+ex.getMessage()+")");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Provided invalid port number");
                }catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(null, "Remoteexception occured");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unkown exception");
                }
            } else if (e.getSource() == artSuch && server_t2.getText() != null && port_t2.getText() != null) {
                try {
                
                    // cast port form string
                    int portInt = Integer.parseInt(port_t2.getText());
                    // get all articles from the soa service
                    SucheEintragStub.Articles[] articles = client.callSucheEintrag(suche_t2.getText(), server_t2.getText(), portInt);
                    // if there were no articles for the search word found, display that to the user
                    if (articles == null) {
                        JOptionPane.showMessageDialog(null, "Cound't find any corresponding article");
                        // else print the number of articles that were found
                        } else {
                        JOptionPane.showMessageDialog(null, "" + articles.length + " matches found.");

                        // remove all tabs
                        tp2.removeAll();

                        // every article that was returned will be added in a tab
                        for (int i = 0; i < articles.length; i++) {
                            // create new panel
                            JPanel search = new JPanel();
                            // set title and content
                            JLabel titel = new JLabel("Titel: " + articles[i].getTitle());
                            JTextArea content = new JTextArea(articles[i].getContent(), 13, 40);
                            // add them to the panel search
                            search.add(titel);
                            search.add(new JScrollPane(content));
                            // stop editing
                            content.setEditable(false);

                            // add articles to tab view
                            tp2.addTab(articles[i].getTitle(), null, search, "Manage articles");
                        }
                        // add new tab to panel
                        p3.add(tp2);
                        // update the whole panel
                        add(tabbedPane);
                        // repaint it
                        tabbedPane.doLayout();

                    }
                } catch (AxisFault ex) {
                    JOptionPane.showMessageDialog(null, "Error occured. Maybe wrong IP/Port or service not available? ("+ex.getMessage()+")");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Provided invalid port number");
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(null, "Remoteexception occured");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unkown exception");
                }
            }
        }
    }
}
