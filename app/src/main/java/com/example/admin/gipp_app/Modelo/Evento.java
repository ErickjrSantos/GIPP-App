package com.example.admin.gipp_app.Modelo;

import java.io.Serializable;

/**
 * Created by admin on 03/08/2017.
 */

public class Evento implements Serializable {
   private int ecodigo;
   private String evento;
   private String dataHora;
   private String criadorEvento;


    public int getEcodigo() {return ecodigo;}

    public void setEcodigo(int ecodigo) {this.ecodigo = ecodigo;}

    public String getEvento() {return evento;}

    public void setEvento(String evento) {this.evento = evento;}

    public String getDataHora() {return dataHora;}

    public void setDataHora(String dataHora) {this.dataHora = dataHora;}

    public String getCriadorEvento() {return criadorEvento;}

    public void setCriadorEvento(String criadorEvento) {this.criadorEvento = criadorEvento;}
}
