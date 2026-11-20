/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

/**
 *
 * @author MyBook Hype AMD
 */
public class MainKasir {
    public static void main(String[] args) {
        // ===== Tahap 1: uji class Pesanan =====
        Pesanan pesanan = new Pesanan();

        pesanan.tambahItem(15000, 2);   // Nasi Goreng
        pesanan.tambahItem(5000, 3);    // Es Teh
        System.out.println("Setelah 2 menu -> subtotal: " + pesanan.getSubtotal()
                + ", total: " + pesanan.hitungTotal());   // 45000 / 45000

        pesanan.tambahItem(25000, 1);   // Ayam Bakar
        System.out.println("Setelah 3 menu -> subtotal: " + pesanan.getSubtotal()
                + ", total: " + pesanan.hitungTotal());   // 70000 / 63000

        pesanan.reset();
        System.out.println("Setelah reset -> subtotal: " + pesanan.getSubtotal());

        // ===== Tahap 2: dua kasir sebagai thread =====
        System.out.println("\n--- Simulasi dua kasir ---");
        Kasir dina = new Kasir("Dina", 5);
        Kasir eko = new Kasir("Eko", 5);

        dina.start();
        eko.start();

        try {
            dina.join();
            eko.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Semua pelanggan selesai dilayani");
    }
}