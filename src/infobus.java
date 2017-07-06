import java.util.ArrayList;

/**
 * Created by raving on 23/06/17.
 */
public class infobus {
    int id;
    String stazione;
    String nomeMacchina;
    String[] indirizzoIp;
    String[] subnetMask;
    String[] defaultGateway;
    String[] listaFermate;
    private static final String user = "infobus";
    private static final String password = "infobus";

    public infobus(int id, String stazione, String nomeMacchina, String[] indirizzoIp, String[] subnetMask, String[] defaultGateway, String[] listaFermate) {
        super();
        this.id = id;
        this.stazione = stazione;
        this.nomeMacchina = nomeMacchina;
        this.indirizzoIp = indirizzoIp;
        this.subnetMask = subnetMask;
        this.defaultGateway = defaultGateway;
        this.listaFermate = listaFermate;
    }

    public String getStazione() {
        return stazione;
    }

    public void setStazione(String stazione) {
        this.stazione = stazione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMacchina() {
        return nomeMacchina;
    }

    public void setNomeMacchina(String nomeMacchina) {
        this.nomeMacchina = nomeMacchina;
    }

    public String[] getIndirizzoIp() {
        return indirizzoIp;
    }

    public void setIndirizzoIp(String[] indirizzoIp) {
        this.indirizzoIp = indirizzoIp;
    }

    public String[] getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String[] subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String[] getDefaultGateway() {
        return defaultGateway;
    }

    public void setDefaultGateway(String[] defaultGateway) {
        this.defaultGateway = defaultGateway;
    }

    public String[] getListaFermate() {
        return listaFermate;
    }

    public void setListaFermate(String[] listaFermate) {
        this.listaFermate = listaFermate;
    }

    public String returnUrl() {
        String urlWeb = "http://gtteser/SIS/monitor.php?";
        for (int i = 0; i < this.listaFermate.length; i++) {
            int counter = i + 1;
            urlWeb += "fm" + counter + "=" + this.listaFermate[i] + "&";
        }
        urlWeb += "p=1";
        return urlWeb;
    }

}
