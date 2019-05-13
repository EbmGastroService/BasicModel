// created on 18.11.2014 at 15:35
package basicmodel;

//import java.util.Locale;
/**
 * PName<code> übergibt Pallinoos Namen P-Links-1 oder P-Rechts-2 usw. und bestimmt der nächste Richtung.</code>
 * Da es für die Dreheung die Einlasser Oben und unten bedingt ist, Wenn es ON
 * dann fehlt ein Pall vom Lager zum Auf-Kammer und vom Kammer zum BackOfen Es
 * wird 1 mal Links dann beim nächsten Rechts Oben ist im selben Richtung zu
 * Unten also U-Links = O-Links
 * <p>
 * Beispiel: wenn AP 14 Stuck ist, dann Links und Rechts je 7 und den Namen
 * L(13,11,9,7,5,3,1) und R(14,12,10,8,6,4,2). Wird ON aktviert dann bewegt sich
 * die Einlasser Erst Links Oben und nehmt vom Lager ein neues Pall also L15 und
 * übergibt an der Backofen der Pall L1. Aktuelle Namen wäre L(15,13,11,9,7,5,3)
 * und R(14,12,10,8,6,4,2) Beim nächsten betätigung des ON, dann dreht sich
 * Einlasser Rechts, daher vom Lager R16 und übergibt an Ofen R2. Aktuelle Namen
 * wäre L(15,13,11,9,7,5,3) und R(16,14,12,10,8,6,4)
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
class PName {

    int j, x, R, L, AP;
    String[] Rn, Ln;
    String Ein, Bpn, LId, RId;//Einlasser

    PName(int ap) {
        AP = ap;//AnzahlPallinoos im Auftauenkammer Links oder Richts.
        x = AP / 2;//für die Nummernvergabe Gerade oder Ungerade .
        R = AP;//Zb 10,8,6,4,2
        L = AP - 1;//Zb 9,7,5,3,1
        Ein = "*";//Deklaration für No Name daher mit x Zählung
        
        start0();
    }
    /**          
     * new PName(14, "L21", "R22") dann ON();
     * @param ap AnzahlPallinoos im Kammer
     * @param l Linkeseite Name zB L22
     * @param r Rechte Seite Name zB R21
     */

    PName(int ap, String l, String r) {
        AP = ap;
        x = AP / 2;//Je Seite bekommt die Helfte
        LId = l;
        RId = r;
        j = 0;
        start();
    }
    /**
     * Links und Rechts Nummern neue vergeben
     */

    final void start0() {
        Ln = LN();
        Rn = RN();
        Bpn=Ln[Ln.length-1];
    }
    /**
     * Linke und Rechte Nummer testen
     * bestimme die Richtung ob es Rechts oder Links für der nächsten Einlasser
     */

    final void start() {
        L = PN(LId);
        R = PN(RId);
        Ein = EinRichtung();
        Ln = LN();
        Rn = RN();
    }
    /**
     * Steurungselement
     * Bedienung! Wenn es von Rechts dann soll im nächst von Links sein
     */

    void ON() {
        String str = Ein;
        if (null != str) {
            switch (str) {
                case "L":
                    L = L + 2;
                    Ln = LN();//Lnks numern neue vergeben!
                    Bpn=Ln[Ln.length-1];
                    Ein = "R";//nächste Richtung ist von Rechts
                    break;
                case "R":
                    R = R + 2;
                    Rn = RN();//Rechts Numern neue vergeben
                    Bpn=Ln[Ln.length-1];
                    Ein = "L";//Nächste Richtung ist von Links
                    break;
                case "*"://noch keine Rechtung deklariert
                    Ein = "L";
                    break;
            }
        }
    //     Zeige();
    }

    String[] getLN() {
        return Ln;
    }

    String[] getRN() {
        return Rn;
    }

    String getEin() {
        return Ein;
    }
    /**
     * Ankommende L und R Nummern von On()
     * L10 > R09 dann Rechts
     * L08 < R09 dann Links
     * @return 
     */

    String EinRichtung() {
        String str = "";
        if (L > R) {
            str = "R";
        }
        if (L < R) {
            str = "L";
        }
        return str;
    }

    //Laufende Zahl für die Pallinoos.
    int PN(String pn) {
        String str = pn.substring(1, pn.length());
        int n = Integer.parseInt(str);
        return n;
    }

    //L oder R
    String PRichtung(String pn) {
        String str = pn.substring(0, 1);
        return str;
    }
    
    /**
     *  Linke Seite Numerieren, 09,07,05,03,01
     * @return 
     */

    String[] LN() {
        int neu = L;
        String[] str = new String[x];
        for (int i = 0; i < x; i++) {
            if (neu < 10) {
                str[i] = "L0" + neu;
            } else {
                str[i] = "L" + neu;
            }
            neu = neu - 2;
        }
        return str;
    }
    /**
     * Rechte Seite Numerieren, 10,08,06,04,02
     * @return 
     */

    String[] RN() {
        int neu = R;
        String[] str = new String[x];
        for (int i = 0; i < x; i++) {
            if (neu < 10) {
                str[i] = "R0" + neu;
            } else {
                str[i] = "R" + neu;
            }
            neu = neu - 2;
        }
        return str;
    }
    
    void Zeige() {
        System.out.printf("%n **  L:%d, R:%d,  Einlasser:(%s) Bpn: %s", L, R, Ein,Bpn);
        //System.out.printf("%n   %d     %d     %n", L, R);
        for (int i = 0; i < Ln.length; i++) {
            System.out.printf("%n  %s ", Ln[i]);
            System.out.printf("  %s ", Rn[i]);
        }
    }
/*
    public static void main(String[] args) {
        PName Pn = new PName(14, "L13", "R14"); Pn.ON(); Pn.ON();Pn.ON();
        Pn = new PName(14, "L21", "R22"); Pn.ON(); Pn.ON();
        PName Pn1 = new PName(8); Pn1.ON(); Pn1.ON();
    }
    */
}
