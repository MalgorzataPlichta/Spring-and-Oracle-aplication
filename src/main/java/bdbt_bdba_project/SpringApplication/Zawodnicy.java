package bdbt_bdba_project.SpringApplication;

import lombok.Data;

import java.util.Date;

public class Zawodnicy {
    private int nr_zawodnika;
    private Date data_dolaczenia;
    private String nazwisko;
    private String imie;
    private Date data_urodzenia;
    private long pesel;
    private long numer_konta;
    private int nr_klubu;

    public Zawodnicy(int nr_zawodnika, Date data_dolaczenia, String nazwisko, String imie, Date data_urodzenia, long pesel, long numer_konta, int nr_klubu) {
        this.nr_zawodnika = nr_zawodnika;
        this.data_dolaczenia = data_dolaczenia;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.data_urodzenia = data_urodzenia;
        this.pesel = pesel;
        this.numer_konta = numer_konta;
        this.nr_klubu = nr_klubu;
    }

    public void setNr_zawodnika(int nr_zawodnika) {
        this.nr_zawodnika = nr_zawodnika;
    }

    public void setData_dolaczenia(Date data_dolaczenia) {
        this.data_dolaczenia = data_dolaczenia;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public void setNumer_konta(long numer_konta) {
        this.numer_konta = numer_konta;
    }

    public void setNr_klubu(int nr_klubu) {
        this.nr_klubu = nr_klubu;
    }

    public int getNr_zawodnika() {
        return nr_zawodnika;
    }

    public Date getData_dolaczenia() {
        return data_dolaczenia;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public long getPesel() {
        return pesel;
    }

    public long getNumer_konta() {
        return numer_konta;
    }

    public int getNr_klubu() {
        return nr_klubu;
    }

    @Override
    public String toString() {
        return "Zawodnicy{" +
                "nr_zawodnika=" + nr_zawodnika +
                ", data_dolaczenia=" + data_dolaczenia +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", pesel=" + pesel +
                ", numer_konta=" + numer_konta +
                ", nr_klubu=" + nr_klubu +
                '}';
    }
}
