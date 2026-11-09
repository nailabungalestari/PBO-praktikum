/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Hype AMD
 */
// Custom Exception untuk menangani nilai yang tidak valid
public class NilaiInvalidException extends Exception {

    public NilaiInvalidException(String pesan) {
        super(pesan);
    }
}
