/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan_dpbo6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dell
 */
public class dbConnection {
    public static Connection con;
    public static Statement stm;
    
    public void connect (){
        try{
            String url = "jdbc:mysql://localhost/db_latihan_dpbo";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stm = con.createStatement();
            System.out.println("Koneksi Berhasil!");
  
        } catch(Exception e){
            System.err.println("Koneksi Gagal" + e.getMessage());
        }
    }
    
    public DefaultTableModel readTable(){
        DefaultTableModel dataTabel = null;
        try{
            Object [] column = {"No", "Nama", "Nim"};
            connect();
            dataTabel = new DefaultTableModel(null, column);
            String sql = "Select nama,nim from mahasiswa";
            ResultSet res = stm.executeQuery(sql);
            int no=1;
            while(res.next()){
                Object [] hasil = new Object[3];
                hasil[0] = no;
                hasil[1] = res.getString("nama");
                hasil[2] = res.getString("nim");
                no++;
                System.out.println(hasil[1]);
                dataTabel.addRow(hasil);
            }
        } catch(Exception e){
            System.err.println("Read Gagal21" + e.getMessage());
        }
        return dataTabel;
    }
    public void query(String inputan){
        try{
            connect();
            String sql = inputan;
            stm.execute(sql);
        }catch(Exception e){
            System.err.println("Read Gagal!!" + e.getMessage());
        }
    }
}
