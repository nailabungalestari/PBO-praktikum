/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyBook Hype AMD
 */
import java.util.ArrayList;
public class SistemKasirToko {
    // ============ CLASS UTAMA (main program) ============
    public static void main(String[] args) {
 
        // ============ ARRAY / COLLECTION ============
        // Menyimpan beberapa object Barang menggunakan ArrayList
        ArrayList<Barang> daftarBarang = new ArrayList<>();
 
        // ============ MEMBUAT OBJECT ============
        // Membuat object-object dari class Barang lewat constructor
        daftarBarang.add(new Barang("Mie Instan", 3500, 10));
        daftarBarang.add(new Barang("Beras 5kg", 65000, 3));
        daftarBarang.add(new Barang("Minyak Goreng", 18000, 0));
 
        // Jumlah barang yang ingin dibeli pelanggan (untuk simulasi)
        int[] jumlahBeli = {5, 2, 1};
 
        System.out.println("=== STRUK BELANJA TOKO ===\n");
 
        double totalBelanja = 0;
 
        // ============ LOOPING (for) ============
        // Looping untuk memproses setiap barang di dalam daftar
        for (int i = 0; i < daftarBarang.size(); i++) {
 
            Barang b = daftarBarang.get(i);
            int beli = jumlahBeli[i];
 
            b.tampilkanInfo();
 
            // Menggunakan method kondisional untuk cek stok
            if (b.cekStokCukup(beli)) {
 
                // ============ EXCEPTION HANDLING ============
                // try-catch untuk menangani potensi error,
                // misalnya pembagian oleh nol saat menghitung
                // rata-rata harga per stok
                try {
                    double rataRataPerStok = b.harga / b.stok; // bisa error jika stok = 0
                    System.out.println("Rata-rata harga per stok: Rp" + rataRataPerStok);
                } catch (ArithmeticException e) {
                    System.out.println("Peringatan: Stok kosong, tidak bisa dihitung rata-rata!");
                }
 
                double totalHargaBarang = b.hitungTotalHarga(beli);
                totalBelanja += totalHargaBarang;
 
                System.out.println("Jumlah beli : " + beli);
                System.out.println("Total bayar : Rp" + totalHargaBarang);
 
            } else {
                System.out.println("Maaf, stok " + b.namaBarang + " tidak mencukupi!");
            }
 
            System.out.println("-----------------------------");
        }
 
        // Menampilkan total belanja akhir (hasil ke layar output)
        System.out.println("\nTOTAL BELANJA KESELURUHAN: Rp" + totalBelanja);
        System.out.println("(Sudah termasuk pajak " + (Barang.PAJAK * 100) + "%)");
    }
}

