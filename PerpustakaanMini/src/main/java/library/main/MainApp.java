/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.main;
import library.exception.BookAlreadyBorrowedException;
import library.exception.BookNotFoundException;
import library.exception.BorrowLimitExceededException;
import library.model.Book;
import library.model.Member;
import library.service.LibraryService;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author MyBook Hype AMD
 */
public class MainApp {

    private static final LibraryService service = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenu();
            String pilihanInput = scanner.nextLine().trim();
            int pilihan;
            try {
                pilihan = Integer.parseInt(pilihanInput);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka menu 1-7.\n");
                continue;
            }

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    service.tampilkanDaftarBuku();
                    break;
                case 3:
                    cariBuku();
                    break;
                case 4:
                    pinjamBuku();
                    break;
                case 5:
                    kembalikanBuku();
                    break;
                case 6:
                    service.cetakLaporan();
                    break;
                case 7:
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
                    break;
                default:
                    System.out.println("Pilihan tidak dikenali. Silakan pilih 1-7.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("========== SISTEM PERPUSTAKAAN MINI ==========");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Daftar Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Pinjam Buku");
        System.out.println("5. Kembalikan Buku");
        System.out.println("6. Laporan Perpustakaan");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void tambahBuku() {
        System.out.print("Judul buku   : ");
        String judul = scanner.nextLine().trim();
        System.out.print("Penulis      : ");
        String penulis = scanner.nextLine().trim();

        int tahun = 0;
        boolean validTahun = false;
        while (!validTahun) {
            System.out.print("Tahun terbit : ");
            String tahunInput = scanner.nextLine().trim();

            boolean semuaDigit = !tahunInput.isEmpty();
            for (int i = 0; i < tahunInput.length(); i++) {
                if (!Character.isDigit(tahunInput.charAt(i))) {
                    semuaDigit = false;
                    break;
                }
            }

            if (semuaDigit) {
                tahun = Integer.parseInt(tahunInput);
                validTahun = true;
            } else {
                System.out.println("Tahun harus berupa angka. Coba lagi.");
            }
        }

        System.out.print("Kategori     : ");
        String kategori = scanner.nextLine().trim();

        Book bukuBaru = new Book(judul, penulis, tahun, kategori);
        service.tambahBuku(bukuBaru);
        System.out.println("Buku \"" + judul + "\" berhasil ditambahkan.");
    }

    private static void cariBuku() {
        System.out.print("Masukkan kata kunci (judul/kategori): ");
        String keyword = scanner.nextLine();
        ArrayList<Book> hasil = service.cariBuku(keyword);

        if (hasil.isEmpty()) {
            System.out.println("Tidak ada buku yang cocok dengan kata kunci \"" + keyword + "\".");
        } else {
            System.out.println("Ditemukan " + hasil.size() + " buku:");
            for (Book b : hasil) {
                System.out.println("- " + b);
            }
        }
    }

    private static void pinjamBuku() {
        System.out.print("ID Anggota   : ");
        String idAnggota = scanner.nextLine().trim();

        Member member = service.cariAnggota(idAnggota);
        if (member == null) {
            System.out.print("Anggota belum terdaftar. Masukkan nama untuk mendaftar (kosongkan untuk batal): ");
            String nama = scanner.nextLine().trim();
            if (nama.isEmpty()) {
                System.out.println("Peminjaman dibatalkan.");
                return;
            }
            member = new Member(idAnggota, nama);
            service.tambahAnggota(member);
            System.out.println("Anggota baru \"" + nama + "\" berhasil didaftarkan.");
        }

        System.out.print("Judul buku yang dipinjam: ");
        String judul = scanner.nextLine().trim();

        try {
            service.pinjamBuku(idAnggota, judul);
            System.out.println("Buku \"" + judul + "\" berhasil dipinjam oleh " + member.getNama() + ".");
        } catch (BookNotFoundException | BorrowLimitExceededException | BookAlreadyBorrowedException e) {
            System.out.println("Gagal meminjam buku: " + e.getMessage());
        }
    }

    private static void kembalikanBuku() {
        System.out.print("ID Anggota   : ");
        String idAnggota = scanner.nextLine().trim();
        System.out.print("Judul buku yang dikembalikan: ");
        String judul = scanner.nextLine().trim();

        try {
            service.kembalikanBuku(idAnggota, judul);
            System.out.println("Buku \"" + judul + "\" berhasil dikembalikan. Terima kasih!");
        } catch (BookNotFoundException e) {
            System.out.println("Gagal mengembalikan buku: " + e.getMessage());
        }
    }

    private static void seedData() {
        service.tambahBuku(new Book("Laskar Pelangi", "Andrea Hirata", 2005, "Fiksi"));
        service.tambahBuku(new Book("Bumi Manusia", "Pramoedya Ananta Toer", 1980, "Fiksi"));
        service.tambahBuku(new Book("Filosofi Teras", "Henry Manampiring", 2018, "Non-Fiksi"));
        service.tambahBuku(new Book("Sapiens", "Yuval Noah Harari", 2011, "Non-Fiksi"));
        service.tambahBuku(new Book("Belajar Java Dasar", "Budi Santoso", 2020, "Teknologi"));

        service.tambahAnggota(new Member("A001", "Siti Aminah"));
        service.tambahAnggota(new Member("A002", "Rudi Hartono"));
    }
}

