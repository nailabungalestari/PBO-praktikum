/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;
import java.util.ArrayList;
/**
 *
 * @author MyBook Hype AMD
 */
public class Member {

    private String id;
    private String nama;
    private ArrayList<Book> daftarPinjaman;

    public Member(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Book> getDaftarPinjaman() {
        return daftarPinjaman;
    }

    public int jumlahPinjaman() {
        return daftarPinjaman.size();
    }

    public void tambahPinjaman(Book book) {
        daftarPinjaman.add(book);
    }

    public void hapusPinjaman(Book book) {
        daftarPinjaman.remove(book);
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | Sedang meminjam: %d buku",
                id, nama, daftarPinjaman.size());
    }
}
