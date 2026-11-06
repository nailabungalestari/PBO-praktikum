/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalkendaraan;

/**
 *
 * @author MyBook Hype AMD
 */
import java.util.Locale;

public class Main {

    // Helper format rupiah: 1500000 -> Rp1.500.000
    static String rp(double angka) {
        return "Rp" + String.format(Locale.US, "%,.0f", angka).replace(",", ".");
    }

    public static void main(String[] args) {

        System.out.println("===== SISTEM RENTAL KENDARAAN =====\n");

        // RUNTIME POLYMORPHISM: objek subclass disimpan di referensi superclass
        Kendaraan[] daftar = {
            new Mobil("AB 1234 XY", "Toyota Avanza", 350000, 7),
            new Motor("AB 5678 ZZ", "Honda Vario", 80000, 150),
            new Bus("AB 9012 QQ", "Hino Medium Bus", 1200000, 35),
            new Mobil("AB 3456 RR", "Daihatsu Ayla", 250000, 5)
        };

        // 1. Tampilkan semua kendaraan (info() dipanggil sesuai tipe aslinya)
        System.out.println("--- 1. Daftar Kendaraan ---");
        for (Kendaraan k : daftar) {
            System.out.println(k.info());
        }

        // 2. Polymorphism pada hitungBiaya + cek interface
        int hari = 3;
        System.out.println("\n--- 2. Simulasi Sewa " + hari + " Hari ---");
        for (Kendaraan k : daftar) {
            System.out.println(k.getMerk() + " -> biaya sewa: " + rp(k.hitungBiaya(hari)));

            if (k instanceof Asuransi) {
                Asuransi a = (Asuransi) k;
                System.out.println("   + Asuransi " + a.getJenisAsuransi()
                        + ": " + rp(a.hitungAsuransi(hari)));
            } else {
                System.out.println("   (tidak tersedia asuransi)");
            }
        }

        // 3. Overloading: hitungBiaya dengan diskon
        System.out.println("\n--- 3. Overloading (diskon 10%) ---");
        Kendaraan avanza = daftar[0];
        System.out.println("Tanpa diskon : " + rp(avanza.hitungBiaya(5)));
        System.out.println("Diskon 10%   : " + rp(avanza.hitungBiaya(5, 10)));

        // 4. Encapsulation: ubah data lewat setter + validasi
        System.out.println("\n--- 4. Encapsulation (setter + validasi) ---");
        Kendaraan vario = daftar[1];
        System.out.println("Tarif awal   : " + rp(vario.getTarifPerHari()));
        vario.setTarifPerHari(-5000);
        System.out.println("Setelah coba set -5000: " + rp(vario.getTarifPerHari()));
        vario.setTarifPerHari(90000);
        System.out.println("Setelah set 90000     : " + rp(vario.getTarifPerHari()));

        // 5. Status ketersediaan
        System.out.println("\n--- 5. Proses Penyewaan ---");
        System.out.println("Sewa Avanza (pertama) : " + (avanza.sewa() ? "BERHASIL" : "GAGAL"));
        System.out.println("Sewa Avanza (kedua)   : " + (avanza.sewa() ? "BERHASIL" : "GAGAL (sedang disewa)"));
        System.out.println(avanza.info());
        avanza.kembalikan();
        System.out.println("Setelah dikembalikan  : " + avanza.info());

        System.out.println("\n===== SELESAI =====");
    }
}