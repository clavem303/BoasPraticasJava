package tech.clavem303.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.clavem303.client.ClientHttpConfiguration;
import tech.clavem303.domain.Shelter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShelterServiceTest {
    private final ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private ShelterService shelterService = new ShelterService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Shelter shelter = new Shelter("Scooby", "19999999999", "scooby@scooby.com");

    @Test
    public void shouldCallServiceWhenGetRequestIsMade () throws IOException, InterruptedException {
        shelter.setId(0L);
        String expectedSheltersRegistered = "Abrigos cadastrados:";
        String expectedIdAndName = "0 - Scooby";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        when(response.body()).thenReturn("[{+shelter.toString()+}]");
        when(client.triggerGetRequest(anyString())).thenReturn(response);

        shelterService.listShelters();

        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator());
        String actualShelterRegistered = lines[0];
        String actualIdAndName = lines[1];

        Assertions.assertEquals(expectedSheltersRegistered, actualShelterRegistered);
        Assertions.assertEquals(expectedIdAndName, actualIdAndName);
    }

}
