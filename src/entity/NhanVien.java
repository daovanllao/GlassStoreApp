/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author anctWin10
 */
public class NhanVien {
    private String maNV;
    private String hoTen;
    private String matKhau;
    private String DiaChi;
    private String email;
    private String sdt;
    private boolean vaiTro;

    @Override
    public String toString() {
        return this.hoTen;//"NhanVien{" + "hoTen=" + hoTen + '}';
    }

    public NhanVien() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
  
    public NhanVien(String maNV, String hoTen, String matKhau, String DiaChi, String email, String sdt, boolean vaiTro) {
          this.maNV = maNV;
          this.hoTen = hoTen;
          this.matKhau = matKhau;
          this.DiaChi = DiaChi;
          this.email = email;
          this.sdt = sdt;
          this.vaiTro = vaiTro;
    }  
}
