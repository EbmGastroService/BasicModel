/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

/**
 *
 * @author EbmGastroService
 */
public interface Modellinteface {    
    double getKey(double Temp);
    double getZeitBedarf(double Temp, double Diff);
    double getZeitProGrad(double Temp, double Diff);         
    double getAbbauWert(double offZeit,double Temp,double Diff);    
    double getTempReihenAbbau(double offZeit,double ReiheTemp,double Diff);
    double getTempReihenAufbau(double offZeit,double ReiheTemp,double Diff);
    double getPallTempAufbau(double offZeit,double ReihenTemp,double PallTemp,double Diff); 
    double getPallTemp(double onZeit, double ReihenTemp, double PallTemp, double Diff);
    double getPauseZeit();
    void setPauseZeit(double offZeit);
    void showMe();    
}
