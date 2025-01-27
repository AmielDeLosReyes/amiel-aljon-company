package amiel_aljon.auth_server.util.Email;

public interface EmailSender {
    void send(String to, String email);
}