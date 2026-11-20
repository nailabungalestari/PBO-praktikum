/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

/**
 *
 * @author MyBook Hype AMD
 */

import javax.swing.*;
import java.util.Locale;

public class KasirFrame extends JFrame {
    private JLabel lblNama, lblHarga, lblJumlah, lblTotal, lblStatus;
    private JTextField txtNama, txtHarga, txtJumlah;
    private JButton btnTambah, btnHitung, btnCetak;
    private JTextArea areaStruk;
    private JScrollPane scrollStruk;
    private Pesanan pesanan;

    public KasirFrame() {
        pesanan = new Pesanan();

        setTitle("Kasir Kantin UNS");
        setSize(420, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Tahap 3: komponen dan posisi manual
        lblNama = new JLabel("Nama Menu");
        lblNama.setBounds(20, 20, 90, 25);
        txtNama = new JTextField();
        txtNama.setBounds(120, 20, 260, 25);

        lblHarga = new JLabel("Harga");
        lblHarga.setBounds(20, 55, 90, 25);
        txtHarga = new JTextField();
        txtHarga.setBounds(120, 55, 260, 25);

        lblJumlah = new JLabel("Jumlah");
        lblJumlah.setBounds(20, 90, 90, 25);
        txtJumlah = new JTextField();
        txtJumlah.setBounds(120, 90, 260, 25);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(20, 130, 110, 30);
        btnHitung = new JButton("Hitung Total");
        btnHitung.setBounds(140, 130, 120, 30);
        btnCetak = new JButton("Cetak Struk");
        btnCetak.setBounds(270, 130, 110, 30);

        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        scrollStruk = new JScrollPane(areaStruk);
        scrollStruk.setBounds(20, 175, 360, 160);

        lblTotal = new JLabel("Total: Rp 0");
        lblTotal.setBounds(20, 345, 360, 25);

        // Tahap 4: label status
        lblStatus = new JLabel("Status: siap");
        lblStatus.setBounds(20, 380, 360, 25);

        add(lblNama);   add(txtNama);
        add(lblHarga);  add(txtHarga);
        add(lblJumlah); add(txtJumlah);
        add(btnTambah); add(btnHitung); add(btnCetak);
        add(scrollStruk);
        add(lblTotal);
        add(lblStatus);

        // Event handling
        btnTambah.addActionListener(e -> tambahItem());
        btnHitung.addActionListener(e -> hitungTotal());
        btnCetak.addActionListener(e -> cetakStruk());
    }

    private void tambahItem() {
        String nama = txtNama.getText().trim();
        String hargaStr = txtHarga.getText().trim();
        String jumlahStr = txtJumlah.getText().trim();

        // Validasi: field kosong
        if (nama.isEmpty() || hargaStr.isEmpty() || jumlahStr.isEmpty()) {
            tampilkanError("Semua field harus diisi.");
            return;
        }

        double harga;
        int jumlah;
        try {
            harga = Double.parseDouble(hargaStr);
            jumlah = Integer.parseInt(jumlahStr);
        } catch (NumberFormatException ex) {
            tampilkanError("Harga dan jumlah harus berupa angka.");
            return;
        }

        if (harga <= 0 || jumlah <= 0) {
            tampilkanError("Harga dan jumlah harus lebih dari nol.");
            return;
        }

        pesanan.tambahItem(harga, jumlah);
        areaStruk.append(nama + " x" + jumlah + " @ " + rupiah(harga)
                + " = " + rupiah(harga * jumlah) + "\n");

        txtNama.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtNama.requestFocus();
    }

    private void hitungTotal() {
        lblTotal.setText("Total: " + rupiah(pesanan.hitungTotal()));
    }

    // Tahap 4: penantian 2 detik di thread baru agar jendela tidak macet
    private void cetakStruk() {
        btnCetak.setEnabled(false);
        lblStatus.setText("Mencetak struk...");

        Thread cetak = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Komponen Swing harus diubah lewat Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                lblStatus.setText("Struk selesai");
                btnCetak.setEnabled(true);
            });
        });
        cetak.start();
    }

    private void tampilkanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Kesalahan", JOptionPane.ERROR_MESSAGE);
    }

    private String rupiah(double nilai) {
        return "Rp " + String.format(Locale.forLanguageTag("id-ID"), "%,.0f", nilai);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KasirFrame().setVisible(true));
    }
}
