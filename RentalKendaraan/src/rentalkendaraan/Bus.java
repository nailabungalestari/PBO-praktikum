/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalkendaraan;

/**
 *
 * @author MyBook Hype AMD
 */
public class Bus extends Kendaraan {
 
    private int kapasitas;
 
    public Bus(String plat, String merk, double tarifPerHari, int kapasitas) {
        super(plat, merk, tarifPerHari);
        this.kapasitas = kapasitas;
    }
 
    // Tarif x hari + biaya sopir 300.000 per hari
    @Override
    public double hitungBiaya(int hari) {
        return getTarifPerHari() * hari + (300000 * hari);
    }
 
    @Override
    public String info() {
        return "BUS    | " + super.info() + " | kapasitas " + kapasitas + " orang";
    }
 
    public int getKapasitas() { return kapasitas; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
}
