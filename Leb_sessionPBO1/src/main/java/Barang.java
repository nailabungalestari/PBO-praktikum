/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyBook Hype AMD
 */
public class Barang {
 
    // Atribut (variabel) milik class
    String namaBarang;
    double harga;
    int stok;
 
    // ============ KONSTANTA ============
    // Konstanta pajak 10%, nilainya tidak boleh berubah (final)
    static final double PAJAK = 0.10;
 
    // ============ CONSTRUCTOR ============
    // Constructor untuk mengisi nilai awal atribut saat object dibuat
    public Barang(String namaBarang, double harga, int stok) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }
 
    // ---- METHOD 1: menghitung total harga + pajak ----
    public double hitungTotalHarga(int jumlahBeli) {
        double subtotal = harga * jumlahBeli;
        double totalDenganPajak = subtotal + (subtotal * PAJAK);
        return totalDenganPajak;
    }
 
    // ---- METHOD 2: mengecek apakah stok cukup ----
    // ============ KONDISIONAL (if-else) ============
    public boolean cekStokCukup(int jumlahBeli) {
        if (jumlahBeli <= stok) {
            return true;
        } else {
            return false;
        }
    }
 
    // ---- METHOD 3: menampilkan info barang ----
    // ============ CHARACTER & STRING ============
    public void tampilkanInfo() {
        // Mengambil huruf pertama nama barang -> tipe data char
        char hurufPertama = namaBarang.charAt(0);
 
        // Method String: toUpperCase() dan length()
        String namaKapital = namaBarang.toUpperCase();
        int panjangNama = namaBarang.length();
 
        System.out.println("Nama Barang : " + namaKapital);
        System.out.println("Huruf awal  : " + hurufPertama);
        System.out.println("Panjang nama: " + panjangNama + " karakter");
        System.out.println("Harga satuan: Rp" + harga);
        System.out.println("Stok        : " + stok);
    }
}
 



