package dao;

import entity.NhanVien;
import utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends GLASS_STORE_APPDAO<NhanVien, String>{
    final String INSERT_SQL = "INSERT INTO NhanVien(MANV, MATKHAU, HOTEN, DiaChi, SDT, Email, VAITRO) VALUES(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE TaiKhoan SET MATKHAU = ?, HOTEN = ?,DiaChi = ?, SDT = ?, Email = ?, VAITRO = ? WHERE MaNV = ?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(),entity.getMatKhau(), entity.getHoTen(),
                entity.getDiaChi(),entity.getSdt(),entity.getEmail(), entity.isVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMatKhau(), entity.getHoTen(),
                entity.getDiaChi(),entity.getSdt(),entity.getEmail(), entity.isVaiTro(), entity.getMaNV());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);    
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);    
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
            if(list.isEmpty()) {
                return null;
            }
            return list.get(0);    
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setDiaChi(rs.getString("Diachi"));
                entity.setSdt(rs.getString("SDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;    
    }
    
    
}
