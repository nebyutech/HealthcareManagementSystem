package healthcare.dao;
 import healthcare.model.Doctor;
 import healthcare.jdbc.DatabaseConnection;

 import java.sql.*;
 import java.util.ArrayList;
 import java.util.List;


public class DoctorDAO {

     public void createDoctor(Doctor doctor) {
         String sql = "INSERT INTO Doctors (doctorId, firstName, lastName, specialty, email) VALUES (?,?,?,?,?)";
         try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setInt(1, doctor.getDoctorId());
             pstmt.setString(2, doctor.getFirstName());
             pstmt.setString(3, doctor.getLastName());
             pstmt.setString(4, doctor.getSpecialty());
             pstmt.setString(5, doctor.getEmail());
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

    // Method to retrieve a doctor by ID
    public Doctor getDoctorbyId(int doctorId) {
        String sql = "SELECT * FROM Doctors Where doctorId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("doctorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialty"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Method to update doctor details
    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE Doctors SET firstName = ?, lastName = ?, specialty = ?, email = ? WHERE doctorId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, doctor.getFirstName());
            pstmt.setString(2, doctor.getLastName());
            pstmt.setString(3, doctor.getSpecialty());
            pstmt.setString(4, doctor.getEmail());
            pstmt.setInt(5, doctor.getDoctorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to delete a doctor by ID
    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM Doctors WHERE doctorId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to retrieve all doctors from the database
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("doctorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialty"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}