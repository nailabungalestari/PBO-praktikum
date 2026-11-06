/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalkendaraan;

/**
 *
 * @author MyBook Hype AMD
 */
/**
 * ABSTRACT CLASS + SUPERCLASS.
 * Menjadi "cetakan" umum untuk semua kendaraan rental.
 */
public abstract class Kendaraan {

    // ENCAPSULATION: semua atribut private
    private String plat;
    private String merk;
    private double tarifPerHari;
    private boolean tersedia;

    // CONSTRUCTOR (dipanggil oleh subclass lewat super())
    public Kendaraan(String plat, String merk, double tarifPerHari) {
        this.plat = plat;
        this.merk = merk;
        setTarifPerHari(tarifPerHari); // lewat setter supaya tervalidasi
        this.tersedia = true;
    }

    // ABSTRACT METHOD: wajib diimplementasikan subclass
    public abstract double hitungBiaya(int hari);

    // OVERLOADING: nama sama (hitungBiaya), parameter beda
    public double hitungBiaya(int hari, double diskonPersen) {
        double total = hitungBiaya(hari); // memanggil versi abstract
        return total - (total * diskonPersen / 100);
    }

    // Perubahan status hanya lewat method, bukan diubah langsung
    public boolean sewa() {
        if (!tersedia) return false;
        tersedia = false;
        return true;
    }

    public void kembalikan() {
        tersedia = true;
    }

    // Method ini akan di-OVERRIDE oleh subclass
    public String info() {
        return merk + " [" + plat + "] - " + (tersedia ? "Tersedia" : "Sedang disewa");
    }

    // GETTER
    public String getPlat() { return plat; }
    public String getMerk() { return merk; }
    public double getTarifPerHari() { return tarifPerHari; }
    public boolean isTersedia() { return tersedia; }

    // SETTER (dengan validasi)
    public void setMerk(String merk) { this.merk = merk; }

    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari <= 0) {
            System.out.println("  [!] Tarif tidak valid, perubahan ditolak.");
            if (this.tarifPerHari == 0) this.tarifPerHari = 100000; // nilai default
            return;
        }
        this.tarifPerHari = tarifPerHari;
    }
}