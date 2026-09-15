/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_l0325008_nailabungalestari;

import java.util.Scanner;

/**
 *
 * @author MyBook Hype AMD
 */
public class StudentManager {
  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta jumlah siswa
        System.out.print("Masukkan jumlah siswa: ");
        int jumlahSiswa = Integer.parseInt(scanner.nextLine().trim());

        Student[] students = new Student[jumlahSiswa];

        // Input data setiap siswa
        for (int i = 0; i < jumlahSiswa; i++) {
            System.out.println("\nData siswa ke-" + (i + 1) + ":");

            System.out.print("Masukkan nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan nilai: ");
            double nilai = Double.parseDouble(scanner.nextLine().trim());

            students[i] = new Student(nama, nilai);
        }

        // Loop untuk mengecek kelulusan semua siswa
        for (int i = 0; i < jumlahSiswa; i++) {
            students[i].checkPassed();
        }

        System.out.println();

        // Variabel untuk menghitung rata-rata dan jumlah lulus/tidak lulus
        double totalNilai = 0;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;

        // Loop untuk menampilkan data semua siswa
        for (int i = 0; i < jumlahSiswa; i++) {
            Student s = students[i];
            String status;

            // Kondisional untuk menampilkan status kelulusan
            if (s.passed) {
                status = "Lulus";
                jumlahLulus++;
            } else {
                status = "Tidak Lulus";
                jumlahTidakLulus++;
            }

            System.out.println("Siswa: " + s.name + " - Nilai: " + s.score + " - " + status);

            totalNilai += s.score;
        }

        // Menghitung rata-rata nilai kelas
        double rataRata = totalNilai / jumlahSiswa;

        System.out.println();
        System.out.printf("Rata-rata nilai kelas: %.2f%n", rataRata);
        System.out.println("Jumlah siswa lulus: " + jumlahLulus);
        System.out.println("Jumlah siswa tidak lulus: " + jumlahTidakLulus);

        scanner.close();
    }
}


