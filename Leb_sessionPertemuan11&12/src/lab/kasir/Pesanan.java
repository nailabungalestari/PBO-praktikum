/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

/**
 *
 * @author MyBook Hype AMD
 */
public class Pesanan {
    private static final double BATAS_DISKON = 50000;
    private static final double PERSEN_DISKON = 0.10;

    private double subtotal;

    public void tambahItem(double harga, int jumlah) {
        subtotal += harga * jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double hitungTotal() {
        if (subtotal > BATAS_DISKON) {
            return subtotal - (subtotal * PERSEN_DISKON);
        }
        return subtotal;
    }

    public void reset() {
        subtotal = 0;
    }
}
