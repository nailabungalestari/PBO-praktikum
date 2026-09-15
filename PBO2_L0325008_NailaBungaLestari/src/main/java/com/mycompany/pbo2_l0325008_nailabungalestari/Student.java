/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2_l0325008_nailabungalestari;

/**
 *
 * @author MyBook Hype AMD
 */
import java.util.Scanner;
public class Student {
    // Kelas Student: merepresentasikan data satu siswa
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
    String name;      // nama siswa
    double score;     // nilai siswa
    boolean passed;   // status kelulusan

    // Constructor untuk mengisi name dan score
    public Student(String name, double score) {
        this.name = name;
        this.score = score;
        this.passed = false; // default sebelum dicek
    }

    // Method untuk mengecek kelulusan (nilai >= 70 -> passed = true)
    public void checkPassed() {
        if (this.score >= 70) {
            this.passed = true;
        } else {
            this.passed = false;
        }
    }
}

