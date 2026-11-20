/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

/**
 *
 * @author MyBook Hype AMD
 */
public class Kasir extends Thread {
    private String namaKasir;
    private int jumlahPelanggan;

    public Kasir(String namaKasir, int jumlahPelanggan) {
        this.namaKasir = namaKasir;
        this.jumlahPelanggan = jumlahPelanggan;
    }

    public String getNamaKasir() {
        return namaKasir;
    }

    @Override
    public void run() {
        for (int n = 1; n <= jumlahPelanggan; n++) {
            System.out.println("Kasir " + namaKasir + " melayani pelanggan ke-" + n);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Kasir " + namaKasir + " terinterupsi");
                return;
            }
        }
    }
}
