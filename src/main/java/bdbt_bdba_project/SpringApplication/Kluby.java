package bdbt_bdba_project.SpringApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Kluby {
    private int nr_klubu;
    private String nazwa;
    private Date data_otwarcia;
    private int nr_telefonu;
    private String adres_email;
    private int nr_adresu;


    public Date ToDate(String SDate) throws ParseException {
        String newDate = SDate+" "+"00"+":"+"00"+":"+"00"+".0";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = formatter.parse(newDate);
        return date;




    }
    public Kluby(){
    }

    public Kluby(int nr_klubu, String nazwa, String data_otwarcia, int nr_telefonu, String adres_email, int nr_adresu) throws ParseException {
        this.nr_klubu = nr_klubu;
        this.nazwa = nazwa;
        this.data_otwarcia = ToDate(data_otwarcia);
        this.nr_telefonu = nr_telefonu;
        this.adres_email = adres_email;
        this.nr_adresu = nr_adresu;
    }

    public int getNr_klubu() {
        return nr_klubu;
    }

    public void setNr_klubu(int nr_klubu) {
        this.nr_klubu = nr_klubu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getData_otwarcia() {return data_otwarcia = new Date();
    }

    public void setData_otwarcia(Date data_otwarcia) {
        this.data_otwarcia = data_otwarcia;
    }

    public int getNr_telefonu() {
        return nr_telefonu;
    }

    public void setNr_telefonu(int nr_telefonu) {
        this.nr_telefonu = nr_telefonu;
    }

    public String getAdres_email() {
        return adres_email;
    }

    public void setAdres_email(String adres_mail) {
        this.adres_email = adres_mail;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }

    @Override
    public String toString() {
        return "Kluby{" +
                "nr_klubu=" + nr_klubu +
                ", nazwa='" + nazwa + '\'' +
                ", data_otwarcia=" + data_otwarcia +
                ", nr_telefonu=" + nr_telefonu +
                ", adres_email='" + adres_email + '\'' +
                ", nr_adresu=" + nr_adresu +
                '}';
    }
}
