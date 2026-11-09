/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyBook Hype AMD
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PengelolaanNilaiSiswa {

    // Method untuk memasukkan data siswa dan menyimpannya ke file
    public static void inputDanSimpanData() throws IOException {

        Scanner input = new Scanner(System.in);

        int jumlahSiswa;
        double totalNilai = 0;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;

        System.out.println("==================================");
        System.out.println("   PROGRAM PENGELOLAAN NILAI SISWA");
        System.out.println("==================================");

        // Input jumlah siswa
        System.out.print("Masukkan jumlah siswa: ");
        jumlahSiswa = Integer.parseInt(input.nextLine());

        // Validasi jumlah siswa
        if (jumlahSiswa <= 0) {
            System.out.println("Jumlah siswa harus lebih dari 0!");
            return;
        }

        // Membuka file untuk menulis data
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("laporan_nilai.txt"))) {

            writer.write("========================================");
            writer.newLine();
            writer.write("         LAPORAN NILAI SISWA");
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.newLine();

            // Perulangan untuk memasukkan data setiap siswa
            for (int i = 1; i <= jumlahSiswa; i++) {

                System.out.println("\nData Siswa ke-" + i);

                System.out.print("Nama siswa : ");
                String nama = input.nextLine();

                System.out.print("Nilai siswa: ");
                double nilai = Double.parseDouble(
                        input.nextLine().replace(',', '.'));

                // Menentukan status kelulusan
                String status;

                if (nilai >= 70) {
                    status = "Lulus";
                    jumlahLulus++;
                } else {
                    status = "Tidak Lulus";
                    jumlahTidakLulus++;
                }

                // Menghitung total nilai
                totalNilai += nilai;

                // Menulis data siswa ke file
                writer.write("Siswa ke-" + i);
                writer.newLine();
                writer.write("Nama   : " + nama);
                writer.newLine();
                writer.write("Nilai  : " + nilai);
                writer.newLine();
                writer.write("Status : " + status);
                writer.newLine();
                writer.write("----------------------------------------");
                writer.newLine();
            }

            // Menghitung rata-rata nilai kelas
            double rataRata = totalNilai / jumlahSiswa;

            // Menulis ringkasan ke dalam file
            writer.newLine();
            writer.write("             RINGKASAN NILAI");
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.write("Rata-rata nilai kelas : " + rataRata);
            writer.newLine();
            writer.write("Jumlah siswa lulus    : " + jumlahLulus);
            writer.newLine();
            writer.write("Jumlah siswa tidak lulus : "
                    + jumlahTidakLulus);
            writer.newLine();
            writer.write("========================================");

        }

        // Membaca kembali isi file
        System.out.println("\n==================================");
        System.out.println("     HASIL PEMBACAAN FILE");
        System.out.println("==================================");

        try (BufferedReader reader = new BufferedReader(
                new FileReader("laporan_nilai.txt"))) {

            String baris;

            // Membaca file baris demi baris sampai akhir file
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }

        }

        System.out.println("\nData berhasil disimpan dan dibaca!");
        System.out.println("File: laporan_nilai.txt");
    }

    // Method utama program
    public static void main(String[] args) {

        try {
            inputDanSimpanData();

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mengakses file: "
                    + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Input jumlah siswa atau nilai harus berupa angka!");

        }
    }
}
