/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Hype AMD
 */
public class Mahasiswa {

    // Konstanta standar kelulusan
    public static final double FINAL_PASSING_GRADE = 70.0;

    // Atribut mahasiswa
    private final String nama;
    private final String nim;
    private final double nilai;

    // Constructor
    public Mahasiswa(String nama, String nim, double nilai)
            throws NilaiInvalidException {

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama tidak boleh kosong!"
            );
        }

        if (nim == null) {
            throw new IllegalArgumentException(
                    "NIM tidak boleh kosong!"
            );
        }

        nim = nim.trim();

        // Assertion untuk memastikan NIM tidak kosong
        assert !nim.isEmpty() : "NIM tidak boleh kosong!";

        if (nim.isEmpty()) {
            throw new IllegalArgumentException(
                    "NIM tidak boleh kosong!"
            );
        }

        // Validasi NIM hanya boleh angka
        for (int i = 0; i < nim.length(); i++) {
            if (!Character.isDigit(nim.charAt(i))) {
                throw new IllegalArgumentException(
                        "NIM hanya boleh berisi angka!"
                );
            }
        }

        // Validasi nilai menggunakan Custom Exception
        if (nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(
                    "Nilai harus berada di antara 0 sampai 100!"
            );
        }

        this.nama = nama.trim().toUpperCase();
        this.nim = nim;
        this.nilai = nilai;
    }

    // Getter nama
    public String getNama() {
        return nama;
    }

    // Getter NIM
    public String getNim() {
        return nim;
    }

    // Getter nilai
    public double getNilai() {
        return nilai;
    }

    // Menentukan grade mahasiswa
    public String getGrade() {

        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 75) {
            return "B";
        } else if (nilai >= FINAL_PASSING_GRADE) {
            return "C";
        } else {
            return "D";
        }
    }

    // Menentukan status kelulusan
    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }

    // Menampilkan informasi mahasiswa
    @Override
    public String toString() {

        String status = isLulus()
                ? "LULUS"
                : "TIDAK LULUS";

        return String.format(
                "NIM: %s | Nama: %s | Nilai: %.2f | Grade: %s | Status: %s",
                nim, nama, nilai, getGrade(), status
        );
    }
}