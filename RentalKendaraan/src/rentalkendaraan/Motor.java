/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalkendaraan;

/**
 *
 * @author MyBook Hype AMD
 */
/** SUBCLASS 2: Motor. extends Kendaraan, implements Asuransi. */
public class Motor extends Kendaraan implements Asuransi {

    private int cc;

    public Motor(String plat, String merk, double tarifPerHari, int cc) {
        super(plat, merk, tarifPerHari);
        this.cc = cc;
    }

    // Tarif x hari + sewa helm 10.000 per hari
    @Override
    public double hitungBiaya(int hari) {
        return getTarifPerHari() * hari + (10000 * hari);
    }

    @Override
    public String info() {
        return "MOTOR  | " + super.info() + " | " + cc + " cc";
    }

    @Override
    public String getJenisAsuransi() { return "Total Loss Only (TLO)"; }

    @Override
    public double hitungAsuransi(int hari) { return 15000 * hari; }

    public int getCc() { return cc; }
    public void setCc(int cc) { this.cc = cc; }
}
