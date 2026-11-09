/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author MyBook Hype AMD
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.Mahasiswa;
import model.NilaiInvalidException;

public class Main {

    public static void main(String[] args) {

        // Collection untuk menyimpan data mahasiswa
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        // Scanner untuk input dari keyboard
        try (Scanner input = new Scanner(System.in)) {

            System.out.println("==================================");
            System.out.println("     PROGRAM DATA NILAI MAHASISWA");
            System.out.println("==================================");

            int jumlahMahasiswa = inputJumlahMahasiswa(input);

            // Perulangan input data mahasiswa
            for (int i = 0; i < jumlahMahasiswa; i++) {

                System.out.println("\nData mahasiswa ke-" + (i + 1));

                String nama = inputNama(input);
                String nim = inputNim(input);
                double nilai = inputNilai(input);

                try {

                    // Membuat objek mahasiswa
                    Mahasiswa mahasiswa = new Mahasiswa(
                            nama, nim, nilai
                    );

                    // Menambahkan objek ke ArrayList
                    daftarMahasiswa.add(mahasiswa);

                    System.out.println(
                            "Data berhasil ditambahkan!"
                    );

                } catch (NilaiInvalidException e) {

                    System.out.println(
                            "Gagal menambahkan data: " + e.getMessage()
                    );

                    i--;

                } catch (IllegalArgumentException e) {

                    System.out.println(
                            "Data tidak valid: " + e.getMessage()
                    );

                    i--;
                }
            }

            // Menampilkan rekapitulasi
            tampilkanRekap(daftarMahasiswa);

            // Menyimpan hasil ke file
            simpanKeFile(daftarMahasiswa);

        } catch (Exception e) {

            System.out.println(
                    "Terjadi kesalahan: " + e.getMessage()
            );
        }
    }

    // Method input jumlah mahasiswa
    private static int inputJumlahMahasiswa(Scanner input) {

        while (true) {

            System.out.print("Masukkan jumlah mahasiswa: ");

            String teks = input.nextLine().trim();

            try {

                int jumlah = Integer.parseInt(teks);

                if (jumlah > 0) {
                    return jumlah;
                }

                System.out.println(
                        "Jumlah mahasiswa harus lebih dari 0!"
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Masukkan jumlah dalam bentuk angka bulat!"
                );
            }
        }
    }

    // Method input nama
    private static String inputNama(Scanner input) {

        while (true) {

            System.out.print("Nama mahasiswa : ");

            String nama = input.nextLine().trim();

            if (!nama.isEmpty()) {
                return nama;
            }

            System.out.println(
                    "Nama tidak boleh kosong!"
            );
        }
    }

    // Method input NIM
    private static String inputNim(Scanner input) {

        while (true) {

            System.out.print("NIM            : ");

            String nim = input.nextLine().trim();

            if (nim.isEmpty()) {

                System.out.println(
                        "NIM tidak boleh kosong!"
                );

                continue;
            }

            boolean hanyaAngka = true;

            for (int i = 0; i < nim.length(); i++) {

                if (!Character.isDigit(nim.charAt(i))) {

                    hanyaAngka = false;
                    break;
                }
            }

            if (hanyaAngka) {
                return nim;
            }

            System.out.println(
                    "NIM hanya boleh berisi angka!"
            );
        }
    }

    // Method input nilai
    private static double inputNilai(Scanner input) {

        while (true) {

            System.out.print("Nilai          : ");

            String teks = input.nextLine().trim();

            try {

                double nilai = Double.parseDouble(teks);

                if (Double.isNaN(nilai)
                        || Double.isInfinite(nilai)) {

                    System.out.println(
                            "Nilai harus berupa angka yang valid!"
                    );

                    continue;
                }

                if (nilai < 0 || nilai > 100) {

                    throw new NilaiInvalidException(
                            "Nilai harus berada di antara 0 sampai 100!"
                    );
                }

                return nilai;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Masukkan nilai dalam bentuk angka!"
                );

            } catch (NilaiInvalidException e) {

                System.out.println(
                        "Input ditolak: " + e.getMessage()
                );
            }
        }
    }

    // Method menampilkan rekapitulasi nilai
    private static void tampilkanRekap(
            ArrayList<Mahasiswa> daftar) {

        double totalNilai = 0;

        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;

        System.out.println("\n============= REKAP NILAI =============");

        // For-each untuk membaca setiap objek mahasiswa
        for (Mahasiswa mahasiswa : daftar) {

            System.out.println(mahasiswa);

            totalNilai += mahasiswa.getNilai();

            if (mahasiswa.isLulus()) {

                jumlahLulus++;

            } else {

                jumlahTidakLulus++;
            }
        }

        double rataRata = totalNilai / daftar.size();

        System.out.println("---------------------------------------");

        System.out.println(
                "Jumlah mahasiswa : " + daftar.size()
        );

        System.out.printf(
                "Rata-rata nilai  : %.2f%n", rataRata
        );

        System.out.println(
                "Jumlah lulus     : " + jumlahLulus
        );

        System.out.println(
                "Tidak lulus      : " + jumlahTidakLulus
        );

        System.out.println("=======================================\n");
    }

    // Method menyimpan laporan ke file teks
    private static void simpanKeFile(
            ArrayList<Mahasiswa> daftar) {

        double totalNilai = 0;

        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;

        // BufferedWriter dan FileWriter untuk menulis file
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("laporan_nilai.txt"))) {

            writer.write(
                    "============= LAPORAN NILAI MAHASISWA ============="
            );

            writer.newLine();
            writer.newLine();

            for (Mahasiswa mahasiswa : daftar) {

                writer.write(mahasiswa.toString());
                writer.newLine();

                totalNilai += mahasiswa.getNilai();

                if (mahasiswa.isLulus()) {

                    jumlahLulus++;

                } else {

                    jumlahTidakLulus++;
                }
            }

            double rataRata = totalNilai / daftar.size();

            writer.newLine();

            writer.write(
                    "---------------- STATISTIK KELAS ----------------"
            );

            writer.newLine();

            writer.write(
                    "Jumlah mahasiswa : " + daftar.size()
            );

            writer.newLine();

            writer.write(
                    String.format(
                            "Rata-rata nilai  : %.2f",
                            rataRata
                    )
            );

            writer.newLine();

            writer.write(
                    "Jumlah lulus     : " + jumlahLulus
            );

            writer.newLine();

            writer.write(
                    "Tidak lulus      : " + jumlahTidakLulus
            );

            writer.newLine();

            System.out.println(
                    "Laporan berhasil disimpan ke laporan_nilai.txt"
            );

        } catch (IOException e) {

            System.out.println(
                    "Gagal menyimpan file: " + e.getMessage()
            );
        }
    }
}