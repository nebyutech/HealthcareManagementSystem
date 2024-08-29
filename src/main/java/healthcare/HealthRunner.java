package healthcare;// Add imports for AppointmentDAO and Appointment classes at the top
import healthcare.dao.AppointmentDAO;
import healthcare.model.Appointment;

import healthcare.dao.PatientDAO;
import healthcare.dao.DoctorDAO;
import healthcare.model.Patient;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HealthRunner {

    public static void main(String[] args) throws SQLException {

        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Manage Patients");
        System.out.println("2. Manage Doctors");
        System.out.println("3. Manage Appointments");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                managePatients(patientDAO, scanner);
                break;
            case 2:
                manageDoctors(doctorDAO, scanner);
                break;
            case 3:
                manageAppointments(appointmentDAO, scanner);
                break;
            case 4:
                System.out.println("Exiting the application.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }



    private static void manageDoctors(DoctorDAO doctorDAO, Scanner scanner) throws SQLException {
        // Implement similar to managePatients with Doctor-specific CRUD operations
    }


    // Existing methods for managePatients() and manageDoctors() ...

    private static void managePatients(PatientDAO patientDAO, Scanner scanner) throws SQLException {
        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Create Patient
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());
                    patientDAO.createPatient(newPatient);
                    System.out.println("Patient created successfully.");
                    break;
                case 2:
                    // Read Patient
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    Patient patient = patientDAO.getPatientById(patientId);
                    if (patient != null) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
                        System.out.println("Email: " + patient.getEmail());
                        System.out.println("Phone: " + patient.getPhoneNumber());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:
                    // Update Patient
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    patient = patientDAO.getPatientById(patientId);
                    if (patient != null) {
                        System.out.print("Enter new first name: ");
                        patient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        patient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        patient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        patient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        patient.setPhoneNumber(scanner.nextLine());
                        patientDAO.updatePatient(patient);
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    // Delete Patient
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    patientDAO.deletePatient(patientId);
                    System.out.println("Patient deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void manageAppointments(AppointmentDAO appointmentDAO, Scanner scanner) throws SQLException {
        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment by ID");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.println("5. List All Appointments");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Appointment newAppointment = new Appointment(0, 0, 0, "", ""); // Create a default appointment object
                System.out.print("Enter appointment ID: ");
                newAppointment.setAppointmentId(scanner.nextInt());
                scanner.nextLine(); // consume newline
                System.out.print("Enter patient ID: ");
                newAppointment.setPatientId(scanner.nextInt());
                scanner.nextLine(); // consume newline
                System.out.print("Enter doctor ID: ");
                newAppointment.setDoctorId(scanner.nextInt());
                scanner.nextLine(); // consume newline
                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                newAppointment.setAppointmentDate(scanner.nextLine());
                System.out.print("Enter notes: ");
                newAppointment.setNotes(scanner.nextLine());
                appointmentDAO.createAppointment(newAppointment);
                System.out.println("Appointment created successfully.");
                break;
            case 2:
                System.out.print("Enter Appointment ID: ");
                int appointmentId = scanner.nextInt();
                Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.println("Appointment ID: " + appointment.getAppointmentId());
                    System.out.println("Patient ID: " + appointment.getPatientId());
                    System.out.println("Doctor ID: " + appointment.getDoctorId());
                    System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                    System.out.println("Notes: " + appointment.getNotes());
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 3:
                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                appointment = appointmentDAO.getAppointmentById(appointmentId);
                if (appointment != null) {
                    System.out.print("Enter new patient ID: ");
                    appointment.setPatientId(scanner.nextInt());
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter new doctor ID: ");
                    appointment.setDoctorId(scanner.nextInt());
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                    appointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter new notes: ");
                    appointment.setNotes(scanner.nextLine());
                    appointmentDAO.updateAppointment(appointment);
                    System.out.println("Appointment updated successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 4:
                System.out.print("Enter Appointment ID to delete: ");
                appointmentId = scanner.nextInt();
                appointmentDAO.deleteAppointment(appointmentId);
                System.out.println("Appointment deleted successfully.");
                break;
            case 5:
                List<Appointment> appointments = appointmentDAO.getAllAppointments();
                for (Appointment app : appointments) {
                    System.out.println(app);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
