package be.pxl.app.userdetails;

public interface AppAuthenticatedPrincipal {

    String getFirstName();
    String getLastName();
    String getFirstAndLastName();
    String getEmail();
}
