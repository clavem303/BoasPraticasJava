package tech.clavem303.service;

import tech.clavem303.client.ClientHttpConfiguration;
import tech.clavem303.domain.Shelter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShelterService {

    private final ClientHttpConfiguration client;

    public ShelterService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void listShelters() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = client.triggerGetRequest(uri);
        String responseBody = response.body();

        Shelter[] shelters = new ObjectMapper().readValue(responseBody, Shelter[].class);
        List<Shelter> shelterList = Arrays.stream(shelters).toList();

        System.out.println("Abrigos cadastrados:");
        for (Shelter shelter : shelterList) {
            long id = shelter.getId();
            String name = shelter.getName();
            System.out.println(id +" - " +name);
        }
    }

    public void registerShelter() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String phoneNumber = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Shelter shelter = new Shelter(name, phoneNumber, email);

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = client.triggerPostRequest(uri, shelter);
        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

}
