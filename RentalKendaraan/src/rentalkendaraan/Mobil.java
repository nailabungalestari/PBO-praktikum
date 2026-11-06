/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalkendaraan;

/**
 *
 * @author MyBook Hype AMD
 */
/** SUBCLASS 1: Mobil. extends Kendaraan, implements Asuransi. */
public class Mobil extends Kendaraan implements Asuransi {

    private int jumlahKursi;

    public Mobil(String plat, String merk, double tarifPerHari, int jumlahKursi) {
        super(plat, merk, tarifPerHari); // panggil constructor superclass
        this.jumlahKursi = jumlahKursi;
    }

    // Implementasi abstract method: tarif x hari + biaya cuci mobil flat
    @Override
    public double hitungBiaya(int hari) {
        return getTarifPerHari() * hari + 50000;
    }

    @Override
    public String info() {
        return "MOBIL  | " + super.info() + " | " + jumlahKursi + " kursi";
    }

    // Kontrak interface Asuransi
    @Override
    public String getJenisAsuransi() { return "All Risk"; }

    @Override
    public double hitungAsuransi(int hari) { return 50000 * hari; }

    public int getJumlahKursi() { return jumlahKursi; }
    public void setJumlahKursi(int jumlahKursi) { this.jumlahKursi = jumlahKursi; }
}
