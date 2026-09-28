/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;

/**
 *
 * @author MyBook Hype AMD
 */
public class Book {
    
    // Reference type
    private String judul;
    private String penulis;
    private String kategori;

    // Primitive type
    private int tahunTerbit;
    private boolean statusKetersediaan; // true = tersedia, false = sedang dipinjam

    public Book(String judul, String penulis, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.statusKetersediaan = true;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean isStatusKetersediaan() {
        return statusKetersediaan;
    }

    public void setStatusKetersediaan(boolean statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }

    @Override
    public String toString() {
        String status = statusKetersediaan ? "Tersedia" : "Dipinjam";
        return String.format("%-30s | %-20s | %-6d | %-15s | %s",
                judul, penulis, tahunTerbit, kategori, status);
    }
}
