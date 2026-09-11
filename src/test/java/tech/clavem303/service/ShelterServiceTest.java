package tech.clavem303.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.clavem303.client.ClientHttpConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShelterServiceTest {

    private final ClientHttpConfiguration client =
            mock(ClientHttpConfiguration.class);

    private final ShelterService shelterService =
            new ShelterService(client);

    @SuppressWarnings("unchecked")
    private final HttpResponse<String> response =
            mock(HttpResponse.class);

    private final PrintStream saidaOriginal = System.out;

    @AfterEach
    public void restaurarSaidaPadrao() {
        System.setOut(saidaOriginal);
    }

    @Test
    public void shouldCallServiceWhenGetRequestIsMade()
            throws IOException, InterruptedException {

        String expectedSheltersRegistered = "Abrigos cadastrados:";
        String expectedIdAndName = "0 - Scooby";

        String json = """
                [
                    {
                        "id": 0,
                        "nome": "Scooby",
                        "telefone": "19999999999",
                        "email": "scooby@scooby.com"
                    }
                ]
                """;

        when(response.body()).thenReturn(json);
        when(client.triggerGetRequest(anyString())).thenReturn(response);

        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        shelterService.listShelters();

        String[] lines = byteArrayOutputStream
                .toString()
                .split(System.lineSeparator());

        String actualShelterRegistered = lines[0];
        String actualIdAndName = lines[1];

        Assertions.assertEquals(
                expectedSheltersRegistered,
                actualShelterRegistered
        );
        Assertions.assertEquals(
                expectedIdAndName,
                actualIdAndName
        );

        verify(client).triggerGetRequest(anyString());
    }
}