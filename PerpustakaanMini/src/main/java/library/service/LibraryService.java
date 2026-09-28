/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.service;
import library.exception.BookAlreadyBorrowedException;
import library.exception.BookNotFoundException;
import library.exception.BorrowLimitExceededException;
import library.model.Book;
import library.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author MyBook Hype AMD
 */
public class LibraryService {

    private ArrayList<Book> daftarBuku;
    private HashMap<String, Member> daftarAnggota;
    private HashMap<String, Integer> statistikPinjamBuku;
    private int totalTransaksiPinjam;

    private static final int MAX_PINJAM = 3;

    public LibraryService() {
        this.daftarBuku = new ArrayList<>();
        this.daftarAnggota = new HashMap<>();
        this.statistikPinjamBuku = new HashMap<>();
        this.totalTransaksiPinjam = 0;
    }

    // ===================== MANAJEMEN BUKU =====================

    public void tambahBuku(Book book) {
        daftarBuku.add(book);
    }

    public ArrayList<Book> getDaftarBuku() {
        return daftarBuku;
    }

    public void tampilkanDaftarBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku dalam koleksi.");
            return;
        }
        System.out.println("=== Daftar Buku ===");
        int nomor = 1;
        for (Book b : daftarBuku) {
            System.out.println(nomor + ". " + b);
            nomor++;
        }
    }

    public ArrayList<Book> cariBuku(String keyword) {
        ArrayList<Book> hasil = new ArrayList<>();
        String keywordLower = keyword.toLowerCase().trim();

        for (Book b : daftarBuku) {
            boolean cocokJudul = b.getJudul().toLowerCase().contains(keywordLower);
            boolean cocokKategori = b.getKategori().toLowerCase().contains(keywordLower);
            if (cocokJudul || cocokKategori) {
                hasil.add(b);
            }
        }
        return hasil;
    }

    public HashMap<String, Integer> hitungJumlahPerKategori() {
        HashMap<String, Integer> jumlahKategori = new HashMap<>();
        for (Book b : daftarBuku) {
            String kategori = b.getKategori();
            if (jumlahKategori.containsKey(kategori)) {
                jumlahKategori.put(kategori, jumlahKategori.get(kategori) + 1);
            } else {
                jumlahKategori.put(kategori, 1);
            }
        }
        return jumlahKategori;
    }

    private Book cariBukuByJudulExact(String judul) {
        for (Book b : daftarBuku) {
            if (b.getJudul().equalsIgnoreCase(judul.trim())) {
                return b;
            }
        }
        return null;
    }

    // ===================== MANAJEMEN ANGGOTA =====================

    public void tambahAnggota(Member member) {
        daftarAnggota.put(member.getId(), member);
    }

    public Member cariAnggota(String id) {
        return daftarAnggota.get(id);
    }

    public HashMap<String, Member> getDaftarAnggota() {
        return daftarAnggota;
    }

    // ===================== PEMINJAMAN & PENGEMBALIAN =====================

    public void pinjamBuku(String memberId, String judulBuku)
            throws BookNotFoundException, BorrowLimitExceededException, BookAlreadyBorrowedException {

        Member member = cariAnggota(memberId);

        assert member != null : "Anggota dengan ID '" + memberId + "' tidak valid/tidak ditemukan";

        if (member == null) {
            throw new BookNotFoundException("Anggota dengan ID '" + memberId + "' tidak ditemukan.");
        }

        Book book = cariBukuByJudulExact(judulBuku);
        if (book == null) {
            throw new BookNotFoundException("Buku dengan judul \"" + judulBuku + "\" tidak ditemukan.");
        }

        if (!book.isStatusKetersediaan()) {
            throw new BookAlreadyBorrowedException("Buku \"" + book.getJudul() + "\" sedang dipinjam anggota lain.");
        }

        if (member.jumlahPinjaman() >= MAX_PINJAM) {
            throw new BorrowLimitExceededException(
                    "Anggota " + member.getNama() + " sudah mencapai batas maksimum " + MAX_PINJAM + " buku.");
        }

        book.setStatusKetersediaan(false);
        member.tambahPinjaman(book);
        totalTransaksiPinjam++;

        String key = book.getJudul().toLowerCase();
        statistikPinjamBuku.put(key, statistikPinjamBuku.getOrDefault(key, 0) + 1);
    }

    public void kembalikanBuku(String memberId, String judulBuku) throws BookNotFoundException {
        Member member = cariAnggota(memberId);

        assert member != null : "Anggota dengan ID '" + memberId + "' tidak valid";

        if (member == null) {
            throw new BookNotFoundException("Anggota dengan ID '" + memberId + "' tidak ditemukan.");
        }

        Book bukuDipinjam = null;
        for (Book b : member.getDaftarPinjaman()) {
            if (b.getJudul().equalsIgnoreCase(judulBuku.trim())) {
                bukuDipinjam = b;
                break;
            }
        }

        if (bukuDipinjam == null) {
            throw new BookNotFoundException(
                    "Anggota " + member.getNama() + " tidak sedang meminjam buku \"" + judulBuku + "\".");
        }

        bukuDipinjam.setStatusKetersediaan(true);
        member.hapusPinjaman(bukuDipinjam);
    }

    // ===================== ANALISIS & LAPORAN =====================

    public void cetakLaporan() {
        System.out.println("========== LAPORAN PERPUSTAKAAN ==========");
        System.out.println("Total transaksi peminjaman  : " + totalTransaksiPinjam);

        System.out.println("\n--- Jumlah Buku per Kategori ---");
        HashMap<String, Integer> jumlahKategori = hitungJumlahPerKategori();
        if (jumlahKategori.isEmpty()) {
            System.out.println("Belum ada data buku.");
        } else {
            for (Map.Entry<String, Integer> entry : jumlahKategori.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue() + " buku");
            }
        }

        Member anggotaPalingAktif = null;
        int maxPinjamAnggota = -1;
        for (Map.Entry<String, Member> entry : daftarAnggota.entrySet()) {
            Member m = entry.getValue();
            if (m.jumlahPinjaman() > maxPinjamAnggota) {
                maxPinjamAnggota = m.jumlahPinjaman();
                anggotaPalingAktif = m;
            }
        }
        System.out.println("\nAnggota paling aktif        : " +
                (anggotaPalingAktif != null && maxPinjamAnggota > 0
                        ? anggotaPalingAktif.getNama() + " (" + maxPinjamAnggota + " buku sedang dipinjam)"
                        : "Belum ada data peminjaman"));

        HashMap<String, Integer> pinjamPerKategori = new HashMap<>();
        for (Map.Entry<String, Integer> entry : statistikPinjamBuku.entrySet()) {
            Book b = cariBukuByJudulExact(entry.getKey());
            if (b != null) {
                String kategori = b.getKategori();
                pinjamPerKategori.put(kategori, pinjamPerKategori.getOrDefault(kategori, 0) + entry.getValue());
            }
        }
        String kategoriPopuler = null;
        int maxKategori = -1;
        for (Map.Entry<String, Integer> entry : pinjamPerKategori.entrySet()) {
            if (entry.getValue() > maxKategori) {
                maxKategori = entry.getValue();
                kategoriPopuler = entry.getKey();
            }
        }
        System.out.println("Kategori paling populer     : " +
                (kategoriPopuler != null ? kategoriPopuler + " (" + maxKategori + " kali dipinjam)" : "Belum ada data"));

        String bukuTerpopuler = null;
        int maxDipinjam = -1;
        for (Map.Entry<String, Integer> entry : statistikPinjamBuku.entrySet()) {
            if (entry.getValue() > maxDipinjam) {
                maxDipinjam = entry.getValue();
                bukuTerpopuler = entry.getKey();
            }
        }
        System.out.println("Buku paling sering dipinjam : " +
                (bukuTerpopuler != null
                        ? kapitalisasiJudul(bukuTerpopuler) + " (" + maxDipinjam + " kali)"
                        : "Belum ada data"));

        System.out.println("===========================================");
    }

    public String kapitalisasiJudul(String judul) {
        StringBuilder hasil = new StringBuilder();
        boolean awalKata = true;
        for (int i = 0; i < judul.length(); i++) {
            char c = judul.charAt(i);
            if (Character.isWhitespace(c)) {
                awalKata = true;
                hasil.append(c);
            } else if (awalKata) {
                hasil.append(Character.toUpperCase(c));
                awalKata = false;
            } else {
                hasil.append(Character.toLowerCase(c));
            }
        }
        return hasil.toString();
    }

    public int getTotalTransaksiPinjam() {
        return totalTransaksiPinjam;
    }
}
