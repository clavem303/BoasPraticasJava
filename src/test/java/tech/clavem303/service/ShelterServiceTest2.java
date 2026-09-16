package tech.clavem303.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.clavem303.client.ClientHttpConfiguration;
import tech.clavem303.domain.Shelter;
import tools.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ShelterServiceTest2 {

    //Simula a configuração do cliente HTTP passando a classe ClientHttpConfiguration como argumento.
    private final ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);

    // Instancia o serviço de abrigos passando o objeto client para o construtor.
    private final ShelterService shelterService = new ShelterService(client);

    // Simula a resposta HTTP passando a classe HttpResponse como argumento.
    @SuppressWarnings("unchecked")
    private final HttpResponse<String> response = mock(HttpResponse.class);

    // Instancia um abrigo para ser usado nos testes.
    Shelter shelter = new Shelter("Scooby",  "19999999999", "scooby@scooby.com");

    // Testa se o serviço de abrigos é chamado quando uma solicitação GET é feita.
    @Test
    public void shouldCallServiceWhenGetRequestIsMade() throws IOException, InterruptedException {

        // Cadastra um id para o abrigo.
        shelter.setId(0L);

        // Especifica o resultado esperado para a lista de abrigos cadastrados.
        String expectedSheltersRegistered = "Abrigos cadastrados:";
        String expectedIdAndName = "0 - Scooby";

        // Redireciona a saída do console (System.out) para um buffer em memória.
        // Isso permite capturar e validar os textos que o listShelters() vai imprimir
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        String json = null; //remover

        when(response.body()).thenReturn(json); //remover
        when(client.triggerGetRequest(anyString())).thenReturn(response);





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