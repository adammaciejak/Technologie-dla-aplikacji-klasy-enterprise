package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import lab.dto.ComplaintDTO;

import java.util.List;

public class Main {
    
    private static final String BASE_URL = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";
    
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        
        try {
            System.out.println("=== SPRAWDZENIE STATUSU SKARGI 301 ===");
            String status = client.target(BASE_URL + "/301/status")
                    .request(MediaType.TEXT_PLAIN)
                    .get(String.class);
            System.out.println("Status skargi 301: " + status);
            System.out.println();
            
            // a. Pobierz i wyświetl wszystkie skargi
            System.out.println("=== WSZYSTKIE SKARGI ===");
            List<ComplaintDTO> allComplaints = client.target(BASE_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<ComplaintDTO>>() {});
            
            System.out.println("Liczba wszystkich skarg: " + allComplaints.size());
            for (ComplaintDTO complaint : allComplaints) {
                System.out.println(complaint);
            }
            System.out.println();
            
            // b. Pobierz i wyświetl jedną z otwartych skarg
            System.out.println("=== JEDNA OTWARTA SKARGA ===");
            // Znajdź pierwszą otwartą skargę
            ComplaintDTO openComplaint = null;
            for (ComplaintDTO complaint : allComplaints) {
                if ("open".equals(complaint.getStatus())) {
                    openComplaint = complaint;
                    break;
                }
            }
            
            if (openComplaint != null) {
                System.out.println("Znaleziona otwarta skarga: " + openComplaint);
                
                // c. Zmodyfikuj skargę - zmień status na zamknięty
                System.out.println("=== MODYFIKACJA SKARGI ===");
                openComplaint.setStatus("closed");
                
                client.target(BASE_URL + "/" + openComplaint.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.entity(openComplaint, MediaType.APPLICATION_JSON));
                
                System.out.println("Skarga została zamknięta. ID: " + openComplaint.getId());
                
                ComplaintDTO updatedComplaint = client.target(BASE_URL + "/" + openComplaint.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get(ComplaintDTO.class);
                System.out.println("Zaktualizowana skarga: " + updatedComplaint);
                System.out.println();
                
            } else {
                System.out.println("Nie znaleziono otwartych skarg.");
                System.out.println();
            }
            
            // d. Pobierz i wyświetl wszystkie otwarte skargi
            System.out.println("=== WSZYSTKIE OTWARTE SKARGI ===");
            List<ComplaintDTO> openComplaints = client.target(BASE_URL)
                    .queryParam("status", "open")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<ComplaintDTO>>() {});
            
            System.out.println("Liczba otwartych skarg: " + openComplaints.size());
            for (ComplaintDTO complaint : openComplaints) {
                System.out.println(complaint);
            }
            
        } catch (Exception e) {
            System.err.println("Błąd podczas wykonywania operacji: " + e.getMessage());
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}